package generics;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * Множество, которое может содержать изменяемые объекты.
 * Можно только добавлять элементы.
 */
public class MutableSet<E> extends AbstractSet<E> {
	private final ArrayList<E> list = new ArrayList<>();

	private boolean changed = false;
	private int hashCode = 0;

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public boolean add(E e) {
		if (list.contains(e)) {
			return false;
		}
		list.add(e);
		changed = true;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean any = false;
		for (E e : c) {
			any |= add(e);
		}
		return any;
	}

	@Override
	public int hashCode() {
		if (changed) {
			changed = false;
			hashCode = 0;
			for (E e : list) {
				hashCode += Objects.hashCode(e);
			}
		}
		return hashCode;
	}

}
