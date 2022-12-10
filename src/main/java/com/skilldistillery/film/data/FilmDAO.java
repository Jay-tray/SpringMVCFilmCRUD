package com.skilldistillery.film.data;

import java.util.List;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
		public Film findFilmByFilmId(int filmId);

		public Actor findActorById(int actorId);

		public List<Actor> findActorsByFilmId(int filmId);

		public List<Film> findFilmByActorId(int actorId);

		public List<Film> findFilmByKeywords(String keyword);

		public String getLanguageOfFilm(int filmId);

		public Actor createActor(Actor actor);

		public boolean saveActor(Actor actor);

		public boolean deleteActor(Actor actor);

		public Film createFilm(Film film);

		public boolean deleteFilm(Film film);
	}

