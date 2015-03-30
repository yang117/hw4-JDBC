package hw4;

public class Comment {
	protected String comment;
	protected Date date;
	protected String commentId;
	protected String username;
	protected String movieId;
	
	public Comment(){}
	
	public Comment(String comment, Date date, String commentId,
			String username, String movieId) {
		super();
		this.comment = comment;
		this.date = date;
		this.commentId = commentId;
		this.username = username;
		this.movieId = movieId;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getCommentId() {
		return commentId;
	}



	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getMovieId() {
		return movieId;
	}



	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}



	
}

