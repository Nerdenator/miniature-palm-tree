package c02_searching;

public class BinarySearch {
	// O(log n)
	public static int binarySearchRecursive(int []A, int x){
		return binarySearchRecursive(A,0,A.length-1,x);
	}
	
	private static int binarySearchRecursive(int []A, int left, int right, int x){
		//element not found
		if(left > right) 
			return -1; 
		
		int mid = (left+right)/2;
		// found the element in the middle
		if(A[mid] == x) 
			return mid;
		
		// look in first half
		if(A[mid]>x) 
			return binarySearchRecursive(A, left, mid-1, x);

		// look in second half
		if(x > A[mid]) 
			return binarySearchRecursive(A, mid+1, right, x);
		
		// element not found
		return -1;
	}
	
	//O(log n)
	public static int binarySearchIterative(int []A, int x){
		int left = 0;
		int right = A.length-1;
		while(left <= right){
			int mid = (left+right)/2;
			//continue looking in the first half
			if(x < A[mid]) 
				right = mid-1;
			// continue looking in the second half
			else if(x > A[mid]) 
				left = mid+1;
			// found the element in the middle
			else 
				return mid;
		}
		return -1;
	}
}
