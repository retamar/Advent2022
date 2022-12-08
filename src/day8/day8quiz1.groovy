package day8




File input = "docs/day8.txt" as File
def matrixAsText = input.text.readLines()

int forestWidth = matrixAsText[0].size()
int forestLenght = matrixAsText.size()

println "FOREST: $forestWidth x $forestLenght"
Integer[][] heightMatrix =  new Integer[forestWidth][forestLenght]
Boolean[][] visibilityMatrix =  new Boolean[forestWidth][forestLenght]

matrixAsText.eachWithIndex { line, xIndex->
	line.eachWithIndex { tree, yIndex ->
		heightMatrix[xIndex][yIndex] = (tree as Integer)
		visibilityMatrix[xIndex][yIndex] = true
	}
}

println heightMatrix

def isVisibleFromTop = {int treeRow, int treeCol ->
	
	int higherTree = -1;
	for (int i=0; i<treeRow; i++) {
		higherTree = (heightMatrix[i][treeCol] > higherTree) ? heightMatrix[i][treeCol] : higherTree
	}

	return heightMatrix[treeRow][treeCol] > higherTree
}

def isVisibleFromBottom = {int treeRow, int treeCol ->
	
	int higherTree = -1;
	for (int i=treeRow+1; i<forestLenght; i++) {
		higherTree = (heightMatrix[i][treeCol] > higherTree) ? heightMatrix[i][treeCol] : higherTree
	}

	return heightMatrix[treeRow][treeCol] > higherTree
	
}

def isVisibleFromLeft = {int treeRow, int treeCol ->

	int higherTree = -1;
	for (int i=0; i<treeCol; i++) {
		higherTree = (heightMatrix[treeRow][i] > higherTree) ? heightMatrix[treeRow][i] : higherTree
	}

	return heightMatrix[treeRow][treeCol] > higherTree
		
}

def isVisibleFromRight = {int treeRow, int treeCol ->
	
	int higherTree = -1;
	for (int i = treeCol+1; i<forestWidth; i++) {
		higherTree = (heightMatrix[treeRow][i] > higherTree) ? heightMatrix[treeRow][i] : higherTree
	}

	return heightMatrix[treeRow][treeCol] > higherTree
		
}

for (int treeRow=0; treeRow<forestWidth; treeRow++) {
	for (int treeCol=0; treeCol<forestLenght; treeCol++) {
		visibilityMatrix[treeRow][treeCol] = isVisibleFromTop(treeRow, treeCol) || 
			isVisibleFromBottom(treeRow, treeCol) || isVisibleFromLeft(treeRow, treeCol) ||
			isVisibleFromRight(treeRow, treeCol)
	}
}

int visibles = 0
for (int treeX=0; treeX<forestWidth; treeX++) {
	for (int treeY=0; treeY<forestLenght; treeY++) {
		if (visibilityMatrix[treeX][treeY]) {
			visibles++
		}
	}
}

println visibles
