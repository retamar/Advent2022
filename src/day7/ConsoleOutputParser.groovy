package day7

class ConsoleOutputParser {

	def INSTRUCTION_PREFIX = "\$"
	def ROOT_DIR_NAME = "/"
	def UPDIR_NAME = ".."
	def DIR = "dir"

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
			println currentDir.content
		}
		if (currentLine) {
			executeNextInstruction(currentLine, iterator)
		}
	}

	def COMMANDS = ["cd":this.cd_command, "ls":this.ls_command]

	Directory rootDir = new Directory(name:ROOT_DIR_NAME)
	Directory currentDir = null





	void parse(File input) {

		def currentInstruction
		Iterator inputIterator = input.iterator()
		while (inputIterator.hasNext()) {
			executeNextInstruction(inputIterator.next(), inputIterator)
		}
	}


	private void executeNextInstruction(def instruction, def iterator) {

		println "EXECUTING: $instruction"
		println COMMANDS

		def parsedInstruction = instruction.split(" ")
		def commandCall = parsedInstruction[1]
		def commandToBeExecuted = COMMANDS.get(commandCall)
		commandToBeExecuted.call(instruction, iterator)
	}
}
