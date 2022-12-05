package day5

class FileParser {
	
	File file;
	
	Ship ship;
	
	
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
		//parseMoves(movesDefinition)
		
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
	
}
