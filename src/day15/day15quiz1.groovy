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
	
	def beacon = [beaconX, beaconY] 
	sensors << new Sensor(sensorX, sensorY, beacon)
	beacons << beacon
	
}

println sensors

int rowToBeTested = 10
def notBeaconPoints = ([] as Set)
sensors.each {Sensor sensor->
	notBeaconPoints.addAll(sensor.notBeaconPointsAtRow(rowToBeTested))
}

notBeaconPoints.removeAll(beacons)

println notBeaconPoints
println notBeaconPoints.size()

