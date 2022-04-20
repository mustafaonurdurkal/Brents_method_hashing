import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V, C> extends AbstractMap<K, V, C> {
	protected int n = 0; // number of entries in the dictionary
	protected int capacity; // length of the table
	private int prime; // prime factor
	private long scale, shift; // the shift and scaling factors

	/** Creates a hash table with the given capacity and prime factor. */
	public AbstractHashMap(int cap, int p) {
		prime = p;
		capacity = cap;
		Random rand = new Random();
		scale = rand.nextInt(prime - 1) + 1;
		shift = rand.nextInt(prime);
		createTable();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public AbstractHashMap(int cap) {
		this(cap, 109345121);
	} // default prime

	/** Creates a hash table with capacity 17 and prime factor 109345121. */
	public AbstractHashMap() {
		this(17);
	} // default capacity

	// public methods

	@Override
	public int size() {
		return n;
	}

	@Override
	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	@Override
	public V put(K key, V value) {
		// System.out.println(key);
		V answer = bucketPut(hashValue(key), key, value,1);
		// System.out.println(key);
		if (n > capacity / 2) // keep load factor <= 0.5
			resize(2 * capacity - 1); // (or find a nearby prime)
		return answer;
	}

	// private utilities
	/** Hash function applying MAD method to default hash code. */
	private int hashValue(K key) {

		return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
	}

	/** Updates the size of the hash table and rehashes all entries. */
	private void resize(int newCap) {
		ArrayList<Entry<K, V, C>> buffer = new ArrayList<>(n);
		for (Entry<K, V, C> e : entrySet())
			buffer.add(e);
		capacity = newCap;
		createTable(); // based on updated capacity
		n = 0; // will be recomputed while reinserting entries
		for (Entry<K, V, C> e : buffer)
			put(e.getKey(), e.getValue());
	}

	// protected abstract methods to be implemented by subclasses
	/** Creates an empty table having length equal to current capacity. */
	protected abstract void createTable();

	protected abstract V bucketGet(int h, K k);

	protected abstract V bucketPut(int h, K k, V v,int count);

	protected abstract V bucketRemove(int h, K k);

	public abstract void search(K k, V v) ;
}