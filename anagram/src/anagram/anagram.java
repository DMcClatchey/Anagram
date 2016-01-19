//David McClatchey CSC3410 HW#1 Anagram
package anagram;
import java.util.*;
import java.io.*;

//Program purpose: The purpose of the program is to take a text input file, copy all 
//of the words into two arrays, one array with the original words with capitalization and
//punctuation, and the other array all lower case words with no punctuation and the words'
//letters sorted alphabetically. Then by comparing all of the "keywords" in the second
//array, we can determine if anagrams exist in the input file. Then the program prints
//all of the original words from the first array into a new file, grouping anagrams on
//a single line together.

//Algorithms used: I used a while loop to read the input file's words and repeatedly 
//placed those words into an array. I also used an algorithm that puts the words in 
//lowercase and rearranges the letters of the word alphabetically. This was part of the
//while loop. Another algorithm I used was to compare the keyword values in the keyword
//array. If they were found to be a match, that indicated an anagram which was recorded
//into the output file. After recording all of the anagram words on one line, the
//algorithm makes the values that were found to match into null elements in the keyword
//array. This way, they are ignored and not printed out again as the loop algorithm 
//goes forward.

//How to use the program and expected input/output: The user is prompted to enter a 
//text file, and upon doing so, a new output file will be created with the any anagrams 
//found in the input file printed on the same line. Non-anagram words are printed on
//a line by themselves.

public class anagram {

	public static void main(String[] args) {
		//this block of code makes the user input a file name to be used
		Scanner fileInput;
		Scanner david = new Scanner(System.in);
		System.out.println("What is the file address?");
		String filename = david.next();//user inputs file name
		File inFile = new File(filename);
		//"/Users/davidmcclatchey/Documents/input.txt" file I used to test
		String arrayelements;
		String[] initialwords = new String[0];//initialize original words array with no elements
		String[] keywords = new String [0];//initialize keywords array with no elements
		int arrayindex=0;
		
		//this block of code reads the file and puts the words into the arrays
		try {
			fileInput = new Scanner(inFile);
			while (fileInput.hasNext()){
				arrayelements = fileInput.next();
				if (arrayelements.length()<=12){//blocks words with more than 12 letters
				initialwords = Arrays.copyOf(initialwords, (initialwords.length + 1));
				keywords = Arrays.copyOf(keywords, (keywords.length + 1));
				initialwords[initialwords.length-1] = arrayelements;
				//the three lines above copy the previous array and make a new one with a
				//new entry for one more word at the end of the array
				char[] chars = arrayelements.toLowerCase().replaceAll("\\W", "").toCharArray();
				Arrays.sort(chars);
				String sortedword = new String(chars);
				//the above three lines sort the letters of each word alphabetically to
				//add into the keywords array below
				keywords[keywords.length-1] = sortedword;
				arrayindex++;
				}
			}
			//System.out.println(Arrays.toString(initialwords)); FOR TESTING PURPOSES
			//System.out.println(Arrays.toString(keywords)); FOR TESTING PURPOSES
			fileInput.close();
			if(arrayindex==0){//case when file has no words
				System.out.println("The input file is empty.");
			}
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}
		
		
		try{
			
			PrintWriter output = new PrintWriter(new FileWriter("output.txt"));
			
			if(initialwords.length>50){//case where file has over 50 words
			System.out.println("There are more than 50 words in the input file.");
			output.println("There are more than 50 words in the input file.");
			}
			
			else if(arrayindex==0){//case where file is empty
				output.println("The input file is empty.");
			}
			
			else{//compares the keywords in the keywords array to find matches.
				//Once a match is found, that match is recorded and that array element's
				//value gets changed to null so it does not come up later in the for
				//loop below
				for(int i=0; i<initialwords.length; i++){
					if(initialwords[i]!=null){
						System.out.print(initialwords[i]);
						output.print(initialwords[i]);
						for(int j=(i+1); j<initialwords.length; j++){
							if(keywords[i].equals(keywords[j])){
								System.out.print(" " + initialwords[j]);
								output.print(" " + initialwords[j]);
								initialwords[j]=null;//clears entry to trigger the if 
								//statement above so there's no repetition
							}	
						}
						System.out.println("");
						output.println(" ");
					}
				}
		}
			
		output.close();
		}
		catch (IOException e){
			System.out.println(e);
			System.exit(1);
			
		}
		
	}
	
	

}
