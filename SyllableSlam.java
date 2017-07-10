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
		int count = 0; 
		
		for(int i=0; i<word.length();i++){
			char c = word.charAt(i);
			if(isVowel(c)){
				count++;
			}
		}
		
		return count;
	}
	
	public boolean isVowel(char c){
		return vowels.indexOf(c)!=-1;
	}
}