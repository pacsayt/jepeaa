package spring.boot.jepeaa.manytoone;

import javax.persistence.*;

@Entity
public class ParentEntityMo
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

  //  @OneToOne(cascade = CascadeType.PERSIST) // Child element will be automatically persisted
  @ManyToOne
  private ChildEntityMo childEntityMo;

  public ParentEntityMo()
  {
  }

  public ParentEntityMo(String iniName, ChildEntityMo iniChildEntityMo)
  {
    name = iniName;
    childEntityMo = iniChildEntityMo;
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

  public ChildEntityMo getChildEntityMo() {
    return childEntityMo;
  }

  public void setChildEntityMo(ChildEntityMo childEntityMo) {
    this.childEntityMo = childEntityMo;
  }

  @Override
  public String toString() {
    return "ParentEntityMo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", childEntityMo=" + childEntityMo +
            '}';
  }
}