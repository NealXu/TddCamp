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

  private var argsParser: Args = _

  private val args = Array("-l", "-p 8080", "-d /user/log")

  override def beforeAll(): Unit = {
    argsParser = new Args(schema, args)
  }

  override def afterAll(): Unit = {
    argsParser = null
  }

  "Specify valid flag key" should "get flay type" in {
    assertResult(Some(flagLogType))(argsParser.querySchemaBy(flagLog))
    assertResult(Some(flagDirType))(argsParser.querySchemaBy(flagDir))
    assertResult(Some(flagPortType))(argsParser.querySchemaBy(flagPort))
  }

  "Specify invalid flag key" should "get none" in {
    assertResult(None)(argsParser.querySchemaBy(flagInvalid))
  }


}
