package spring.boot.jepeaa.jpakickstart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * JPA Entities
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/jpa-entity.html
 * No enum or interface allowed
 *
 * The entity class must be top-level class (not inner/nested). An enum or interface cannot be an entity
 * Entity class constructor
 *
 * The class must have a public or protected, no-argument constructor. The class may have other additional constructors.
 * Non final Entity class
 *
 * The class must not be declared final. No persistent instance variables or corresponding methods must be declared final.
 *
 * JPA - Basic Persistable Types
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/persistable-basic-types.html
 */

@Entity
// @Table(name = "exampleTable") // if default name cannot be used
public class Person
{
  @Id
  @GeneratedValue
  // @Basic : fetch = EAGER / LAZY (?), optional = true / false
  private long objId; // BIGINT (19)
//  @Column(name = "example_String") // if default name cannot be used.
//  has other optional elements to specify attributes like length, nullable, unique for the column.
  private String name; // VARCHAR(n)
//  @Transient
// private int someFieldThatWillNotBePartOfTheDBTable
  public Person()
  {
  }

  public Person( long iniObjId, String iniName)
  {
    objId = iniObjId;
    name = iniName;
  }

  public long getObjId()
  {
    return objId;
  }

  public void setObjId(long iniObjId)
  {
    objId = iniObjId;
  }

  public String getName()
  {
    return name;
  }

  public void setStr( String iniName)
  {
    name = iniName;
  }

  @Override
  public String toString()
  {
    return "Person{ objId=" + objId + ", name='" + name + '}';
  }
}
