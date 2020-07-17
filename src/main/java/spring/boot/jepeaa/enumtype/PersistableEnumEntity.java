package spring.boot.jepeaa.enumtype;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class PersistableEnumEntity
{
  @Id
  /**
   * https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/seq-generator.html
   *
   * @GeneratedValue(strategy = GenerationType.SEQUENCE) GenerationType.TABLE /
   * GenerationType.IDENTITY : This special type column is populated internally by the table itself without using a separate sequence.
   *                           H2 database which doesn't support IDENTITY column.
   * GenerationType.AUTO     : the persistence provider should automatically pick an appropriate strategy for the particular database
   *
   * @SequenceGenerator(name = "sajatSeqGen", sequenceName = "sajatSeq", initialValue = 5, allocationSize = 100)
   * @GeneratedValue(generator = "sajatSeqGen")
   **/
  private long id;

  @Enumerated(EnumType.STRING) // Can be EnumType.ORDINAL
  private PersistableEnum persistableEnum;

  public PersistableEnumEntity()
  {
  }

  public PersistableEnumEntity( long iniId, PersistableEnum iniPersistableEnum)
  {
    id = iniId;
    persistableEnum = iniPersistableEnum;
  }

  public long getId()
  {
    return id;
  }

  public void setId( long iniId)
  {
    id = iniId;
  }

  public PersistableEnum getPersistableEnum()
  {
    return persistableEnum;
  }

  public void setPersistableEnum( PersistableEnum iniPersistableEnum)
  {
    persistableEnum = iniPersistableEnum;
  }

  @Override
  public String toString()
  {
    return "PersistableEnumEntity{id=" + id + ", persistableEnum=" + persistableEnum + '}';
  }
}
