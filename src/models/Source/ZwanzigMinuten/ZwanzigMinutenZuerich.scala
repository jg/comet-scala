class ZwanzigMinutenZuerich extends ZwanzigMinuten {
  def isRipe(date: Date) = true

  def aggregate(date: Date): Option[AggregationData] = get(aggregationUrl(date)) match {
    case Some(Mechanize.PdfFile(f)) => Some(SinglePdf(f))
    case _ => None
  }

  private def aggregationUrl(date: Date) = {
    val fmtDate = date.strftime("YMMdd")
    s"http://www.20min.ch/printpdf/ZH_$fmtDate.pdf"
  }
}
