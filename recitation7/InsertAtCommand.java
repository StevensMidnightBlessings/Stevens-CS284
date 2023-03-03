import java.util.ArrayList;

public class InsertAtCommand extends UndoCommand {
	// Your field declarations here
	private ArrayList<String> items;
	private int index;
	private String item;
	
	// Your constructor code here
	public InsertAtCommand(ArrayList<String> items, int index, String item) {
	   this.items = items;
	   this.index = index;
	   this.item = item;
	}
	
	@Override
	public void execute() {
		// Your code here
		items.add(index, item);
	}
	public void undo() {
	   items.remove(index);
	}
}
