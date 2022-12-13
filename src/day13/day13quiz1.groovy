package day13


File input = "docs/day13.txt" as File

def divider1 = [[2]]
def divider2 = [[6]]
def signals = [divider1, divider2]
input.eachLine { line ->
	if (!line) {
		return
	}
	def parsedLine = Eval.me(line)
	signals << parsedLine	
}

SignalComparator comparator = new SignalComparator()
def sortedSignals = signals.sort {def signal1, def signal2 -> comparator.compare(signal1, signal2)  }

sortedSignals.each {
	println it
}

int divider1Position = sortedSignals.indexOf(divider1)+1
int divider2Position = sortedSignals.indexOf(divider2)+1

int decoderKey = divider1Position*divider2Position
println decoderKey