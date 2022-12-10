package day10

class ProgramContext {
	
	int currentCycle = 0
	
	int vValue = 1
	
	void nextCycle() {
		currentCycle++
	}
	
	void updateV(int num) {
		this.vValue+=num
	}
	
	String toString() {
		"Current Cycle: $currentCycle - V: $vValue"
	}
}
