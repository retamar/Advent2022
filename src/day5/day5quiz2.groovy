package day5


File input = "docs/day5.txt" as File
FileParser parser = new FileParser(input)
parser.parse()

Ship ship = parser.ship
println ship

def moves = parser.moves
println moves

moves.each { move->	
	println move
	def packet = ship.unloadPacked(move.from, move.items)
	println packet
	ship.loadPacked(move.to, packet)
	println ship
}

ship.getOnTop().each { 
	print it
}
