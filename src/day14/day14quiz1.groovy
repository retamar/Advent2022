package day14

File input = "docs/day14.txt" as File

CaveMap cave = new CaveMap()
cave.parseInput(input)
println cave.walls

cave.startSandFlow()
cave.printBlocks()
println "SAND BLOCKS ${cave.allocatedSandBlockNum-1}"
