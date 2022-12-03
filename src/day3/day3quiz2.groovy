package day3
import java.util.regex.Pattern

def VALUES = ('a'..'z')+('A'..'Z')


def findBadge = {def group ->	
	def sharedWithDups = group[0].findAll{group[1].contains(it) && group[2].contains(it)}
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

int totalValue = 0
def currentGroup = []

input.eachLine { rucksack ->
	if (currentGroup.size < 3) {
		currentGroup << rucksack
	}	

	if (currentGroup.size()<3) {
		return
	}
	println currentGroup
	def badge = findBadge(currentGroup)
	println badge
	
	int badgeValue = computeValue(badge)
	println badgeValue
	
	totalValue+=badgeValue
	currentGroup.clear()
}

println totalValue