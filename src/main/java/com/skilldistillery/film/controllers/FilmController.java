package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(path = "SearchFilmForm.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmByFilmID(@RequestParam("id") String filmId) {
		ModelAndView mv = new ModelAndView();

		int id = Integer.parseInt(filmId);

		if (id != 0) {
			Film film = filmDAO.findFilmByFilmId(id);
			mv.addObject("film", film);
			mv.setViewName("searchFilmResult.jsp");

		} else

			mv.addObject("noFilm", "No film found, try again");
		mv.setViewName("error.html");
		return mv;
	}

	@RequestMapping(path = "AddFilmForm.do", method = RequestMethod.GET)
	public ModelAndView getCreateFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
		Film f = filmDAO.createFilm(film);
		mv.addObject("film", f);
		mv.setViewName("/MVCFilmSite/src/main/webapp/WEB-INF/views/addNewFilmResult.jsp");
		return mv;
	}

}
