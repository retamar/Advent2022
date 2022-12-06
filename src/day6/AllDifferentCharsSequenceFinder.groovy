package day6

class AllDifferentCharsSequenceFinder {
	
	private def buffer = []
	private def startMsgIndex = 1
	private int PACKET_SIZE
	private def message
	
	AllDifferentCharsSequenceFinder(def message, int PACKET_SIZE) {
		this.message = message
		this.PACKET_SIZE = PACKET_SIZE
	}
	
	int find() {
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
		
		return startMsgIndex
	}
	
	private boolean bufferIsFull() {
		return buffer.size() >= PACKET_SIZE
	}
	
	private void removeOldestCharFromBuffer() {
		buffer.remove(0)
	}
	
	private void pushDataToBuffer(def data) {
		buffer << data
	}
	
	private boolean checkAllCharactersInBufferAreDifferent() {
		return (buffer as Set).size() == PACKET_SIZE
	}
	

}
