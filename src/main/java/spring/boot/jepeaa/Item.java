package spring.boot.jepeaa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description on possible annotations :
 * https://www.tutorialspoint.com/jpa/jpa_orm_components.htm
 */
@Entity
public class Item
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;

  public Item()
  {
  }

  public Item( String iniValue)
  {
    name = iniValue;
  }

  public Item( Integer iniId, String iniValue)
  {
    id = iniId;
    name = iniValue;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer iniId)
  {
    id = iniId;
  }

  public String getName()
  {
    return name;
  }

  public void setValue(String iniName)
  {
    name = iniName;
  }

  @Override
  public String toString()
  {
    return "Item{ id=" + id + ", name='" + name + "'}";
  }
}