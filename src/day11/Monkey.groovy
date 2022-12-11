package day11

class Monkey {
	
	int id
	
	def items = []
	
	Operation operation
	
	Test test
	
	int doTurn() {
		int worryLevel = takeItem()
		worryLevel = worry(worryLevel)
		worryLevel = bore(worryLevel)
		return send(worryLevel)
	}
	
	private int takeItem() {
		return items.remove(0)
	}
	
	private int worry(int item) {
		return operation.execute(item)
	}
	
	private int bore(int worryLevel) {
		return (int) worryLevel/3
	}
	
	private int send(int worryLevel) {
		return test.test(worryLevel)
	}
}
