package day7


def INSTRUCTION_PREFIX = "\$"
def ROOT_DIR_NAME = "/"
def UPDIR_NAME = ".."
def DIR = "dir"

Directory rootDir = new Directory(name:ROOT_DIR_NAME)
Directory currentDir = null

def currentInstruction

def COMMANDS = []
def executeNextInstruction = {def instruction, def iterator ->

	println "EXECUTING: $instruction"
	
	def parsedInstruction = instruction.split(" ")
	def commandCall = parsedInstruction[1]
	def commandToBeExecuted = COMMANDS.get(commandCall)
	commandToBeExecuted.call(instruction, iterator)
}


def cd_command = {def instruction, def iterator ->
	
	def parsedInstruction = instruction.split(" ")
	String dir = parsedInstruction[2]	
	if (dir == ROOT_DIR_NAME) {
		currentDir = rootDir
	} else if (dir == UPDIR_NAME){
		currentDir = currentDir.parent
	} else {
		currentDir = currentDir.getSubdir(dir)
	}
}


def ls_command = {def instruction, def iterator ->
	
	def currentLine = null
	while (true) {
		if (!iterator.hasNext()) {
			return;
		}
		currentLine = iterator.next()
		def parsedInstruction = currentLine.split(" ")
		if (parsedInstruction[0] == INSTRUCTION_PREFIX) {
			break;
		}
		
		if (parsedInstruction[0] == DIR) {
			currentDir.addDirectory(parsedInstruction[1])
		} else {
			currentDir.addFile(parsedInstruction[0] as Integer, parsedInstruction[1])
		}
	}
	if (currentLine) {
		executeNextInstruction(currentLine, iterator)
	}
}




COMMANDS = ["cd":cd_command, "ls":ls_command]
File input = "docs/day7test.txt" as File
Iterator inputIterator = input.iterator()
while (inputIterator.hasNext()) {
	executeNextInstruction(inputIterator.next(), inputIterator)
}
