import java.util.*;
import java.io.*;

public class SyllableSlam{
	ArrayList<char> vowels = new ArrayList<char>(); 
	
	public static void main(String[]args){
		SyllableSlam 
		
	
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
		
	}
}