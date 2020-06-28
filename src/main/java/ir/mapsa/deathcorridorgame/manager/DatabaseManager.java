package ir.mapsa.deathcorridorgame.manager;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ir.mapsa.deathcorridorgame.helper.TeamType;
import ir.mapsa.deathcorridorgame.soldier.Soldier;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class DatabaseManager {
    private static DatabaseManager instance;
    private MongoCollection<Document> soldierCollection;

    private DatabaseManager(){
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = client.getDatabase("DeathCorridorGame");
        soldierCollection = database.getCollection("soldier");
    }

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void addSoldiers(List<Document> soldiers){
        soldierCollection.insertMany(soldiers);
    }

    public long getSoldierCount(TeamType teamType){
        return soldierCollection.countDocuments(eq("teamType", teamType.name()));
    }

    public Soldier getFirstSoldier(TeamType teamType){
        return new Soldier(soldierCollection.findOneAndDelete(eq("teamType", teamType.name())));
    }

    public void addSoldier(Soldier soldier){
        soldierCollection.insertOne(soldier.generateDocument());
    }

}
