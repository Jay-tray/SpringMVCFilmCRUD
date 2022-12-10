package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(path = "AddFilmForm.do", params = "addFilm", method = RequestMethod.GET)
	public ModelAndView getCreateFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		Film f = filmDAO.createFilm(film);
		mv.addObject("film", f);
		mv.setViewName("addNewFilmForm.jsp");
		return mv;
	}

}
