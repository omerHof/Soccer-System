package DBLayer;

/**
 *
 */

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.net.UnknownHostException;
import java.util.function.Function;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Shamik Mitra
 *
 */
public class MongoContext {
    private static MongoContext ctx = new MongoContext();
    private  MongoClient client;
    private  MongoClient client2;
    private  DB db;
    private MongoDatabase database;

    public DB getDb() {
        return db;
    }

    public MongoDatabase getDatabase() {
        return database;
    }


    private MongoContext(){
        try{
            init();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void init() throws UnknownHostException{
        this.client = new MongoClient("localhost" , 27017);
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.client2 = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    }
    public static MongoContext get(){
        return ctx;
    }
    public MongoContext connectDb(String dbname){
        if(db !=null){
            throw new RuntimeException("Already conected to " + db.getName() + "can't connect " + dbname);
        }
        this.db = client.getDB(dbname);
        if(database !=null){
            throw new RuntimeException("Already conected to " + database.getName() + "can't connect " + dbname);
        }
        this.database = client2.getDatabase(dbname);
        System.out.println("DB Details :: " + db.getName());
        System.out.println("DB Details :: " + database.getName());

        return ctx;
    }
    public <T,X> DBCursor findByKey(String collectionName,String key,T value,Function<T,X> convertDataType){
        DBCollection collection = db.getCollection(collectionName);
        //BasicDBObject searchQuery = new BasicDBObject();
        //searchQuery.put(key, convertDataType.apply(value));
        //System.out.println("search Query ::" + searchQuery);
        //DBCursor cursor = collection.find(searchQuery);
        DBCursor cursor = collection.find();
        return cursor;
    }
    public <T,X> DBCursor findByKey2(String collectionName){
        DBCollection collection = db.getCollection(collectionName);
        DBCursor cursor = collection.find();
        return cursor;
    }
    public MongoCursor findByKey3(String collectionName){
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor cursor = collection.find().iterator();
        return cursor;
    }
}
