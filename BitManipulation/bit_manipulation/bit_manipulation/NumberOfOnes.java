package bit_manipulation;

// calculate the number of ones in a binary number

public class NumberOfOnes {
	public static int countOnes(int n){
		//this goes through all the bits, set or unset once
		//Worst case still O(log(n)) but all cases are O(log(n))
		int num=0;
		int x=0x80000000; //1 followed by 31 0's in hex
//		int x=(int)Math.pow(2,30);
		while(n!=0){
			//System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xFFFFFFFF)).replace(' ', '0'));
			//AND to check if FIRST bit is set
			if((n & x) != 0)
				num++;
			n=n<<1;
		}
		return num;
	}
	
	public static int countOnesAgain(int n){
		//this goes through all the bits, set or unset once
		//Worst case still O(log(n)) but all cases are O(log(n))
		int num=0;
		int x=1; //least significant bit set
		while(n!=0){
//			System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xFFFFFFFF)).replace(' ', '0'));
			//AND to check if LAST bit is set
			if((n&x) != 0)
				num++;
			n=n>>>1; //right unsigned shift
		}
		return num;
	}
	
	public static int countOnesAlternate(int n){
		//This algorithm goes through as many iterations as there are set bits. 
		//Goes only once if only one set bit, so in practice goes through less iterations.
		//An integer n has log(n) bits, hence the worst case is O(log(n))
		int num=0;
		while(n!=0){
			n&=n-1; // clear the least significant bit set
			num++;
		}
		return num;
	}
	
	public static int countOnesLookupTable(int n){//use a lookup table for close to constant time
		//if the lookup table would have 2^32 entries, lookup time would be constant
		int num=0; 
		// how many ones does the current nibble contain
		//                   0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7
		//                   0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF
		int [] lookup_nibble={0,   1,   1,   2,   1,   2,   2,   3,
				              1,   2,   2,   3,   2,   3,   3,   4};

		int mask = 0xF; //000....01111; (n & mask) saves the last 4 bits and indexes into the array
		
		//an int is created by concatenating 8 nibbles
		for(int i=0;i<8;i++){ //look at the bits 4-by-4
			num+=lookup_nibble[n & mask]; //which nibble do the last 4 bits correspond to?
			n=n>>4;
		}
		
		return num;
	}
	
	public static void main(String [] args){
		int b=0x0F00008F;
		String s = String.format("%32s", Integer.toBinaryString(b & 0xFFFFFFFF)).replace(' ', '0');
		
		int n1=countOnes(b);
		System.out.println(s +" has "+n1+" ones.");
		
		int n2=countOnesAgain(b);
		System.out.println(s +" has "+n2+" ones.");

		int n3=countOnesAlternate(b);
		System.out.println(s +" has "+n3+" ones.");

		int n4=countOnesLookupTable(b);
		System.out.println(s +" has "+n4+" ones.");
	}
}
