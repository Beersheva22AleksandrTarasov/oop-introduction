package telran.util;

import java.util.Objects;

public interface Map<K, V> {
	class Entry<K, V> implements Comparable<Entry<K, V>> {
		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		@Override
		public int hashCode() {
			return Objects.hash(key);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			return Objects.equals(key, other.key);
		}

		private K key;
		private V value;

		@SuppressWarnings({ "unchecked" })
		@Override
		public int compareTo(Entry<K, V> o) {

			return ((Comparable<K>) key).compareTo(o.key);
		}

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

	/**
	 * 
	 * @param key
	 * @param value either adds new entry or updates value in the existing one
	 * @return null if new entry is added
	 * @return reference to old value if the value is replaced with new value
	 */
	V put(K key, V value);

	/***********************************************/
	/**
	 * 
	 * @param key
	 * @param value adds new entry if entry doesn't exist
	 * @return null if new entry is added
	 * @return reference to value if entry exists
	 */
	V putIfAbsent(K key, V value);

	/*********************************************************/
	/**
	 * 
	 * @param key
	 * @return value matching a given key or null if no entry with the key
	 */
	V get(K key);

	/****************************************************************/
	/**
	 * 
	 * @param key
	 * @param value
	 * @return a value matching a given key or the given value (second parameter) if
	 *         no entry with the key
	 */
	V getOrDefault(K key, V value);

	/**
	 * 
	 * @param key
	 * @return true if there is an entry with a given key otherwise false
	 */
	boolean containsKey(K key);

	/************************************************************/
	/**
	 * 
	 * @param value
	 * @return true if there is an entry with a given value otherwise false
	 */
	boolean containsValue(V value);

	/*******************************************************************/
	/**
	 * 
	 * @return collection of values
	 */
	Collection<V> values();

	/*********************************************************************/
	/**
	 * 
	 * @return Set with key values
	 */
	Set<K> keySet();

	/********************************************************************/
	/**
	 * 
	 * @return set with entry objects
	 */
	Set<Entry<K, V>> entrySet();

	/************************************************************************/
	/**
	 * 
	 * @param key removes entry with a given key value
	 * @return either reference to value of removed entry (removed) or null (not
	 *         removed)
	 */
	V remove(K key);

}
