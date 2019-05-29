package io.neal

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterAll

class ArgsTest extends FlatSpec with BeforeAndAfterAll {

  private val flagLog = "l"
  private val flagLogType = ""
  private val flagPort = "p"
  private val flagPortType = "#"
  private val flagDir = "d"
  private val flagDirType = "*"
  private val flagInvalid = "3"
  private val flags = Map((flagLog, flagLogType), (flagPort, flagPortType), (flagDir, flagDirType))
  private val schema = flags.map(x => x._1 + x._2).mkString(",")

  private val minusSign = "-"
  private val port = 8080
  private val dir = "/usr/log"
  private val argsValid1 = Array(s"$minusSign$flagLog", s"$minusSign$flagPort", s"$port", s"$minusSign$flagDir", s"$dir")
  private val argsInvalid1 = Array(s"$minusSign")

//  private var argsParser: Args = _
//  override def beforeAll(): Unit = {
//    argsParser = new Args(schema, argsValid1)
//  }
//
//  override def afterAll(): Unit = {
//    argsParser = null
//  }

  "Specify valid flag key" should "get flay type" in {
    val argsParser = new Args(schema)
    assertResult(Some(flagLogType))(argsParser.querySchemaBy(flagLog))
    assertResult(Some(flagDirType))(argsParser.querySchemaBy(flagDir))
    assertResult(Some(flagPortType))(argsParser.querySchemaBy(flagPort))
  }

  "Specify invalid flag key" should "get none" in {
    val argsParser = new Args(schema)
    assertResult(None)(argsParser.querySchemaBy(flagInvalid))
  }

  "Specify log flag, and query value" should "be returned with true" in {
    val argsParser = new Args(schema, argsValid1)
    assertResult(Some(true))(argsParser.queryArgBy(flagLog))
  }

}
