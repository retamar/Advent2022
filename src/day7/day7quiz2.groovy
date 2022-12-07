package day7

int TOTAL_DISK_SPACE = 70000000
int NEEDED_SPACE = 30000000

File input = "docs/day7.txt" as File



def console = new ConsoleOutputParser()
console.parse(input)
def rootDir = console.rootDir

int totalUsedSpace = rootDir.size
int totalUnusedSpace = TOTAL_DISK_SPACE - totalUsedSpace
int totalSpaceToBeRemoved = NEEDED_SPACE - totalUnusedSpace

def allDirs = rootDir.allSubdirectories
allDirs.sort{it.size}

def toBeRemoved = allDirs.find{it.size >= totalSpaceToBeRemoved}
println totalSpaceToBeRemoved
println toBeRemoved
println toBeRemoved.size