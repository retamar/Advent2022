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
		return Math.abs(sensorX-beacon[0])+Math.abs(sensorY-beacon[1])		
	}
	
	def notBeaconPointsAtRow(int row) {
		int distanceToRow = Math.abs(sensorY - row)
		if (distanceToRow > this.distanceToBeacon) {
			return []
		}
		
		
		int xWidth = this.distanceToBeacon - distanceToRow
		def points = []
		(this.sensorX-xWidth..this.sensorX+xWidth).each{
			points << [it,row]
		}
		
		println "${this} -> $points -> ${points.size()} -> $distanceToBeacon"
		
		return points
	}
	
	String toString() {
		return "S: ($sensorX, $sensorY) - B: $beacon"
	}
	
}
