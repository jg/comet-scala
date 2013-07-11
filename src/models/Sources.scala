object Sources {
  val sourceList = List(
    new ZwanzigMinutenZuerich())

  def getSourceInstanceByType(t: String): Option[Source] = sourceList.find(_.sourceType == t)
}
