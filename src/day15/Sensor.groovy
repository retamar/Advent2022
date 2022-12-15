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
	
	
	def notBeaconPointsAtRow(int row) {
 
		if (getDistanceToRow(row) > this.distanceToBeacon) {
			return null
		}
		
		
		int xWidth = this.distanceToBeacon - getDistanceToRow(row)
		def result = [from:this.sensorX-xWidth,to:this.sensorX+xWidth]
		return result
	}
	
	def getExternalPerimeter(int mapSize) {
		
		int distance = distanceToBeacon
		def externalPerimeter = [] as Set
		
		for (int y = sensorY; y<=Math.max(0, sensorY-distance); y++) {
			int xWidth = this.distanceToBeacon - getDistanceToRow(y)
			externalPerimeter.add([x:sensorX-xWidth-1, y:y, sensor:this])
			externalPerimeter.add([x:sensorX-xWidth-1, y:y-1, sensor:this])
		}
		
		
		for (int y = sensorY; y<=sensorY+distance; y++) {
			int xWidth = this.distanceToBeacon - getDistanceToRow(y)
			externalPerimeter.add([x:sensorX-xWidth-1, y:y, sensor:this])
			externalPerimeter.add([x:sensorX-xWidth-1, y:y-1, sensor:this])
		}
		
		for (int y = sensorY; y<=sensorY+distance; y++) {
			int xWidth = this.distanceToBeacon - getDistanceToRow(y)
			externalPerimeter.add([x:sensorX+xWidth+1, y:y, sensor:this])
			externalPerimeter.add([x:sensorX+xWidth+1, y:y+1, sensor:this])
		}
		
		for (int y = sensorY-distance; y<=sensorY; y++) {
			int xWidth = this.distanceToBeacon - getDistanceToRow(y)
			externalPerimeter.add([x:sensorX-xWidth-1, y:y, sensor:this])
			externalPerimeter.add([x:sensorX-xWidth-1, y:y+1, sensor:this])
		}
		
		
		for (int y = sensorY-distance; y<=sensorY; y++) {
			int xWidth = this.distanceToBeacon - getDistanceToRow(y)
			externalPerimeter.add([x:sensorX-xWidth+1, y:y, sensor:this])
			externalPerimeter.add([x:sensorX-xWidth+1, y:y+1, sensor:this])			
		}
		
		
		return externalPerimeter
	}
	
	
	String toString() {
		return "S: ($sensorX, $sensorY) - B: $beacon"
	}
	
}
