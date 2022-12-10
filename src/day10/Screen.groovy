package day10

class Screen extends ProgramContext {
	
	
	int currentPosition = 0
	
	int lineLenght = 40
	
	
	def display = [createLine()]
	
	Screen() {
		currentCycle = 0
	}
	
	void nextCycle() {
		super.nextCycle()
		println this
		paintPixel()		
		println "$currentPosition, ${getCurrentLine()}"
		moveToNextDisplayPostion()
	}
	
	def getCurrentPixelRange() {
		return (vValue-1..vValue+1)
	}
	
	def getCurrentLine() {
		return display[-1]
	}
	
	void paint() {
		display.each {line ->
			line.each {pixel ->
				print pixel
			}
			println ""
		}
	}
	
	private def createLine() {
		def line = []
		(1..lineLenght).each {
			line << "."
		}
		return line
	}
	
	private void paintPixel() {
		if (currentPosition in getCurrentPixelRange()) {
			getCurrentLine()[currentPosition] = "#"
		} 
	}
	
	private void moveToNextDisplayPostion() {
		currentPosition ++
		if (currentPosition >= lineLenght) {
			currentPosition = 0
			display << createLine()								
		}
	}
}
