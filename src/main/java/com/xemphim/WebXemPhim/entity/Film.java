package com.xemphim.WebXemPhim.entity;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;
	
	@Column(name = "film_name")
	private String filmName;
	
	@Column(name = "film_poster_path")
	private String filmPosterPath;
	
	@Column(name = "trailer_path")
	private String trailerPath;
	
	@Column(name = "film_description")
	private String filmDescription;
	
	@Column(name = "film_duration")
	private Integer filmDuration;
	
	@Column(name = "release_time")
	private Date releaseTime;
	
	@Column(name = "odd_film")
	private Integer oddFilm;
	@Column(name = "average_rating")
	private Float rating;

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	@ManyToOne
	@JoinColumn(name = "film_producer_id")
	private FilmProducer filmProducer;

	@ManyToOne
	@JoinColumn(name = "nation_id")
	private Nation nation;

	@ManyToOne
	@JoinColumn(name = "director_id")
	private Director director;
	
	@OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
	private Collection<Episode> episodes;

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFilmPosterPath() {
		return filmPosterPath;
	}

	public void setFilmPosterPath(String filmPosterPath) {
		this.filmPosterPath = filmPosterPath;
	}

	public String getTrailerPath() {
		return trailerPath;
	}

	public void setTrailerPath(String trailerPath) {
		this.trailerPath = trailerPath;
	}

	public String getFilmDescription() {
		return filmDescription;
	}

	public void setFilmDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}

	public Integer getFilmDuration() {
		return filmDuration;
	}

	public void setFilmDuration(Integer filmDuration) {
		this.filmDuration = filmDuration;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Integer getOddFilm() {
		return oddFilm;
	}

	public void setOddFilm(Integer oddFilm) {
		this.oddFilm = oddFilm;
	}

	public FilmProducer getFilmProducer() {
		return filmProducer;
	}

	public void setFilmProducer(FilmProducer filmProducer) {
		this.filmProducer = filmProducer;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Collection<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Collection<Episode> episodes) {
		this.episodes = episodes;
	}
}