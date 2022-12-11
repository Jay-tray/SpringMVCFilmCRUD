package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDAO;

	@RequestMapping("home.do")
	public String home() {
		return "WEB-INF/views/home.jsp";
	}

	// displays Film using ID search
	@RequestMapping(path = "SearchByID.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmByFilmID(String id) {
		ModelAndView mv = new ModelAndView();
		int idInt = Integer.parseInt(id);
		Film film = null;
		if (idInt != 0) {
			film = filmDAO.findFilmByFilmId(idInt);
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/views/searchFilmResult.jsp");

		}
		return mv;
	}

	// displays list of Films based off Keyword search
	@RequestMapping(path = "SearchByKeyword.do", params = "keyword", method = RequestMethod.GET)
	public ModelAndView findFilmByKeywords(@RequestParam("keyword") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = null;
		System.out.println(keyword);
		if (!keyword.isEmpty()) {
		films = filmDAO.findFilmByKeywords(keyword);
		mv.addObject("film", films);
		mv.setViewName("WEB-INF/views/listOfFilmsResult.jsp");
		}
		return mv;
	}

	// DIsplays that film was or was not successfully added
	@RequestMapping(path = "AddFilmForm.do", method = RequestMethod.GET)
	public ModelAndView getCreateFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
		Film f = filmDAO.createFilm(film);
		mv.addObject("film", f);
		mv.setViewName("WEB-INF/views/addNewFilmResult.jsp");
		return mv;
	}

	// Deletes Film from database and displays deleteFilmResult jsp page for success
	// or failure
	@RequestMapping(path = "DeleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm( int filmID) {
		ModelAndView mv = new ModelAndView();
		int idInt = filmID;
		boolean deleted = false;

		if (idInt > 1000) {
			Film film = filmDAO.findFilmByFilmId(idInt);
			deleted = filmDAO.deleteFilm(film);

		}
		mv.addObject("deleted", deleted);
		mv.setViewName("WEB-INF/views/deleteFilm.jsp");
		return mv;
	}

	/*
	 * @RequestMapping(path = "editFilm.do", method=RequestMethod.POST) public
	 * ModelAndView updateFilm(@ModelAttribute("film") Film film) { ModelAndView mv
	 * = new ModelAndView(); int idInt = Int }
	 */

}
