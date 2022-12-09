package day9


Rope rope = new Rope(1)

def moveHeadUp = {
	rope.moveRopeUp()
}

def moveHeadDown = {
	rope.moveRopeDown()
}

def moveHeadLeft = {
	rope.moveRopeLeft()
}

def moveHeadRight = {
	rope.moveRopeRight()
}


def COMMANDS = ["R":moveHeadRight, "U":moveHeadUp, "L":moveHeadLeft, "D":moveHeadDown]



File input = "docs/day9.txt" as File
input.eachLine { line ->
	
	def instruction = line.tokenize(" ")
	def command = instruction[0]
	int moves = instruction[1] as Integer
	println "$command  - $moves"
	(1..moves).each {
		COMMANDS[command].call()
	}
	
}

def uniqueTailPositions = rope.tailPositions as Set
println uniqueTailPositions

println uniqueTailPositions.size()
