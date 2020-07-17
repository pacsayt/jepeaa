package spring.boot.jepeaa.onetomany;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChildEntityOm
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

  ChildEntityOm()
  {
  }

  ChildEntityOm(String iniName)
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
    return "ChildEntityOm{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}