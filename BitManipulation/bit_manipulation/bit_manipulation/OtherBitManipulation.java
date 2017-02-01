package bit_manipulation;
//zeros at the end of the number

public class OtherBitManipulation {
	public static boolean isPowerTwo(int x){
		// if a number is a power of 2 then it has EXACTLY 1 bit set
		if(x==0) return false;
		if((x & (x-1))==0) return true;
		return false;
	}
	
	public static int swap(int a, int b){
		a=a^b; b=a^b; a=a^b;
		return b;
	}
	
	public static int multiply(int a, int b){
		//swap if b>a
		if(max(a,b)==b)
			b=swap(a,a=b);
		int sum=0;
		while(b!=0){
			if((b&1)==1) //if the last bit is set, then add a
				sum+=a;
			a=a<<1;
			b=b>>1;
		}
		return sum;
	}
	
	// Write a method which finds the maximum of two numbers. You should not use if-else or any other comparison operator.
	public static int max(int a, int b){
		// if a>b then a-b>=0, so check the most significant bit if the number is positive or negative
		int bit=a-b;
		bit = bit&(0x80000000);
		if(bit==0)//if the number is positive
			return a;
		return b;
	}
	
	public static void main(String [] args){
		System.out.println(isPowerTwo(0));
		int a=10, b=12;

		System.out.println(max(a,b));
		
		System.out.println(a+" "+b+" "+multiply(a, b));
	}
}
