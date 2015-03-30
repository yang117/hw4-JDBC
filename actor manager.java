package hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActorManager {
	
	Connection connection=null;
	PreparedStatement statement=null;
	ResultSet results=null;
	
	String createActorSql=" INSERT INTO ACTOR(FIRSTNAME,LASTNAME,DATEOFBIRTH,ID) VALUES(?,?,?,?);";
	String readAllActorsSql="SELECT * FROM ACTOR;";
	String readActorSql="SELECT * FROM ACTOR WHERE ID=?;";
	String updateActorSql="UPDATE ACTOR SET FIRSTNAME=?, LASTNAME=?, DATEOFBIRTH=? WHERE ID=? ;";
	String deleteActorSql="DELETE FROM ACTOR WHERE ID=?;";
	
	public void createActor(Actor newActor){
		try{
			connection=ds.getConnection();
			statement=connection.prepareStatement(createActorSql);
			statement.setString(1, newActor.getFirstName());
			statement.setString(2, newActor.getLastName());
			statement.setDate(3, newActor.getDateOfBirth());
			statement.setString(4, newActor.getId());
		}catch (SQLException e) {
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
		
	public List<Actor> readAllActors(){
		List<Actor> actors=new ArrayList<Actor>();
		try {
			connection=ds.getConnection();
			statement=connection.prepareStatement(readAllActorsSql);
			results=statement.executeQuery();
			while(results.next()){
				Actor actor=new Actor(results.getString("id"),results.getString("firstname"),results.getString("lastname"),results.getDate("dateOfBirth"));
				actors.add(actor);
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
		return actors;
	}
	
	public Actor readActor(String actorId){
		try {
			connection=ds.getConnection();
			statement=connection.prepareStatement(readActorSql);
			statement.setString(1, actorId);
			results=statement.executeQuery();
			if(results.next()){
				Actor actor=new Actor(results.getString("id"),results.getString("firstname"),results.getString("lastname"),results.getDate("dateOfBirth"));
				return actor;
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
		return null;//where not return actor?
		
	}
	
	public void updateActor(String actorId, Actor actor){
		try {
			connection=ds.getConnection();
			statement=connection.prepareStatement(updateActorSql);
			statement.setString(1,actor.getFirstName() );
			statement.setString(2,actor.getLastName() );
			statement.setDate(3,actor.getDateOfBirth() );
			statement.setString(4, actorId);
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
	
	public void deleteActor(String actorId){
		try {
			connection=ds.getConnection();
			statement=connection.prepareStatement(deleteActorSql);
			statement.setString(1, actorId);
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
	public ActorManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/hw4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}	


