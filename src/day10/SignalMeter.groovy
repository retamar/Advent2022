package day10

class SignalMeter extends ProgramContext {

	
	int initialSignalPhase = 20
	int signalWidth = 40
	
	int nextSignalCycle = initialSignalPhase
	
	def signalTicks = []
	
	void nextCycle() {
		super.nextCycle()
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
	

	
	String toString() {
		"Current Cycle: $currentCycle - V: $vValue - STR: $signalStrength"
	}
}
