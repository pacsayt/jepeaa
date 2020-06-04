package JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class QueryUtil
{
  public static void queryNative( EntityManager entityManager)
  {
    System.out.println("----\nnative query");
    Query nativeQuery = entityManager.createNativeQuery("select * from Person");

    List resultList = nativeQuery.getResultList();

    for (Object o : resultList)
    {
      if (o.getClass().isArray())
      {
        Object oa[] = (Object[]) o;
        System.out.println( Arrays.asList(oa));
      }
      else
      {
        System.out.println(o);
      }
    }
  }

  public static void showGeneratedTables( EntityManager entityManager) {
    queryNative( entityManager, "SHOW TABLES");
    queryNative( entityManager, "SHOW COLUMNS from MyEntity");
    entityManager.close();
  }
}
