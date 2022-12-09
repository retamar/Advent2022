package day9

class Rope {

	def knots = []

	def tailPositions = []

	Rope(int knots) {
		(0..knots).each {
			this.knots << new Point(0,0)
		}
		tailPositions<<getTail()
	}

	Point getHead() {
		knots[0]
	}

	Point getTail() {
		knots[-1]
	}


	void moveRopeUp() {
		moveHeadUp()
		followRope()
	}

	void moveRopeDown() {
		moveHeadDown()
		followRope()
	}

	void moveRopeLeft() {
		moveHeadLeft()
		followRope()
	}

	void moveRopeRight() {
		moveHeadRight()
		followRope()
	}


	private void moveHeadUp() {
		knots[0] = new Point(getHead().x, getHead().y+1)
	}

	private void moveHeadDown() {
		knots[0] = new Point(getHead().x, getHead().y-1)
	}

	private void moveHeadLeft() {
		knots[0] = new Point(getHead().x-1, getHead().y)
	}

	private void moveHeadRight() {
		knots[0] = new Point(getHead().x+1, getHead().y)
	}

	private void followRope() {
		(1..knots.size()-1).each {knot ->
			moveKnot(knot)
		}
		tailPositions << getTail()
	}
	
	
	private void moveKnot(int position) {
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
}
