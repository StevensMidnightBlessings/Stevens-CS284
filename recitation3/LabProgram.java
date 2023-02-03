import java.util.Scanner;

public class LabProgram {
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      String[] names = { "Ryley", "Edan", "Reagan", "Henry", "Caius", "Jane", "Guto", "Sonya", "Tyrese", "Johnny" };
      int index;

		index = scnr.nextInt();
      /* Type your code here. */
      
      /* catch {
         (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length 10");
      }
      */
      try {
         System.out.println("Name: " + names[index]);
      } catch (ArrayIndexOutOfBoundsException e) {
         if(index < 0) {
         System.out.println("Exception! Index " + index + " out of bounds for length 10\nThe closest name is: Ryley");
      }
      if(index >= 10) {
         System.out.println("Exception! Index " + index + " out of bounds for length 10\nThe closest name is: Johnny");
      }
      }
      
      
   }
}
