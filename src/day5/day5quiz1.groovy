package day5


File input = "docs/day5.txt" as File
FileParser parser = new FileParser(input)
parser.parse()

Ship ship = parser.ship
println ship

def moves = parser.moves
println moves

