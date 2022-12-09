package day9

import com.sun.org.apache.bcel.internal.generic.RETURN

class Point {
	private int x
	private int y


	Point(int x, int y) {
		this.x = x
		this.y = y
	}

	int getX() {
		return this.x
	}

	int getY() {
		return this.y
	}


	boolean equals(Object p) {
		if (!p instanceof Point) {
			return false
		}
		return p.x == this.x && p.y == this.y
	}

	public int hashCode() {
		return [x, y].hashCode()
	}

	def distanceTo (Point p) {
		def distanceSquared = Math.pow(this.y-p.y, 2) + Math.pow(this.x-p.x, 2)
		return Math.sqrt(distanceSquared)
	}

	boolean isAdjacent(Point p) {
		return p in getAdjacents()
	}
	
	def getAdjacents() {
		return getOrthogonalAdjacents() +[
			new Point(x+1, y+1),
			new Point(x-1, y-1),
			new Point(x-1, y+1),
			new Point(x+1, y-1)]
	}

	def getOrthogonalAdjacents() {
		return [
			new Point(x-1, y),
			new Point(x+1,y),
			new Point(x, y-1),
			new Point(x, y+1)]		
	}

	def getClosestOrthogonalAdjacent(Point p) {
		if (p == this) {
			return this
		}

		getOrthogonalAdjacents().sort{it.distanceTo(p)}.get(0)
	}

	
	def getClosestAdjacent(Point p) {
		if (p == this) {
			return this
		}

		getAdjacents().sort{it.distanceTo(p)}.get(0)
	}

	String toString() {
		return "[$x,$y]"
	}
}
