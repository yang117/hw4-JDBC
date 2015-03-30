package hw4;

public class Movie {
	protected String id;
	protected String title;
	protected String posterImage;
	protected Date releaseDate;
	
	public Movie(){}
	public Movie(String id, String title, String posterImage, Date releaseDate){
		super();       
		this.id =id;
                      this.title=title;
		this.posterImage=posterImage;
		this.releaseDate=releaseDate;
		
	}

	public String getId(){
 	   return id;
           }

           public void setId(String id){
		   this.id = id;
	}


            public String getTitle(){
    	             return title;
           }

            public void setTitle(String title){
    	            this.title=title;
           }


             public String getPosterImage(){
           	return posterImage;
            }

             public void setPosterImage(String posterImage){
    	           this.posterImage = posterImage;
            }


             public Date getReleaseDate(){
    	           return releaseDate;
             }
 
             public void setReleaseDate(Date releaseDate){
    	            this.releaseDate = releaseDate;
             }
    
}
