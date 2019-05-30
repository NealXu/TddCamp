package io.neal

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterAll

class ArgsTest extends FlatSpec with BeforeAndAfterAll {

  import Args._

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
  private val argsAll = Array(s"$minusSign$flagLog", s"$minusSign$flagPort", s"$port", s"$minusSign$flagDir", s"$dir")
  private val argsWithoutLogFlag = Array(s"$minusSign$flagPort", s"$port", s"$minusSign$flagDir", s"$dir")
  private val argsWithoutPortFlag = Array(s"$minusSign$flagLog", s"$minusSign$flagDir", s"$dir")
  private val argsWithoutDirFlag = Array(s"$minusSign$flagLog", s"$minusSign$flagPort", s"$port")

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
    val argsParser = new Args(schema, argsAll)
    assertResult(Some(true))(argsParser.queryArgBy(flagLog))
  }

  "Specify port flag, and query flag value" should "be returned with port number" in {
    val argsParser = new Args(schema, argsAll)
    assertResult(Some(port))(argsParser.queryArgBy(flagPort))
  }

  "Specify dir flag, and query flag value" should "be returned with dir string" in {
    val argsParser = new Args(schema, argsAll)
    assertResult(Some(dir))(argsParser.queryArgBy(flagDir))
  }

  "Not specify log flag, and query value" should "be returned with default value(false)" in {
    val argsParser = new Args(schema, argsWithoutLogFlag)
    assertResult(Some(BooleanDefault))(argsParser.queryArgBy(flagLog))
  }

  "Not specify port flag, and query value" should "be returned with default value(0)" in {
    val argsParser = new Args(schema, argsWithoutPortFlag)
    assertResult(Some(IntDefault))(argsParser.queryArgBy(flagPort))
  }

  "Not specify dir flag, and query value" should "be returned with default value(\"\")" in {
    val argsParser = new Args(schema, argsWithoutDirFlag)
    assertResult(Some(StringDefault))(argsParser.queryArgBy(flagDir))
  }
}
