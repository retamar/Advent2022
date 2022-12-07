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
	
	
	String toString() {
		return "<DIR:$name>"
	}	
}
