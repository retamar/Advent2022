package day3
import java.util.regex.Pattern

def VALUES = ('a'..'z')+('A'..'Z')

def divideRucksack = {String rucksack ->
	
	int compartmentSize = rucksack.length()/2
	def compartments = []
	compartments << rucksack.substring(0, compartmentSize) 
	compartments << rucksack.substring(compartmentSize, rucksack.length()) 
	return compartments	
}

def findSharedItems = {def compartments ->	
	def sharedWithDups = compartments[0].findAll{compartments[1].contains(it)}
	return sharedWithDups.unique();
}

def computeValue = {def sharedItems ->
	int totalValue = 0
	sharedItems.each { 
		totalValue+=VALUES.indexOf(it)+1
	}
	return totalValue
}


File input = "docs/day3.txt" as File

int totalValue= 0

input.eachLine { rucksack ->
	def compartments = divideRucksack(rucksack)
	def sharedItem = findSharedItems(compartments)
	println sharedItem
	int value = computeValue(sharedItem)
	println value
	totalValue+=value
}

println totalValue