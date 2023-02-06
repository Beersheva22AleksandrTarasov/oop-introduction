package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;

	@Override
	public V put(K key, V value) {
		V result = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			result = entry.getValue();
			entry.setValue(value);
		} else {
			set.add(new Entry<>(key, value));
		}
		return result;
	}

	@Override
	public V putIfAbsent(K key, V value) {

		return get(key) != null ? value : put(key, value);
	}

	@Override
	public V get(K key) {
		V result = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			result = entry.getValue();
		}
		return result;
	}

	@Override
	public V getOrDefault(K key, V value) {
		V result = get(key);
		return result != null ? result : value;
	}

	@Override
	public boolean containsKey(K key) {

		return get(key) != null;
	}

	@Override
	public boolean containsValue(V value) {

		return set.stream().anyMatch(n -> n.getValue().equals(value));
	}

	@Override
	public Collection<V> values() {
		Collection<V> list = new LinkedList<>();
		set.forEach(n -> list.add(n.getValue()));
		return list;
	}

	@Override
	public Set<K> keySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<K> result = set.getClass().getConstructor().newInstance();
			set.forEach(n -> result.add(n.getKey()));
			return result;
		} catch (Exception e) {
			throw new IllegalStateException();
		}

	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		try {
			@SuppressWarnings("unchecked")
			Set<Entry<K, V>> result = set.getClass().getConstructor().newInstance();
			set.forEach(n -> result.add(n));
			return result;
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public V remove(K key) {
		V result = null;
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		if (entry != null) {
			result = entry.getValue();
			set.remove(entry);
		}
		return result;
	}

}
