package day4


def parseRange = {String rangeToBeParsed ->
	
	def limits = rangeToBeParsed.split("-");
	int lowerLimit = limits[0] as Integer
	int upperLimit = limits[1] as Integer
	return (lowerLimit..upperLimit)
	
}

def line = "37-87,36-87"
def rangeListToBeParsed = line.split(",")
def elfRanges = rangeListToBeParsed.collect {parseRange(it)}
println elfRanges