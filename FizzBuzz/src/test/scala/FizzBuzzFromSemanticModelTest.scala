import org.scalatest.FlatSpec

class FizzBuzzFromSemanticModelTest extends FlatSpec {

  s"if the number is divisible by three, ${FizzBuzzFromSemanticModel.FIZZ_STR}}" should s"be output" in {
    val number = 33
    assertResult(s"${FizzBuzzFromSemanticModel.FIZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  s"if the number is divisible by five, ${FizzBuzzFromSemanticModel.FIZZ_STR}}" should s"be output" in {
    val number = 55
    assertResult(s"${FizzBuzzFromSemanticModel.BUZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  s"if the number is divisible by three and five, ${FizzBuzzFromSemanticModel.FIZZ_STR}${FizzBuzzFromSemanticModel.BUZZ_STR}}" should s"be output" in {
    val number = 3 * 5 * 11
    assertResult(s"${FizzBuzzFromSemanticModel.FIZZ_STR}${FizzBuzzFromSemanticModel.BUZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  s"if the number includes digit three, ${FizzBuzzFromSemanticModel.FIZZ_STR}" should "be output" in {
    val number = 173
    assertResult(s"${FizzBuzzFromSemanticModel.FIZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  s"if the number includes digit five, ${FizzBuzzFromSemanticModel.BUZZ_STR}" should "be output" in {
    val number = 511
    assertResult(s"${FizzBuzzFromSemanticModel.BUZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  s"if the number includes digit three and five, ${FizzBuzzFromSemanticModel.FIZZ_STR}${FizzBuzzFromSemanticModel.BUZZ_STR}" should "be output" in {
    val number = 503
    assertResult(s"${FizzBuzzFromSemanticModel.FIZZ_STR}${FizzBuzzFromSemanticModel.BUZZ_STR}")(FizzBuzzFromSemanticModel.outputFor(number))
  }

  "others, the number" should s"be output" in {
    val number = 7 * 7
    assertResult(s"${number.toString}")(FizzBuzzFromSemanticModel.outputFor(number))
  }
}
