import java.net._
import java.io._

class MechanizeAgent {

  def get(uri: String): Option[Page] = {
    val url: URL = new URL(uri)
    val conn: URLConnection = url.openConnection()
    val reader: BufferedReader =
      new BufferedReader(new InputStreamReader(url.openStream()))

    val lines = Iterator.continually(reader.readLine()).takeWhile(_ != null)
    val pageBody = lines.reduceLeft[String] { (acc, n) => acc + "\n" + n}
    Some(new Page(pageBody))
  }
}
