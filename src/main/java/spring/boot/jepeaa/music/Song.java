package spring.boot.jepeaa.music;

import javax.persistence.*;

@Entity
public class Song
{
  @Id
  @GeneratedValue( strategy= GenerationType.AUTO )
  private int id;

  private String title;

  @ManyToOne
  private Artist artist;

  public Song()
  {
  }

  public Song( int iniId, String iniTitle, Artist iniArtist)
  {
    id = iniId;
    title = iniTitle;
    artist = iniArtist;
  }

  public int getId()
  {
    return id;
  }

  public void setId( int iniId)
  {
    id = iniId;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String iniTitle)
  {
    title = iniTitle;
  }

  public Artist getArtist()
  {
    return artist;
  }

  public void setArtist(Artist iniArtist)
  {
    artist = iniArtist;
  }
}
