package day5

class Move {
	
	int items
	int from
	int to
	
	Move(int items, int from, int to) {
		this.items = items
		this.from = from
		this.to = to
	}
	
	String toString() {
		return "move $items from $from to $to"
	}
	
}
