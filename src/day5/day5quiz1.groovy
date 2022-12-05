package day5


File input = "docs/day5test.txt" as File
FileParser parser = new FileParser(input)
parser.parse()

Ship ship = parser.ship
println ship

def moves = parser.moves
println moves

moves.each { move->	
	(1..move.items).each {
		println move	
		def item = ship.unload(move.from)
		println item
		ship.load(move.to, item)
		println ship
	}	
}

println ship.getOnTop()