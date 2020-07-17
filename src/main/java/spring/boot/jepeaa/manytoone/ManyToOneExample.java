package spring.boot.jepeaa.manytoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

/**
 * JPA - ManyToOne relationship
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/many-to-one.html
 */
public class ManyToOneExample
{
  public static void run(EntityManagerFactory entityManagerFactory)
  {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    ChildEntityMo childEntityMo = new ChildEntityMo("ChildName1");
    ParentEntityMo parentEntityMo1 = new ParentEntityMo("ParentName1", childEntityMo);
    ParentEntityMo parentEntityMo2 = new ParentEntityMo("ParentName2", childEntityMo);

    entityManager.getTransaction().begin();

    // Attempting to save one or more entities that have a non-nullable association with an unsaved transient entity.
    // The unsaved transient entity must be saved in an operation prior to saving these dependent entities.
    entityManager.persist(childEntityMo);
    entityManager.persist(parentEntityMo1);
    entityManager.persist(parentEntityMo2);
    entityManager.getTransaction().commit();

    entityManager.close();

    // ------- L O A D -------
    loadChildEntityMo( entityManagerFactory);

    loadParentEntityMo( entityManagerFactory);
  }

  private static void loadChildEntityMo( EntityManagerFactory entityManagerFactory)
  {
    System.out.println("-- Loading ChildEntityMo --");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    List<ChildEntityMo> entityAList = entityManager.createQuery("Select c from ChildEntityMo c").getResultList();
    entityAList.forEach( System.out::println);

    entityManager.close();
  }

  private static void loadParentEntityMo( EntityManagerFactory entityManagerFactory) {
    System.out.println( "-- Loading ParentEntityMo --");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    List<ParentEntityMo> entityBList = entityManager.createQuery("Select p from ParentEntityMo p").getResultList();
    entityBList.forEach( System.out::println);

    entityManager.close();
  }
}
