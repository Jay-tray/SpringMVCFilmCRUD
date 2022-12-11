package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOImpl implements FilmDAO {
		private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
		private static final String user = "student";
		private static final String pass = "student";

		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Unable to load database driver:");
			}
		}

		@Override
		public Film findFilmByFilmId(int filmId) {
			if (filmId <= 0) {
				return null;
			}
			Film film = null;
			//String sql = "SELECT * FROM film WHERE film.id = ?";
			String sql ="SELECT * FROM film JOIN film_category on film.id=film_category.film_id JOIN category on film_category.category_id = category.id WHERE film.id = ?";
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, filmId);
				ResultSet filmResult = stmt.executeQuery();

				if (filmResult.next()) {
					film = new Film();

					film.setId(filmResult.getInt("id"));
					film.setTitle(filmResult.getString("title"));
					film.setDescription(filmResult.getString("description"));
					film.setRelYear(filmResult.getInt("release_year"));
					film.setLanId(filmResult.getInt("language_id"));
					film.setRenDur(filmResult.getDouble("rental_duration"));
					film.setRenRat(filmResult.getDouble("rental_rate"));
					film.setLength(filmResult.getInt("length"));
					film.setRepCost(filmResult.getDouble("replacement_cost"));
					film.setRating(filmResult.getString("rating"));
					film.setSpecFeat(filmResult.getString("special_features"));
					film.setActors(findActorsByFilmId(filmId));
					film.setLanguage(getLanguageOfFilm(filmId));
					film.setCategory(filmResult.getString("category.name"));
				}

				filmResult.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return film;
		}

		@Override
		public Actor findActorById(int actorId) {
			if (actorId <= 0) {
				return null;
			}
			Actor actor = null;
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actorId);
				ResultSet actorResult = stmt.executeQuery();

				if (actorResult.next()) {
					actor = new Actor(); // Create the object
					actor.setId(actorResult.getInt("id"));
					actor.setFirstName(actorResult.getString("first_name"));
					actor.setLastName(actorResult.getString("last_name"));

				}
				actorResult.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return actor;
		}

		@Override
		public List<Actor> findActorsByFilmId(int filmId) {
			if (filmId <= 0) {
				return null;
			}
			List<Actor> listActor = new ArrayList<>();
			String sql = "SELECT actor.id,actor.first_name,actor.last_name,film.id FROM film JOIN film_actor ON film.id=film_actor.film_id JOIN actor ON film_actor.actor_id = actor.id WHERE film.id=?";
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, filmId);
				ResultSet idResult = stmt.executeQuery();

				while (idResult.next()) {
					int id = idResult.getInt("id");
					String firstName = idResult.getString("first_name");
					String lastName = idResult.getString("last_name");

					Actor actor = new Actor(id, firstName, lastName);
					listActor.add(actor);
				}

				idResult.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listActor;
		}

		@Override
		public List<Film> findFilmByActorId(int actorId) {
			if (actorId <= 0) {
				return null;
			}
			List<Film> listOfFilms = new ArrayList<>();
			String sql = "SELECT film.title, film.id FROM actor JOIN film_actor ON actor.id=film_actor.actor_id JOIN film ON film_actor.film_id=film.id WHERE actor.id= ?";
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actorId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					String title = rs.getString("film.title");
					Film film = new Film(title);
					listOfFilms.add(film);
				}

				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listOfFilms;
		}

		@Override
		public List<Film> findFilmByKeywords(String keyword) {

			List<Film> listOfFilms = new ArrayList<>();
			String sql = "SELECT * FROM film JOIN film_category on film.id=film_category.film_id JOIN category on film_category.category_id = category.id  WHERE film.title LIKE ? OR film.description LIKE ? ";
			Film film = null;
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%" + keyword + "%");
				stmt.setString(2, "%" + keyword + "%");
				ResultSet filmResult = stmt.executeQuery();

				while (filmResult.next()) {
					film = new Film();
					film.setId(filmResult.getInt("id"));
					film.setTitle(filmResult.getString("title"));
					film.setDescription(filmResult.getString("description"));
					film.setRelYear(filmResult.getInt("release_year"));
					film.setLanId(filmResult.getInt("language_id"));
					film.setRenDur(filmResult.getDouble("rental_duration"));
					film.setRenRat(filmResult.getDouble("rental_rate"));
					film.setLength(filmResult.getInt("length"));
					film.setRepCost(filmResult.getDouble("replacement_cost"));
					film.setRating(filmResult.getString("rating"));
					film.setSpecFeat(filmResult.getString("special_features"));
					film.setActors(findActorsByFilmId(film.getId()));
					film.setLanguage(getLanguageOfFilm(film.getId()));
					film.setCategory(filmResult.getString("category.name"));
					
					listOfFilms.add(film);
				}

				filmResult.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listOfFilms;
		}

		public String getLanguageOfFilm(int filmId) {
			if (filmId <= 0) {
				return null;
			}
			String sql = "SELECT language.name FROM film JOIN language ON film.language_id=language.id WHERE film.id = ?";
			String language = "";
			try {
				Connection conn = DriverManager.getConnection(URL, user, pass);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, filmId);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					language = rs.getString("language.name");
				}

				rs.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return language;
		}

		/*
		 * public String getCategory(int categoryId) { if (categoryId <= 0) { return
		 * null; } String sql =
		 * "SELECT category.name FROM film_category JOIN film ON film_category.film_id=film.id WHERE film.id = ?"
		 * ; //FIGURE THIS OUT String category = ""; try { Connection conn =
		 * DriverManager.getConnection(URL, user, pass); PreparedStatement stmt =
		 * conn.prepareStatement(sql); stmt.setInt(1, categoryId); ResultSet rs =
		 * stmt.executeQuery();
		 * 
		 * if (rs.next()) { category = rs.getString("category.name"); }
		 * 
		 * rs.close(); stmt.close(); conn.close();
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } return category;
		 */
		//}
		@Override
		public Actor createActor(Actor actor) {
			Actor newActor = actor;
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
				String sql = "INSERT INTO actor (first_name, last_name) VALUES (?,?)";
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, newActor.getFirstName());
				stmt.setString(2, newActor.getLastName());

				int updateCount = stmt.executeUpdate();

				if (updateCount == 1) {
					ResultSet keys = stmt.getGeneratedKeys();

					if (keys.next()) {
						int newActorId = keys.getInt(1);
						newActor.setId(newActorId);
					}
					keys.close();

				}
				conn.commit(); // COMMIT TRANSACTION
				stmt.close();
				conn.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				throw new RuntimeException("Error inserting actor  " + actor.getFirstName() + " " + actor.getLastName());
			}
			return newActor;
		}

		@Override
		public boolean saveActor(Actor actor) {
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
				String sql = "UPDATE actor SET first_name=?, last_name=? WHERE id=?";
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1, actor.getFirstName());
				stmt.setString(2, actor.getLastName());
				stmt.setInt(3, actor.getId());

				int updateCount = stmt.executeUpdate();

				if (updateCount == 1) {
					// Replace actor's film list
					sql = "DELETE FROM film_actor WHERE actor_id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, actor.getId());
					updateCount = stmt.executeUpdate();
					sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
					stmt = conn.prepareStatement(sql);

					for (Film film : actor.getFilms()) {
						stmt.setInt(1, film.getId());
						stmt.setInt(2, actor.getId());
						updateCount = stmt.executeUpdate();
					}

					conn.commit(); // COMMIT TRANSACTION
					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} // ROLLBACK TRANSACTION ON ERROR
					catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}

		@Override
		public boolean deleteActor(Actor actor) {
			Connection conn = null;
			

			try {
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
				String sql = "DELETE FROM film_actor WHERE actor_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());

				int updateCount = stmt.executeUpdate();
				if (updateCount == 1) {
					System.out.println("You deleted " + updateCount + "actor.");
				}

				conn.commit(); // COMMIT TRANSACTION
				stmt.close();
				conn.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}

		@Override
		public Film createFilm(Film film) {
			Film newFilm = film;
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
				String sql = "INSERT INTO film (title, description, release_year, language_id , rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUES (?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, newFilm.getTitle());
				stmt.setString(2, newFilm.getDescription());
				stmt.setInt(3, newFilm.getRelYear());
				stmt.setInt(4, newFilm.getLanId());
				stmt.setDouble(5, newFilm.getRenDur());
				stmt.setDouble(6, newFilm.getRenRat());
				stmt.setInt(7,  newFilm.getLength());
				stmt.setDouble(8, newFilm.getRepCost());
				stmt.setString(9, newFilm.getRating());
				stmt.setString(10, newFilm.getSpecFeat());

				int updateCount = stmt.executeUpdate();

				if (updateCount == 1) {
					ResultSet keys = stmt.getGeneratedKeys();

					if (keys.next()) {
						int newFilmId = keys.getInt(1);
						film.setId(newFilmId);
					}
				} else {
					film = null;
				}
				conn.commit(); // COMMIT TRANSACTION
				conn.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				throw new RuntimeException("Error inserting film " + film);
			}
			return film;
		}
		@Override
		public boolean updateFilm(Film film) {
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(URL, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
				String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? where id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, film.getTitle());
				stmt.setString(2, film.getDescription());
				stmt.setInt(3, film.getRelYear());
				stmt.setInt(4, film.getLanId());
				stmt.setDouble(5, film.getRenDur());
				stmt.setDouble(6, film.getRenRat());
				stmt.setInt(7,  film.getLength());
				stmt.setDouble(8, film.getRepCost());
				stmt.setString(9, film.getRating());
				stmt.setString(10, film.getSpecFeat());
				
				int updateCount = stmt.executeUpdate();

				if (updateCount == 1) {
					// Replace actor's film list
					sql = "DELETE FROM film_actor WHERE actor_id = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, film.getId());
					updateCount = stmt.executeUpdate();
					sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
					stmt = conn.prepareStatement(sql);

					if (film.getActors() != null ) {
					for (Actor actor : film.getActors()) {
						stmt.setInt(1, film.getId());
						stmt.setInt(2, actor.getId());
						updateCount = stmt.executeUpdate();
					}
					}
					conn.commit(); // COMMIT TRANSACTION
					conn.close();
				}

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} // ROLLBACK TRANSACTION ON ERROR
					catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}
		@Override
		public boolean deleteFilm(Film film) {
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(URL, user, pass);
				String sql = "DELETE FROM film WERE id = ?";
				conn.setAutoCommit(false);
	
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());
				stmt.executeUpdate();
		
				conn.commit(); // COMMIT TRANSACTION
				conn.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException sqle2) {
						System.err.println("Error trying to rollback");
					}
				}
				return false;
			}
			return true;
		}
	}
