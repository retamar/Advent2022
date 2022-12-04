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
	
	boolean overlapped = elfRanges[0].containsAll(elfRanges[1]) || elfRanges[1].containsAll(elfRanges[0])	
	if (overlapped) {
		overlappedAreas++
	}
}

println overlappedAreas