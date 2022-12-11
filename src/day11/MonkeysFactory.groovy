package day11

class MonkeysFactory {
	
	def parse(File input) {
		
		def monkeys = []
		Iterator inputIterator = input.iterator()
		while (inputIterator.hasNext()) {
			monkeys << parseMonkey(inputIterator)
		}
		return monkeys
	}
	
	private Monkey parseMonkey(Iterator iterator) {
		Monkey monkey = new Monkey()
		String currentLine = iterator.next()
		while (!currentLine) {
			currentLine = iterator.next()
		}
		
		def header = currentLine.trim().tokenize(" :")
		monkey.id = (header[1] as Integer)
		
		currentLine = iterator.next()
		currentLine -= "Starting items:"
		def items = currentLine.trim().tokenize(" ,")
		monkey.items = items.collect{it as Integer}
		
		currentLine = iterator.next()
		currentLine -="Operation: new ="
		def operation = currentLine.trim().tokenize(" ")
		monkey.operation = new Operation(operation[0], operation[1], operation[2])
		
		Test test = new Test()
		currentLine = iterator.next()
		def testDivision = currentLine.tokenize(" ")
		test.divisible = testDivision[-1] as Integer
		
		currentLine = iterator.next()
		def whenTrue = currentLine.tokenize(" ")
		test.whenTrue = whenTrue[-1] as Integer
		
		currentLine = iterator.next()
		def whenFalse = currentLine.tokenize(" ")
		test.whenFalse = whenFalse[-1] as Integer
		
		monkey.test = test
		
		return monkey
	}
	
}
