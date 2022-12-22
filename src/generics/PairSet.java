package generics;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PairSet<T> extends AbstractSet<T> {
	private T item0;
	private T item1;
	private int size;

	public PairSet() {
		size = 0;
	}

	public PairSet(T value) {
		this(value, null);
	}

	public PairSet(T firstValue, T secondValue) {
		this.size = 0;
		setAvailableSlot(firstValue);
		setAvailableSlot(secondValue);
	}

	private boolean setAvailableSlot(T value) {
		if (value == null) {
			return false;
		}
		switch (size) {
			case 0 -> {
				item0 = value;
				size = 1;
				return true;
			}
			case 1 -> {
				if (item0.equals(value)) {
					return false;
				}
				item1 = value;
				size = 2;
				return true;
			}
			default -> {
				return false;
			}
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int next = 0;

			@Override
			public boolean hasNext() {
				return next < size;
			}

			@Override
			public T next() {
				var result = switch (next) {
					case 0 -> item0;
					case 1 -> item1;
					default -> throw new NoSuchElementException();
				};
				++next;
				return result;
			}
		};
	}

	@Override
	public boolean add(T e) {
		return setAvailableSlot(e);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (size == 2) {
			return false;
		}
		boolean result = false;
		for (T value : c) {
			if (size == 2) {
				break;
			}
			result |= setAvailableSlot(value);
		}
		return result;
	}

	// // TEST:
	// public static void main(String[] args) {
	// 	var parents = new PairSet<String>("mother", "father");

	// 	System.out.println(parents);
	// 	System.out.println(parents.contains("moth"));
	// 	System.out.println(parents.add("third parent"));
	// 	System.out.println(parents);
	// 	System.out.println(parents.contains("third parent"));
	// }
}
