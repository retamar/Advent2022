package day13

class SignalComparator {
	
	int compare(Integer signal1, Integer signal2) {
		return signal1<=>signal2
	}
	
	int compare(Collection signal1, Integer signal2) {
		return compare(signal1, wrapMember(signal2))
	}
	
	int compare(Integer signal1, Collection signal2) {
		return compare(wrapMember(signal1), signal2)
	}
	
	int compare(Collection signal1, Collection signal2) {
			
			
		if (signal1.size()<signal2.size()) {
			return compareLeftSmaller(signal1, signal2)
		}
		
		if (signal1.size()>signal2.size()) {
			return compareRightSmaller(signal1, signal2)
		}
		
		return compareEqualSize(signal1, signal2)
		
	}
	
	private int compareLeftSmaller(Collection signal1, Collection signal2) {
		
		for (int it=0; it<signal1.size(); it++) {
			def left = signal1[it]
			def right = signal2[it]
				
			int collectionComparation = compare(left, right)
			if (collectionComparation != 0) {
				return collectionComparation
			}
		}
			
		return -1		
	}
	
	private int compareRightSmaller(Collection signal1, Collection signal2) {
		
		for (int it=0; it<signal2.size(); it++) {
			def left = signal1[it]
			def right = signal2[it]
				
			int collectionComparation = compare(left, right)
			if (collectionComparation != 0) {
				return collectionComparation
			}
		}
			
		return 1		
	}
	
	private int compareEqualSize(Collection signal1, Collection signal2) {
		for (int it=0; it<signal2.size(); it++) {
			def left = signal1[it]
			def right = signal2[it]
				
			int collectionComparation = compare(left, right)
			if (collectionComparation != 0) {
				return collectionComparation
			}
		}
			
		return 0
	}
	
	private wrapMember(def member) {
		return (member instanceof Collection) ? member : [member]
	}
	
	
	
}
