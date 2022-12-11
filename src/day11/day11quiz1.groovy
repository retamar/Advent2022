package day11

int rounds = 20


def monkeys = []
File input = "docs/day11test.txt" as File
MonkeysFactory monkeysFactory = new MonkeysFactory()
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


