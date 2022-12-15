package day15

class Sensor {
	
	int sensorX
	
	int sensorY
	
	
	def beacon
	

	Sensor(int sensorX, int sensorY, def beacon) {
		super()
		this.sensorX = sensorX
		this.sensorY = sensorY
		this.beacon = beacon
	}
	
	int getDistanceToBeacon() {		
		return getDistanceToPoint(beacon.x, beacon.y)		
	}

	int getDistanceToPoint(int x, int y) {
		return Math.abs(sensorX-x)+Math.abs(sensorY-y)
	}
	
	int getDistanceToRow(int row) {
		return Math.abs(sensorY-row)
	}
	
	boolean isInRange(int x, int y) {
		return (getDistanceToBeacon()>=getDistanceToPoint(x, y))
	}
	
	def notBeaconPointsAtRow(int row) {
 
		if (getDistanceToRow(row) > this.distanceToBeacon) {
			return null
		}
		
		
		int xWidth = this.distanceToBeacon - getDistanceToRow(row)
		def result = [from:this.sensorX-xWidth,to:this.sensorX+xWidth]
		return result
	}
	
	def getTopLeftPerimeter() {
		int distance = distanceToBeacon
		def perimeter = [:]
		
		def top = [y:sensorY-distance-1, x:sensorX]
		def left = [y:sensorY, x:sensorX-distance-1]
		
		return [from:top, to:left]
	}
	
	def getTopRightPerimeter() {
		int distance = distanceToBeacon
		def perimeter = [:]
		
		def top = [y:sensorY-distance-1, x:sensorX]
		def right = [y:sensorY, x:sensorX+distance+1]
		
		return [from:top, to:right]
	}
	
	def getBottomRightPerimeter() {
		int distance = distanceToBeacon
		def perimeter = [:]
		
		def bottom = [y:sensorY+distance+1, x:sensorX]
		def right = [y:sensorY, x:sensorX+distance+1]
		
		return [from:bottom, to:right]
	}
	
	def getBottomLeftPerimeter() {
		int distance = distanceToBeacon
		def perimeter = [:]
		
		def bottom = [y:sensorY+distance+1, x:sensorX]
		def left = [y:sensorY, x:sensorX-distance-1]
		
		return [from:bottom, to:left]
	}
	
	
	String toString() {
		return "S: ($sensorX, $sensorY) - B: $beacon"
	}
	
}
