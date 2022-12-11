package day11

class Operation {
	
	static final OLD = "old"
	
	def leftOperand
	
	def rightOperand
	
	String operator
	
	def OPERANDS = ["+":{int l, int r -> l+r}, "*":{int l, int r -> l*r}]
	
	
	Operation(def leftOperand, String operator, def rightOperand) {
		this.leftOperand = leftOperand
		this.rightOperand = rightOperand
		this.operator = operator
	}
	
	String toString() {
		return "$leftOperand $operator $rightOperand"
	}
	
	
	int getLeftOperandValue(int worryLevel) {
		if (leftOperand == OLD) {
			return worryLevel
		}
		return leftOperand as Integer
	}
	
	int getRightOperandValue(int worryLevel) {
		if (rightOperand == OLD) {
			return worryLevel
		}
		
		return rightOperand as Integer
	}
	
	int execute(int worryLevel) {
		
		int leftValue = getLeftOperandValue(worryLevel)
		int rightValue = getRightOperandValue(worryLevel)
		
		return OPERANDS[operator].call(leftValue, rightValue)
	}
}
