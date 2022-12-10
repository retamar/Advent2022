package day10



def executeProgram = {def program, def context ->
	program.each {instruction ->
		println instruction
		while (!instruction.finished()) {
			instruction.execute(context)
			context.nextCycle()
		}
	}
}



File input = "docs/day10.txt" as File
def program = new ProgramParser().parse(input)
println program

ProgramContext context = new ProgramContext()
executeProgram(program, context)
println context
println context.signalTicks

