package io.neal

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfterAll

class ArgsTest extends FlatSpec with BeforeAndAfterAll {

  private val schema = "l,p#,d*"

  private var instance: Args = _

  override def beforeAll(): Unit = {
    instance = new Args(schema)
  }


}
