package day15

class Sensor {
	
	int sensorX
	
	int sensorY
	
	int beaconX
	
	int beaconY
	
	Sensor(int sensorX, int sensorY, int beaconX, int beaconY) {
		super()
		this.sensorX = sensorX
		this.sensorY = sensorY
		this.beaconX = beaconX
		this.beaconY = beaconY
	}
	
	int getDistanceToBeacon() {		
		return Math.abs(sensorX-beaconX)+Math.abs(sensorY-beaconY)		
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
		return "S: ($sensorX, $sensorY) - B:($beaconX, $beaconY)"
	}
	
}
