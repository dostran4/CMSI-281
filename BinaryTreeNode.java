package tree.binary;

public class BinaryTreeNode {
    
    private String data;
    private BinaryTreeNode left, right;
    
    BinaryTreeNode (String s) {
        data = s;
        left = null;
        right = null;
    }
    
    public void add (String s, String child) {
        if (child.equals("L")) {
            left = new BinaryTreeNode(s);
        } else if (child.equals("R")) {
            right = new BinaryTreeNode(s);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public BinaryTreeNode getChild (String child) {
        return (child.equals("L")) ? left : right;
        // return left node if child.equals "L"; otherwise, return right
    }
    
    public String getString () {
        return data;
    }
    
    
    public void doubleTree () {
    	doubler (this);
    }
    
    public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
    	if (n1 == null || n2 == null) {
    		return (n1 == n2);
    	}
    	return ((n1.data == n2.data) && (sameTree(n1.left, n2.left)) && (sameTree(n1.right, n2.right)));
    }
    
    private void doubler (BinaryTreeNode doubleMe) {
    	if (doubleMe == null) {
    		return;
    	}
    	if (doubleMe.left != null) {
        	BinaryTreeNode tempNode = doubleMe.left;
        	BinaryTreeNode addMe = new BinaryTreeNode(doubleMe.data);
        	doubleMe.left = addMe;
        	addMe.left = tempNode;
    		doubler (addMe.left);
   		} else {
   			doubleMe.add(doubleMe.data, "L");
   		}
    	doubler (doubleMe.right);
    }
}
