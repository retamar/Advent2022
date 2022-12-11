package day11

int rounds = 10000


def monkeys = []
File input = "docs/day11test.txt" as File
MonkeysFactory monkeysFactory = new MonkeysFactory(worrinessDivider: 1)
monkeys = monkeysFactory.parse(input)

println monkeys

(1..rounds).each{int round ->
	println "ROUND $round"
	monkeys.each {Monkey monkey ->
		println "Turn ${monkey.id}"
		monkey.doMonkeyTurns(monkeys)
	}
	println monkeys.items
}

def mostActiveMonkeys = monkeys.sort{it.inspectionTimes}
println monkeys.inspectionTimes
double monkeyBusinessLevel = mostActiveMonkeys[-1].inspectionTimes * mostActiveMonkeys[-2].inspectionTimes
println monkeyBusinessLevel

