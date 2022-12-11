package day11

class Monkey {
	
	int id
	
	def items = []
	
	Operation operation
	
	Test test
	
	BigInteger currentWorryLevel
	
	int inspectionTimes = 0
	
	def boredMonkeyClosure

	
	
	void doMonkeyTurns(def otherMonkeys) {
		while (items) {
			int itemDestination = doTurn()
			otherMonkeys[itemDestination].receiveItem(this.currentWorryLevel)
		}
	}
	
	void receiveItem(BigInteger item) {
		this.items << item
	}
	
	private int doTurn() {
		this.inspectionTimes++
		this.currentWorryLevel = takeItem()
		this.currentWorryLevel = worry(this.currentWorryLevel)
		this.currentWorryLevel = bore(this.currentWorryLevel)
		return send(this.currentWorryLevel)
	}
	
	
	private BigInteger takeItem() {
		return items.remove(0)
	}
	
	private BigInteger worry(BigInteger item) {
		return operation.execute(item)
	}
	
	private BigInteger bore(BigInteger worryLevel) {
		return boredMonkeyClosure.call(worryLevel)
	}
////			worryLevel = Math.floor(worryLevel/inspectionWorrinessDivider)
//
//		return (worryLevel % 96577)
//	}
	
	private int send(BigInteger worryLevel) {
		return test.test(worryLevel)
	}
	
	String toString() {
		return "Monkey $id - Items: $items - Operation: $operation - Test: $test - WorryLevel $currentWorryLevel"
	}
}
