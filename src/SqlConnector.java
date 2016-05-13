import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by peach on 12.05.16
 */
public class SqlConnector {

    private EntityManager em;

    public SqlConnector(String persistenceUnitName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }
}
