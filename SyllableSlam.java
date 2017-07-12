import java.util.*;
import java.io.*;

public class SyllableSlam{
	private ArrayList<Character> vowels = new ArrayList<Character>(); 
	
	public SyllableSlam(){
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}
	
	public static void main(String[]args){
		new SyllableSlam().start();
	}
	
	public void start(){
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			String word = sc.next();
			System.out.println(word + ": " + getSyllables(word.toLowerCase()));
		}
		sc.close(); 
	}
	
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
		
		// TODO: Dipthongs and Tripthongs
		
		for (int i = 0; i < chars.length; ++i) {
			char c = chars[i];
			if (isVowel(c) && !silents.get(i)) syllables++;
		}
		
		// Always assume at least 1 syllable
		return syllables == 0 ? 1 : syllables;
	}
	
	public int estimateSyllables(String word){
		
		//if 2 vowels in a row, 
		
		int count = 0; 
		boolean lastCharVowel = false; 
		
		for(int i=0; i<word.length();i++){
			char c = word.charAt(i);
			if(isVowel(c)){	
				if(word.length() > 2){
					if(i == word.length()-1 && c=='e'){
						//add syllable if word ends in le and letter before l is a consonant
						if(word.charAt(i-2) == 'l' && !isVowel(word.charAt(i-3))) {
							count++;
						}else{
							break;
						}
					}else if(i == word.length()-1 && c=='s'){
						//add syllable if word ends in les and letter before l is a consonant
						if(word.charAt(i-2) == 'e' && word.charAt(i-2) == 'l' && !isVowel(word.charAt(i-3))) {
							count++;
						}else {
							break;
						}
					}
				}
				if(lastCharVowel==true){ //Two vowels in a row	
					//if the last char is the same as this char
					if(c != (word.charAt(i-1))){
						//not the same vowel
						count++;
					}
				}else{ //not two vowels in a row
					count++; 
				}
				lastCharVowel=true;
			}else{
				lastCharVowel =false; 
			}		
		}
		return count==0 ? 1 : count;
	}
	
	public boolean isVowel(char c){
		return vowels.indexOf(c)!=-1;
	}
	
	public boolean hasVowel(char[] chars, int from, int to) {
		for(int i = from; i < to; ++i) {
			if (isVowel(chars[i])) return true;
		}
		return false;
	}
}
