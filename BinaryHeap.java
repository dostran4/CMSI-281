package tree.heap;

import java.util.ArrayList;

public class BinaryHeap {
	
	ArrayList<Integer> heap;
    
    BinaryHeap () {
        heap = new ArrayList<Integer>();
    }

    /*
     * Continues to bubble values up the tree until we
     * find a node that is greater than it
     */
    public void bubbleUp (int index) {
        if (index == 0) {return;}

        int parent = getParent(index);

        if (heap.get(parent) < heap.get(index)) {
            Integer temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);
            bubbleUp(parent);
        }

    }
    
    public void insert (Integer toInsert) {
        heap.add(toInsert);
        bubbleUp(heap.size() - 1);
    }

    // Traversal helpers
    public int getParent (int index) {
        return (index - 1) / 2;
    }
    
    public int getChild (int index, char child) {
        int result = (index * 2) + 1;
        if (child == 'R') {
            result++;
        }
        return result;
    }
    
    public void print () {
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i));
        }
    }
    
    public ArrayList<Integer> getSortedElements () {
    	    	
    	ArrayList<Integer> sortedGuy = new ArrayList<Integer>(heap);
 	
    	sorter(sortedGuy, 0, sortedGuy.size());
    	
    	this.print();
    	
        for (Integer number : sortedGuy) {
     	   System.out.println("Number = " + number);
        }
    	
    	return sortedGuy;
    	
    }
 
    private void sorter (ArrayList<Integer> toSort, int leftcounter, int rightcounter) {
    	if (rightcounter == 0 || leftcounter == rightcounter) {
    		return;
    	}
    	swap(toSort, leftcounter, rightcounter);
    	leftcounter++;
    	rightcounter--;   	
    	sorter(toSort, leftcounter, rightcounter);	
    }
    
    private void swap(ArrayList<Integer> home, int i, int j) {
        int temp = home.get(i);
        home.set(i, j);
        home.set(j, temp);
    }  
    
    public static void main(String[] args) {
    	
    BinaryHeap testell = new BinaryHeap();
    testell.insert(5);
    testell.insert(6);
    testell.insert(7);
    

    
    testell.getSortedElements();



    
    }

}
