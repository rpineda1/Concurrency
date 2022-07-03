package concurrency;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr = new int[200000000];
		for (int i = 0; i<arr.length; i++) {
			arr[i] = rand.nextInt(10)+1;
		}
		long start = System.currentTimeMillis();
		System.out.println("Single sum: "+Concurrency.sum(arr));
		System.out.println("Time: " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		System.out.println("Parallel sum: "+Concurrency.parallelSum(arr));
		System.out.println("Time: " +(System.currentTimeMillis() - start));
	}

}
