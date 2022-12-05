package day5

class Ship {
		
	def decks = []	
	
	Ship(int decks) {
		(1..decks).each {
			this.decks << new Stack()
		}
	}
	
	
	def unload(int deck) {
		def deckStack= this.decks.get(deck-1)
		return deckStack.pop()
	}
	
	def unloadPacked(int deck, int packetSize) {
		def packet = new Stack()
		(1..packetSize).each {
			packet.push(unload(deck))
		}
		return packet
	}
	
	def loadPacked(int deck, Stack packet) {
		while (!packet.isEmpty()) {
			load(deck, packet.pop())
		}
	}
	
	void load(int deck, def goodToStack) {
		
		if (!goodToStack.trim()) {
			return
		}		
		def deckStack= this.decks.get(deck-1)
		deckStack.push(goodToStack)
	}
	
	def getOnTop() {
		decks*.peek()		
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
