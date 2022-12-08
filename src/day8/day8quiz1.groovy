package day8


File input = "docs/day8test.txt" as File
def matrixAsText = input.text.readLines()

int forestWidth = matrixAsText[0].size()
int forestLenght = matrixAsText.size()

println "FOREST: $forestWidth x $forestLenght"
Integer[][] heightMatrix =  new Integer[forestWidth][forestLenght]
Integer[][] visibilityMatrix =  new Integer[forestWidth][forestLenght]