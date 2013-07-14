import scala.concurrent._
import ExecutionContext.Implicits.global

class ZwanzigMinutenZuerich extends ZwanzigMinuten {
  def sourceType: String = "Source::ZwanzigMinuten::Zuerich"

  def isRipe(date: Date) = true

  def aggregate(date: Date, dataPlaceholder: AggregationData): Future[Option[AggregationData]] =
    getFile(aggregationUrl(date), dataPlaceholder.file).map(_.map(file => SinglePdf(TempFile(file))))

  private def aggregationUrl(date: Date) = {
    val fmtDate = date.strftime("YMMdd")
    s"http://www.20min.ch/printpdf/ZH_$fmtDate.pdf"
  }
}
