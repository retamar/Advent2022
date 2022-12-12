package day12

class Coord {
	
	def HEIGHT_RANGE = ('a'..'z')+('A'..'Z')
	int CLIMB_SKILL = 1
	
	int x
	int y
	def height
	
	def shortestPath = []
	
	Coord(int x, int y, def height) {
		super()
		this.x = x
		this.y = y
		this.height = height
	}
	
	int translateHeight() {
		return HEIGHT_RANGE.indexOf(height) 
	}
	
	int distanceTo(Coord coord) {
		Math.abs(this.x-coord.x)+Math.abs(this.y-coord.y)
	}
	
	boolean canMoveTo(Coord coord) {
		return this.translateHeight()+CLIMB_SKILL >= coord.translateHeight()
	}
	
	boolean equals(Object other) {
		if (!other instanceof Coord) {
			return false
		}
		
		return x==other.x && y == other.y 
	}
	
	public int hashCode() {
		return [x, y].hashCode()	
	}
	
	String toString() {
		return "[$x, $y -> $height]"
	}
		
}


