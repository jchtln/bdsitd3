package exercice1;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;


public class Exercice1 {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(new ConnectionString("mongodb://localhost:27017"));
//  MongoClient mongoClient = MongoClients.create();

        // database.getCollection("démo");
        MongoDatabase database = mongoClient.getDatabase("cine");

        MongoCollection<Document> movies = database.getCollection("movies");
        MongoCollection<Document> artists = database.getCollection("artists");

        // Combien y a-t-il de films et d'artistes dans la bdd ?
        System.out.println("movies :"+ movies.countDocuments());
        System.out.println("artists :"+ artists.countDocuments());

        // Afficher la liste des films
        movies.find().forEach(m-> System.out.println("m ="+ m.toJson()));

        // Donner la liste des titres des films de Science-fiction
        final Consumer<? super Document> printTitre = doc -> System.out.println(doc.getString("title"));
        System.out.println("films de science-fiction :");
        movies.find(Filters.eq("genre","Science-fiction")).forEach(printTitre);

        // Donnez l'ensemble des genres de films
        System.out.println("tous les genres :");
        Set<String> genres= new HashSet<>();
        MongoCursor<Document> all = movies.find().iterator();
        while (all.hasNext()) {
            Document doc = all.next();
            genres.add(doc.getString("genre"));
        }

       // Combien y a-t-il de films et d'artistes dans la base de données ?
        System.out.println("nombre de movies:"+ movies.countDocuments());
        System.out.println("nombre de artistes:"+ artists.countDocuments());

        // Affichez la liste des films
        movies.find().forEach(m-> System.out.println("movies ="+ m.toJson()));

        // Donnez la liste des films de Francis Ford Coppola.
        final Consumer<? super Document> printFilms= doc -> System.out.println(doc.getString("title"));
        System.out.println("films de Francis Ford Coppola:");
        movies.find(Filters.eq("actor","Coppola")).forEach(printFilms);

        //Qui joue Neo dans Matrix ?
        movies.find(

        //Quels sont les rôles joués par Al Pacino ?
        final Consumer<? super Document> printRole = doc -> System.out.println(doc.getString("role"));
        System.out.println("Rôles joués par le monsieur");
        movies.find(Filters.eq("actor","Al Pacino")).forEach(printRole);



       // private static void ajouts(MongoCollection<Document> movies, MongoCollection<Document> artists){
            //Ajoutez le film "Toy Story" dans la collection movies
            Document result = new Document()
                    .append("_id", new ObjectId())
                    .append("title", "Toy story")
                    .append("summary", "Une aventure animée !")
                    .append("genre", Genre.COMEDIE.toString());
            movies.insertOne(result);

            //Ajoutez le film "Vol au dessus d'un nid de coucou" avec Jack Nicholson dans la collection movies
            String idNicholson = artists.find(
                    Filters.and(
                            Filters.eq("last_name", "Nicholson"),
                            Filters.eq("first_name", "Jack")
                    )).first().getString("_id");



            String idCoppola = coppola.getString("_id");
        }
   // }
}
