object FizzBuzzFromSemanticModel {

  val FIZZ_KEY = 3
  val BUZZ_KEY = 5
  val FIZZ_STR = "Fizz"
  val BUZZ_STR= "Buzz"

  type MATCHER = Int => Boolean
  type ACTION = Int => String
  type RULE = Int => String

  def times(n: Int): MATCHER = {
    m: Int => m % n == 0
  }

  def contains(n: Int): MATCHER = {
    m: Int => m.toString.contains(n.toString)
  }

  def always(): MATCHER = {
    _: Int => true
  }

  def to(s: String): ACTION = {
    _: Int => s
  }

  def nop(): ACTION = {
    m: Int => m.toString
  }

  def atom(m: MATCHER, a: ACTION): RULE = {
    n: Int => if (m(n)) a(n) else ""
  }

  def all(rules: List[RULE]): RULE = {
    n: Int => rules.map(r => r(n)).mkString
  }

  def any(rules: List[RULE]): RULE = {
    n: Int => rules.map(r => r(n)).find(_.nonEmpty).getOrElse("")
  }

  def spec(): RULE = {
    val divisibleByFizzKey = atom(times(FIZZ_KEY), to(FIZZ_STR))
    val divisibleByBuzzKey = atom(times(BUZZ_KEY), to(BUZZ_STR))
    val containFizzKey = atom(contains(FIZZ_KEY), to(FIZZ_STR))
    val containBuzzKey = atom(contains(BUZZ_KEY), to(BUZZ_STR))
    val default = atom(always(), nop())
    val anyForFizzKey = any(List(divisibleByFizzKey, containFizzKey))
    val anyForBuzzKey = any(List(divisibleByBuzzKey, containBuzzKey))
    val allForFizzBuzz = all(List(anyForFizzKey, anyForBuzzKey))
    any(List(allForFizzBuzz, default))
  }

  def outputFor(n: Int): String = {
    val saying = spec()
    saying(n)
  }

}

