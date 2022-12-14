package day4


def parseRange = {String rangeToBeParsed ->
	
	def limits = rangeToBeParsed.split("-");
	int lowerLimit = limits[0] as Integer
	int upperLimit = limits[1] as Integer
	return (lowerLimit..upperLimit)
	
}

int overlappedAreas = 0

File input = "docs/day4.txt" as File

input.eachLine { line ->
	def rangeListToBeParsed = line.split(",")
	def elfRanges = rangeListToBeParsed.collect {parseRange(it)}
	
	boolean overlapped = elfRanges[0].intersect(elfRanges[1])	
	if (overlapped) {
		overlappedAreas++
	}
}

println overlappedAreas