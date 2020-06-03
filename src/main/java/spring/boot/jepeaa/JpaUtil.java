package spring.boot.jepeaa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

ehelyett \/ lehet, hogy ez JPA Tutorials https://www.logicbig.com/tutorials/java-ee-tutorial/jpa.html jobb lesz ...

/**
 * Calling Persistence.createEntityManagerFactory > 1 time
 * https://stackoverflow.com/questions/42728479/calling-persistence-createentitymanagerfactory-1-time
 * @Entity(name = "usermaster")
 * @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
 * @Query("select t from ticket as t join t.user as u where u.id = :userId")
 * @ManyToOne(optional=false, cascade= CascadeType.ALL)
 *     @JoinColumn(name = "userid")
 *      @OneToOne
 *      createEntityManagerFactory
 *      JPA tutorial @Entity  @OneToMany @Query @ManyToOne @JoinColumn createEntityManagerFactory
 */
public class JpaUtil
{
  private static HashMap<String, String> properties = new HashMap<String, String>();
  private volatile static EntityManagerFactory factory;

  static {
    properties.put("javax.persistence.jdbc.driver", System.getProperty("DRIVER"));
    properties.put("javax.persistence.jdbc.user", System.getProperty("USER"));
    properties.put("javax.persistence.jdbc.password", System.getProperty("PASSWORD"));
    properties.put("javax.persistence.jdbc.url", System.getProperty("DATABASEURL"));
  }

  public static EntityManagerFactory getEntityManagerFactoryInstance() {
    if (factory == null) {
      synchronized (EntityManagerFactory.class) {
        if (factory == null) {

          factory = Persistence.createEntityManagerFactory("PU", properties); // No Persistence provider for EntityManager named PU
        }
      }
    }
    return factory;
  }

  public static EntityManager getEntityManager() {
    return getEntityManagerFactoryInstance().createEntityManager();
  }
}