package day15

File input = "docs/day15.txt" as File


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

int rowToBeTested = 2000000
def notBeaconPoints = [] as Set
def beaconsInRange = [] as Set

def addRangeToNotBeaconPoints = {def range ->
	
	if (!range) {
		return
	}
		
	def precedentRange = notBeaconPoints.find{range.from <= it.to && it.from<=range.from}		
	def nextRange = notBeaconPoints.find{range.from <= it.to && it.to >=range.to}
	
	def rangesToJoin = [range]	
	
	if (precedentRange) {
		notBeaconPoints.remove(precedentRange)
		rangesToJoin << precedentRange		
	}
	
	if (nextRange) {
		notBeaconPoints.remove(nextRange)
		rangesToJoin << nextRange
	}
	
	
	range.from = rangesToJoin.from.min()
	range.to = rangesToJoin.to.max()
	notBeaconPoints << range
} 

sensors.each {Sensor sensor->
	addRangeToNotBeaconPoints(sensor.notBeaconPointsAtRow(rowToBeTested))
}

beacons.each{beacon->
	if (beacon.y != rowToBeTested) {
		return
	}
	notBeaconPoints.each{range ->
		if (beacon.x>=range.from && beacon.x<=range.to) {
			println "B $beacon in R:$range"
			beaconsInRange<<beacon
		}		
	} 
}

int numOfNotBeaconPoints = 0
notBeaconPoints.each { range ->	
	numOfNotBeaconPoints += Math.abs(range.to-range.from+1)	
}

println notBeaconPoints
println numOfNotBeaconPoints
println beaconsInRange
println numOfNotBeaconPoints-beaconsInRange.size()


