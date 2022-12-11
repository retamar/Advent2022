package day11

class Operation {
	
	static final OLD = "old"
	
	def leftOperand
	
	def rightOperand
	
	String operator
	
	def OPERANDS = ["+":{BigInteger l, BigInteger r -> l+r}, "*":{BigInteger l, BigInteger r -> l*r}]
	
	
	Operation(def leftOperand, String operator, def rightOperand) {
		this.leftOperand = leftOperand
		this.rightOperand = rightOperand
		this.operator = operator
	}
	
	String toString() {
		return "$leftOperand $operator $rightOperand"
	}
	
	
	BigInteger getLeftOperandValue(BigInteger worryLevel) {
		if (leftOperand == OLD) {
			return worryLevel
		}
		return leftOperand as BigInteger
	}
	
	BigInteger getRightOperandValue(BigInteger worryLevel) {
		if (rightOperand == OLD) {
			return worryLevel
		}
		
		return rightOperand as BigInteger
	}
	
	BigInteger execute(BigInteger worryLevel) {
		
		BigInteger leftValue = getLeftOperandValue(worryLevel)
		BigInteger rightValue = getRightOperandValue(worryLevel)
		
		return OPERANDS[operator].call(leftValue, rightValue)
	}
}
