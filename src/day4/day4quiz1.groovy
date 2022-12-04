package day4


def parseRange = {String rangeToBeParsed ->
	
	def limits = rangeToBeParsed.split("-");
	int lowerLimit = limits[0] as Integer
	int upperLimit = limits[1] as Integer
	return (lowerLimit..upperLimit)
	
}

def line = "2-8,13-14"
def rangeListToBeParsed = line.split(",")
def elfRanges = rangeListToBeParsed.collect {parseRange(it)}

boolean overlapped = elfRanges[0].containsAll(elfRanges[1]) || elfRanges[1].containsAll(elfRanges[0])
println overlapped