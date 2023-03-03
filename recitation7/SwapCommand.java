import java.util.ArrayList;

public class SwapCommand extends UndoCommand {
	// Your field declarations here
	private ArrayList<String> items;
	private int index1;
	private int index2;
	
	// Your constructor code here
	public SwapCommand(ArrayList<String> items, int index1, int index2) {
	   this.items = items;
	   this.index1 = index1;
	   this.index2 = index2;
	}
	@Override
	public void execute() {
		// Your code here
		String temp = items.get(index1);
		items.set(index1, items.get(index2));
		items.set(index2, temp);
	}

}
