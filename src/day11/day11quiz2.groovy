package day11

int rounds = 10000


def monkeys = []
def divider = 1
File input = "docs/day11.txt" as File
MonkeysFactory monkeysFactory = new MonkeysFactory(boredMonkeyClosure:{it % divider})
monkeys = monkeysFactory.parse(input)


monkeys.each { 
	divider = divider*it.test.divisible
}


println monkeys

(1..rounds).each{int round ->
	monkeys.each {Monkey monkey ->
		monkey.doMonkeyTurns(monkeys)
	}

}

def mostActiveMonkeys = monkeys.sort{it.inspectionTimes}
println monkeys.inspectionTimes
double monkeyBusinessLevel = (mostActiveMonkeys[-1].inspectionTimes as Double) * (mostActiveMonkeys[-2].inspectionTimes as Double)
println monkeyBusinessLevel

