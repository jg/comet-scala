trait Source {
  /** Source requires an AggregationMethod */
  this: AggregationMethod =>

  def process(data: AggregationData): Seq[JpgFile] = ???

  def name: String = ???

  def issues: Seq[Issue] = ???

  lazy val agent: MechanizeAgent = new MechanizeAgent()
}

