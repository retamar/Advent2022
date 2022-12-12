package day12

class AreaMap {
	
	int mapWidth
	
	int mapLenght
	
	String[][] map
	
	
	void print() {
		for (int i=0; i<mapLenght; i++) {
			for (int j=0; j<mapWidth; j++) {
				print map[j][i]
			}
			println ""
		}
	}
	
	
	def getOrthogonalMovesFrom(Coord coord) {
		
		
		def moves = []
		if (coord.x > 0) {
			Coord left = new Coord(coord.x-1, coord.y, map[coord.x-1][coord.y])
			if (coord.canMoveTo(left)) {
				moves << left
			}
		}
		if (coord.x < mapWidth-1) {
			Coord right = new Coord(coord.x+1, coord.y, map[coord.x+1][coord.y])
			if (coord.canMoveTo(right)) {
				moves << right
			}
		}
		if (coord.y > 0) {
			Coord up = new Coord(coord.x, coord.y-1, map[coord.x][coord.y-1])
			if (coord.canMoveTo(up)) {
				moves << up
			}
		}
		if (coord.y < mapLenght-1) {
			Coord down = new Coord(coord.x, coord.y+1, map[coord.x][coord.y+1])
			if (coord.canMoveTo(down)) {
				moves << down
			}
		}
		return moves
	}
}
