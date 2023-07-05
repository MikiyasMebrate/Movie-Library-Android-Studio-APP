package co.smallacademy.myvideoslibrary;

import java.io.Serializable;

public class Video implements Serializable {
    private String title;
    private String description;
    private String release_date;
    private String imageUrl;
    private String rate;

    public String getRate(){
        return  rate;
    }

    public void setRate(String rate){
        this.rate="Rating: "+rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date ="Release date: "+  release_date;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
