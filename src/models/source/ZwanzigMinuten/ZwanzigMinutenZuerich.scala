class ZwanzigMinutenZuerich extends ZwanzigMinuten {
  def isRipe(date: Date) = true

  def aggregate(date: Date): Option[AggregationData] = {
    val fmtDate = date.strftime("%Y%m%d")
    val url = s"http://www.20min.ch/printpdf/ZH_$fmtDate.pdf"
    Some(new SinglePdf)
  }
}
