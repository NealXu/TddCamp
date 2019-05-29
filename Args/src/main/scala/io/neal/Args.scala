package io.neal

import scala.collection.mutable

class Args(schema: String, args: Array[String] = Array.empty) {

  private lazy val parsedArgs = {
    val pattern = "-([a-zA-Z])".r
    val flagsIdx = args.zipWithIndex.filter(x => pattern.findFirstMatchIn(x._1).isDefined).map(_._2)
    val result = new mutable.HashMap[String, Any]()
    for (idx <- flagsIdx) {
      args(idx) match {
        case pattern(k) =>
          val value = querySchemaBy(k)
          if (value.isEmpty) {
            println(s"invalid flag: -$k")
          } else {
            value.get match {
              case "" => result += k -> true
              case "#" => result += k -> args(idx + 1)
              case "*" => result += k -> args(idx + 1)
            }
          }
        case s: String => println(s"invalid flag: $s")
      }
    }

    result
  }

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

  def queryArgBy(key: String): Option[Any] = {
    parsedArgs.get(key)
  }

}
