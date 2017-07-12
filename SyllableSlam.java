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
			int syllableFreq = estimateSyllables(word);
			System.out.println(syllableFreq);
		}
		sc.close(); 
	}
	
	public int estimateSyllables(String word){
		
		//if 2 vowels in a row, 
		
		int count = 0; 
		boolean lastCharVowel = false; 
		
		for(int i=0; i<word.length();i++){
			char c = word.charAt(i);
			if(isVowel(c)){		
				if(i == word.length()-1 && c=='e'){
					//add syllable if word ends in le and letter before l is a consonant
					if(word.charAt(i-2) == 'l' && !isVowel(word.charAt(i-3)) {
						count++;
					}else {
						break;
					}
				else if(i == word.length()-1 && c=='s'){
					//add syllable if word ends in les and letter before l is a consonant
					if(word.charAt(i-2) == 'e' && word.charAt(i-2) == 'l' && !isVowel(word.charAt(i-3)) {
						count++;
					}else {
						break;
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
}
