package spring.boot.jepeaa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Introduction to Spring Data JPA
 * https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
 */
public interface ItemRepository extends CrudRepository<Item, Integer>
{
//  Optional<Item> findById(Integer id); illen van mar
  List<Item> findByName( String name);

//  @Query("SELECT id, name FROM items WHERE LOWER( name) = LOWER(:name)") //
//  List<Item> findByName( @Param("name") String name);
}
