package day10

class Screen extends ProgramContext {
	
	
	int currentPosition = 1
	
	int lineLenght = 40
	
	
	def display = [[]]
	
	void nextCycle() {
		super.nextCycle()
		paintPixel()
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
	
	private void paintPixel() {
		if (currentCycle in getCurrentPixelRange()) {
			getCurrentLine() <<"#"
		} else {
			getCurrentLine() <<"."
		}
	}
	
	private void moveToNextDisplayPostion() {
		currentPosition ++
		if (currentPosition > lineLenght) {
			currentPosition = 1
			display << currentLine								
		}
	}
}
