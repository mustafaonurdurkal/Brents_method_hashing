import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

public class BrentHashMap<K, V, Integer> extends AbstractHashMap<K, V, Integer> {

	private MapEntry<K, V, Integer>[] table;

	public BrentHashMap() {
		super();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public BrentHashMap(int cap) {
		super(cap);
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public BrentHashMap(int cap, int p) {
		super(cap, p);
	}

	@Override
	public Iterable<Entry<K, V, Integer>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void createTable() {
		this.table = (MapEntry<K, V, Integer>[]) new MapEntry[capacity]; // safe cast
	}

	@Override

	public void search(K k, V v) {
		int intKey = (int) k;
		int hashedValue = intKey % capacity;
		int inc = 0;
		int maximum = 2013;
		while (table[hashedValue] != null && !table[hashedValue].getKey().equals(k)) {
			hashedValue = (intKey % capacity + inc * ((int) k / capacity)) % capacity;
			inc++;
			if (inc > maximum)
				break;
		}
		if (table[hashedValue] == null || inc > maximum) {
			System.out.println("Key not found please try agein");
		} else
			System.out.println("Key=" + intKey + "Count=" + table[hashedValue].getCount() + "Index=" + hashedValue);

	}

	public void sinep() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null)
				System.out.println(i + ":" + table[i].getKey() + "-" + table[i].getValue());
		}
	}

	
	
	@Override
	protected V bucketPut(int h, K k, V v,int count) {
		System.out.println(v);
		int intKey = (int) k;
		int hash = intKey % capacity;
		int a = hash;
		int b = 1;
		while (table[hash] != null) {
			// ayni kelime geldi
			if (table[hash].getValue().equals(v)) {
				count = (int) table[hash].getCount() + 1;
				break;
			} else {
				MapEntry<K, V, Integer> tableHash = table[hash];
				int tableIncrement = 0;
				int tableHashedValue = (int)tableHash.getKey() % capacity;
				while(!table[tableHashedValue].getValue().equals(tableHash.getValue())){
					tableIncrement++;
				    tableHashedValue = ((int)tableHash.getKey() % capacity + tableIncrement * ( (int)tableHash.getKey()/capacity)) % capacity;
				}
				if(tableIncrement>b) {
					table[hash] = new MapEntry<K, V, Integer>(k, v, count); 
					bucketPut(0,tableHash.getKey(),tableHash.getValue(),(int)tableHash.getCount());
				}else {
					hash = (a + b * (intKey / capacity) % capacity) % capacity;
					b++;
				}		
				System.out.println(hash + " " + b + " " + intKey + " " + k + "" + v);
			}

		}
		table[hash] = new MapEntry<K, V, Integer>(k, v, count);
		return v;
	}

	@Override
	protected V bucketRemove(int h, K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected V bucketGet(int h, K k) {
		// TODO Auto-generated method stub
		return null;
	}

}
