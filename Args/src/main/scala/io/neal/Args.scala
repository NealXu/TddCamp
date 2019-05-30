package io.neal

import scala.collection.mutable

class Args(schema: String, args: Array[String] = Array.empty) {

  import Args._

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
              case BooleanType => result += k -> true
              case IntType => result += k -> args(idx + 1).toInt
              case StringType => result += k -> args(idx + 1)
            }
          }
        case s: String => println(s"invalid flag: $s")
      }
    }


    val noSpecifyFlags = internalSchema.keySet &~ result.keySet
    noSpecifyFlags.foreach(x => result += x -> TypeMapToDefault(internalSchema(x)))

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

object Args {

  val BooleanType = ""
  val BooleanDefault = false
  val IntType = "#"
  val IntDefault = 0
  val StringType = "*"
  val StringDefault = ""

  val TypeMapToDefault: Map[String, Any] = Map(
    BooleanType -> BooleanDefault,
    IntType -> IntDefault,
    StringType -> StringDefault
  )

}
