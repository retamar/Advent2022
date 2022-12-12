package day12

class AreaMap {
	
	int mapWidth
	
	int mapLenght
	
	Coord[][] map
		
	
	
	void print() {
		for (int i=0; i<mapLenght; i++) {
			for (int j=0; j<mapWidth; j++) {
				print map[j][i].height
			}
			println ""
		}
	}
	
	void setCoord(Coord coord) {
		map[coord.x][coord.y]=coord
	}
	
	void resetCoords() {
		for (int i=0; i<mapLenght; i++) {
			for (int j=0; j<mapWidth; j++) {
				map[j][i].minDistanceFromSource = Integer.MAX_VALUE
			}
		}
	}
	
	def findAllCoordsWithHeight(def height) {
		def result = []
		for (int i=0; i<mapLenght; i++) {
			for (int j=0; j<mapWidth; j++) {
				if (map[j][i].height == height) {
					result << map[j][i]
				}
			}
		}
		return result
	}
	
	def getOrthogonalMovesFrom(Coord coord) {
		
		
		def moves = []
		if (coord.x < mapWidth-1) {
			Coord right = map[coord.x+1][coord.y]
			if (coord.canMoveTo(right)) {
				moves << right
			}
		}
		if (coord.y < mapLenght-1) {
			Coord down = map[coord.x][coord.y+1]
			if (coord.canMoveTo(down)) {
				moves << down
			}
		}
		if (coord.x > 0) {
			Coord left = map[coord.x-1][coord.y]
			if (coord.canMoveTo(left)) {
				moves << left
			}
		}
		
		if (coord.y > 0) {
			Coord up = map[coord.x][coord.y-1]
			if (coord.canMoveTo(up)) {
				moves << up
			}
		}
		
		return moves
	}
}
