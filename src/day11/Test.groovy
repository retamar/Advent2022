package day11

class Test {
	
	int divisible
	
	int whenTrue
	
	int whenFalse
	
	int test(BigInteger worryValue) {
		
		return (worryValue % divisible == 0) ? whenTrue : whenFalse
	}
	
	String toString() {
		return "When divisible by $divisible -> send to $whenTrue else to $whenFalse"
	}
	
}
