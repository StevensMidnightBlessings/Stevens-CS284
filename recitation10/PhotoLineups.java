import java.util.Scanner;
import java.util.ArrayList;

public class PhotoLineups {

   // TODO: Write method to create and output all permutations of the list of names.
   public static void printAllPermutations(ArrayList<String> permList, ArrayList<String> nameList) {
      if(nameList.isEmpty()) {
         for(int i = 0;i<permList.size();i++) {
            if(i == permList.size() - 1) {
               System.out.print(permList.get(i));
            } else {
               System.out.print(permList.get(i) + ", ");
            }
         }
         System.out.println();
      } else {
         for(int i = 0;i<nameList.size();i++) {
            ArrayList<String> perm = new ArrayList<String>(permList);
            ArrayList<String> names = new ArrayList<String>(nameList);
            perm.add(nameList.get(i));
            names.remove(i);
            printAllPermutations(perm, names);
         }
      }
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      ArrayList<String> nameList = new ArrayList<String>();
      ArrayList<String> permList = new ArrayList<String>();
      String name = scnr.next();
      
      // TODO: Read in a list of names; stop when -1 is read. Then call recursive method.
      while(!name.equals("-1")) {
         nameList.add(name);
         name = scnr.next();
      }
      printAllPermutations(permList, nameList);
   }
}
