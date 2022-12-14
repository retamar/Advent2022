package day14

class SandBlock {
	
	
	int x
	int depth
	
	
	SandBlock(int x, int y) {
		super()
		this.x = x
		this.depth = y
	}
	
	
	boolean equals(Object other) {
		if (!other instanceof SandBlock) {
			return false
		}
		
		return x==other.x && depth == other.depth 
	}
	
	public int hashCode() {
		return [x, depth].hashCode()	
	}
	
	String toString() {
		return "[$x, $depth]"
	}
		
}


