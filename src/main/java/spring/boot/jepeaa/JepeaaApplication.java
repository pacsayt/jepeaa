package spring.boot.jepeaa;

import JpaUtil.QueryUtil;
import org.hibernate.procedure.internal.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.boot.jepeaa.jpakickstart.Person;
import spring.boot.jepeaa.music.Artist;
import spring.boot.jepeaa.music.Song;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Spring Boot + Spring data JPA
 * https://mkyong.com/spring-boot/spring-boot-spring-data-jpa/
 *
 */
@SpringBootApplication
public class JepeaaApplication implements CommandLineRunner
{
	@Autowired
	private ItemRepository itemRepository;

	public static void main(String[] args)
	{
		System.out.println( "JepeaaApplication::main() SpringApplication.run() BEFORE");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(JepeaaApplication.class, args); // Just not to forget that this function returns the context
		System.out.println( "JepeaaApplication::main() SpringApplication.run() AFTER");
	}

	@Override
	public void run( String... args) throws Exception
	{
//		runSpringBootJpa( args);
//		runJpa( args);
//		runPersistenceKickstart( args);
		runPersistingJavaEnum( args);
	}

	/**
	 * JPA - Persisting Java Enum
	 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/persisting-enum.html
	 */
	private void runPersistingJavaEnum( String... args)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test1");
	}

	private static void showGeneratedTables( EntityManagerFactory entityManagerFactory)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		QueryUtil.showGeneratedTables( entityManager);
		entityManager.close();
	}


	/**
	 * JPA Kickstart Example
	 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpa-kickstart.html
	 * @param args
	 */
	private void runPersistenceKickstart( String... args)
	{
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("testPersistenceUnit");
		// EntityManager - not thread safe : will throw exceptions even if threads are synchronized not to access db at the same time
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Person person = new Person();
		person.setStr( "Person1");
		entityManager.persist( person);

		person = new Person();
		person.setStr( "Person2");
		entityManager.persist( person);

		entityManager.getTransaction().commit();

		findObjectById( entityManager);
		queryWithJPQL( entityManager);
		typedQueryWithJPQL( entityManager);
		criteriaQuery( entityManager);
		QueryUtil.queryNative( entityManager); // queryNative( entityManager);

		// When the application has finished using the entity manager factory, and/or at application shutdown,
		// the application should close the entity manager factory by calling emf.close().
		// Once an entity manager factory has been closed, all entity managers created from it are considered
		// to be in the closed state as well.
		entityManagerFactory.close();
	}

	private static void findObjectById( EntityManager entityManager)
	{
		System.out.println("----\nfinding object by id");
		Person person = entityManager.find( Person.class, 2L);
		System.out.println( person);
	}

	private static void queryWithJPQL( EntityManager entityManager)
	{
		System.out.println( "----\nQuerying using JPQL");
		Query query = entityManager.createQuery("select t from Person t");
		List resultList1 = query.getResultList();
		System.out.println( resultList1);
	}

	private static void typedQueryWithJPQL( EntityManager entityManager)
	{
		System.out.println( "----\nTyped Querying using JPQL");
		TypedQuery<Person> q = entityManager.createQuery("select t from Person t", Person.class);
		System.out.println(q.getResultList());
	}

	private static void criteriaQuery( EntityManager entityManager)
	{
		System.out.println( "----\ncriteria query");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		CriteriaQuery<Object> select = criteriaQuery.select( criteriaQuery.from( Person.class));

		TypedQuery<Object> typedQuery = entityManager.createQuery( select);
		System.out.println(typedQuery.getResultList());
	}

/*
	private void runSpringBootJpa( String... args)
	{
		System.out.println( "JepeaaApplication::run() SpringApplication.run() BEFORE");

		itemRepository.save( new Item( "Item1"));
		itemRepository.save( new Item( "Item2"));
		itemRepository.save( new Item( "Item3"));

		System.out.println( "JepeaaApplication::run() : itemRepository.findAll()");
		itemRepository.findAll().forEach( item -> System.out.println( item));

		System.out.println( "JepeaaApplication::run() : itemRepository.findById( 1)");
		itemRepository.findById( 1).ifPresent( item -> System.out.println( item));

		System.out.println( "JepeaaApplication::run() : itemRepository.findByName( Item1)");
		itemRepository.findByName( "Item1").forEach( item -> System.out.println( item));

//		System.out.println( "JepeaaApplication::run() : itemRepository.itemRepository( Item1)");
//		Item item2 = itemRepository.retrieveByName( "Item2");
//		System.out.println( "JepeaaApplication::run() : itemRepository.itemRepository( Item1) -> " + item2);
	}
*/
/* Might be of good use later ...
		// Two artists, with two songs each
		Artist artist1 = new Artist();
		artist1.setName( "Artist1");

		entitymanager.persist( artist1);

    Song song11 = new Song();
    song11.setTitle( "Title11");
    song11.setArtist( artist1);
    entitymanager.persist( song11);

		Song song12 = new Song();
		song12.setTitle( "Title12");
		song12.setArtist( artist1);
		entitymanager.persist( song12);


		Artist artist2 = new Artist();
		artist1.setName( "Artist2");

		entitymanager.persist( artist2);

		Song song21 = new Song();
		song21.setTitle( "Title21");
		song21.setArtist( artist2);
		entitymanager.persist( song11);

		Song song22 = new Song();
		song22.setTitle( "Title22");
		song22.setArtist( artist2);
		entitymanager.persist( song22);

		entitymanager.getTransaction().commit();
		entitymanager.close();
*/
}
