import org.joda.time.DateTime

object Date {
  def now: Date = new Date(new DateTime())

  def apply(s: String) = new Date(DateTime.parse(s))
}

class Date(d: DateTime) {
  /** Available formats http://joda-time.sourceforge.net/apidocs/org/joda/time/format/DateTimeFormat.html */
  def strftime(fmt: String): String = d.toString(fmt)
}
