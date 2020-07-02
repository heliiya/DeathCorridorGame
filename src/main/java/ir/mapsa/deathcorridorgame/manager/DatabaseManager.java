package ir.mapsa.deathcorridorgame.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ir.mapsa.deathcorridorgame.helper.TeamType;
import ir.mapsa.deathcorridorgame.soldier.Soldier;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseManager {
    private static DatabaseManager instance;
    private MongoCollection<Document> collection;

    private DatabaseManager(){
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("DeathCorridorGame");
        collection = database.getCollection("soldier");
    }

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void addSoldiers(List<Document> soldiers){
        collection.insertMany(soldiers);
    }

    public long getSoldierCount(TeamType teamType){
        return collection.countDocuments(eq("teamType", teamType.name()));
    }

    public Soldier findAndDeleteFirstSoldier(TeamType teamType){
        return new Soldier(collection.findOneAndDelete(eq("teamType", teamType.name())));
    }

    public void addSoldier(Soldier soldier){
        collection.insertOne(soldier.generateDocument());
    }

}
