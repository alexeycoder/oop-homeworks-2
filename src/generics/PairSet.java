package generics;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация Множества, допускающего не более двух элементов.
 * Можно только добавлять элементы.
 */
public class PairSet<E> extends AbstractSet<E> {
	public static final int MAX_SIZE = 2;
	private E item0;
	private E item1;
	private int size;

	boolean changed = true;
	private int hashCode;

	public PairSet() {
		size = 0;
	}

	public PairSet(E value) {
		this(value, null);
	}

	public PairSet(E firstValue, E secondValue) {
		this.size = 0;
		setAvailableSlot(firstValue);
		setAvailableSlot(secondValue);
	}

	private boolean setAvailableSlot(E value) {
		if (value == null) {
			return false;
		}
		switch (size) {
			case 0 -> {
				item0 = value;
				size = 1;
				changed = true;
				return true;
			}
			case 1 -> {
				if (item0.equals(value)) {
					return false;
				}
				item1 = value;
				size = 2;
				changed = true;
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
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int next = 0;

			@Override
			public boolean hasNext() {
				return next < size;
			}

			@Override
			public E next() {
				E result = switch (next) {
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
	public boolean add(E e) {
		return setAvailableSlot(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		if (size == MAX_SIZE) {
			return false;
		}
		boolean result = false;
		for (E value : c) {
			if (size == MAX_SIZE) {
				break;
			}
			result |= setAvailableSlot(value);
		}
		return result;
	}

	@Override
	public int hashCode() {
		if (changed) {
			changed = false;
			hashCode = Objects.hashCode(item0) + Objects.hashCode(item1);
		}
		return hashCode;
	}

	// // TEST:
	// public static void main(String[] args) {
	// var parents = new PairSet<String>("mother", "father");

	// System.out.println(parents);
	// System.out.println(parents.contains("moth"));
	// System.out.println(parents.add("third parent"));
	// System.out.println(parents);
	// System.out.println(parents.contains("third parent"));
	// parents.remove("father");
	// parents.add("third parent");
	// System.out.println(parents);
	// }
}
