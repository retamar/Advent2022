package day7


File input = "docs/day7test.txt" as File


def console = new ConsoleOutputParser()
console.parse(input)
def rootDir = console.rootDir

def allDirectoriesWithATotalSizeAtMost100000 = rootDir.findAllSubDirectoriesWithATotalSizeAtMost(100000)
println allDirectoriesWithATotalSizeAtMost100000

int totalSize = allDirectoriesWithATotalSizeAtMost100000.sum{it.size}
println "Total Size $totalSize"