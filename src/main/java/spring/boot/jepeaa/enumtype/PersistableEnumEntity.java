package spring.boot.jepeaa.enumtype;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersistableEnumEntity
{
  @Id
  private long id;
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
}
