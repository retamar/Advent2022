package day10

class ProgramContext {
	
	int currentCycle = 1
	
	int vValue = 1
	
	int initialSignalPhase = 20
	int signalWidth = 40
	
	int nextSignalCycle = initialSignalPhase
	
	def signalTicks = []
	
	void nextCycle() {
		currentCycle++
		if (phaseCompleted()) {
			signalTicks << [cycle:currentCycle, v:vValue, str:currentCycle*vValue]
			nextSignalCycle+=signalWidth
		}
	}
	
	int getSignalStrength() {
		this.signalTicks.sum{it.cycle*it.v}
	}
	
	boolean phaseCompleted() {
		currentCycle == nextSignalCycle
	}
	
	void updateV(int num) {
		this.vValue+=num
	}

	
	String toString() {
		"Current Cycle: $currentCycle - V: $vValue - STR: $signalStrength"
	}
}
