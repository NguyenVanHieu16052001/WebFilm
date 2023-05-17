package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CreEpisodeRequestDTO;
import com.xemphim.WebXemPhim.dto.request.CreFilmRequestDTO;
import com.xemphim.WebXemPhim.entity.Episode;
import com.xemphim.WebXemPhim.entity.Film;
import com.xemphim.WebXemPhim.entity.User;
import com.xemphim.WebXemPhim.repository.*;
import com.xemphim.WebXemPhim.service.AdminService;
import com.xemphim.WebXemPhim.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Value("${project.video}")
    private String path;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private NationRepository nationRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private FilmProducerRepository filmProducerRepository;
    @Autowired
    private FileService fileService;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public APIResponse getUsers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userRepository.findAll());
        return apiResponse;
    }

    @Override
    public void creFilm(CreFilmRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (filmRepository.findOneByFilmNameIgnoreCase(requestDTO.getName()).getFilmName() != null) {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setStatus(400);
                apiResponse.setError("Film title is duplicate");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
            String poster = fileService.uploadFile(path, requestDTO.getPoster());
            String trailer = fileService.uploadFile(path, requestDTO.getTrailer());
            Film film = new Film();
            film.setFilmName(requestDTO.getName().strip());
            film.setTrailerPath(trailer);
            film.setFilmPosterPath(poster);
            film.setFilmDescription(requestDTO.getDescription());
            film.setFilmDuration(requestDTO.getDuration());
            if (requestDTO.getEpisodeRequests().size() > 1) {
                film.setOddFilm(false);
            } else if (requestDTO.getEpisodeRequests().size() == 0) {
                film.setOddFilm(requestDTO.isOdd());
            } else {
                film.setOddFilm(true);
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            film.setNation(nationRepository.findOneByNationId(requestDTO.getNation_id()));
            film.setDirector(directorRepository.findOneByDirectorId(requestDTO.getDirector_id()));
            film.setFilmProducer(filmProducerRepository.findOneByFilmProducerId(requestDTO.getProducer_id()));
            film.setReleaseTime(dateFormat.parse(requestDTO.getRelease_time()));
            filmRepository.save(film);
            film = filmRepository.findOneByFilmNameIgnoreCase(film.getFilmName());
            List<String> episodes = new ArrayList<>();
            for (CreEpisodeRequestDTO e : requestDTO.getEpisodeRequests()) {
                String pathEpisode = fileService.uploadFile(path.concat("content/"), e.getContent());
                episodes.add(pathEpisode);
                Episode episode = new Episode();
                episode.setTitle(e.getTitle());
                episode.setFilm(film);
                episode.setEpisodePath(pathEpisode);
                episode.setCreAt(new Date());
                episodeRepository.save(episode);
            }
            System.out.print(poster + "\n" + trailer + "\n" + episodes);

            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setStatus(400);
            apiResponse.setError(e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public void creEpisodes(String filmName, CreEpisodeRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
            String pathEpisode = fileService.uploadFile(path.concat("content/"), requestDTO.getContent());
            Episode episode = new Episode();
            episode.setTitle(requestDTO.getTitle());
            episode.setFilm(film);
            episode.setEpisodePath(pathEpisode);
            episode.setCreAt(new Date());
            episodeRepository.save(episode);

            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setStatus(400);
            apiResponse.setError(e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }


}
