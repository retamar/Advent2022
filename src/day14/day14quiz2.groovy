package day14

File input = "docs/day14test.txt" as File

CaveMap cave = new CaveMap()
cave.parseInput(input)
cave.addFloorAtDepthLevelBelow(2)

println cave.walls
cave.printBlocks()

cave.startSandFlow()
cave.printBlocks()
println "SAND BLOCKS ${cave.allocatedSandBlockNum}"
