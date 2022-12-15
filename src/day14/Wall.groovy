package day14


class Wall {
	
	Range xRange
	
	Range depthRange
	
	boolean isBlocked(int x, int depth) {
		return (x in xRange) && (depth in depthRange )
	}
	
	int getMaxDepthLevel() {
		return depthRange.to
	}
	
	boolean throwsShadow() {
		return xRange.size()>2
	}
	
	boolean precedentTo(Wall wall) {
		if (this.xRange.size()==1) {
			return false
		}
		if (wall.xRange.size()==1) {
			return false
		}
		return (this.depthRange.from == wall.depthRange.from && this.xRange.to == wall.xRange.from)
	}
	
	boolean nextTo(Wall wall) {
		if (this.xRange.size()==1) {
			return false
		}
		if (wall.xRange.size()==1) {
			return false
		}
		return (this.depthRange.from == wall.depthRange.from && wall.xRange.to == this.xRange.from)
	}
	
	Range getShadowRange() {
		return (xRange.from+1..xRange.to-1)
	}
	
	String toString() {
		return "X: ${xRange} - D: $depthRange"
	}
}
