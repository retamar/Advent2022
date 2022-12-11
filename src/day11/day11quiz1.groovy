package day11



def monkeys = []
File input = "docs/day11test.txt" as File
MonkeysFactory monkeysFactory = new MonkeysFactory()
monkeys = monkeysFactory.parse(input)

println monkeys