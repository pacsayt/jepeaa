# jepeaa
Ezek erdekesek lehetnek :
@RestController
~~~~~~~~~~~~~~~
 The Restful web service controller returns object data that is written directly to the HTTP response as JSON. 


@Transactional
~~~~~~~~~~~~~~
 az adott tipus valtoztatasanal automaikusan nyitja 
 es (vszleg a EntityManager::flush()-nal zarja a tranzakciot

@Repository
@PersistenceContext :
~~~~~~~~~~~~~~~~~~~~~
a Spring altal kezelt (== injektor) objektum,
a Spring hozza letre az EntityManagerFactory segitsegevel,
transactional ("shared") EntityManager lesz, mert
(type=PersistenceContextType.TRANSACTION) a default

EntityManager  
~~~~~~~~~~~~~
 ez (ennek vmilyen leszarmazottja) biztositja az adatbazishoz valo (kozvetett) hozzaferest / a perzisztalas alapelemeit
 nincs "update" metodus : igy kell osszerakni : EntityManager::find(), a kapott objektum modosit, EntityManager::flush()
 a valtoztatasok automatikusan mentodnek
 remove() -> managed statuszbol -> detached statuszba kerul a POJO (mint kozvetlen letrehozasa utan)

 tranzakcio : 
  EntityManager::getTranzaction()
  EntityTranzaction::begin()
  ...
  EntityManager::flush()
  EntityTranzaction::commit()


LocalContainerEntityManagerFactoryBean
@Entity
~~~~~~~
 kell az osztlyba egy default konstruktor
 nem lehet az osztaly final
 se valamelyik tagvaltozoja vagy fuggvenye
 kell egy @Id mezo

a persistence provider mappel a java es adatbazis tipusok kozott
 van implicit/default mappeles
 entity class name -> table with the same name
 ID -> primary key
 all of the attributes -> columns with the same name

@Table
@Column
~~~~~~~
name = ha nem azonos a Java adattag nevevel
nullable
length = sztring hossza

@Id
@GeneratedValue

@ManyToOne
@JoinColumn(name="tabla ? oszlop ? nev melyik a celtabla ?")
vagy :
@JoinTable( name="", joinColumns=@JoinColumnName(name=""), inverseJoinColumns=@JoinColumn(name=""))
A tablak kozti viszonyt Id helyett maga az objektum kepviseli, automagically letrehozza az oszlopokat.
Ugy tunt, hogy a masik osztalyban nem kell annotacio (inverseJoinColumns=@JoinColumn())
@JsonFormat

@OneToMany(cascade=PERSISTS) -> gondolom, autmatikusan menti a tartalmazott osztalyokat is
ikulonben TransientObjectException jon, mert rajon, hogy vannak mentetlen @Entity-k


Entitasok/tablak megfeleltetese :
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
OneToOne    JoinColumn
ManyToOne   JoinColumn
OneToMany   JoinTable
ManyToMany  JoinTable

IApplicationDAO
~~~~~~~~~~~~~~~
diszitve : @Transactional @Repository
@PersistenceContext EntityManager tagvaltozoval: EntityManager::persist()/find()/remove() - ilyeneket hivva
sajat IF ilyenekkel : addApplication() -> EntityManager::persist(), applicationExists(), ...

persitence.xml : Spring 3.2-tol mar nem megkerulhetetlen

@PostMapping -
~~~~~~~~~~~~~~
 ResponseEntity<> : mit csinal ez ?
 konstruktoraba : HttpStatus (CONFLICT/CREATED)
 HttpHeaders is jatszik

Teszt :
@RunWith(SpringRunner.class) <-?-> @SpringBootTest
@SpringBootTest
@AutoConfigureMockMvc(secure=false)
MockMvcRequestBuilder : RequestBuilder
^ ezek messze tulmutatnak a szokasos unit testeken

Postman-nal is tesztel : valahogy ezzel us lehet vizsgalni a valaszt (elvart/valami hiba)
 a "Collections" ful alatt talalhato nehany meghivhato vegpont a sajat programunkon, mintegy tesztelesi peldakent

