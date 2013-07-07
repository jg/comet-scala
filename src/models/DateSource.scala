trait DateSource extends Source {
  this: AggregationMethod =>

  type IssueId = Date
  def isRipe(date: Date): Boolean
}
