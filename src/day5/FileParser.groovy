package day5

class FileParser {
	
	File file;
	
	Ship ship;
	
	def moves = []
	
	
	FileParser(File file) {
		this.file = file
	}
	
	void parse() {
		
		def shipDefinition = []
		def movesDefinition = []
		file.eachLine {
			if (it.startsWith("move")) {
				movesDefinition<<it
			} else if (it){
				shipDefinition<<it
			}
		}
		
		parseShip(shipDefinition)
		parseMoves(movesDefinition)
		
	}
	
	
	private void parseShip(def shipDefinition) {
				
		def deckDefinition = shipDefinition[-1]
		int howManyDecksThereAre = deckDefinition[-2] as Integer
		this.ship = new Ship(howManyDecksThereAre)		
		shipDefinition.each{ line->
			if (line == deckDefinition) {
				return
			}
			parseShipLine(line)
		}
	}
	
	private void parseShipLine(def line) {
		
		int initialDataPosition = 1
		int spanBetweenNumbers = 4
		int currentPosition = 1
		int currentDeck = 0
		
		while (currentPosition < line.size()-1) {
			def stackedGood = line[currentPosition]
			if (!stackedGood) {
				return
			}
			ship.pushToDeck(currentDeck, stackedGood)			
			currentPosition+=spanBetweenNumbers
			currentDeck++
		}
	}
	
	private void parseMoves(def movesDefinition) {
		
		movesDefinition.each {singleMoveDefinition->
			def tokenizedMove = singleMoveDefinition.split(" ")
			moves << new Move(tokenizedMove[1] as Integer, tokenizedMove[3] as Integer, tokenizedMove[5] as Integer)
		}
		
	}
	
}
