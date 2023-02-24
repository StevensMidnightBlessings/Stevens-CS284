package IDLList;
/*
 * 
 * 2/2/23
 * I pledge my honor I have abided by the Stevens Honor System.
 */
import java.util.ArrayList;

public class IDLList<E> {
    //private fields
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    //nodes are static
    public static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E elem) {
            this.data = elem;
        }

        private Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
    }
   
    public int size() {
    	return size;
    }
    
    public IDLList() {
        //creates empty double-linked list
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.indices = new ArrayList<Node<E>>();
    }

    public boolean add(int index, E elem) {
        //adds elem at position index (counting from wherever head is). It uses the index for fast access. Following java.util.List, valid indices are in the range 0 to size (inclusive). 
        //Calling add(size, elem) makes elem the new tail. It always returns true.
        if(index < 0 || index > size ) {
            throw new ArrayIndexOutOfBoundsException();
        } else if(size == 0 || index == 0) {
            add(elem);
        } else if(index == size) {
            append(elem);
        } else {
            Node<E> cur = indices.get(index);
			Node<E> newItem = new Node<E>(elem, cur.prev, cur);
			cur.prev.next = newItem;
			cur.prev = newItem;
			size += 1;
			indices.add(index, newItem);
        }
        return true;
    }

    public boolean add(E elem) {
        //adds elem at head, uses index for fast access, always returns true
        if(head == null) {
            Node<E> newItem = new Node<E>(elem);
            head = newItem;
            tail = head;
            size += 1;
            indices.add(newItem);
        } else {
            Node<E> newHead = new Node<E>(elem, null, head);
            head.prev = newHead;
            head = newHead;
            size += 1;
            indices.add(0, newHead);
        }
        return true;
    }

    public boolean append(E elem) {
        //adds elem at tail, always returns true
        if(tail == null) {
            add(elem);
        } else {
            tail.next = new Node<E>(elem, tail, null);
            tail = tail.next;
            size += 1;
            indices.add(tail);
        }
        return true;
    }

    public E get(int index) {
        //returns data 
        if(index == 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(size == 0) {
            throw new IllegalStateException("Size can't be 0.");
        }
        Node<E> item = indices.get(index);
        return item.data;
    }

    public E getHead() {
        //checks if list is empty and returns head node
        if(this.size == 0) {
            throw new NullPointerException("Empty list.");
        }
        return this.head.data;

    }

    public E getLast() {
        //checks if list is empty and returns tail
        if(this.size == 0) {
            throw new NullPointerException("Empty list.");
        }
        return this.tail.data;
    }
    
    public E remove() {
    	if(head == null) {
    		throw new IllegalStateException("No head");
    	} else {
    		head.next.prev = null;
    		head = head.next;
    		size -= 1;
    		return indices.remove(0).data;
    	}
    }

    public E removeLast() {
        //removes last element in the list
        if(tail == null) {
        	throw new IllegalStateException("No such element");
        } else {
        	tail.prev.next = null;
        	tail = tail.prev;
        	size -= 1;
        	return indices.remove(size).data;
        }
    }

    public E removeAt(int index) {
        //removes and returns the element at the head. Should throw an IllegalStateException if there is no such element.
        if((index < 0) || (index > size)) {
        	throw new IllegalStateException("No such element");
        }
    	if(index == 0) {
            return remove();
        } else if(index == size) {
        	return removeLast();
        } else {
        	Node<E> current = indices.get(index);
        	current.next.prev = current.prev;
        	current.prev.next = current.next;
        	size --;
        	return indices.remove(index).data;
        }
    }

    public boolean remove(E elem) {
        //removes the first occurrence of elem in the list and returns true. Return false if elem was not in the list.
        if(size == 0) {
            throw new IllegalStateException("Empty list.");
        } 
        for(int i = 0;i < size; i++) {
        	if(indices.get(i).data == elem) {
        		removeAt(i);
        		return true;
        	}
        }
        return false;
    }

    public String toString() {
        //represents list of string
    	String temp = "[";
    	for(int i = 0;i<size;i++) {
    		if(i == size - 1) {
    			temp += indices.get(i).data.toString() + "]";
    		} else {
    			temp += indices.get(i).data.toString() + ",";
    		}
    	}
    	return temp;
    }

    public static void main(String[] args) {
        IDLList<Integer> test1 = new IDLList<Integer>();
    }
}
