package autocompleter;

import java.util.ArrayList;
import java.util.Arrays;

import org.omg.CORBA.Current;

public class Autocompleter implements AutocompleterInterface {

    TTNode root;

    Autocompleter () {
        root = null;
    }

    public boolean isEmpty () {
    	return (root == null);
    }

    public void addTerm (String toAdd) {
    	String normalized = normalizeTerm(toAdd);

    	if (isEmpty()) {
    		root = populateDown (normalized);
    	} else {
    		workman(root, normalized);
    	}
    }

    public boolean hasTerm (String query) {
    	String normalized = normalizeTerm(query);
    	TTNode foundish = searchlight(root, normalized);

        if ( foundish == null || foundish.wordEnd == false) {
        	return false;
        }
        return true;
    }

    public String getSuggestedTerm (String query) {
    	String thank = null;
    	String normalized = normalizeTerm(query);
    	ArrayList<String> yeet = getSortedTerms();
    	
    	if (yeet.contains(normalized)) {
    		return normalized;
    	}
    	for (int i = 0; i < yeet.size(); i++) {
    		String weep = yeet.get(i);
    		if (weep.contains(normalized)) {
    			if (thank == null) {
    				thank = weep;
    			}
    			if (weep.length() < thank.length()) {
    				thank = weep;
    			}
    		}
    	}
    	return thank;
    }

    public ArrayList<String> getSortedTerms () {
        ArrayList<String> yeet = new ArrayList<String>();
        
        if (!isEmpty()) {
            String noot = "";
            sortme (root, yeet, noot);
        }
        return yeet;
    }

    private String normalizeTerm (String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    private void sortme (TTNode sly, ArrayList<String> shrew, String prefix) {
	    if (sly.left != null) {
	        sortme (sly.left, shrew, prefix);
	    }
	    
	    String newprefix = prefix + sly.letter;
	    
	    if (sly.wordEnd == true) {
	        shrew.add(newprefix);
	    }
	    if (sly.mid != null) {
	        sortme (sly.mid, shrew, newprefix);
	    }
	    if (sly.right != null) {
	        sortme (sly.right, shrew, prefix);
	    }
    }

    private TTNode searchlight (TTNode home, String findMe) {
    	if (home == null || (findMe.length() == 1 && home.wordEnd == false)) {
    		return home;
    	}
    	
    	int comp = compareChars(home.letter, findMe.charAt(0));

    	if (comp > 0) {
    		return searchlight (home.left, findMe);
		} else if (comp < 0) {
    		return searchlight (home.right, findMe);
		} else {
			if (findMe.length() != 1) {
				return searchlight (home.mid, findMe.substring(1));
			} else {
				return home;
			}
		}
    }
    
    private void workman (TTNode basis, String toAdd) {
    	if (basis == null || toAdd.length() == 0 ) {
    		return;
    	}

    	int camp = compareChars(basis.letter, toAdd.charAt(0));
    	
    	if (camp == 0 && toAdd.length() == 1) {
    		basis.wordEnd = true;
    		return;
    	}
    	if (camp < 0) {
    		if (basis.right == null) {
        		basis.right = populateDown (toAdd);
    		} else {
    			workman (basis.right, toAdd); 			
			}
		} else if (camp > 0) {
    		if (basis.left == null) {
        		basis.left = populateDown (toAdd);
    		} else {
    			workman (basis.left, toAdd);
    		}
    	} else if (basis.mid == null) {
			basis.mid = populateDown (toAdd.substring(1));
		} else if (basis.mid != null ){
			workman (basis.mid, toAdd.substring(1));
		}
	}

    private TTNode populateDown (String pope) {
        if (pope.length() == 1) {
            TTNode grump = new TTNode(pope.charAt(0), true);
        	return grump;
        } else {
            TTNode grump = new TTNode(pope.charAt(0), false);
            grump.mid = populateDown (pope.substring(1));
        	return grump;
        }
    }
    
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }

    }

}