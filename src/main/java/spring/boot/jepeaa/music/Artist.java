package spring.boot.jepeaa.music;

import javax.persistence.*;
import java.util.List;

@Entity
public class Artist
{
  @Id
  @GeneratedValue( strategy= GenerationType.AUTO )
  private int id;
  private String name;

  public Artist( )
  {
  }

  public Artist( int iniId, String iniName)
  {
    id = iniId;
    name = iniName;
  }

  public int getId()
  {
    return id;
  }

  public void setId( int iniId)
  {
    this.id = iniId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String iniName)
  {
    this.name = name;
  }
}
