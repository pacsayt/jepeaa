package spring.boot.jepeaa.onetomany;

import spring.boot.jepeaa.onetoone.ChildEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParentEntityOm
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

  @OneToMany
  private List<ChildEntityOm> childEntityList;

  public ParentEntityOm()
  {
  }

  public ParentEntityOm( String iniName, List<ChildEntityOm> iniChildEntityList)
  {
    name = iniName;
    childEntityList = new ArrayList<>();
    childEntityList.addAll( iniChildEntityList);
  }

  public int getId() {
    return id;
  }

  public void setId( int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name) {
    this.name = name;
  }

  public List<ChildEntityOm> getChildEntityList() {
    return childEntityList;
  }

  public void setChildEntity( List<ChildEntityOm> iniChildEntityList)
  {
    childEntityList = iniChildEntityList;
  }

  @Override
  public String toString() {
    return "ParentEntityMo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", childEntityList=" + childEntityList +
            '}';
  }
}

