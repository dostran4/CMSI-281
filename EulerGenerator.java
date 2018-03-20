package music.Euler;

public class EulerGenerator {
	
	public static int[] getEuler (int j, int[] input) {
		int[] notes = new int[j];
		System.out.println("We escaped the i loop");
		for (int i = 0; i < input.length; i++) {
			System.out.println("We escaped the z loop");
			for (int z = 0; z < input.length; z++) {
				System.out.println("We are in this z loop");
				int q = (input[i] * input[z]);
				System.out.println(q);
				if (!contains(notes, q)) {
					addOneIntToArray(notes, q);
				}
				System.out.println("Almost done with z loop");
			}
		}
		return notes;
	}
	
	public static int[] addOneIntToArray(int[] initialArray , int newValue) {

	    int[] newArray = new int[initialArray.length + 1];
	    for (int index = 0; index < initialArray.length; index++) {
	        newArray[index] = initialArray[index];
	    }

	    newArray[newArray.length - 1] = newValue;
	    return newArray;
	}
	
	public static boolean contains(final int[] array, final int key) {
	    for (final int i : array) {
	        if (i == key) {
	            return true;
	        }
	    }
	    return false;
	}
	
   private static void printArray(int[] anArray) {
      for (int i = 0; i < anArray.length; i++) {
         if (i > 0) {
            System.out.print(", ");
         }
         System.out.print(anArray[i]);
      }
      System.out.println(". ");

   }
   public static void main(String[] args) {
      int[] testArray = { 1, 2 , 3, 5, 7};
      int[] EulerGuy = getEuler(1000, testArray);
      printArray(testArray);
      printArray(EulerGuy);
   }

}
