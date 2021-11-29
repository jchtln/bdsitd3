package exercice2.modele;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Collection;
import java.util.Map;

public class Movies {

    private String _id;
    @BsonProperty(value="country")
    private String country;
    @BsonProperty(value = "genre")
    private String genre;
    @BsonProperty(value = "summary")
    private String summary;

//    private Collection<role> actors;
//    @BsonProperty("director._id")
//    private DirectorID directorID;
    @BsonProperty(value = "title")
    private String title;
    @BsonProperty(value = "year")
    private int year;

 public Movies() {
   }
}
