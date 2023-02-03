/*
 * 
 * 2/2/23
 * I pledge my honor I have abided by the Stevens Honor System.
 */

public class BinaryNumber {
	private int length;
	private int data[];

	public BinaryNumber(int length) {
		//constructor
		this.data = new int[length];
		this.length = length;
	}

	public BinaryNumber(String str) {
		this.length = str.length();
		this.data = new int[this.length];
		
		for(int i = 0; i < str.length(); i++) {
			//char java.lang.String.charAt(int index)
        	//int java.lang.Character.getNumericValue(char ch)
			int val = Character.getNumericValue(str.charAt(i));
			data[i] = val;
		}
	}

	public int[] getInnerArray() {
		//returns data
		return data;
	}

	public int getLength() {
		//returns length
		return length;
	}

	public int getDigit(int index) {
		try {
			//returns if array out of bounds is not caught
            return this.data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Error: Index is out of bounds.");
        }
	}

	public int toDecimal() {
		int decimal = 0;
        for(int i = 0; i < length; i++) {
			//iterates and converts
			decimal += (Math.pow(2, getLength()-1-i) * data[i]);
        }
        return decimal;
	}

	public void bitShift(int direction, int amount) {
		//check for exceptions
		if(amount < 0) {
			throw new IllegalArgumentException("Error: Illegal argument.");
		}
		if(direction < -1 || direction == 0 || direction > 1) {
			throw new IllegalArgumentException("Error: Illegal argument.");
		}
		if(direction == 1 && amount == length) {
			throw new IllegalArgumentException("Error: Illegal argument.");
		}
		//shifts left
		if(direction == -1) {
			int temp1[] = new int[length + amount];
			for(int i = 0; i < length; i++) {
				temp1[i] = data[i];
			}
			this.data = temp1;
			this.length +=amount;
		} else {
		//shifts right
			int temp2[] = new int[length - amount];
			for(int i = 0; i < temp2.length; i++) {
				temp2[i] = data[i];
			}
			this.data = temp2;
			this.length -= amount;
		}
	}

	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		int bwor[] = new int[bn1.length];
		if(bn1.length != bn2.length) {
			throw new IllegalArgumentException("Numbers must be the same length");
		} else {
			for (int i = 0; i < bn1.length; i++) {
				bwor[i] = bn1.data[i] | bn2.data[i];
			}
		}
		return bwor;
	}

	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		int bwand[] = new int[bn1.length];
		if(bn1.length != bn2.length) {
			throw new IllegalArgumentException("Numbers must be the same length");
		} else {
			for(int i = 0; i < bn1.length; i++) {
				bwand[i] = (bn1.data[i] & bn2.data[i]);
			}
		}
		return bwand;
	}

	public void add(BinaryNumber aBinaryNumber) {
		//temp array and initialize carryover
		int temp[];
		int carryover = 0;

		if(aBinaryNumber.length > this.length) {
			int x = aBinaryNumber.length - this.length;
			temp = new int[aBinaryNumber.length];
			for(int i = x; i < aBinaryNumber.length; i++) {
				temp[i] = this.data[i-x];
			}
			this.data = temp;
			this.length += x;
		}
		
		if(this.length > aBinaryNumber.length) {
			int x = this.length - aBinaryNumber.length;
			temp = new int[this.length];
			
			for(int i = x; i < this.length; i++) {
				temp[i] = aBinaryNumber.data[i-x];
			}
			aBinaryNumber.data = temp;
			aBinaryNumber.length += x;
		}
		
		//new array
		int sum[] = new int[this.length];
			
		for(int i = this.length - 1; i >= 0; i--) {
			int add1 = (carryover + this.data[i] + aBinaryNumber.data[i]);
			int add2 = add1 % 2;
			carryover = add1 / 2;
			sum[i] = add2;
		}

		//to carry the 1
		if(carryover == 1) {
			int sums[] = new int[this.length + 1];
			sums[0] = 1;
			for(int x = this.length - 1; x >= 0; x--) {
				sums[x + 1] = sum[x];
			}
			this.data = sums;
			this.length++;	
		} else {
			this.data = sum;
		}
	}		

	public String toString() {
		String result = "";
        for(int i = 0;i < length; i++) {
            result += (char)('0'+data[i]);
        }
        return result;
	}

	public static void main(String[] args) {
        System.out.println("g");
	}
}
