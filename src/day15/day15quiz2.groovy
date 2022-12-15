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

def checkPoint = {point ->
	if (point.x<0 || point.x>mapSize) {
		return false
	}

	if (point.y<0 || point.y>mapSize) {
		return false
	}

	def sensorsInRange = sensors.findAll{it.isInRange(point.x, point.y)}

	return !sensorsInRange
}

def distressBeacon
for(Sensor sensor in sensors) {

	println "Searching area of $sensor"
	println "Looking top-left quadrant"
	def quadrant = sensor.getTopLeftPerimeter()
	println quadrant
	def point = quadrant.from
	while(point != quadrant.to) {
		
		if (checkPoint(point)) {
			distressBeacon = point
			println "DB: $point"
			return
		}
		point = [x:point.x-1, y:point.y+1]
		println "TL $sensor - $point"
	}

	println "Looking top-right quadrant"
	quadrant = sensor.getTopRightPerimeter()
	println quadrant
	point = quadrant.from
	while(point != quadrant.to) {
		if (checkPoint(point)) {
			distressBeacon = point
			println "DB: $point"
			return
		}
		point = [x:point.x+1, y:point.y+1]
		println "TR $sensor - $point"
	}

	println "Looking bottom-right quadrant"
	quadrant = sensor.getBottomRightPerimeter()
	println quadrant
	point = quadrant.from
	while(point != quadrant.to) {
		if (checkPoint(point)) {
			distressBeacon = point
			println "DB: $point"
			return
		}
		point = [x:point.x+1, y:point.y-1]
		println "BR $sensor - $point"
	}

	println "Looking bottom-left quadrant"
	quadrant = sensor.getBottomLeftPerimeter()
	println quadrant
	point = quadrant.from
	while(point != quadrant.to) {
		if (checkPoint(point)) {
			distressBeacon = point
			println "DB: $point"
			double code = mapSize*point.x+y
			println "DISTRESS CODE $code"
			return
		}
		point = [x:point.x-1, y:point.y-1]
		println "BL $sensor - $point"
	}


}
