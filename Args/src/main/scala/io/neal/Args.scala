package io.neal

class Args(schema: String, args: Array[String]) {

  private lazy val internalSchema = {
    val items = schema.split(",").map(_.trim).filter(_.nonEmpty)
    val pattern = "([a-zA-Z])(.*)".r
    items.map{i =>
      val pattern(key, value) = i
      (key, value)
    }.toMap
  }

  def this(schema: String) = {
    this(schema, Array.empty)
  }

}
