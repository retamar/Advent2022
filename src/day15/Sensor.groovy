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
		return Math.abs(sensorX-beacon.x)+Math.abs(sensorY-beacon.y)		
	}
	
	def notBeaconPointsAtRow(int row) {
		int distanceToRow = Math.abs(sensorY - row)
		if (distanceToRow > this.distanceToBeacon) {
			return null
		}
		
		
		int xWidth = this.distanceToBeacon - distanceToRow
		def result = [from:this.sensorX-xWidth,to:this.sensorX+xWidth]
		return result
	}
	
	String toString() {
		return "S: ($sensorX, $sensorY) - B: $beacon"
	}
	
}
