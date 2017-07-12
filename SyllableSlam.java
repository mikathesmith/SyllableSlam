/*
 * Etude 2: Syllable Slam
 * 
 * Authors: Nathan Harding, Mika Smith, Kimberley Louw, Mathew Boyes
 * 
 * Takes in a word as input and prints the number of syllables that word has. 
 * 
 */

import java.util.*;
import java.io.*;

public class SyllableSlam{
	private ArrayList<Character> vowels = new ArrayList<Character>(); 
	
	/*
	 * This constructor adds the vowels to the list of vowels.
	 */
	public SyllableSlam(){
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}
	
	/*
	 * The main method creates a new instance of the SyllableSlam class and starts it. 
	 */
	public static void main(String[]args){
		new SyllableSlam().start();
	}
	
	/*
	 * This method starts the program by scanning in input and calling methods on it. 
	 */
	public void start(){
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			String word = sc.next();
			// TODO: Change output format to match specifications
			System.out.println(word + ": " + getSyllables(word.toLowerCase()));
		}
		sc.close(); 
	}
	
	/**
	 * This method returns the number of syllables in a word by checking against various rules.
	 */
	public int getSyllables(String word) {
		int syllables = 0;
		char[] chars = word.toCharArray();
		
		ArrayList<Boolean> silents = new ArrayList<Boolean>();		
		for (char c : chars) silents.add(false);
		
		// If the last character is an 'e' and there is a preceding vowel, mark this 'e' as silent
		if (chars[chars.length - 1] == 'e' && hasVowel(chars, 0, chars.length - 1)) {
			silents.set(chars.length - 1, true);
		}
		
		// If the word ends with 'le' and the preceding character is a consonant, unmark it as silent
		if (word.endsWith("le") && word.length() > 2 && !isVowel(chars[chars.length - 3])) {
			silents.set(chars.length - 1, false);
		}
		
		// If the word ends with 'les' and the preceding character is not a consonant, mark the 'e' as silent
		if (word.endsWith("les") && word.length() > 3 && isVowel(chars[chars.length - 4])) {
			silents.set(chars.length - 2, true);
		}
		
		// TODO: Dipthongs and Tripthongs, double same vowels
		
		for (int i = 0; i < chars.length; ++i) {
			char c = chars[i];
			if (isVowel(c) && !silents.get(i)) syllables++;
			if (c == 'y' && i != 0 && !silents.get(i)) syllables++;
		}
		
		// Always assume at least 1 syllable
		return syllables == 0 ? 1 : syllables;
	}
	
	public int estimateSyllables(String word){
		
		int count = 0; 
		boolean lastCharVowel = false; 
		
		//Iterate through every character in the word. 
		for(int i=0; i<word.length();i++){
			char c = word.charAt(i);
			
			if(i==word.length()-1 && word.length()>2 && (c=='e' || c=='s')){
				
				if(c=='e' && word.charAt(i-1) == 'l' && !isVowel(word.charAt(i-2))) {
					count++;
				}else if(c=='s' && word.charAt(i-1) == 'e' && word.charAt(i-2) == 'l' && !isVowel(word.charAt(i-3))){
					//Double check this works! 
					count++;
				}else{
					break;
				}
			}else if(isVowel(c)){	
				if(lastCharVowel){ //Two vowels in a row	
					//If the last char is not the same as this char
					if(c != (word.charAt(i-1))){
						count++;
					}
				}else{ //If there are not two vowels in a row
					count++; 
				}
				lastCharVowel=true;
			}else{
				if(i!=0 && c=='y'){
					//If it a 'y' and is not the first letter of the word, then it is a new syllable.
					count++;
				}
				
				lastCharVowel =false;
			}
				
		}
		
		//Returns the count if it is not 0, otherwise returns 1 as a word has at least 1 syllable. 
		return count==0 ? 1 : count;
	}
	
	/**
	 * This method returns true if a character is a vowel, and false if it is a consonant.
	 */
	public boolean isVowel(char c){
		return vowels.indexOf(c)!=-1;
	}
	
	/**
	 * This method returns true if a vowel is present in the char array between the bounds specified
	 */
	public boolean hasVowel(char[] chars, int from, int to) {
		for(int i = from; i < to; ++i) {
			if (isVowel(chars[i])) return true;
		}
		return false;
	}
}
