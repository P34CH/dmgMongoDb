import Uni.Hören;
import Uni.Professoren;
import Uni.Studenten;
import Uni.Vorlesungen;
import com.mongodb.BasicDBList;
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
        Collection<Professoren> professoren = (Collection<Professoren>) namedQuery.getResultList();

        for (Professoren p : professoren) {
            BasicDBObject professor = new BasicDBObject("_id", p.getPersNr())
                    .append("name", p.getName())
                    .append("rang", p.getRang())
                    .append("raum", p.getRaum());


            BasicDBList vorlesungen = new BasicDBList();
            for (Vorlesungen v : p.getVorlesungensByPersNr()) {
                BasicDBObject vorlesung = new BasicDBObject("_id", v.getVorlNr())
                        .append("titel", v.getTitel())
                        .append("sws", v.getSws());

                BasicDBList studenten = new BasicDBList();
                for (Hören h : v.getHörensByVorlNr()) {
                    Studenten s = h.getStudentenByMatrNr();
                    BasicDBObject student = new BasicDBObject("_id", s.getMatrNr())
                            .append("name", s.getName())
                            .append("semester", s.getSemester());
                    studenten.add(student);
                }
                vorlesung.append("studenten", studenten);
                vorlesungen.add(vorlesung);
            }

            professor.append("vorlesungen", vorlesungen);

            mongoDbConnector.getCollection().insert(professor);
            System.out.println("Insertet " + p.getName());
        }
    }

}
