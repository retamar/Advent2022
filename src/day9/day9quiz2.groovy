package day9


def knots = []
(0..9).each {
	knots << new Point(0,0)
}
def getHead = {
	knots[0]
}

def getTail = {
	knots[9]
}

def tailPositions = [getTail()]

def moveHeadUp = {
	knots[0] = new Point(getHead().x, getHead().y+1)
}

def moveHeadDown = {
	knots[0] = new Point(getHead().x, getHead().y-1)
}

def moveHeadLeft = {
	knots[0] = new Point(getHead().x-1, getHead().y)
}

def moveHeadRight = {
	knots[0] = new Point(getHead().x+1, getHead().y)
}


def COMMANDS = ["R":moveHeadRight, "U":moveHeadUp, "L":moveHeadLeft, "D":moveHeadDown]

def moveOrthogonallyTowardsPoint = {int knot, int follower ->
	knots[follower] = knots[knot].getClosestOrthogonalAdjacent(knots[follower])
}

def moveDiagonallyTowardsPoint = {int knot, int follower ->
	knots[follower] = knots[knot].getClosestDiagonalAdjacent(knots[follower])
}


def moveKnot = {int position ->
	Point prev = knots[position-1]
	Point knot = knots[position]
	
	if (prev == knot) {
		return
	}
	
	if (prev.isAdjacent(knot)) {
		return
	}
	
	def adjacentsToPrev = prev.getAdjacents()
	def adjacentsToKnot = knot.getAdjacents()
	def candidatePositions = adjacentsToKnot.intersect(adjacentsToPrev)
	

	knots[position] = candidatePositions.sort{it.distanceTo(prev)}.get(0)

}

def moveRope = {
	(1..9).each {knot ->
		moveKnot(knot)
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
		moveRope()
		tailPositions << getTail()
		println knots
	}
	
}

def uniqueTailPositions = tailPositions as Set
println uniqueTailPositions

println uniqueTailPositions.size()
