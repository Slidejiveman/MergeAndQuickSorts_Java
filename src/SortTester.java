import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SortTester {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		// Make sufficiently sized Queues of three types
		// use getTimeMillis() for start and end time to
		// measure the differences.
		// put in word
		Queue quickQueue = new LinkedBlockingQueue();
		Queue mergeQueue = new LinkedBlockingQueue();
		
		for( int i = 0; i < 10; i++ ) {
			quickQueue.offer(i);
			mergeQueue.offer(i);
		}
		quickQueue.offer(2);
		mergeQueue.offer(2);
		quickSort( quickQueue );
		mergeSort( mergeQueue );
		System.out.println( "quickSort: " + quickQueue.toString() );
		System.out.println( "mergeSort: " + mergeQueue.toString() );
	}
	
	/**
	 * This function does the actual sorting work of mergeSort(). It adds
	 * the items into the queue in the appropriate order.
	 * 
	 * @param smallQueue, the lesser half of the integers in the queue
	 * @param bigQueue, the greater half of the integers in the queue
	 * @param mergedQueue, the sorted queue
	 */
	@SuppressWarnings("unchecked")
	public static void merge( Queue smallQueue, Queue bigQueue, Queue mergedQueue ) {
		
		while( !smallQueue.isEmpty() && !bigQueue.isEmpty() ) {
			
			// Decide which one the queues to take the next element from
			if( (int) smallQueue.peek() < (int) bigQueue.peek() ) {
				mergedQueue.offer( smallQueue.poll() );
			} else {
				mergedQueue.offer( bigQueue.poll() );
			}
		}
		
		// These move any leftovers that might arise
		while( !smallQueue.isEmpty() ) {
			mergedQueue.offer( smallQueue.poll() );
		}
		while( !bigQueue.isEmpty() ) {
			mergedQueue.offer( bigQueue.poll() );
		}
	}
	
	/**
	 * The merge sort function sorts data using a divide and conquer approach.
	 * It splits the problem into two pieces based on size. It then recursively
	 * splits the problem down until the sub units are less than size two. At this
	 * point, it calls its helper function, merge(), and sorts the items as it
	 * adds them into a new queue.
	 * @param queue, the integers to be sorted
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void mergeSort( Queue queue ) {
		int n = queue.size();
		if( n < 2 ) return;			// An array of one is sorted already
		// make new queues
		Queue smallQueue = new LinkedBlockingQueue();
		Queue bigQueue = new LinkedBlockingQueue();
		
		while(smallQueue.size() < n / 2) {
			smallQueue.offer( queue.poll() );
		}
		while(!queue.isEmpty()) {
			bigQueue.offer( queue.poll() );
		}
		
		// two recursive calls to solve the problem (divide and conquer)
		mergeSort( smallQueue );
		mergeSort( bigQueue );
		// use the merge helper function to add all of the items into mergedArray
		merge(smallQueue, bigQueue, queue);
	}
	
	/**
	 * The quickSort function is the opposite of the mergeSort. This sort uses
	 * the first element in the queue as its pivot. It splits the numbers
	 * less than the pivot into an L queue and the E queue is the pivot itself,
	 * while the G pivot gets those over the E pivot value. Splitting all of 
	 * these this way does the sorting on the front end. At the end, they are just
	 * concatenated.
	 * 
	 * @param queue, the data to be sorted
	 * @param comp, the comparator used to sort objects
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void quickSort( Queue queue ) {
		int n = queue.size();
		if ( n < 2 ) return;
		// use pivot to split the problem into three
		int pivot = (int) queue.peek();
		Queue L = new LinkedBlockingQueue();
		Queue E = new LinkedBlockingQueue();
		Queue G = new LinkedBlockingQueue();
		while( !queue.isEmpty() ) {
			int element = (int) queue.poll();
			
			if( element < pivot ) {
				L.offer( element );
			} else if( element == pivot ){
				E.offer( element );
			} else {
				G.offer( element );
			}
		}
		// conquer the proplem with recursion
		quickSort( L );
		quickSort( G );
		// concatenate the results
		while( !L.isEmpty() ) {
			queue.offer( L.poll() );
		}
		while( !E.isEmpty() ) {
			queue.offer( E.poll() );
		}
		while( !G.isEmpty() ) {
			queue.offer( G.poll() );
		}
	}
	
}
