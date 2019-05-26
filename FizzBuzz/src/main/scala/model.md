## Rules
```
    atom1: times(3) -> Fizz
    atom2: times(5) -> Buzz
    atom3: contains(3) -> Fizz
    atom4: contains(5) -> Buzz
    atom5: others -> number

    any1: times(3) || contains(3) -> Fizz
    any2: times(5) || contains(5) -> Buzz

    all1: any1 && any2 -> FizzBuzz

    any3: all1 || atom5
```
## Semantics
```

type MATCHER = Int => Boolean
type ACTION = Int => String
type RULE = Int => String

rule ::= atom | all | any

atom: (matcher, action) -> string
all: rule1 && rule2 ... 
any: rule1 || rule2 ... 
```