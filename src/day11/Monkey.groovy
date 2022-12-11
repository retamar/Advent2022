package day11

class Monkey {
	
	int id
	
	def items = []
	
	Operation operation
	
	Test test
	
	double currentWorryLevel
	
	int inspectionTimes = 0
	
	int inspectionWorrinessDivider = 3
	
	void doMonkeyTurns(def otherMonkeys) {
		while (items) {
			int itemDestination = doTurn()
			otherMonkeys[itemDestination].receiveItem(this.currentWorryLevel)
		}
	}
	
	void receiveItem(double item) {
		this.items << item
	}
	
	private int doTurn() {
		this.inspectionTimes++
		this.currentWorryLevel = takeItem()
		this.currentWorryLevel = worry(this.currentWorryLevel)
		this.currentWorryLevel = bore(this.currentWorryLevel)
		return send(this.currentWorryLevel)
	}
	
	
	private int takeItem() {
		return items.remove(0)
	}
	
	private double worry(double item) {
		return operation.execute(item)
	}
	
	private double bore(double worryLevel) {
		return Math.floor(worryLevel/inspectionWorrinessDivider)
	}
	
	private double send(double worryLevel) {
		return test.test(worryLevel)
	}
	
	String toString() {
		return "Monkey $id - Items: $items - Operation: $operation - Test: $test - WorryLevel $currentWorryLevel"
	}
}
