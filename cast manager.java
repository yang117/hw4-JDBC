package hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class CastManager {
	
	Connection connection=null;
	PreparedStatement pstatement=null;
	ResultSet results=null;
	
	String createCastSql="INSERT INTO CAST(CHARACTERNAME,ACTORID,MOVIEID,CASTID) VALUES (?,?,?,?);";
	String readAllCastsSql="SELECT * FROM CAST;";
	String readAllCastForActorSql="SELECT * FROM CAST WHERE ACTORID=?;";
	String readAllCastForMovieSql="SELECT * FROM CAST WHERE MOVIEID=?;";
	String readCastForIdSql="SELECT * FROM CAST WHERE CASTID=?;";
	String updateCastSql="UPDATE CAST SET CHARACTERNAME=?,ACTORID=?,MOVIEID=? WHERE CASTID=?;";
	String deleteCastSql="DELETE FROM CAST WHERE CASTID=?;";
	
	public void createCast(Cast newCast){
		
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(createCastSql);
			
			pstatement.setString(1, newCast.getCharacterName());
			pstatement.setString(2, newCast.getActorId());
			pstatement.setString(3, newCast.getMovieId());
			pstatement.setString(4, newCast.getCastId());
			pstatement.executeUpdate();
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
	
	public List<Cast> readAllCasts(){
		List<Cast> casts=new ArrayList<Cast>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCastsSql);
			pstatement.executeQuery();
			while(results.next()){
				Cast cast=new Cast(results.getString("characterName"),results.getString("movieId"),results.getString("actorId"),results.getString("castId"));
				casts.add(cast);
			}
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
		
		
		return casts;
		
	}
	
	public List<Cast> readAllCastForActor(String actorId){
		List<Cast> casts=new ArrayList<Cast>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCastForActorSql);
			pstatement.setString(1, actorId);
			pstatement.executeQuery();
			while(results.next()){
				Cast cast=new Cast(results.getString("characterName"),results.getString("movieId"),results.getString("actorId"),results.getString("castId"));
				casts.add(cast);
			}
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
		
		
		return casts;
		
	}
	
	public List<Cast> readAllCastForMovie(String movieId){
		List<Cast> casts=new ArrayList<Cast>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCastForMovieSql);
			pstatement.setString(1, movieId);
			pstatement.executeQuery();
			while(results.next()){
				Cast cast=new Cast(results.getString("characterName"),results.getString("movieId"),results.getString("actorId"),results.getString("castId"));
				casts.add(cast);
			}
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
		
		
		return casts;
		
	}
	
	public Cast readCastForId(String actorId){
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readCastForIdSql);
			pstatement.setString(1, actorId);
			results=pstatement.executeQuery();
			if(results.next()){
				Cast cast=new Cast(results.getString("characterName"),results.getString("movieId"),results.getString("actorId"),results.getString("castId"));
				return cast;
				
			}
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
		
		return null;
		
	}
	
	public void updateCast(String castId, Cast newCast){
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(updateCastSql);
			pstatement.setString(1, newCast.getCharacterName());
			pstatement.setString(2, newCast.getMovieId());
			pstatement.setString(3, newCast.getActorId());
			pstatement.setString(4, castId);
			pstatement.executeUpdate();

			
			
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
	
	public void deleteCast(String castId) {
		try {
			connection = ds.getConnection();
			pstatement = connection.prepareStatement(deleteCastSql);
			pstatement.setString(1, castId);
			pstatement.executeUpdate();
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
	}

	
	
	
	DataSource ds;
	public CastManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/hw4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

