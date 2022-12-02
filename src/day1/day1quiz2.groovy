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


elfs = elfs.sort();
int max = elfs[-1]
int max2 = elfs[-2]
int max3 = elfs[-3]

int maxCalories = max+max2+max3
println maxCalories
