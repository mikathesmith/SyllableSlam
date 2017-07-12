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
			int syllableFreq = estimateSyllables(word.toLowerCase());
			System.out.println(word + " " +syllableFreq);
		}
		sc.close(); 
	}
	
	public int estimateSyllables(String word){
		
		int count = 0; 
		boolean lastCharVowel = false; 
		
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
				if(i!=0 && c=='y'){
					//If it a 'y' and is not the first letter of the word, then it is a new sound
					count++;
				}
				
				lastCharVowel =false;
			}
				
		}
		return count==0 ? 1 : count;
	}
	
	public boolean isVowel(char c){
		return vowels.indexOf(c)!=-1;
	}
}
