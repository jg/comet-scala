package Mechanize

import java.net._
import java.io._

import com.ning.http.client._
import com.ning.http.client.AsyncHandler.STATE
import scala.concurrent._
import ExecutionContext.Implicits.global

trait Agent {
  val client = new AsyncHttpClient()

  /** Nedds an uri and a File to write to, will return same file with contents or None */
  def getFile(uri: String, file: File): Future[Option[File]] = {
    def buildGetRequest: Request = {
      val builder = new RequestBuilder("GET")
      builder.setUrl(uri).build()
    }

    val resultPromise = promise[Option[File]]
    val futureWithResult: Future[Option[File]] = resultPromise.future
    val outStream = new FileOutputStream(file)

    val response = client.executeRequest(buildGetRequest, new AsyncHandler[Future[Option[File]]]() {
      def onStatusReceived(headers: HttpResponseStatus): AsyncHandler.STATE = STATE.CONTINUE

      def onHeadersReceived(headers: HttpResponseHeaders): AsyncHandler.STATE = STATE.CONTINUE

      def onBodyPartReceived(bodyPart: HttpResponseBodyPart): AsyncHandler.STATE = {
        bodyPart.writeTo(outStream)
        STATE.CONTINUE
      }

      def onCompleted() =  {
        resultPromise.success(Some(file))
        futureWithResult
      }

      def onThrowable(t: Throwable) = {
        resultPromise.failure(t)
      }
    })

    futureWithResult
  }
}
