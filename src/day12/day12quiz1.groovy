package day12


def SPECIAL_CHARACTERS_HEIGHT_MAP = ["S":"a", "E":"z"]

File input = "docs/day12.txt" as File
def mapAsText = input.text.readLines()

AreaMap map = new AreaMap()

map.mapWidth = mapAsText[0].size()
map.mapLenght = mapAsText.size()

map.map =  new String[map.mapWidth][map.mapLenght]
Coord startingPoint;
Coord endPoint;


mapAsText.eachWithIndex { line, yIndex->
	line.eachWithIndex { cell, xIndex ->
		
		def height = SPECIAL_CHARACTERS_HEIGHT_MAP.get(cell) ?: cell
		map.map[xIndex][yIndex] = height
		
		if (cell == "S") {
			startingPoint = new Coord(xIndex, yIndex, height)
		}
		
		if (cell == "E") {
			endPoint = new Coord(xIndex, yIndex, height)
		}
	}
}


map.print()

def visitedNodes = [] as Set
PriorityQueue openSolutions = new PriorityQueue({def path1, def path2 ->
	if ((path1.lastStep in visitedNodes) && !(path2.lastStep in visitedNodes)) {
		return 1
	}
	if (!(path1.lastStep in visitedNodes) && (path2.lastStep in visitedNodes)) {
		return -1
	}
	return path1.distanceTo(endPoint) <=> path2.distanceTo(endPoint)
} as Comparator)
PriorityQueue closedSolutions = new PriorityQueue({def path1, def path2 ->
	return path1.distance <=> path2.distance
} as Comparator)


openSolutions.add(new Path(startingPoint))


while (openSolutions) {
	
	Path workingSolution = openSolutions.poll()
	Coord lastStep = workingSolution.lastStep
	
	if (closedSolutions) {
		Path shortestPath = closedSolutions.peek()
		if (shortestPath.distance <= workingSolution.distance + lastStep.distanceTo(endPoint)) {
			continue
		}
	}
	
	
	if (!(lastStep in visitedNodes)) {
		visitedNodes << lastStep
	}
	
	println "${lastStep.distanceTo(endPoint)} N: ${visitedNodes.size()}"
	if (closedSolutions) {
		println "S: ${closedSolutions.peek().distance}"
	}
	
	def moves = map.getOrthogonalMovesFrom(lastStep)
	def unvisitedMoves = moves.findAll{!(it in visitedNodes)}
	def visitedMoves = moves.findAll{it in visitedNodes}
	
	(unvisitedMoves+visitedMoves).each {step ->
		
		if (workingSolution.hasVisited(step)) {
			return
		}
		
		
		Path nextPath = workingSolution.fork(step)
		if (nextPath.hasVisited(endPoint)) {
			closedSolutions.add(nextPath)
		} else {
			openSolutions.add(nextPath)
		}
				
	}

	
	
	//println "VISITED NODES ${visitedNodes.size()} DE ${map.mapWidth * map.mapLenght} - OPEN SOLUTIONS: ${openSolutions.size()} - FOUND PATHS: ${closedSolutions.size()}"
}

println closedSolutions

println "SHORTEST DISTANCE ${closedSolutions.poll().distance}"



