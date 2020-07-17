package spring.boot.jepeaa.onetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class OneToOneExample
{

  public static void run(EntityManagerFactory entityManagerFactory)
  {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    ChildEntity childEntity = new ChildEntity( "ChildName1");
    ParentEntity parentEntity = new ParentEntity( "ParentName1", childEntity);

    entityManager.getTransaction().begin();

    // Attempting to save one or more entities that have a non-nullable association with an unsaved transient entity.
    // The unsaved transient entity must be saved in an operation prior to saving these dependent entities.
    entityManager.persist( childEntity);
    entityManager.persist( parentEntity);
    entityManager.getTransaction().commit();

    entityManager.close();
  }
}
