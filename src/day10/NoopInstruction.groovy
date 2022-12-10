package day10

class NoopInstruction {
	
	int duration = 1
	
	int currentTick = 0;
	
	boolean finished() {
		currentTick == duration
	}
	
	void execute(ProgramContext context) {
		currentTick++		
	}
 	
	String toString() {
		return "noop"
	}
}
