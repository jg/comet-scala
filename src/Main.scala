import java.net._
import java.io._

object Main extends App {

  def get(urlString: String): Page = {
    val url: URL = new URL(urlString)
    val conn: URLConnection = url.openConnection()
    val reader: BufferedReader =
      new BufferedReader(new InputStreamReader(url.openStream()))

    val lines = Iterator.continually(reader.readLine()).takeWhile(_ != null)
    val pageBody = lines.reduceLeft[String] { (acc, n) => acc + "\n" + n}
    new Page(pageBody)
  }

  override def main(args: Array[String]) = {

  }

  def f() = {
    val page = get("http://www.google.com")
    println(page.body)
  }
}
