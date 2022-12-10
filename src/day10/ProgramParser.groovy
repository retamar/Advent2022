package day10

class ProgramParser {

	def INSTRUCTION_FACTORY = ["addx":{line-> new AddXInstruction(line[1] as Integer)}, "noop":{new NoopInstruction()}]


	def parse(File input) {
		def program = []
		input.eachLine { line ->
			def parsedLine = line.tokenize(" ")
			program << INSTRUCTION_FACTORY.get(parsedLine[0])?.call(parsedLine)
		}
		return program
	}
}
