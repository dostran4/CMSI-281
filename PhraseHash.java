package sentinal;

import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {
    
    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;
    
    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash () {
    	size = 0;
    	longest = 0;
    	buckets = new LinkedList[BUCKET_COUNT];
    }
    
    /* (non-Javadoc)
	 * @see sentinal.PhraseHashInterfac#size()
	 */
    @Override
	public int size () {
    	return size;
    }
    
    /* (non-Javadoc)
	 * @see sentinal.PhraseHashInterfac#isEmpty()
	 */
    @Override
	public boolean isEmpty () {
    	return (size == 0);
    }
    
    /* (non-Javadoc)
	 * @see sentinal.PhraseHashInterfac#put(java.lang.String)
	 */
    @Override
	public void put (String s) {
    	if (get(s) == null) {
    		int hashton = hash(s);
        	String[] prop = curt(s);
        	size ++;
        	
        	if (prop.length > longest) {
        		longest = prop.length;
        	}
        	if (buckets[hashton] == null) {
        		buckets[hashton] = new LinkedList<String>();
        	}
        	buckets[hashton].add(s);
    	}
    }
    
    /* (non-Javadoc)
	 * @see sentinal.PhraseHashInterfac#get(java.lang.String)
	 */
    @Override
	public String get (String s) {
		int hashton = hash(s);
    	if (buckets[hashton] != null && buckets[hashton].contains(s)) {
			return s;
    	}
    	return null;
    }
    
    /* (non-Javadoc)
	 * @see sentinal.PhraseHashInterfac#longestLength()
	 */
    @Override
	public int longestLength () {
    	return longest;
    }
    
    private int hash (String s) {
    	int k = 0;
    	int p = s.length();
    	if (p > 5) {
    		p = 5;
    	}
    	for (int i = 0 ; i < p; i++) {
    		k += (s.charAt(i));
    	}
    	return ((k) * p % BUCKET_COUNT);
    }
    
    private String[] curt (String s) {
    	String[] cuttup = s.split(" ");
    	return cuttup;
    }
    
}
