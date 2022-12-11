package day11

class Monkey {
	
	int id
	
	def items = []
	
	Operation operation
	
	Test test
	
	int currentWorryLevel
	
	void doMonkeyTurns(def otherMonkeys) {
		while (items) {
			int itemDestination = doTurn()
			otherMonkeys[itemDestination].receiveItem(this.currentWorryLevel)
		}
	}
	
	void receiveItem(int item) {
		this.items << item
	}
	
	private int doTurn() {
		this.currentWorryLevel = takeItem()
		this.currentWorryLevel = worry(this.currentWorryLevel)
		this.currentWorryLevel = bore(this.currentWorryLevel)
		return send(this.currentWorryLevel)
	}
	
	
	private Integer takeItem() {
		return (items) ? items.remove(0) : null
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
	
	String toString() {
		return "Monkey $id - Items: $items - Operation: $operation - Test: $test - WorryLevel $currentWorryLevel"
	}
}
