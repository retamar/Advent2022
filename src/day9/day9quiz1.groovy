package day9


def head = new Point(0,0)
def tail = new Point(0,0)
def tailPositions = [tail]

def moveHeadUp = {
	head = new Point(head.x, head.y+1)
}

def moveHeadDown = {
	head = new Point(head.x, head.y-1)
}

def moveHeadLeft = {
	head = new Point(head.x-1, head.y)
}

def moveHeadRight = {
	head = new Point(head.x+1, head.y)
}

def COMMANDS = ["R":moveHeadRight, "U":moveHeadUp, "L":moveHeadLeft, "D":moveHeadDown]


def moveTailNextToHead = {
	if (!head.isAdjacent(tail)) {
		Point closestAdjacent = head.getClosestOrthogonalAdjacent(tail)
		tail = closestAdjacent
		tailPositions << closestAdjacent
	}	
}

File input = "docs/day9.txt" as File
input.eachLine { line ->
	
	def instruction = line.tokenize(" ")
	def command = instruction[0]
	int moves = instruction[1] as Integer
	println "$command  - $moves"
	(1..moves).each {
		COMMANDS[command].call()
		moveTailNextToHead()
		println "H: $head - T:$tail"
	}
}

def uniqueTailPositions = tailPositions as Set
println uniqueTailPositions

println uniqueTailPositions.size()
