package day6


int PACKET_SIZE = 4

File input = "docs/day6.txt" as File
def message = input.text 

def buffer = []
def startMsgIndex = 1

def bufferIsFull = {
	return buffer.size() >= PACKET_SIZE 
}

def removeOldestCharFromBuffer = {
	buffer.remove(0)
}

def pushDataToBuffer = {data ->
	buffer << data
}

def checkAllCharactersInBufferAreDifferent = {
	return (buffer as Set).size() == 4
}

message.any {def data ->
	
	if (bufferIsFull()) {
		removeOldestCharFromBuffer()
	}
	
	pushDataToBuffer(data)
	if (checkAllCharactersInBufferAreDifferent()) {
		return true
	}
	
	startMsgIndex++
	return false
} 

println startMsgIndex