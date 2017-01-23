package dynamic_programming_src;

import java.util.ArrayList;
import java.util.Arrays;

//Write algorithm to return change in denominations of 10$, 5$, 1$, 25 cents, 10 cents
//assume input is in cents

public class MakeChangeDP {
	private int change, ncoins;
	private ArrayList<Integer> combo;
	static final int[] denom = { 5, 25, 100, 50, 1, 4 };//cents

	MakeChangeDP(int change) {
		this.change = change;
	}

	//greedy non-optimal solution
	public void printChange() {
		System.out.println("The current change is: ");
		int num, chng = change;
		Arrays.sort(denom);
		for (int i = denom.length - 1; i >= 0; i--) {
			int denomi = denom[i];
			num = chng / denomi;
			if (num > 0)
				System.out.println(num + " x " + denomi);
			chng = chng - num * denomi;
		}
	}

	//dynamic programming solution
	private void makeChange() {
		int numCoins[] = new int[change + 1];
		ArrayList<ArrayList<Integer>> whichCoins = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= change; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			whichCoins.add(row);
		}

		int minNum = 0;
		for (int i = 1; i <= change; i++) {
			minNum = Integer.MAX_VALUE;
			for (int den : denom) {
				if (i - den >= 0 && minNum > numCoins[i - den]) {
					minNum = numCoins[i - den];
				}
			}

			for (int den : denom) {
				if (i - den >= 0 && minNum == numCoins[i - den]) {
					ArrayList<Integer> row2 = whichCoins.get(i - den);
					ArrayList<Integer> row = new ArrayList<Integer>();
					row.addAll(row2);
					row.add(den);
					whichCoins.set(i, row);
				}
			}

			numCoins[i] = minNum + 1;
		}
		combo = whichCoins.get(change);
		ncoins = numCoins[change];
	}

	public static void main(String[] args) {
		MakeChangeDP money = new MakeChangeDP(18);
		System.out.println("GREEDY:");
		money.printChange();
		System.out.println("\nDYNAMIC PROGRAMMING:");
		money.makeChange();
		System.out.println("Number of coins needed: " + money.ncoins + " by using: " + money.combo);
	}
}