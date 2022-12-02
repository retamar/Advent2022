package day1


File input = "docs/day1.txt" as File

int currentElfAccCalories = 0;
def elfs = []

input.eachLine { line ->
	
	if (!line) {
		elfs << currentElfAccCalories
		currentElfAccCalories = 0
		return
	}
	
	int calories = line as Integer
	currentElfAccCalories += calories
	
}


int maxCalories = elfs.max()
println maxCalories

return maxCalories

