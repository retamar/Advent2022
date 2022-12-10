package day10



def executeProgram = {def program, def screen ->
	program.each {instruction ->
		println instruction
		while (!instruction.finished()) {
			instruction.execute(screen)
			screen.nextCycle()
		}
	}
}



File input = "docs/day10.txt" as File
def program = new ProgramParser().parse(input)
println program

Screen screen = new Screen()
executeProgram(program, screen)
screen.paint()

