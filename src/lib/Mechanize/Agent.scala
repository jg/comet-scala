package Mechanize

import java.net._
import java.io._
import Comet.TempFile

trait Agent {
  def get(uri: String): Option[Entity] = {
    val url: URL = new URL(uri)
    val conn: URLConnection = url.openConnection()

    /*
    val inputStream = new InputStreamReader(url.openStream())
        val reader: BufferedReader = new BufferedReader(inputStream)
        val lines = Iterator.continually(reader.readLine()).takeWhile(_ != null)
        val content = lines.reduceLeft[String] { (acc, n) => acc + "\n" + n}
    */

    conn.getContentType() match  {
      case "application/pdf" => Some(new PdfFile(inputStreamToFile(url.openStream())))
      case _ @ contentType => throw new Exception(s"Unmatched content type $contentType")
    }
  }

  private def inputStreamToFile(is: InputStream): TempFile = {
    val f = TempFile()
    val in = scala.io.Source.fromInputStream(is)(scala.io.Codec.ISO8859)
    val out = new java.io.PrintWriter(f)
    try { in.getLines().foreach(out.print(_)) }
    finally { out.close }
    f
  }
}
