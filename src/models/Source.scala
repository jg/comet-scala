trait Source {
  /** Source requires an AggregationMethod */
  this: AggregationMethod =>

  var id: Long = -1
  var name: String = ""

  /** Use to identify the piece of code responsible for source aggregation */
  def sourceType: String

  def process(data: AggregationData): Seq[JpgFile] = ???
}

object Source {
  val sourceList = List(new ZwanzigMinutenZuerich())

  def fromDBTuple(id: Long, name: String, sourceType: String): Option[Source] = {
    getSourceInstanceByType(sourceType) match {
      case Some(source) => {
        source.id = id
        source.name = name
        Some(source)
      }
      case None => None
    }
  }

  def getSourceInstanceByType(t: String): Option[Source] = sourceList.find(_.sourceType == t)

  def apply(id: Long, name: String, sourceType: String): Source = {
    // This assumes we have a class with corresponding sourceType in the Database
    // Better fail at this point anyway
    val source = getSourceInstanceByType(sourceType).get
    source.id = id
    source.name = name
    source
  }

  def unapply(s: Source) = Some(s.id, s.name, s.sourceType)
}
