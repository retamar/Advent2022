package day8




File input = "docs/day8.txt" as File
def matrixAsText = input.text.readLines()

int forestWidth = matrixAsText[0].size()
int forestLenght = matrixAsText.size()

println "FOREST: $forestWidth x $forestLenght"
Integer[][] heightMatrix =  new Integer[forestWidth][forestLenght]
Integer[][] scenicMatrix =  new Boolean[forestWidth][forestLenght]

matrixAsText.eachWithIndex { line, xIndex->
	line.eachWithIndex { tree, yIndex ->
		heightMatrix[xIndex][yIndex] = (tree as Integer)
		scenicMatrix[xIndex][yIndex] = 0
	}
}

println heightMatrix

def viewedTreesFromTop = {int treeRow, int treeCol ->
	
	int viewedTrees = 0;
	boolean blockedView = false;
	for (int i=treeRow-1; i>=0 && !blockedView; i--) {
		viewedTrees++
		int currentTreeHeight = heightMatrix[i][treeCol]
		if (currentTreeHeight >= heightMatrix[treeRow][treeCol]) {
			blockedView = true
		}
	}

	return  viewedTrees
}

def viewedTreesFromBottom = {int treeRow, int treeCol ->
	
	int viewedTrees = 0;
	boolean blockedView = false;
	for (int i=treeRow+1; i<forestLenght && !blockedView; i++) {
		viewedTrees++
		int currentTreeHeight = heightMatrix[i][treeCol]
		if (currentTreeHeight >= heightMatrix[treeRow][treeCol]) {
			blockedView = true
		}
	}

	return  viewedTrees	
}

def viewedTreesFromLeft = {int treeRow, int treeCol ->
	
	int viewedTrees = 0;
	boolean blockedView = false;
	for (int i=treeCol-1; i>=0 && !blockedView; i--) {
		viewedTrees++
		int currentTreeHeight = heightMatrix[treeRow][i]
		if (currentTreeHeight >= heightMatrix[treeRow][treeCol]) {
			blockedView = true
		}
	}

	return  viewedTrees
		
}

def viewedTreesFromRight = {int treeRow, int treeCol ->
	
	int viewedTrees = 0;
	boolean blockedView = false;
	for (int i = treeCol+1; i<forestWidth && !blockedView; i++) {
		viewedTrees++
		int currentTreeHeight = heightMatrix[treeRow][i]
		if (currentTreeHeight >= heightMatrix[treeRow][treeCol]) {
			blockedView = true
		}
	}

	return  viewedTrees
	
		
}

for (int treeRow=0; treeRow<forestWidth; treeRow++) {
	for (int treeCol=0; treeCol<forestLenght; treeCol++) {
		scenicMatrix[treeRow][treeCol] = viewedTreesFromTop(treeRow, treeCol) * 
			viewedTreesFromBottom(treeRow, treeCol) * viewedTreesFromLeft(treeRow, treeCol) *
			viewedTreesFromRight(treeRow, treeCol)
	}
}

int highestScenicValue = 0
for (int treeX=0; treeX<forestWidth; treeX++) {
	for (int treeY=0; treeY<forestLenght; treeY++) {
		print scenicMatrix[treeX][treeY] 
		print " "
		if (scenicMatrix[treeX][treeY] > highestScenicValue) {
			highestScenicValue=scenicMatrix[treeX][treeY]
		}
	}
	println " "
}

println highestScenicValue
