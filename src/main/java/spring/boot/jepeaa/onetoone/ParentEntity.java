package spring.boot.jepeaa.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParentEntity
{
  @Id
  @GeneratedValue
  private int id;

  private String name;

//  @OneToOne(cascade = CascadeType.PERSIST) // Child element will be automatically persisted
  @OneToOne(optional = false) // cannot be null, and must be saved before
//  Kapcsolotabla SELECT * FROM MY_JOIN_TABLE hatasara kiirja mindketto referencialt elemet
//  @JoinTable(name = "MY_JOIN_TABLE",
//          joinColumns = {
//                  @JoinColumn(name = "ENTITYA_FK", referencedColumnName = "myIdA")
//          },
//          inverseJoinColumns = {
//                  @JoinColumn(name = "ENTITYB_FK", referencedColumnName = "myIdB", unique = true)
//          }
//  )


  private ChildEntity childEntity;

  public ParentEntity()
  {
  }

  public ParentEntity( String iniName, ChildEntity iniChildEntity)
  {
    name = iniName;
    childEntity = iniChildEntity;
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

  public ChildEntity getChildEntity() {
    return childEntity;
  }

  public void setChildEntity(ChildEntity childEntity) {
    this.childEntity = childEntity;
  }

  @Override
  public String toString() {
    return "ParentEntityMo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", childEntity=" + childEntity +
            '}';
  }
}
