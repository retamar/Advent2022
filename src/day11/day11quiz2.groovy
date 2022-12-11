package day11

int rounds = 10000


def monkeys = []
def divider = []
File input = "docs/day11.txt" as File
MonkeysFactory monkeysFactory = new MonkeysFactory(boredMonkeyClosure:{it % divider[0]})
monkeys = monkeysFactory.parse(input)

int dividerValue = 1;
monkeys.each { 
	dividerValue = dividerValue*it.test.divisible
}
divider[0] = dividerValue

println divider[0]
println monkeys

(1..rounds).each{int round ->
	monkeys.each {Monkey monkey ->
		monkey.doMonkeyTurns(monkeys)
	}
	if (round == 1) {
		println "ROUND $round"
		println monkeys.inspectionTimes
	}
	if (round == 20) {
		println "ROUND $round"
		println monkeys.inspectionTimes
	}
	if (round % 1000 == 0) {
		println "ROUND $round"
		println monkeys.inspectionTimes
		println monkeys.items
	}
}

def mostActiveMonkeys = monkeys.sort{it.inspectionTimes}
println monkeys.inspectionTimes
double monkeyBusinessLevel = (mostActiveMonkeys[-1].inspectionTimes as Double) * (mostActiveMonkeys[-2].inspectionTimes as Double)
println monkeyBusinessLevel

