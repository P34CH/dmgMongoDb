import Uni.Professoren;
import Uni.Vorlesungen;
import com.mongodb.BasicDBObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by peach on 12.05.16
 */
public class Main {

    private static SqlConnector sqlConnector;
    private static MongoDbConnector mongoDbConnector;

    public static void main(String[] args) {
        sqlConnector = new SqlConnector("Uni");
        mongoDbConnector = new MongoDbConnector("uni", "profs");
        migrate();
    }

    private static void migrate() {
        EntityManager em = sqlConnector.getEm();

        Query namedQuery = em.createNamedQuery("Professoren.findAll");
        Collection<Professoren> professoren = (Collection<Professoren>)namedQuery.getResultList();

        for (Professoren p : professoren) {
            BasicDBObject doc = new BasicDBObject("id", p.getPersNr())
                    .append("name", p.getName())
                    .append("rang", p.getRang())
                    .append("raum", p.getRaum());
            mongoDbConnector.getCollection().insert(doc);
            System.out.println("Insertet " + p.getName());
        }
    }

}
