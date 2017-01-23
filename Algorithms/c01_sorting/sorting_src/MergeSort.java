package sorting_src;
/*
 * Split the array down the middle recursively and  merge the two sorted halves
 * Analysis: merge method takes O(n)
 * 
 * Let mergesort take T(n). Then, T(n) = 2*T(n/2) + n   
 * [invokes mergesort twice on 1/2 array, and then merges]
 * Master theorem gives O(nlogn) runtime
 * 
 * O(n) space on arrays (O(1) on linked lists)
 * 
 * STABLE
 * NOT IN PLACE
 * 
 * merge sort is a good choice for data sets that are too large to fit into memory. 
 * - split large file into multiple smaller files, sort each file and then merge them
 * 
 */
public class MergeSort {
	// Total runtime: O(nlogn)
	public static void sort(int [] A){
		/*
		 *  A hybrid merge sort occurs when a different sorting algorithm is used to sort subsets
		 *  below a specified minimum size.
		 *  Example:
		 *  	if(A.length<10){ 
		 *  		//reduced overhead for insertion sort on small arrays
		 *  		InsertionSort.sort(A); 
		 *  	}
		 */
		sort(A,0,A.length-1);
	}
	
	// recursive mergesort method
	// this runs in O(nlogn)
	private static void sort(int [] A, int low, int high){ 
		// should ideally stop in the middle
		if(low >= high) return; 
		
		// index of array middle (prevent overflow)
		int mid = (high-low)/2 + low;//(low+high)/2; 
		
		// sort first half
		sort(A,low,mid);
		
		// sort second half
		sort(A,mid+1,high);
		
		// merge the two halves
		merge(A,low,mid,high);
	}
	
	// merge two sorted halves of the array so that they're sorted
	// this runs in O(n)
	private static void merge(int [] A, int low, int mid, int high){ 
		//merge arrays [low ... mid] and [mid+1 ... high]
		
		// goes through first array half
		int idx1 = low;
		// goes through second array half
		int idx2 = mid+1;
		// goes through the entire array to get it in order
		int idxA = low;
		
		// copy the array to save it, since Java changes by reference
		int [] helper = A.clone();
		
		// now make A the right way
		while(idx1 <= mid && idx2 <= high){
			// if the smaller value is in first half
			if(helper[idx1] <= helper[idx2]){
				A[idxA] = helper[idx1];
				idx1++;
			}
			
			// if the smaller value is in second half
			else{
				A[idxA] = helper[idx2];
				idx2++;
			}
			idxA++;
		}
		
		// copy what's left over from the first half
		while(idx1 <= mid){
			A[idxA] = helper[idx1];
			idx1++; 
			idxA++;
		}
		
		// copy what's left over from the second half
		while(idx2 <= high){
			A[idxA] = helper[idx2];
			idx2++; 
			idxA++;
		}
	}
	
}
