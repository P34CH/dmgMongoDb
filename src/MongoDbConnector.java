import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by peach on 12.05.16
 */
public class MongoDbConnector {

    private DB db;
    private DBCollection collection;

    public MongoDbConnector(String dbName, String collectionName) {
        this(dbName);
        setCollectionName(collectionName);
    }

    public MongoDbConnector(String dbName) {
        MongoClient mongoClient = null;

        try {
            mongoClient = new MongoClient();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        if (mongoClient != null) {
            db = mongoClient.getDB(dbName);
        } else {
            System.out.println("MongoClient not available!");
        }
    }

    public void setCollectionName(String collectionName) {
        collection = db.getCollection(collectionName);
    }

    public DBCollection getCollection() {
        return collection;
    }
}
