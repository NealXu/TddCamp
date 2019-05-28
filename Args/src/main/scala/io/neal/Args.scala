package io.neal

class Args(schema: String, args: Array[String] = Array.empty) {

  private lazy val internalSchema = {
    val items = schema.split(",").map(_.trim).filter(_.nonEmpty)
    val pattern = "([a-zA-Z])(.*)".r
    items.map {
      case pattern(k, v) => (k, v)
      case s: String =>
        println(s"invalid flag: $s")
        ("", "")
    }.filter(_._1.nonEmpty).toMap
  }

  def querySchemaBy(key: String): Option[String] = {
    internalSchema.get(key)
  }

}
