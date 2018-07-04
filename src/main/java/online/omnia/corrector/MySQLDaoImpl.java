package online.omnia.corrector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MySQLDaoImpl {
    private static Configuration masterDbConfiguration;
    private static SessionFactory masterDbSessionFactory;

    private static MySQLDaoImpl instance;

    static {
        masterDbConfiguration = new Configuration()
                .addAnnotatedClass(PostBackEntity.class)
                .configure("/hibernate.cfg.xml");

        Map<String, String> properties = FileWorkingUtils.iniFileReader();

        masterDbConfiguration.setProperty("hibernate.connection.password", properties.get("master_db_password"));
        masterDbConfiguration.setProperty("hibernate.connection.username", properties.get("master_db_username"));
        masterDbConfiguration.setProperty("hibernate.connection.url", properties.get("master_db_url"));

        while (true) {
            try {
                masterDbSessionFactory = masterDbConfiguration.buildSessionFactory();
                break;
            } catch (PersistenceException e) {
                try {
                    e.printStackTrace();
                    System.out.println("Can't connect to master db");
                    System.out.println("Waiting for 30 seconds");
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    public List<PostBackEntity> getPostbacks(int firstElement) {
        Session session = masterDbSessionFactory.openSession();
        List<PostBackEntity> postBackEntities = session.createQuery("from PostBackEntity", PostBackEntity.class)
                .setFirstResult(firstElement * 100)
                .setMaxResults(100)
                .getResultList();
        session.close();
        return postBackEntities;
    }

    public void update(PostBackEntity postBackEntity) {
        Session session = masterDbSessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("update PostBackEntity set offername=:offername, t1=:t1, t2=:t2, t3=:t3, t4=:t4, t5=:t5, t6=:t6, t7=:t7, t8=:t8, t9=:t9, t10=:t10 where id=:id")
                .setParameter("offername", postBackEntity.getOfferName())
                .setParameter("t1", postBackEntity.getT1())
                .setParameter("t2", postBackEntity.getT2())
                .setParameter("t3", postBackEntity.getT3())
                .setParameter("t4", postBackEntity.getT4())
                .setParameter("t5", postBackEntity.getT5())
                .setParameter("t6", postBackEntity.getT6())
                .setParameter("t7", postBackEntity.getT7())
                .setParameter("t8", postBackEntity.getT8())
                .setParameter("t9", postBackEntity.getT9())
                .setParameter("t10", postBackEntity.getT10())
                .setParameter("id", postBackEntity.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private MySQLDaoImpl() {
    }

    public static SessionFactory getMasterDbSessionFactory() {
        return masterDbSessionFactory;
    }

    public static synchronized MySQLDaoImpl getInstance() {
        if (instance == null) instance = new MySQLDaoImpl();
        return instance;
    }
}
