package com.xemphim.WebXemPhim.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "episodes")
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "episode_id")
	private Integer episodeId;

	@Column(name = "episode_path")
	private String episodePath;

	@Column(name = "title")
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id")
	private Film film;

	@Column(name = "cre_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "Asia/Ho_Chi_Minh")
	private Date creAt;

	public Date getCreAt() {
		return creAt;
	}

	public void setCreAt(Date creAt) {
		this.creAt = creAt;
	}

	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public String  getEpisodePath() {
		return episodePath;
	}

	public void setEpisodePath(String episodePath) {
		this.episodePath = episodePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
}