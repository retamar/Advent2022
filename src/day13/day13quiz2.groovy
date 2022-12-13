package day13


File input = "docs/day13.txt" as File

def signals = []
def currentSignal = []

input.eachLine { line ->
	
	line = line.trim()
	if (!line) {
		signals << currentSignal
		currentSignal = []
		return
	}
	
	def parsedLine = Eval.me(line)
	currentSignal << parsedLine
	
}
signals << currentSignal

println signals
SignalComparator comparator = new SignalComparator()
def rightOrder = []

signals.each {pair ->
	int comparing = comparator.compare(pair[0], pair[1])  
	println "$pair -> $comparing"
	rightOrder << (comparing <= 0)	
}
println rightOrder

int result = 0
rightOrder.eachWithIndex { it, index-> 
	result += (it ? index+1 : 0)	
}

println result