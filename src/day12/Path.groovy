package day12

class Path {
	
	def steps = []
	
	Path(Path path) {
		super()
		this.steps = [] + path.steps
	}
	
	Path(Coord startingPoint) {
		super()
		this.steps << startingPoint
	}
	
	int getDistance() {
		return this.steps.size()-1
	}
	
	int distanceTo(Coord target) {
		return getLastStep().distanceTo(target)
	}
	
	void addStep(Coord step) {
		this.steps << step
	} 
	
	Coord getLastStep() {
		return steps[-1]
	}
	
	Path fork(Coord nextStep) {
		Path forked = new Path(this)
		forked.addStep(nextStep)
		return forked
	}
	
	boolean hasVisited(Coord step) {
		steps.find{step.x==it.x && step.y==it.y}
	}
	
	String toString() {
		return "PATH: ${steps.toString()}"
	}
	
}
