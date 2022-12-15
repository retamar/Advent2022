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
		this.beaconX = beaconY
	}
	
	int getDistanceToBeacon() {		
		return Math.abs(sensorX-beaconX)+Math.abs(sensorY-beaconY)		
	}
	
	String toString() {
		return "S: ($sensorX, $sensorY) - B:($beaconX, $beaconY)"
	}
	
}
