package spring.boot.jepeaa.jpautil;

import spring.boot.jepeaa.jpakickstart.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class QueryUtil
{
  public static void typedQueryWithJPQL( EntityManager entityManager, Class clazz)
  {
    System.out.println( "----\nTyped Querying using JPQL");

    TypedQuery<Person> q = entityManager.createQuery("select t from " + clazz.getCanonicalName() + " t", clazz);
    System.out.println( q.getResultList());
  }

  public static void nativeQuery( EntityManager entityManager, String sqlCommand)
  {
    System.out.println("----\nnative query");
    Query nativeQuery = entityManager.createNativeQuery( sqlCommand);

    List resultList = nativeQuery.getResultList();

    for ( Object o : resultList )
    {
      if ( o.getClass().isArray() )
      {
        Object oa[] = (Object[]) o;
        System.out.println( Arrays.asList( oa));
      }
      else
      {
        System.out.println( o);
      }
    }
  }

  public static void showGeneratedTables( EntityManager entityManager) {
    nativeQuery( entityManager, "SHOW TABLES");
    nativeQuery( entityManager, "SHOW COLUMNS from MyEntity");
    nativeQuery( entityManager, "SHOW COLUMNS from PersistableEnumEntity");
    nativeQuery( entityManager, "SELECT * FROM INFORMATION_SCHEMA.SEQUENCES");

    nativeQuery( entityManager, "SELECT * FROM INFORMATION_SCHEMA.SEQUENCES");
    nativeQuery( entityManager, "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC' ");

//    Table "SEQUENCES" not found; SQL statement: SELECT * FROM SEQUENCES
//    nativeQuery( entityManager, "SELECT * FROM SEQUENCES"); // HIBERNATE_SEQUENCES
//    nativeQuery( entityManager, "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'SEQUENCES'"); // HIBERNATE_SEQUENCES

  }
}
