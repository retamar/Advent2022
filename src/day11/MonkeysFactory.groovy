package day11

class MonkeysFactory {
	
	
	Closure boredMonkeyClosure;
			

	def parse(File input) {
		
		def monkeys = []
		Iterator inputIterator = input.iterator()
		while (inputIterator.hasNext()) {
			monkeys << parseMonkey(inputIterator)
		}
		return monkeys
	}
	
	private Monkey parseMonkey(Iterator iterator) {
		Monkey monkey = new Monkey(boredMonkeyClosure:boredMonkeyClosure)
		loadHeader(monkey, iterator)
		loadItems(monkey, iterator)
		loadOperation(monkey, iterator)
		loadTest(monkey, iterator)
		
		
		return monkey
	}
	
	private void loadHeader(Monkey monkey, Iterator iterator) {
		
		String currentLine = readNextLine(iterator)
		def header = currentLine.trim().tokenize(" :")
		monkey.id = (header[1] as Integer)
		
	}
	
	private void loadItems(Monkey monkey, Iterator iterator) {
		String currentLine = readNextLine(iterator)
		currentLine -= "Starting items:"
		def items = currentLine.trim().tokenize(" ,")
		monkey.items = items.collect{it as Integer}		
	}
	
	private void loadOperation(Monkey monkey, Iterator iterator) {
		String currentLine = iterator.next()
		currentLine -="Operation: new ="
		def operation = currentLine.trim().tokenize(" ")
		monkey.operation = new Operation(operation[0], operation[1], operation[2])
	}
	
	private void loadTest(Monkey monkey, Iterator iterator) {
		Test test = new Test()
		String currentLine = iterator.next()
		def testDivision = currentLine.tokenize(" ")
		test.divisible = testDivision[-1] as Integer
		
		currentLine = iterator.next()
		def whenTrue = currentLine.tokenize(" ")
		test.whenTrue = whenTrue[-1] as Integer
		
		currentLine = iterator.next()
		def whenFalse = currentLine.tokenize(" ")
		test.whenFalse = whenFalse[-1] as Integer
		
		monkey.test = test
	}
	
	private String readNextLine(Iterator iterator) {
		String currentLine = iterator.next()
		while (!currentLine) {
			currentLine = iterator.next()
		}
		return currentLine
	}
	
}
