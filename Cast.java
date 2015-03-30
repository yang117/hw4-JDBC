package hw4;

public class Cast {
	protected String characterName;
	protected String movieId;
	protected String actorId;
	protected String castId;
	
	public Cast(){}

	public Cast(String characterName, String movieId, String actorId,String castId) {
		super();
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
		this.castId=castId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getCastId() {
		return castId;
	}

	public void setCastId(String castId) {
		this.castId = castId;
	}
	


}


