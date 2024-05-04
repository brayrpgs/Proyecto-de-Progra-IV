package cr.ac.una.booleanKitchen.domain;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {

    
    private Integer id;

    
    private String identifier;

   
    private String title;

    
    private String summary;

    
    private Date date;

    
    private String author;

    
    private Boolean state;
    private String url;
    private String image;

    public Notice() {
    }

    public Notice(Integer id, String identifier, String title, String summary, Date date, String author, Boolean state,
            String url, String image) {
        this.id = id;
        this.identifier = identifier;
        this.title = title;
        this.summary = summary;
        this.date = date;
        this.author = author;
        this.state = state;
        this.url = url;
        this.image = image;
    }
}