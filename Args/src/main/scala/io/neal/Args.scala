package io.neal

class Args(schema: String, args: Array[String]) {

  private lazy val internalSchema = {
    val item = schema.split(",").map(_.trim).filter(_.nonEmpty)
    val pattern = "([a-zA-Z])(.*)".r

  }

  def this(schema: String) = {
    this(schema, Array.empty)
  }

}
