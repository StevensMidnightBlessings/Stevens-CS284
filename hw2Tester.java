package IDLList;
/*
 * 
 * 2/2/23
 * I pledge my honor I have abided by the Stevens Honor System.
 * TESTER
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IDLListTest {
    @Test
    void testAddIndexE() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        test1.add(0, null);
        assertEquals(test1.add(0, null), true);
        test1.add(2, 10);
        assertEquals(test1.add(2, 10), true);
        test1.add(4, 5);
        assertEquals(test1.add(4, 5), true);
    }

    @Test
    void testAddE() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        test1.add(4);
        assertEquals(test1.add(4), true);
        test1.add(10);
        assertEquals(test1.add(10), true);
        test1.add(null);
        assertEquals(test1.add(null), true);
    }
    
    @Test
    void testAppend() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        test1.append(4);
        test1.append(7);
        test1.append(10);
        test1.append(null);
        assertEquals(test1.get(3), 4);
        assertEquals(test1.get(4), 7);
        assertEquals(test1.get(5), 10);
        assertEquals(test1.get(6), null);
    }

    @Test
    void testEGet() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3, 4};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.get(1), 2);
        assertEquals(test1.get(2), 3);
        assertEquals(test1.get(3), 4);
    }

    @Test
    void testGetHead() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.getHead(), 1);
    }

    @Test
    void testGetLast() {
    	IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.getLast(), 3);
    }

    @Test
    void testRemove() {
    	IDLList<Integer> test1 = new IDLList<Integer>();
    	Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.remove(), 1);
        assertEquals(test1.remove(), 2);
    }
    
    @Test
    void testRemoveLast() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.removeLast(), 3);
        assertEquals(test1.removeLast(), 2);
    }

    @Test
    void testRemoveAt() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3, 4};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.removeAt(1), 2);
        assertEquals(test1.removeAt(test1.size()), 4);
    }
    
    @Test
    void testRemoveElem() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        Integer[] arr = {1, 2, 3, 4, 5};
        for(int i =0;i<arr.length;i++) {
        	 test1.append(arr[i]);
        }
        assertEquals(test1.remove(3), true);
        assertEquals(test1.remove(7), false);
        assertEquals(test1.remove(test1.size()), true);
    }
    
    @Test
    void testToString() {
        IDLList<Integer> test1 = new IDLList<Integer>();
        test1.add(5);
        test1.add(4);
        test1.add(3);
        test1.add(2);
        test1.add(1);
        test1.add(0);
		assertEquals("[0,1,2,3,4,5]", test1.toString());
    }
}
