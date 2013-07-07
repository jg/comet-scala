trait NumberSource extends Source {
  this: AggregationMethod =>
  
  type IssueId = AppearanceNumber
  def appearanceNumber: AppearanceNumber
  def isRipe: Boolean
}
