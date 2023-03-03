import java.util.*;
import java.io.PrintWriter;

public class GroceryList {
	protected ArrayList<String> listItems = new ArrayList<String>();
	protected Stack<UndoCommand> undoStack = new Stack<UndoCommand>();

	public void addWithUndo(String newItemName) {
		// Add the new list item
		listItems.add(newItemName);

		// Make an undo command that removes the last item and push onto stack
		undoStack.push(new RemoveLastCommand(listItems));
	}

	public void removeAtWithUndo(int removalIndex) {
	   String removedItem = listItems.remove(removalIndex);

      InsertAtCommand command = new InsertAtCommand(listItems, removalIndex, removedItem);
      undoStack.push(command);
	}

	public void swapWithUndo(int index1, int index2) {
		String temp = listItems.get(index1);
      listItems.set(index1, listItems.get(index2));
      listItems.set(index2, temp);
      SwapCommand command = new SwapCommand(listItems, index1, index2);
      undoStack.push(command);
	}

   // Pop and execute the command at the top of the stack
	public void executeUndo() {
      // Your code here
      if(!undoStack.isEmpty()) {
         UndoCommand command = undoStack.pop();
         command.execute();
      }
	}

	final int getListSize() {
		return listItems.size();
	}

	final int getUndoStackSize() {
		return undoStack.size();
	}

	final ArrayList<String> getVectorCopy() {
		return listItems;
	}

	public void print(PrintWriter outputStream) {
		for (int i = 0; i < listItems.size(); i++) {
			outputStream.write(i + ". " + listItems.get(i) + "\n");
		}
	}
}
