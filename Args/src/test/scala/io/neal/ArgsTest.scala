package io.neal

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterAll

class ArgsTest extends FlatSpec with BeforeAndAfterAll {

  private val flags = List(("l", ""), ("p", "#"), ("d", "*"))
  private val schema = flags.map(x => x._1 + x._2).mkString(",")

  private var args: Args = _

  override def beforeAll(): Unit = {
    args = new Args(schema)
  }

  override def afterAll(): Unit = {
    args = _
  }

  "Specify valid flag key" should "get flay type" in {
    assertResult(Some(""))(args.querySchemaBy("l"))
    assertResult(Some("#"))(args.querySchemaBy("p"))
    assertResult(Some("*"))(args.querySchemaBy("d"))
  }

  "Specify invalid flag key" should "get none" in {
    assertResult(None)(args.querySchemaBy("a"))
  }


}
