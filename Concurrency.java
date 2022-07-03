/**
* The Concurrency program implements a 
* parallel array sum, and compare 
* performance with single thread sum.
*
* @author  Ricardo Pineda
* @version 1.0
* @since   07/03/2022
*/
package concurrency;
import java.util.random.*;
public class Concurrency extends Thread{
	private int[]arr;
	private int low, high, piece;
	public Concurrency(int[]arr,int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high=Math.min(high,  arr.length);
	}
	public int getHalfSum() {
		return piece;
	}
	/**
	 * This run method is used to construct separate threads
	 * @param piece This is Used to carry the sums of numbers
	 * @return sum This returns the sum of the random number
	 * @return total This returns the total amount.
	 */
	@Override
	public void run() {
		piece = sum(arr,low, high);
	}
	public static int sum(int[] arr) {
		return sum(arr,0,arr.length);
	}
	public static int sum(int[] arr, int low, int high) {
		int total = 0;
		for (int i = low; i < high; i++) {
			total += arr[i];
		}
		return total;
	}
	/**
	 * This method is used to compare the sizes of the sums of the random numbers
	 * @return parallelSum returns the time it took for the sum of the parallel sum
	 * @param total This is used to add the sum of the total
	 * @return total returns the sum.
	 */
	public static int parallelSum(int[] arr) {
		return parallelSum(arr, Runtime.getRuntime().availableProcessors());
	}
	public static int parallelSum(int[] arr, int threads) {
		int size = (int) Math.ceil(arr.length * 1.0 / threads);
		Concurrency[] sums = new Concurrency[threads];
		for(int i = 0; i<threads; i++) {
			sums[i] = new Concurrency(arr, i * size, (i+1)*size);
			sums[i].start();
		}
		// pause the current thread until thread death
		try {
			for (Concurrency sum : sums) {
				sum.join();
			}
		}
		catch (InterruptedException e) {

		}
		int total = 0;
		for (Concurrency sum : sums) {
			total += sum.getHalfSum();
		}
		return total;
	}
}
