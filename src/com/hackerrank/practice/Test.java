package com.hackerrank.practice;

public class Test {

	static int getHCF(int[] arr) {
		int hcf = 0;
		for (int number : arr) {
			hcf = findHCF(hcf, number);
		}
		return hcf;
	}

	static int findHCF(int a, int b) {
		if (b == 0) {
			return a;
		}
		return findHCF(b, a % b);
	}

	static int getLCM(int[] arr) {
		int lcm = arr[0];
		for (int number : arr) {
			lcm = findLCM(lcm, number);
		}
		return lcm;
	}

	static int findLCM(int a, int b) {
		return (a * b) / findHCF(a, b);
	}

	static int findIntermediateNumbers(int lcm, int hcf) {
		int numberOfIterations = hcf / lcm;
		int intermediateNumbers = 0;
		for (int i = 1; i < numberOfIterations; i++) {
			if (hcf % (lcm * i) == 0) {
				intermediateNumbers++;
			}
		}
		return intermediateNumbers;
	}

	public static void main(String args[]) {
		int hcf = getHCF(new int[] { 4, 8, 12 });
		System.out.println(hcf);
	}
}