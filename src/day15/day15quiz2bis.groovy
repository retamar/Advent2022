package day15

File input = "docs/day15test.txt" as File


def sensors = []
def beacons = []

input.eachLine { line ->
	
	def sensorAndBeaconConfiguration = line.tokenize(":")
	
	def parsedSensorData = (sensorAndBeaconConfiguration[0] - "Sensor at ").tokenize(",")
	int sensorX = ((parsedSensorData[0]-"x=").trim()) as Integer
	int sensorY = ((parsedSensorData[1]-"y=").trim()) as Integer
	
	def parsedBeaconData = (sensorAndBeaconConfiguration[1] - " closest beacon is at ").tokenize(",")
	int beaconX = ((parsedBeaconData[0]-"x=").trim()) as Integer
	int beaconY = ((parsedBeaconData[1]-"y=").trim()) as Integer
	
	def beacon = [x:beaconX, y:beaconY]
	sensors << new Sensor(sensorX, sensorY, beacon)
	beacons << beacon
	
}

println sensors

int mapSize = 20//4000000

def candidatePoints = []
sensors.each {Sensor sensor ->
	def perimeter = sensor.getExternalPerimeter()
	println sensor
	println perimeter
	candidatePoints.addAll(perimeter)
}


println candidatePoints
println candidatePoints.size()
println "----------------------------------"

def distressPoint = candidatePoints.find{point->
	
	println point
	
	if (point.x<0 || point.x>mapSize) {
		return false
	}
	
	if (point.y<0 || point.y>mapSize) {
		return false
	}
	
	if (point.x==14 && point.y==11) {
		println "AQUI"
	}
	
	def distancesToSensors = sensors.collect {it.getDistanceToPoint(point.x, point.y)}
	
	int minDistanceToSensor = distancesToSensors.min()	
	int distanceToSensor = point.sensor.getDistanceToPoint(point.x, point.y)  
	
	println "DTS $distanceToSensor - $minDistanceToSensor"
	return (distanceToSensor < minDistanceToSensor)
}

println "DP: $distressPoint"
