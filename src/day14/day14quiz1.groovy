package day14

File input = "docs/day14.txt" as File

def walls = []
input.eachLine {line-> 
	def trace = line.tokenize("->")
	def wallInProgress = null 
	trace.each {point ->
		def parsedPoint = point.tokenize(", ")
		int x = parsedPoint[0] as Integer
		int depth = parsedPoint[1] as Integer
		
		if (wallInProgress) {
			walls << new Wall(xRange:((wallInProgress.x)..x), depthRange: ((wallInProgress.depth)..depth))			
		}
		
		wallInProgress = [x:x, depth:depth]
	}
}

walls = walls.sort{it.depthRange.from}

println walls

int maxDepth = walls.depthRange.to.max()
int minX = walls.xRange.to.min()
int maxX = walls.xRange.to.max()

int startingSandPosition = 500


int maxSandDepth = 0
boolean allMapFilled = false
def sandBlocks = []

def canFallTo = {int x, int depth ->
	
	if (depth > maxDepth) {
		return false
	}
	
	def blockingWall = walls.find{it.isBlocked(x, depth)}
	if (blockingWall) {
		return false
	}
	
	return !sandBlocks.find{it.x == x && it.depth == depth}
}

def printBlocks = {
	println ""
	(0..maxDepth).each {depth ->
		print "$depth "
		(minX-1..maxX+1).each {x ->		
			if (walls.find{it.isBlocked(x, depth)}) {
				print "#"
			} else if (sandBlocks.find{it.x == x && it.depth == depth}) {
				print "o"
			} else {
				print "."
			}			
		}
		println ""
	}
	println ""
}

int units = 0
while (true) {
	boolean sandBlocked = false
	SandBlock fallingBlock = new SandBlock(startingSandPosition, 0)
	sandBlocks << fallingBlock
	while (!sandBlocked) {	
		if (canFallTo(fallingBlock.x, fallingBlock.depth+1)) {
			fallingBlock.depth++
			continue;
		}
		
		if (canFallTo(fallingBlock.x-1, fallingBlock.depth+1)) {
			fallingBlock.depth++
			fallingBlock.x--
			continue
		}
		
		if (canFallTo(fallingBlock.x+1, fallingBlock.depth+1)) {
			fallingBlock.depth++
			fallingBlock.x++
			continue
		}
		
		sandBlocked = true		
	}	
	if (fallingBlock.depth == 0) {
		break
	}
	if (fallingBlock.depth >= maxDepth) {
		break
	}
	println fallingBlock
//	units++
//	println "UNITS $units"
//	printBlocks()
}

printBlocks()
println "SAND BLOCKS ${sandBlocks.size()-1}"
