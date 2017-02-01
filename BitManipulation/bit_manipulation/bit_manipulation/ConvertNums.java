package bit_manipulation;

//Write a Java method that will return the number of bits that will need to be changed in 
//order to convert an integer, X, into another integer, Y and vice versa.

public class ConvertNums {
	public static int convert_nums(int x, int y){
		int num=0;
		//xor to get 1s only where the numbers differ
		x=x ^ y;
//		System.out.println("n = "+String.format("%32s", Integer.toBinaryString(n & 0xFFFFFFFF)).replace(' ', '0'));
		while(x!=0){
			x=x&(x-1);//get rid of the most significant set bit
			num++;
		}
		return num;
	}
	public static void main(String [] args){
		int x=12, y=16; //return 3
		System.out.println("x = "+String.format("%32s", Integer.toBinaryString(x & 0xFFFFFFFF)).replace(' ', '0'));
		System.out.println("y = "+String.format("%32s", Integer.toBinaryString(y & 0xFFFFFFFF)).replace(' ', '0'));
		System.out.println("Need to change "+convert_nums(x, y)+" bits.");
	}
}
