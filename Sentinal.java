package sentinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {
    
    private PhraseHash posHash, negHash;
    
    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        File pfil = new File(posFile);
        File nfil = new File(negFile);
    	posHash = new PhraseHash();
    	negHash = new PhraseHash();
    	loadSentimentFile (pfil.getAbsolutePath(), true);
    	loadSentimentFile (nfil.getAbsolutePath(), false);
    }
    
    public void loadSentiment (String phrase, boolean positive) {
    	if (positive == true) {
        	posHash.put(phrase);    		
    	} else {
    		negHash.put(phrase);
    	}
    }
    
    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
    	if (positive == true) {
    		readFile(filename, true);
    	} else {
    		readFile(filename, false);
    	}
    }
    
    public String sentinalyze (String filename) throws FileNotFoundException {
    	int x = workHorse(filename);
    	if (x > 0) {
    		return "positive";
    	} else if (x < 0) {
    		return "negative";
    	}
    	return "neutral";
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    public void readFile (String filename, boolean x) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            loadSentiment(sc.nextLine(), x);
        }
        sc.close();
    }
    
    public int workHorse (String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        int k = 0;

        while (sc.hasNextLine()) {
            k += detectinate(sc.nextLine());
        }
        sc.close();
        return k;
    }
    
    public String combo (String[] tocombo, int g, int k) {
    	String answer = "";
    	// g represents the longest phrase possible, k represents the starting position of combo
        for (int t = k; t < (k+g); t++) {
        	if (answer == "") {
        		answer = tocombo[t];
        	} else {
            	answer += " " + tocombo[t];
        	}
        }
    	return answer;
    }
    
    public int detectinate (String sentence) {
    	int j = 0;
        int h = 0;

        String[] words = sentence.split(" ");
        
        if (posHash.longestLength() > negHash.longestLength()) {
        	h = posHash.longestLength();
        } else {
        	h = negHash.longestLength();
        }
        
        for (int t = 1; t <= h; t++) {
            for (int i = 0; i < (words.length - t + 1); i++) {
            	String serf = combo(words, t, i);
            	if (posHash.get(serf) != null && posHash.get(serf).contentEquals(serf)) {
            		j++;
            	} else if (negHash.get(serf) != null && negHash.get(serf).contentEquals(serf)) {
            		j--;
            	}
            }
        }
    	return j;
    }
}

