class Issue(val date: Date) {
  var aggregationData: Option[AggregationData] = None

  def source: Source = ???

}
