package hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MovieManager {
	
	Connection connection=null;
	PreparedStatement statement=null;
	ResultSet results=null;
	
	String createMovieSql="INSERT INTO MOVIE(TITLE,POSTERIMAGE,RELEASEDATE,ID) VALUES(?,?,?,?);";
	String readMovieSql="SELECT * FROM MOVIE WHERE ID=?;";
	String readAllMoviesSql="SELECT * FROM MOVIE;";
	String updateMovieSql="UPDATE MOVIE SET TITLE=?, POSTERIMAGE=?, RELEASEDATE=? WHERE ID=?;";
	String deleteMovieSql="DELETE FROM MOVIE WHERE ID=?;";
	
	public void createMovie(Movie newMovie){
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(createMovieSql);
			statement.setString(1, newMovie.getTitle());
			statement.setString(2, newMovie.getPosterImage());
			statement.setDate(3, newMovie.getReleaseDate());
			statement.setString(4, newMovie.getId());
            statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(readAllMoviesSql);
			results = statement.executeQuery();
			while(results.next()) {
				Movie movie = new Movie(results.getString("id"), results.getString("title"),results.getString("posterImage"),results.getDate("releaseDate"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return movies;
	}
	
	public Movie readMovie(String movieId){
		try {
			connection=ds.getConnection();
			statement = connection.prepareStatement(readMovieSql);
			statement.setString(1,movieId);
			results = statement.executeQuery();
			if(results.next()){
				Movie movie=new Movie(results.getString("Id"),results.getString("title"),results.getString("posterImage"),results.getDate("releaseDate"));
				return movie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	public void updateMovie(String movieId, Movie movie){
		try {
			connection=ds.getConnection();
			statement=connection.prepareStatement(updateMovieSql);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			statement.setString(4, movieId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void deleteMovie(String movieId){
		try {
			connection = ds.getConnection();
			statement=connection.prepareStatement(deleteMovieSql);
			statement.setString(1, movieId);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	DataSource ds;
	public MovieManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/hw4”);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
