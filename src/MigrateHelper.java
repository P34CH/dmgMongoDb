import Uni.*;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;

import static javafx.scene.input.KeyCode.T;


/**
 * Created by Arakis on 19/05/2016.
 */
public class MigrateHelper {

    MigrateHelper(){};

    public void migrateVersionOne() {
        System.out.println("----------------------------------------------");
        System.out.println("-- Migrate Version 1 -------------------------");
        System.out.println("----------------------------------------------");
        SqlConnector sqlCon =  new SqlConnector("Uni");
        System.out.println("----------------------------------------------");
        System.out.println("mySQL:\t\tConnected to Schema: \"Uni\"");

        //Generate Schema "uni" & collection "professoren"
        MongoDbConnector mongoCon = new MongoDbConnector("uni", "profs");
        System.out.println("MongoDB:\tConnected to Schema: \"uni\", Collection: \"profs\"");

        //Drop current collection "professoren"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"profs\": dropped");

        //Entity magic
        EntityManager em = sqlCon.getEm();

        Query namedQuery = em.createNamedQuery("Professoren.findAll");
        Collection<Professoren> professoren = (Collection<Professoren>) namedQuery.getResultList();

        System.out.println("\t\t\t----------------------------------");
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

            mongoCon.getCollection().insert(professor);
            System.out.println("MongoDB:\tInserted: \""+ p.getName()+ "\"");
        }

        System.out.println("----------------------------------------------");
    }

    public void migrateVersionTwo() {
        System.out.println("----------------------------------------------");
        System.out.println("-- Migrate Version 2 -------------------------");
        System.out.println("----------------------------------------------");
        SqlConnector sqlCon =  new SqlConnector("Uni");
        System.out.println("----------------------------------------------");
        System.out.println("mySQL:\t\tConnected to Schema: \"Uni\"");

        //--------------------------------------------------------------------------------------------------------------
        // Professoren
        //--------------------------------------------------------------------------------------------------------------
        //Generate Schema "uni" & collection "professoren"
        MongoDbConnector mongoCon = new MongoDbConnector("uni", "professoren");
        System.out.println("MongoDB:\tConnected to Schema: \"uni\", Collection: \"professoren\"");

        //Drop current collection "professoren"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"professoren\": dropped");

        //Entity magic
        EntityManager em = sqlCon.getEm();

        Query namedQuery = em.createNamedQuery("Professoren.findAll");
        Collection<Professoren> professoren = namedQuery.getResultList();

        for (Professoren prof : professoren) {
            BasicDBObject basicOpjProfessor = new BasicDBObject("_id", prof.getPersNr())
                    .append("name", prof.getName())
                    .append("rang", prof.getRang())
                    .append("raum", prof.getRaum());

            mongoCon.getCollection().insert(basicOpjProfessor);
            System.out.println("MongoDB:\tInserted Professor: \""+ prof.getName()+ "\"");
        }
        //--------------------------------------------------------------------------------------------------------------
        // Assistenten
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "assistenten"
        mongoCon.setCollectionName("assistenten");
        System.out.println("MongoDB:\tCurrent collection: \"assistenten\"");

        //Drop current collection "assistenten"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"assistenten\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Assistenten.findAll");
        Collection<Assistenten> assistenten = namedQuery.getResultList();

        for (Assistenten assi : assistenten) {
            BasicDBObject basicOpjAssistent = new BasicDBObject("_id", assi.getPersNr())
                    .append("name", assi.getName())
                    .append("fachgebiet", assi.getFachgebiet())
                    .append("boss", assi.getBoss());

            mongoCon.getCollection().insert(basicOpjAssistent);
            System.out.println("MongoDB:\tInserted Assistent: \""+ assi.getName()+ "\"");
        }

        //--------------------------------------------------------------------------------------------------------------
        // Studenten
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "studenten"
        mongoCon.setCollectionName("studenten");
        System.out.println("MongoDB:\tCurrent collection: \"studenten\"");

        //Drop current collection "studenten"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"studenten\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Studenten.findAll");
        Collection<Studenten> studenten = namedQuery.getResultList();

        for (Studenten stud : studenten) {
            BasicDBObject basicOpjStudent = new BasicDBObject("_id", stud.getMatrNr())
                    .append("name", stud.getName())
                    .append("semester", stud.getSemester());

            mongoCon.getCollection().insert(basicOpjStudent);
            System.out.println("MongoDB:\tInserted Student: \""+ stud.getName()+ "\"");
        }

        //--------------------------------------------------------------------------------------------------------------
        // Hören
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "hören"
        mongoCon.setCollectionName("hören");
        System.out.println("MongoDB:\tCurrent collection: \"hören\"");

        //Drop current collection "hören"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"hören\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Hören.findAll");
        Collection<Hören> hoeren = namedQuery.getResultList();

        for (Hören hoer : hoeren) {
            BasicDBObject basicOpjHoeren = new BasicDBObject("_id", hoer.hashCode())
                    .append("matrNr", hoer.getMatrNr())
                    .append("vorlNr", hoer.getVorlNr());

            mongoCon.getCollection().insert(basicOpjHoeren);
            System.out.println("MongoDB:\tInserted Hören-Entry: \""+ hoer.getMatrNr()+ "\"/\"" + hoer.getVorlNr()+ "\"");
        }

        //--------------------------------------------------------------------------------------------------------------
        // Prüfen
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "prüfen"
        mongoCon.setCollectionName("prüfen");
        System.out.println("MongoDB:\tCurrent collection: \"prüfen\"");

        //Drop current collection "prüfen"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"prüfen\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Prüfen.findAll");
        Collection<Prüfen> pruefen = namedQuery.getResultList();

        for (Prüfen pruef : pruefen) {
            BasicDBObject basicOpjPruef = new BasicDBObject("_id", pruef.hashCode())
                    .append("matrNr", pruef.getMatrNr())
                    .append("vorlNr", pruef.getVorlNr())
                    .append("persNr", pruef.getPersNr())
                    .append("note", pruef.getNote());

            mongoCon.getCollection().insert(basicOpjPruef);
            System.out.println("MongoDB:\tInserted Prüfen-Entry: \""+ pruef.getMatrNr()+ "\"/\"" + pruef.getVorlNr()+ "\"/\"" + pruef.getPersNr()+ "\"/\"" + pruef.getNote() + "\"");
        }

        //--------------------------------------------------------------------------------------------------------------
        // Voraussetzen
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "voraussetzen"
        mongoCon.setCollectionName("voraussetzen");
        System.out.println("MongoDB:\tCurrent collection: \"voraussetzen\"");

        //Drop current collection "voraussetzen"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"voraussetzen\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Voraussetzen.findAll");
        Collection<Voraussetzen> voraussetzen = namedQuery.getResultList();

        for (Voraussetzen voraus : voraussetzen) {
            BasicDBObject basicOpjPruef = new BasicDBObject("_id", voraus.hashCode())
                    .append("vorgaenger", voraus.getVorgänger())
                    .append("nachfolger", voraus.getNachfolger());

            mongoCon.getCollection().insert(basicOpjPruef);
            System.out.println("MongoDB:\tInserted Voraussetzen-Entry: \""+ voraus.getVorgänger()+ "\"/\"" + voraus.getNachfolger()+ "\"");
        }

        //--------------------------------------------------------------------------------------------------------------
        // Vorlesungen
        //--------------------------------------------------------------------------------------------------------------
        System.out.println("----------------------------------------------");

        //Generate Schema "uni" & collection "vorlesungen"
        mongoCon.setCollectionName("vorlesungen");
        System.out.println("MongoDB:\tCurrent collection: \"vorlesungen\"");

        //Drop current collection "vorlesungen"
        mongoCon.getCollection().drop();
        System.out.println("MongoDB:\tCollection \"vorlesungen\": dropped");

        //Entity magic
        namedQuery = em.createNamedQuery("Vorlesungen.findAll");
        Collection<Vorlesungen> vorlesungen = namedQuery.getResultList();

        for (Vorlesungen vorlesung : vorlesungen) {
            BasicDBObject basicOpjPruef = new BasicDBObject("_id", vorlesung.getVorlNr())
                    .append("titel", vorlesung.getTitel())
                    .append("sws", vorlesung.getSws())
                    .append("gelesenVon", vorlesung.getGelesenVon());

            mongoCon.getCollection().insert(basicOpjPruef);
            System.out.println("MongoDB:\tInserted Vorlesung-Entry: \""+ vorlesung.getTitel());
        }
    }
}


