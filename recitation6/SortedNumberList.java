
public class SortedNumberList {
	public Node head;
	public Node tail;

	public SortedNumberList() {
		head = null;
		tail = null;
	}

   // Optional: Add any desired private methods here
   
   
	// Inserts the number into the list in the correct position such that the
	// list remains sorted in ascending order.
	public void insert(double number) {
	   Node newNode = new Node(number);
      if(head == null) {
        head = newNode;
        tail = newNode;
      } else if(number <= head.getData()) {
         newNode.setNext(head);
         head.setPrevious(newNode);
         head = newNode;
      } else if (number >= tail.getData()) {
         newNode.setPrevious(tail);
         tail.setNext(newNode);
         tail = newNode;
      } else {
         Node current = head.getNext();
         while (current != null && current.getData() < number) {
            current = current.getNext();
         }
         newNode.setNext(current);
         newNode.setPrevious(current.getPrevious());
         current.getPrevious().setNext(newNode);
         current.setPrevious(newNode);
      } 
	}

	// Removes the node with the specified number value from the list. Returns
	// true if the node is found and removed, false otherwise.
	public boolean remove(double number) {
	   // Your code here
	   if(head == null) {
	      return false;
	   }
	   
	   if(head.data == number) {
	      head = head.getNext();
        if (head != null) {
            head.setPrevious(null);
        } else {
            tail = null;
        }
        return true;
      }
      Node current = head.getNext();
      while (current != null) {
         if (current.getData() == number) {
            current.getPrevious().setNext(current.getNext());
            if (current.getNext() != null) {
               current.getNext().setPrevious(current.getPrevious());
            } else {
               tail = current.getPrevious();
            }
            return true;
         }
         current = current.getNext();
      }
	   return false;
	}
}
