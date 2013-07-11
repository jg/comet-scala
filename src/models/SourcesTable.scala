import scala.slick.driver.PostgresDriver.simple._
import scala.slick.session.Database
import Database.threadLocalSession
import scala.slick.jdbc.{GetResult, StaticQuery => Q}
import Q.interpolation

object SourcesTable extends Table[(Long, String, String)]("sources") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def sourceType = column[String]("type", O.NotNull)
  def * = id ~ name ~ sourceType
}

object SourcesDAO {
  val database = Database.forURL("jdbc:postgresql:comet", driver = "org.postgresql.Driver", user = "comet", password = "comet")

  def count: Int = database withSession {
    Query(SourcesTable).list.length
  }

  def all: List[Source] = database withSession {
    for {
      (id: Long, name: String, sourceType: String) <- Query(SourcesTable).list
      source <- Source.fromDBTuple(id, name, sourceType)
    } yield source
  }

}

// class Sources extends Table[Source]("SOURCE") {
//   def id = column[Long]("ID", O.PrimaryKey)
//   def name = column[String]("NAME", O.PrimaryKey)
//   def * = id ~ name
// }
