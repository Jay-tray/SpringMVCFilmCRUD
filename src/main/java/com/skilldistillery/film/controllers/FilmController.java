package com.skilldistillery.film.controllers;

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

	
	// displays list of from ID search, then Keyword search as applicable
	@RequestMapping(path = "SearchFilmForm.do", params = "id", method = RequestMethod.GET)
	public ModelAndView findFilmByFilmID(@RequestParam("id") String filmId) {
		ModelAndView mv = new ModelAndView();

		int id = Integer.parseInt(filmId);

		if (id != 0) {
			Film film = filmDAO.findFilmByFilmId(id);
			mv.addObject("film", film);
			mv.setViewName("WEB-INF/views/searchFilmResult.jsp");

		} else

			mv.addObject("noFilm", "No film found, try again");
		mv.setViewName("error.html");
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
	
	//Deletes Film from database and displays deleteFilmResult jsp page for success or failure
 @RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
 public ModelAndView deleteFilm(@ModelAttribute("film") String string) {
	 ModelAndView mv = new ModelAndView();
	 int idInt = Integer.parseInt(string);
	 boolean deleted = false;
	 
	 if (idInt > 1000) {
		 Film film = filmDAO.findFilmByFilmId(idInt);
		 deleted = filmDAO.deleteFilm(film);
		 
	 }
	 mv.addObject("deleted", deleted);
	 mv.setViewName("WEB-INF/views/deleteFilmResult.jsp");
	return mv;
 }
 
 
	/*
	 * @RequestMapping(path = "editFilm.do", method=RequestMethod.POST) public
	 * ModelAndView updateFilm(@ModelAttribute("film") Film film) { ModelAndView mv
	 * = new ModelAndView(); int idInt = Int }
	 */
 
 
 
 
}
