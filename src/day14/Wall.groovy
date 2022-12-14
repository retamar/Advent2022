package day14

class Wall {
	
	Range xRange
	
	Range depthRange
	
	boolean isBlocked(int x, int depth) {
		return (x in xRange) && (depth in depthRange )
	}
	
	String toString() {
		return "X: ${xRange} - D: $depthRange"
	}
}
