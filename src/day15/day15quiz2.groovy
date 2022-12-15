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

int mapSize = 4000000
def notBeaconPoints = [] as Set

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
	
	
	range.from = Math.max(0, rangesToJoin.from.min())
	range.to = Math.min(rangesToJoin.to.max(), mapSize)
	
	def toBeRemoved = notBeaconPoints.findAll{it.from>=range.from && it.to<=range.to	}
	notBeaconPoints.removeAll(toBeRemoved)
	
	def includerRange = notBeaconPoints.find{range.from>=it.from && range.to<=it.to}
	if (!includerRange) {
		notBeaconPoints << range
	}	
}



def notBeaconPointsInRow = {int row ->
	sensors.each {Sensor sensor->
		addRangeToNotBeaconPoints(sensor.notBeaconPointsAtRow(row))
	}
	
//	beacons.each{beacon->
//		if (beacon.y != row) {
//			return
//		}
//		notBeaconPoints.each{range ->
//			if (beacon.x>=range.from && beacon.x<=range.to) {
//				beaconsInRange<<beacon
//			}
//		}
//	}
	
	int numOfNotBeaconPoints = 0
	notBeaconPoints.each { range ->
		numOfNotBeaconPoints += Math.abs(range.to-range.from+1)
	}
	
	return numOfNotBeaconPoints
	
}


notBeaconPoints = [] as Set


def resultRow = []

for (int i=0; i<=mapSize; i++) {
	
	notBeaconPoints = [] as Set
		
	int points = notBeaconPointsInRow(i)
	int candidatePoints = points-mapSize
	println "$i: $candidatePoints - $notBeaconPoints"	
	if (candidatePoints < 1) {
		break;
	}
	if (notBeaconPoints.size()>1) {
		break
	}
}


println notBeaconPoints
println resultRow



