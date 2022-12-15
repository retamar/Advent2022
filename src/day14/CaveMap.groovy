package day14

class CaveMap {

	def walls = []

	int startingSandPosition = 500
	
	int maxDepth 
	int minX 
	int maxX 
	
	int maxSandDepth = 0
	boolean allMapFilled = false
	
	def sandBlocks = []	
	
	void parseInput(File input) {

		input.eachLine {line->
			def trace = line.tokenize("->")
			def wallInProgress = null
			trace.each {point ->
				def parsedPoint = point.tokenize(", ")
				int x = parsedPoint[0] as Integer
				int depth = parsedPoint[1] as Integer

				if (wallInProgress) {
					Wall toBeConstructed = new Wall(xRange:((wallInProgress.x)..x), depthRange: ((wallInProgress.depth)..depth))
					Wall precedentWall = walls.find{it -> it.precedentTo(toBeConstructed)}
					Wall nextWall = walls.find{it -> it.nextTo(toBeConstructed)}
					
					if (precedentWall) {
						toBeConstructed.xRange = (precedentWall.xRange.from..toBeConstructed.xRange.to)
						walls.removeElement(precedentWall)
					}
					
					if (nextWall) {
						toBeConstructed.xRange = (toBeConstructed.xRange.from..nextWall.xRange.to)
						walls.removeElement(nextWall)
					}
					
					walls << toBeConstructed
															
				}

				wallInProgress = [x:x, depth:depth]
		
			}
		}

		walls = walls.sort{it.depthRange.from}
		maxDepth = walls.depthRange.to.max()
		minX = walls.xRange.from.min()
		maxX = walls.xRange.to.max()
	}
	

	
	void addFloorAtDepthLevelBelow(int depth) {
		maxDepth += depth
		minX = startingSandPosition-maxDepth-2
		maxX = startingSandPosition+maxDepth+2
		walls << new Wall(xRange:minX..maxX, depthRange:maxDepth..maxDepth)
		
	}
	
	boolean blockedByWall(int x, int depth) {
		return walls.find{it.isBlocked(x, depth)}
	}
	
	boolean canFallTo(int x, int depth) {
		
		if (depth > maxDepth) {
			return false
		}
		
		boolean blockingWall = blockedByWall(x,depth)
		if (blockingWall) {
			return false
		}
		
		return !sandBlocks.find{it.x == x && it.depth == depth}
	}
	
	
	void printBlocks() {
		println ""
		(0..maxDepth).each {depth ->
			print String.format("%03d", depth)
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

	
	void startSandFlow() {
		int blocks = 1
		while (true) {
			println "THROWING ${blocks++} BLOCKS"
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
		}
	}
	
	int getAllocatedSandBlockNum() {
		return sandBlocks.size()
	}
}
