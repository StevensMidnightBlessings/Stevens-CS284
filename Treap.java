/*
 *  Name: Ivan Chow
 *	Pledge: I pledge my honor I have abided by the Stevens Honor System.
 *  4/13/23
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E extends Comparable<E>> {
		//data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			//Creates a new node with the given data and priority. The pointers to child nodes are null. Throw exceptions if data is null
			if (data == null) {
				throw new IllegalArgumentException("Pointer data is null");
			}
			this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
		}

		public Node<E> rotateRight() {
			//update using pointers
			if (this.left == null) {
				return this;
			}
				
			Node<E> node = this.left;
			this.left = node.right;
			node.right = this;
			return node;
		}
		public Node<E> rotateLeft() {
			//update using pointers
			if (this.right == null) {
				return this;
			}
				
			Node<E> node = this.right;
			this.right = node.left;
			node.left = this;
			return node;
		}
		
		@Override
		public String toString() {
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}

	/*
     * Treap Class
     */

    //data fields
	private Random priorityGenerator;
	private Node<E> root;

	//constructors
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
        //creates an empty treap and initializes priorityGenerator using new Random (seed)
        this.root = null;
        this.priorityGenerator = new Random(seed);
    }

	/* Hints
     * storing each node in the path from the root until the spot where the new node will be inserted, in a stack
     * after performing the insertion, use a helper function reheap (with appropriate parameters that should include the stack) to restore the heap invariant. 
     * Note that if our nodes had pointer to their parents, then we would not need a stack.
     * have add(E key) call the add(E key, int priority) method once it has generated the random priority. Thus all the “work” is performed by the latter method
    */
	
	public boolean add(E key) {
		// Calling add(E key, int priority) after generating random priority
        int priority = priorityGenerator.nextInt();
        return add(key, priority);
    }

	public boolean add(E key, int priority) {
		Node node = root;
		Stack stack = new Stack<>();
        
		//returns true if empty tree, inserts at root
		if(this.root == null){
			this.root = new Node(key, priority);
			return true;
		}
		
		/*
		 * Traverse the tree
		 * compare the keys, if less -> go right subtree, if greater -> go left subtree, else the key already is in the tree and we return false
		 * then reheap to restore heap invariant
		 */
		Boolean debounce = true;
		while(debounce) {
            int comparison = node.data.compareTo(key);
			stack.add(node);

            if(comparison < 0) {
				if(node.right == null) {
                    debounce = false;
					node.right = new Node(key, priority);
				}
				node = node.right;
			} else if(comparison > 0) { 
				if(node.left == null) {
                    debounce = false;
					node.left = new Node(key, priority);
				}
				node = node.left;
			} else {
				return false;
			}
		}
		//restore heap invariant
		reheap(stack, node);
		return true;
	}

	private void reheap(Stack<Node<E>> stack, Node<E> current) {
		/*
		 * use stack to keep track 
		 * check priorities
		 * rotate to move up the tree
		 */
		Node<E> parent = null;
		
		//checks if stack is not empty and the current priority > parent's priority
	    while (!stack.isEmpty() && current.priority > (parent = stack.peek()).priority) {
	    	//pop the stack to access the next node and to prevent infinite loop
	    	stack.pop();
	        //rotate
	        if (parent.left == current) {
	            parent.rotateRight();
	        } else {
	            parent.rotateLeft();
	        }
	        /*
	         * if empty -> current node becomes root
	         * checks if parent's node was the left child of the node that is at the top of the stack
	         *  	- top of the stack's left child becomes current 
	         *  	- else -> top of the stack's right child becomes current
	         */
	        if (stack.isEmpty()) {
	            root = current;
	        } else if (stack.peek().left == parent) {
	            stack.peek().left = current;
	        } else {
	            stack.peek().right = current;
	        }
	    }
	}

	public boolean delete(E key) {
		//deletes the node with the given key from the treap and returns true. If not found, returns false.
    	//find the node, then trickle down with rotate and when its a leaf node, set to null
		Node node = root;
		Stack<Node<E>> Stacks = new Stack();

		//looks for node, if not in tree -> return false
		if(!this.find(key)) {
			return false;
		}
		
		while(true) {
			Stacks.add(node); //keeps track of nodes
            int comparison = node.data.compareTo(key);
			if(comparison < 0) {
				node = node.right;
			} else if(comparison > 0) {
				node = node.left;
			} else if(comparison == 0) {
				break;
			}
		}
		
        // in order to keep track of parent, pop the node to peek the parent 
		node = Stacks.pop();
		Node parent = Stacks.peek();
		
        /*
         * trickle until leaf node
         * check if node has left and right child
         * if left.data > right.data -> right rotation
         * if left.data < right.data -> left rotation
         */

		while(node.left != null && node.right != null){
			if(node.left.data.compareTo(node.right.data) > 0){ 
				if(parent.right == node) {
					parent.right = node.left;
				} else {
					parent.left = node.left;
				}
				parent = node.left;
				node.rotateRight();
			} else {
				if(parent.right == node) {
					parent.right = node.right;
				} else {
					parent.left = node.right;
				}
				parent = node.right;
				node.rotateLeft();
			}
		}
		
        /*
         * Deletion
         *  - no children
         *  - left child, no right
         *  - right child, no left
         *  - both children
         */
        
        // no children
		if(node.left == null && node.right == null) {
            if(parent.left == node) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        //left child but no right child
        } else if(node.left != null && node.right == null) {
            if(parent.left == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        //right child but no left child
        } else if(node.left == null && node.right != null) {
            if(parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        //both children
        } else {
            Node<E> successor = node.right;
            Node<E> succParent = node;
            while(successor.left != null) {
                succParent = successor;
                successor = successor.left;
            }
            node.data = successor.data;
            if(succParent.left == successor) {
                succParent.left = successor.right;
            } else {
                succParent.right = successor.right;
            }
        }
		return true;
	}
	
	private boolean find(Node<E> root, E key) {
		///Finds a node with the given key in the treap and returns true if it finds it and false otherwise.
		if(root == null) {
			return false;
		}
		
		int comparison = key.compareTo(root.data);
		if(comparison == 0) {
			return true;
		} else if(comparison > 0) {
			return find(root.right, key);
		} else {
			return find(root.left, key);
		}
	}

	public boolean find(E key) {
		//check if null then calls find(Node<E> root, E key)
		if(key == null) {
			throw new IllegalArgumentException();
		}
		return find(root,key);
	}

	public String toString() {
		// string representation of the treap
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses treap and adds to stringbuilder
		for (int i = 0; i < depth - 1; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	public static void main(String[] args) {
		Treap<Integer> test = new Treap<Integer>();
        test.add(4,19);
        test.add(2,31);
        test.add(6,70);
        test.add(1,84);
        test.add(3,12);
        test.add(5,83);
        test.add(7,26);

        System.out.println(test.toString());
	}
}
