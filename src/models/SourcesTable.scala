import scala.slick.driver.PostgresDriver.simple._
import scala.slick.session.{Database => SlickDatabase}
import Database.threadLocalSession
import scala.slick.jdbc.{GetResult, StaticQuery => Q}
import Q.interpolation
import scala.slick.lifted.CanBeQueryCondition
import scala.slick.lifted.Shape
import scala.slick.lifted.AbstractTable

class SourcesTable extends Table[Source]("Sources") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def sourceType = column[String]("type", O.NotNull)
  def * = id ~ name ~ sourceType <> (Source.apply _, Source.unapply _)
}

object SourcesTable extends SourcesTable

trait Database {
  val database = Database.forURL("jdbc:postgresql:comet", driver = "org.postgresql.Driver", user = "comet", password = "comet")
}

trait Finders {
  this: Database =>

  def find[T, K, E](query: Query[E, K])(f: E => T)(implicit wt: CanBeQueryCondition[T]): Option[K] = database withSession {
    query.filter(f).firstOption
  }
}


object SourcesDAO extends Database with Finders {
  val query = Query(SourcesTable)

  def findByType(sourceType: String): Option[Source] = database withSession {
    find(query)(_.sourceType === sourceType)
  }

  def all: List[Source] = database withSession {for {source <- query.list} yield source}

  def count: Int = database withSession {query.list.length}
}
