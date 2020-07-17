package spring.boot.jepeaa.onetomany;

import spring.boot.jepeaa.manytoone.ChildEntityMo;
import spring.boot.jepeaa.manytoone.ParentEntityMo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;

public class OneToManyExample
{
  public static void run(EntityManagerFactory entityManagerFactory)
  {
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    createPersistEntities( entityManagerFactory);

    loadChildEntityOm( entityManagerFactory);

    loadParentEntityOm( entityManagerFactory);
  }

  private static void createPersistEntities( EntityManagerFactory entityManagerFactory) {
    System.out.println("-- createPersistEntities --");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    ChildEntityOm childEntityOm1 = new ChildEntityOm( "ChildName1");
    ChildEntityOm childEntityOm2 = new ChildEntityOm( "ChildName2");
    ChildEntityOm childEntityOm3 = new ChildEntityOm( "ChildName3");
    ChildEntityOm childEntityOm4 = new ChildEntityOm( "ChildName4");

    List<ChildEntityOm> childEntityOmList1 = Arrays.asList( childEntityOm1, childEntityOm2, childEntityOm3);
//    List<ChildEntityOm> childEntityOmList2 = Arrays.asList( childEntityOm1);  // many-to-many relationship -> constraint violation
    List<ChildEntityOm> childEntityOmList2 = Arrays.asList( childEntityOm4);

    ParentEntityOm parentEntityOm1 = new ParentEntityOm( "ParentName1", childEntityOmList1);
    ParentEntityOm parentEntityOm2 = new ParentEntityOm( "ParentName2", childEntityOmList2);

    entityManager.getTransaction().begin();

    // Attempting to save one or more entities that have a non-nullable association with an unsaved transient entity.
    // The unsaved transient entity must be saved in an operation prior to saving these dependent entities.
    entityManager.persist( childEntityOm1);
    entityManager.persist( childEntityOm2);
    entityManager.persist( childEntityOm3);
    entityManager.persist( childEntityOm4);

    System.out.println( "entityManager.persist( parentEntityOm1) -----------------------");
    entityManager.persist( parentEntityOm1);
    System.out.println( "entityManager.persist( parentEntityOm2) -----------------------");
    entityManager.persist( parentEntityOm2);

    System.out.println( "entityManager.getTransaction().commit() -----------------------");
    entityManager.getTransaction().commit();

    entityManager.close();
  }

  private static void loadChildEntityOm( EntityManagerFactory entityManagerFactory)
  {
    System.out.println("-- Loading ChildEntityOm --");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    List<ChildEntityMo> entityAList = entityManager.createQuery("Select c from ChildEntityMo c").getResultList();
    entityAList.forEach( System.out::println);

    entityManager.close();
  }

  private static void loadParentEntityOm( EntityManagerFactory entityManagerFactory)
  {
    System.out.println( "-- Loading ParentEntityOm --");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    List<ParentEntityMo> entityBList = entityManager.createQuery("Select p from ParentEntityMo p").getResultList();
    entityBList.forEach( System.out::println);

    entityManager.close();
  }

}
