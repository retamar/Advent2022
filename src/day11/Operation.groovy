package day11

class Operation {
	
	static final OLD = "old"
	
	def leftOperand
	
	def rightOperand
	
	String operator
	
	def OPERANDS = ["+":{double l, double r -> l+r}, "*":{double l, double r -> l*r}]
	
	
	Operation(def leftOperand, String operator, def rightOperand) {
		this.leftOperand = leftOperand
		this.rightOperand = rightOperand
		this.operator = operator
	}
	
	String toString() {
		return "$leftOperand $operator $rightOperand"
	}
	
	
	double getLeftOperandValue(double worryLevel) {
		if (leftOperand == OLD) {
			return worryLevel
		}
		return leftOperand as Integer
	}
	
	double getRightOperandValue(double worryLevel) {
		if (rightOperand == OLD) {
			return worryLevel
		}
		
		return rightOperand as Integer
	}
	
	double execute(double worryLevel) {
		
		double leftValue = getLeftOperandValue(worryLevel)
		double rightValue = getRightOperandValue(worryLevel)
		
		return OPERANDS[operator].call(leftValue, rightValue)
	}
}
