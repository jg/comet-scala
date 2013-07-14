import java.io._
object AggregationData {
  implicit def aggregationDataToFile(a: AggregationData): File = a.file
}
abstract class AggregationData(val file: File)
case class SinglePdf(f: TempFile) extends AggregationData(f)
