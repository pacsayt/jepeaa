package spring.boot.jepeaa.manytoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChildEntityMo
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

  ChildEntityMo()
  {
  }

  ChildEntityMo(String iniName)
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