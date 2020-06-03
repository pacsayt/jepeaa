package spring.boot.jepeaa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.boot.jepeaa.music.Artist;
import spring.boot.jepeaa.music.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		runJpa( args);
	}

	/**
	 * JPA Kickstart Example
	 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpa-kickstart.html
	 * @param args
	 */
	private void runpersistenceKickstart( String... args)
	{

	}

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
/*
		System.out.println( "JepeaaApplication::run() : itemRepository.itemRepository( Item1)");
		Item item2 = itemRepository.retrieveByName( "Item2");
		System.out.println( "JepeaaApplication::run() : itemRepository.itemRepository( Item1) -> " + item2);
*/
	}

	private void runJpa( String... args)
	{
//		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
//		EntityManager entitymanager = emfactory.createEntityManager( );
		EntityManager entitymanager = JpaUtil.getEntityManager();

		entitymanager.getTransaction( ).begin( );

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

		JpaUtil.getEntityManagerFactoryInstance().close();
	}
}
