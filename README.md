# jepeaa

javax.persistence.* referenciakent

Adatbazis tablankent van egy "service".

Ezek erdekesek lehetnek :
@Service

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

@JsonFormat
~~~~~~~~~~~

@Table
~~~~~~

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

@MappedSuperclass
~~~~~~~~~~~~~~~~~
 a leszarmazott osztalyok vannak @Entity-vel annotalva
 az ezzel annotalt osztalynak nem lesz kulon tabla, csak a gyerekeknek egyenkent ?
 azaz pl. nem lehet lekerdezni az osszes ebbol leszarmazo objetumot polimorfikus modon

Single Table strategia (default)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 @Entity
 @Inheritance(strategy=InheritanceType.SINGLE_TABLE)

 minden tipust egy tablaba zutyul be ("Hatekony, mert csak egy tabla van, de nem lehet NOT NULL-t deklaralni egyik oszlopon sem emiatt")

 A discriminator type adja meg, milyen tipusu a tablaban talalhato rekord. Ugy nez ki, megegyezik az osztaly nevevel
 DTYPE az oszlop neve (@DiscriminatorColumn)

Table per class strategia
~~~~~~~~~~~~~~~~~~~~~~~~~
 szuloosztalyban : @Entiy + @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
 gyerekosztalyokban : @Entiy
 a gyerekosztalyokba mindbe belezutyulja az oroklott oszlopokat is, csak az minden tipus kulon tablaba kerul

Joined table strategia
~~~~~~~~~~~~~~~~~~~~~~~~~
 szuloosztalyban : @Entiy + @Inheritance(strategy=InheritanceType.JOINED)
 gyerekosztalyokban : @Entiy
 minden kulon tablaba, csak, ami az adott osztalyra specifikus, az kerul bele
 tabla szinten nem latszik a kapcsolat ... az osszetartozo rekordok id-je azonos a kulonbozo tablak eseteben. hm.

JPQL
~~~~
A find metodus id-vel mukodik.
Ha ennel tobb kell, JPQL ...

Osztalynevek, nem tablanevek
Mezonevek, nem oszlopnevek
Az SQL-nek csak egy reszet tudja most, de meg fejlodik ...

Query EntityManager::createQuery( "JPQL")
      EntityManager::createNativeQuery( "SQL")

@NamedQuery(name="...", query="Bug.findSevereBugs")
 @Entity osztalyon
public class Bug
 nem valtoztathato futasidoben

 named query resembles the way dynamic queries are used : entityManager.creatNamedQuery("Bug.findSevereBugs).getResultList()

@NamedStoredProcedureQuery
@StoredProcedureParameter

getResultStream()

Criteria API
~~~~~~~~~~~~
The Criteria API is an alternate method for constructing queries that uses a Java programming language API instead of JPQL or Native SQL. JPA has two main ways of querying the database; JPQL and the Criteria API. As we've seen, JPQL queries are written as simple strings, usually easy to read, but as strings they cannot be checked by a Java compiler. The downsides to JPQL is that if an entity attribute gets renamed, all queries using it break without giving any warning. The JPA Criteria API seeks to overcome this short coming. The Criteria API is a predefined API used to define queries for entities. It is the alternative way of defining a JPQL query. There are several advantages to using the Criteria API. First, with the Criteria API, queries are written using Java programming language APIs and are type-safe and portable. Type-safe provides compile time checks, code completion and better refactoring support. This means errors can be detected earlier during compile time. Portable means that queries work regardless of the underlying data store. Lastly, the Criteria API is nice for building dynamic queries. It is important to note that string-based JPQL queries and JPA criteria-based queries are the same in performance and efficiency. There you have it, the Criteria API is an alternative method for constructing queries in JPA and is used in place of JPQL, providing queries that are type-safe and portable.

Re, bonyas a megvalositasa, 6 lepeses ...
EntityManager -> CriteriaBuilder -> CriteriaQuery -> Root<...> -> CriteriaQuery::select() -> EntityManager::createQuery() -> TypedQuery<> -> TypedQuery<>::getResultList()

CriteriaQuery::select()/from()/where()/like()/orderBy()/groupBy().

Root<>::join( "tablanev") automagically az id-k alapjan

CriteriaQuery::select( Root<>).where( CriteriaBuilder.equal( Root<>::get( "column"), "<column_value>")) huh

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

