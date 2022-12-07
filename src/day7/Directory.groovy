package day7

class Directory {
	
	String name
	def content = []
	Directory parent
	
	Directory getSubdir(String name) {
		return content.find{it.name == name && it instanceof Directory} 
	}

	void addDirectory(String name) {
		if (getSubdir(name)) {
			return
		}
		
		Directory subdir = new Directory(name:name, parent:this)
		content << subdir
	}
	
	void addFile(int size, String name) {
		content << new FileInfo(size:size, name:name)
	}
	
	
	int getSize() {
		return content.sum { it.size }
	}
	
	def getAllSubdirectories() {
		def result = []
		content.each {
			if (!(it instanceof Directory)) {
				return
			}
			result << it
			result.addAll(it.allSubdirectories)
		}
		return result
	}
	
	def findAllSubDirectoriesWithATotalSizeAtMost(int size) {
		def result = []
		content.each {
			if (!(it instanceof Directory)) {
				return
			}
			if (it.size <= size) {
				result << it 
			}
			
			result.addAll(it.findAllSubDirectoriesWithATotalSizeAtMost(size))
		}
		return result		
	}
	
	String toString() {
		return "<DIR:$name>"
	}	
}
