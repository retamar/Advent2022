package day10

def INSTRUCTION_FACTORY = ["addx":{line-> new AddXInstruction(line[1] as Integer)}, "noop":{new NoopInstruction()}]


def parseProgram = {File input ->
	def program = []
	input.eachLine { line ->
		def parsedLine = line.tokenize(" ")
		program << INSTRUCTION_FACTORY.get(parsedLine[0])?.call(parsedLine)
	}
	return program
}

def executeProgram = {def program, def context ->
	program.each {instruction ->
		println instruction
		while (!instruction.finished()) {
			instruction.execute(context)
			context.nextCycle()
		}
	}
}



File input = "docs/day10test.txt" as File
def program = parseProgram(input)
println program

ProgramContext context = new ProgramContext()
executeProgram(program, context)
println context
println context.signalTicks

