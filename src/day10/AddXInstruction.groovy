package day10

class AddXInstruction {
	
	int duration = 2;
	
	int currentTick = 0;
	
	int num;
	
	AddXInstruction(int num) {
		this.num = num
	}
	
	boolean finished() {
		currentTick == duration
	}
	
	void execute(ProgramContext context) {
		currentTick++
		if (finished()) {
			context.updateV(num)
		}
	}
	
	String toString() {
		return "addx $num"
	}
	
}
