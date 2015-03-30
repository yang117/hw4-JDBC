
package hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class CommentManager {
	
	Connection connection=null;
	PreparedStatement pstatement=null;
	ResultSet results=null;
	
	String createCommentSql="INSERT INTO COMMENT(COMMENT,DATE,COMMENTID,USERNAME,MOVIEID) VALUES (?,?,?,?,?);";
	String readAllCommentsSql="SELECT * FROM COMMENT;";
	String readAllCommentsForUsernameSql="SELECT * FROM COMMENT WHERE USERNAME=?;";
	String readAllCommentsForMovieSql="SELECT * FROM COMMENT WHERE MOVIEID=?;";
	String readCommentForIdSql="SELECT * FROM COMMENT WHERE COMMENTID=?;";
	String updateCommentSql="UPDATE COMMENT SET COMMENT=?,DATE=?,USERNAME=?,MOVIEID=? WHERE COMMENTID=?;";
	String deleteCommentSql="DELETE FROM COMMENT WHERE COMMENTID=?;";
	
	public void createComment(Comment newComment){
		
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(createCommentSql);
			pstatement.setString(1, newComment.getComment());
			pstatement.setDate(2, newComment.getDate());
			pstatement.setString(3, newComment.getCommentId());
			pstatement.setString(4, newComment.getUsername());
			pstatement.setString(5, newComment.getMovieId());
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
	
	public List<Comment> readAllComments(){
		List<Comment> comments=new ArrayList<Comment>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCommentsSql);
			pstatement.executeQuery();
			while(results.next()){
				Comment comment=new Comment(results.getString("comment"),results.getDate("date"),results.getString("commentId"),results.getString("username"),results.getString("movieId"));
				comments.add(comment);
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
		
		
		return comments;
		
	}
	
	public List<Comment> readAllCommentsForUsername(String username){
		List<Comment> comments=new ArrayList<Comment>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCommentsForUsernameSql);
			pstatement.setString(1, username);
			pstatement.executeQuery();
			while(results.next()){
				Comment comment=new Comment(results.getString("comment"),results.getDate("date"),results.getString("commentId"),results.getString("username"),results.getString("movieId"));
				comments.add(comment);
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
		
		
		return comments;
		
	}
	
	public List<Comment> readAllCommentsForMovie(String movieId){
		List<Comment> comments=new ArrayList<Comment>();
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readAllCommentsForMovieSql);
			pstatement.setString(1, movieId);
			pstatement.executeQuery();
			while(results.next()){
				Comment comment=new Comment(results.getString("comment"),results.getDate("date"),results.getString("commentId"),results.getString("username"),results.getString("movieId"));
				comments.add(comment);
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
		
		
		return comments;
		
	}
	
	public Comment readCommentForId(String commentId){
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(readCommentForIdSql);
			pstatement.setString(1, commentId);
			results=pstatement.executeQuery();
			if(results.next()){
				Comment comment=new Comment(results.getString("comment"),results.getDate("date"),results.getString("commentId"),results.getString("username"),results.getString("movieId"));
				return comment;
				
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
	
	public void updateComment(String commentId, Comment newComment){
		try {
			connection=ds.getConnection();
			pstatement=connection.prepareStatement(updateCommentSql);
			pstatement.setString(1, newComment.getComment());
			pstatement.setDate(2, newComment.getDate());
			pstatement.setString(3, newComment.getUsername());
			pstatement.setString(4, newComment.getMovieId());
			pstatement.setString(5, commentId);
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
	
	public void deleteComment(String commentId) {
		try {
			connection = ds.getConnection();
			pstatement = connection.prepareStatement(deleteCommentSql);
			pstatement.setString(1, commentId);
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
	public CommentManager() {
		try {
			Context jndi = new InitialContext();
			ds = (DataSource) jndi.lookup("java:comp/env/jdbc/hw4”);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


