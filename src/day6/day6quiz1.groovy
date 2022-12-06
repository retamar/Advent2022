package day6


int PACKET_SIZE = 4

File input = "docs/day6.txt" as File
def message = input.text 


int startPacketIndex = new AllDifferentCharsSequenceFinder(message, PACKET_SIZE).find()
println startPacketIndex