package spring.boot.jepeaa.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChildEntity
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

  ChildEntity()
  {
  }

  ChildEntity( String iniName)
  {
    name = iniName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ChildEntityMo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
