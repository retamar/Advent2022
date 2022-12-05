package day5

class Ship {
		
	def decks = []	
	
	Ship(int decks) {
		(1..decks).each {
			this.decks << new Stack()
		}
	}
	
	void pushToDeck(int deck, def goodToStack) {
		def deckStack= this.decks.get(deck)
		deckStack.push(goodToStack)
	}
	
	String toString() {
		StringBuffer sb = new StringBuffer()
		decks.each {
			sb.append(it)
			sb.append("\n")
		}
		return sb
	}
	
}
