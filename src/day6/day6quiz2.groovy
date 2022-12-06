package day6


int PACKET_SIZE = 14

File input = "docs/day6.txt" as File
def message = input.text 


int startMessageIndex = new AllDifferentCharsSequenceFinder(message, PACKET_SIZE).find()
println startMessageIndex