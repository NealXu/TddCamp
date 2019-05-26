import org.scalatest.FlatSpec

class FizzBuzzTest extends FlatSpec {

  s"if the number is divisible by three, ${FizzBuzz.FIZZ_STR}}" should s"be output" in {
    val number = 33
    assertResult(s"${FizzBuzz.FIZZ_STR}")(FizzBuzz.outputFor(number))
  }

  s"if the number is divisible by five, ${FizzBuzz.FIZZ_STR}}" should s"be output" in {
    val number = 55
    assertResult(s"${FizzBuzz.BUZZ_STR}")(FizzBuzz.outputFor(number))
  }

  s"if the number is divisible by three and five, ${FizzBuzz.FIZZ_STR}${FizzBuzz.BUZZ_STR}}" should s"be output" in {
    val number = 3 * 5 * 11
    assertResult(s"${FizzBuzz.FIZZ_STR}${FizzBuzz.BUZZ_STR}")(FizzBuzz.outputFor(number))
  }


  s"if the number includes digit three, ${FizzBuzz.FIZZ_STR}" should "be output" in {
    val number = 173
    assertResult(s"${FizzBuzz.FIZZ_STR}")(FizzBuzz.outputFor(number))
  }

  s"if the number includes digit five, ${FizzBuzz.BUZZ_STR}" should "be output" in {
    val number = 511
    assertResult(s"${FizzBuzz.BUZZ_STR}")(FizzBuzz.outputFor(number))
  }

  s"if the number includes digit three and five, ${FizzBuzz.FIZZ_STR}${FizzBuzz.BUZZ_STR}" should "be output" in {
    val number = 503
    assertResult(s"${FizzBuzz.FIZZ_STR}${FizzBuzz.BUZZ_STR}")(FizzBuzz.outputFor(number))
  }

  "FizzBuzz show" should "be as expected" in {
    FizzBuzz.show(100)
  }

  "others, the number" should s"be output" in {
    val number = 7 * 7
    assertResult(s"${number.toString}")(FizzBuzz.outputFor(number))
  }
}
