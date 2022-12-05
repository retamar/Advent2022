package day5


def ship = []
def moveList = []

def parseShipLine = {line ->
	
	int initialDataPosition = 1	
	int spanBetweenNumbers = 4
	int currentPosition = 1
	int currentDeck = 0
	
	while (currentPosition < line.size()-1) {
		def stackedGood = line[currentPosition]
		if (!stackedGood) {
			return
		} 		
		def deckStack= ship.get(currentDeck)
		deckStack.push(stackedGood)	
		currentPosition+=spanBetweenNumbers
		currentDeck++
	}	
}

def parseMoveLine = {line ->
	
}

def parseMoves = {movesDefinition ->
	
}


def parseShip = {shipDefinition ->
	
	def deckDefinition = shipDefinition[-1]
	int howManyDecksThereAre = deckDefinition[-2] as Integer
	(1..howManyDecksThereAre).each {
		ship << new Stack()
	}
	shipDefinition.each{ line->
		if (line == deckDefinition) {
			return
		}
		parseShipLine(line)		
	}
	println ship
}

def parseFile = {	
	
	File input = "docs/day5.txt" as File
	def shipDefinition = []
	def movesDefinition = []	
	input.eachLine { 
		if (it.startsWith("move")) {
			movesDefinition<<it
		} else if (it){
			shipDefinition<<it
		}		
	}
	
	parseShip(shipDefinition)
	parseMoves(movesDefinition)	
}

parseFile()
println "SHIP"
ship.each {
	println it
}


