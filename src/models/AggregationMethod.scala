trait AggregationMethod

/** For sources for which needs manual uploading */
trait ManualAggregation extends AggregationMethod

/** For sources in which we can somehow pick the issue we want to aggregate
 *  IssueId can be for example a Date (for DateSources) or an AppearanceNumber (for NumberSources)
 */
trait FullAggregation extends AggregationMethod {
  type IssueId
  def aggregate(id: IssueId): Option[AggregationData]
}

/** For some sources we can only grab what is available at the moment of aggregation */
trait LimitedAggregation extends AggregationMethod {
  type IssueId

  def aggregateAvailable: Option[AggregationData]
}
