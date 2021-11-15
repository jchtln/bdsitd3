package exercice1;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

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
    }
}
