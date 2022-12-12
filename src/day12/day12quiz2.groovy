package day12


def SPECIAL_CHARACTERS_HEIGHT_MAP = ["S":"a", "E":"z"]

File input = "docs/day12.txt" as File
def mapAsText = input.text.readLines()

AreaMap map = new AreaMap()

map.mapWidth = mapAsText[0].size()
map.mapLenght = mapAsText.size()

map.map =  new Coord[map.mapWidth][map.mapLenght]
Coord endPoint;


mapAsText.eachWithIndex { line, yIndex->
	line.eachWithIndex { cell, xIndex ->

		def height = SPECIAL_CHARACTERS_HEIGHT_MAP.get(cell) ?: cell
		Coord coord = new Coord(xIndex, yIndex, height)
		map.setCoord(coord)

		if (cell == "E") {
			endPoint = coord
		}
	}
}


map.print()


def startingPoints = map.findAllCoordsWithHeight("a")
int paths = startingPoints.size()
int minDistance = Integer.MAX_VALUE
startingPoints.eachWithIndex { Coord startingPoint, int index ->

	PriorityQueue unsettledNodes = new PriorityQueue({Coord node1, Coord node2 ->
		return node1.minDistanceFromSource <=> node2.minDistanceFromSource
	} as Comparator)
	def settledNodes = []
	unsettledNodes.add(startingPoint)
	
	map.resetCoords()
	startingPoint.minDistanceFromSource = 0

	while (unsettledNodes) {
		Coord step = unsettledNodes.poll()
		def moves = map.getOrthogonalMovesFrom(step)
		moves.each { move->
			if (!(move in settledNodes)) {
				if (step.minDistanceFromSource + 1 < move.minDistanceFromSource) {
					move.minDistanceFromSource = step.minDistanceFromSource+1
					move.shortestPath << step
					unsettledNodes.add(move)
				}
			}
		}
		settledNodes << step
	}

	println "$startingPoint  $index of $paths"
	println endPoint.minDistanceFromSource
	
	if (endPoint.minDistanceFromSource < minDistance) {
		minDistance = endPoint.minDistanceFromSource
	}
}

println "MIN $minDistance"
