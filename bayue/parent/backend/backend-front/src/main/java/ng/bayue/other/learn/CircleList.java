package ng.bayue.other.learn;

import java.util.AbstractSequentialList;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class CircleList<E> extends AbstractSequentialList<E>
		implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

	/**  */
	private static final long serialVersionUID = -6283018477767525827L;

	private transient int size;

	private transient Node<E> first;

	private transient Node<E> last;

	@Override
	public int size() {
		return size;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		checkPositionIndex(index);
		return new ListItr(index);
	}

	@Override
	public boolean add(E e) {
		linkLast(e);
		return true;
	}

	private void linkFirst(E element) {
		final Node<E> f = first;
		final Node<E> newNode = new Node<E>(null, element, f);
		first = newNode;
		if (null == f)
			last = newNode;
		else
			f.prev = newNode;
		size++;
		modCount++;
	}

	private void linkLast(E element) {
		final Node<E> l = last;
		final Node<E> newNode = new Node<E>(l, element, null);
		last = newNode;
		if (null == l)
			first = newNode;
		else
			l.next = newNode;
		size++;
		modCount++;
	}

	private void linkBefore(E e, Node<E> node) {
		final Node<E> pred = node.prev;
		final Node<E> newNode = new Node<E>(pred, e, node);
		node.prev = newNode;// 更新node节点的前节点为新插入的节点
		if (null == pred)
			first = newNode;
		else
			pred.next = newNode;
		size++;
		modCount++;
	}

	private void linkAfter(E e, Node<E> node) {
		final Node<E> after = node.next;
		final Node<E> newNode = new Node<E>(node, e, after);
		node.next = newNode;
		if (null == after)
			last = newNode;
		else
			after.prev = newNode;
		size++;
		modCount++;
	}

	private E unlinkFirst(Node<E> f) {
		// assert f == first && f != null;
		final E element = f.element;
		final Node<E> next = f.next;
		f.element = null;
		f.next = null; // help GC
		first = next;
		if (next == null)
			last = null;
		else
			next.prev = null;
		size--;
		modCount++;
		return element;
	}

	/**
	 * Unlinks non-null last node l.
	 */
	private E unlinkLast(Node<E> l) {
		// assert l == last && l != null;
		final E element = l.element;
		final Node<E> prev = l.prev;
		l.element = null;
		l.prev = null; // help GC
		last = prev;
		if (prev == null)
			first = null;
		else
			prev.next = null;
		size--;
		modCount++;
		return element;
	}

	private E unlink(Node<E> x) {
		// assert x != null;
		final E element = x.element;
		final Node<E> next = x.next;
		final Node<E> prev = x.prev;

		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
			x.prev = null;
		}

		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
			x.next = null;
		}

		x.element = null;
		size--;
		modCount++;
		return element;
	}

	private static class Node<E> {
		E element;
		Node<E> prev;// 前一节点
		Node<E> next;// 后一节点

		public Node(Node<E> prev, E element, Node<E> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}
	}

	public void circle(int index) {
		checkElementIndex(index);
		Node<E> f = first;
		Node<E> l = last;
		f.prev = last;
		l.next = first;

		Node<E> n = node(index);
		Node<E> pred = n.prev;
		first = n;
		first.prev = null;
		if (null == pred) {
			last = n;
		} else {
			last = pred;
		}
		last.next = null;
	}

	public void inverse() {
		final Node<E> f = first;
		final Node<E> l = last;
		Node<E> fn = f.next;
		while (null != fn) {
			switchNode(first, fn);
		}

	}
	
	public static void main(String[] args) {
		CircleList<String> list = new CircleList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		System.out.println(list);
		list.inverse();
		System.out.println(list);
	}

	private void switchNode(Node<E> node, Node<E> next) {
		final Node<E> t = node;
		node = next;
		node.prev = t.prev;
		node.next = t.next;
		next = t;
		next.prev = next.prev;
		next.next = next.next;

	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private class ListItr implements ListIterator<E> {
		private Node<E> lastReturned = null;
		private Node<E> next;
		private int nextIndex;
		private int expectedModCount = modCount;

		ListItr(int index) {
			// assert isPositionIndex(index);
			next = (index == size) ? null : node(index);
			nextIndex = index;
		}

		public boolean hasNext() {
			return nextIndex < size;
		}

		public E next() {
			checkForComodification();
			if (!hasNext())
				throw new NoSuchElementException();

			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.element;
		}

		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		public E previous() {
			checkForComodification();
			if (!hasPrevious())
				throw new NoSuchElementException();

			lastReturned = next = (next == null) ? last : next.prev;
			nextIndex--;
			return lastReturned.element;
		}

		public int nextIndex() {
			return nextIndex;
		}

		public int previousIndex() {
			return nextIndex - 1;
		}

		public void remove() {
			checkForComodification();
			if (lastReturned == null)
				throw new IllegalStateException();

			Node<E> lastNext = lastReturned.next;
			unlink(lastReturned);
			if (next == lastReturned)
				next = lastNext;
			else
				nextIndex--;
			lastReturned = null;
			expectedModCount++;
		}

		public void set(E e) {
			if (lastReturned == null)
				throw new IllegalStateException();
			checkForComodification();
			lastReturned.element = e;
		}

		public void add(E e) {
			checkForComodification();
			lastReturned = null;
			if (next == null)
				linkLast(e);
			else
				linkBefore(e, next);
			nextIndex++;
			expectedModCount++;
		}

		final void checkForComodification() {
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
		}
	}

	Node<E> node(int index) {
		// assert isElementIndex(index);

		if (index < (size >> 1)) {
			Node<E> x = first;
			for (int i = 0; i < index; i++)
				x = x.next;
			return x;
		} else {
			Node<E> x = last;
			for (int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	public E removeFirst() {
		final Node<E> f = first;
		if (f == null)
			throw new NoSuchElementException();
		return unlinkFirst(f);
	}

	/**
	 * Removes and returns the last element from this list.
	 *
	 * @return the last element from this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	public E removeLast() {
		final Node<E> l = last;
		if (l == null)
			throw new NoSuchElementException();
		return unlinkLast(l);
	}

	public E remove(int index) {
		checkElementIndex(index);
		return unlink(node(index));
	}

	public boolean remove(Object o) {
		if (o == null) {
			for (Node<E> x = first; x != null; x = x.next) {
				if (x.element == null) {
					unlink(x);
					return true;
				}
			}
		} else {
			for (Node<E> x = first; x != null; x = x.next) {
				if (o.equals(x.element)) {
					unlink(x);
					return true;
				}
			}
		}
		return false;
	}

	public E get(int index) {
		checkElementIndex(index);
		return node(index).element;
	}

	@Override
	public void addFirst(E e) {
		linkFirst(e);
	}

	@Override
	public void addLast(E e) {
		linkLast(e);
	}

	@Override
	public boolean offerFirst(E e) {
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		return false;
	}

	@Override
	public E pollFirst() {
		return null;
	}

	@Override
	public E pollLast() {
		return null;
	}

	@Override
	public E getFirst() {
		return null;
	}

	@Override
	public E getLast() {
		return null;
	}

	@Override
	public E peekFirst() {
		return null;
	}

	@Override
	public E peekLast() {
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		return false;
	}

	@Override
	public boolean offer(E e) {
		return false;
	}

	@Override
	public E remove() {
		return null;
	}

	@Override
	public E poll() {
		return null;
	}

	@Override
	public E element() {
		return null;
	}

	@Override
	public E peek() {
		return null;
	}

	@Override
	public void push(E e) {
	}

	@Override
	public E pop() {
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		return null;
	}

}
