object FizzBuzz {

  val FIZZ_KEY = 3
  val BUZZ_KEY = 5
  val FIZZ_STR = "Fizz"
  val BUZZ_STR= "Buzz"

  private val divisibleByThree = divisible(FIZZ_KEY)(_)
  private val divisibleByFive = divisible(BUZZ_KEY)(_)
  private val includeThree = include(FIZZ_KEY)(_)
  private val includeFive = include(BUZZ_KEY)(_)

  type RULE = (List[PREDICATE], String)
  type PREDICATE = Int => Boolean
  private val rules = List[RULE](
    (List(divisibleByThree, includeThree), FIZZ_STR),
    (List(divisibleByFive, includeFive), BUZZ_STR)
  )

  /**
    * show fizzbuzz string list for numbers which in specified range
    * @param maxNumber max number to show
    */
  def show(maxNumber: Int): Unit = {
    (1 to maxNumber).foreach(n => println(outputFor(n)))
  }

  /**
    * check and output fizzbuzz result for a number
    *
    * @param number number to check
    * @return
    */
  def outputFor(number: Int): String = {
    val result = rules.map {
      rule =>
        val predicates = rule._1
        val output = rule._2
        if (predicates.exists(p => p(number))) output else ""
    }.mkString

    if (result.nonEmpty) result else number.toString
  }

  private def divisible(divisor: Int)(dividend: Int): Boolean = {
    dividend % divisor == 0
  }

  private def include(targetNumber: Int)(sourceNumber: Int): Boolean = {
    toDigits(sourceNumber).contains(targetNumber)
  }

  private def toDigits(number: Int, tmp: List[Int] = Nil): List[Int] = {
    if (number <= 0) return tmp
    val l = (number % 10) :: tmp
    toDigits(number / 10, l)
  }

}
