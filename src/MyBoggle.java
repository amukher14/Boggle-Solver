import java.io.*;
import java.util.*;
public class MyBoggle {
	public static void main(String [] args) throws IOException
	{
		Scanner fileScan = new Scanner(new FileInputStream("dictionary.txt"));
		String inputFromFile;
		DictionaryInterface dictionary = new SimpleDictionary();
		DictionaryInterface wordsFound = new SimpleDictionary();
		StringBuilder potentialWord = new StringBuilder("");
		ArrayList<String> tom = new ArrayList<String>();
		
		String [][] board = new String[4][4];
		int [][] trackSpaces = new int [4][4];
//load dictionary		
		while (fileScan.hasNext())
		{
			inputFromFile = fileScan.nextLine();
			dictionary.add(inputFromFile);
	
		}
//load board game		
		fileScan = new Scanner(new FileInputStream("board1.txt"));
		String boardGame = fileScan.nextLine();
		int c = 0;
		for(int i = 0 ; i<=3 ; i++){
			for(int j = 0 ; j<=3 ; j++){
				board [i][j] = Character.toString( boardGame.charAt(c));
				c++;
			}
		}
		
		fileScan.close();

//print out board
		for(int i = 0 ; i<=3 ; i++){
			for(int j = 0 ; j<=3 ; j++){
				System.out.print(board [i][j]);
			}
			System.out.println("");
		}	
		
		
//start looking for words
	    for (int row = 0; row <= 3; row++) {
	        for (int col = 0; col <= 3; col++) {
	        	BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row, col);
	        }
	    }	
	}
//end of main
	
	public static boolean BoggleSearch(ArrayList<String> tom, String [][] board, int [][] trackSpaces, DictionaryInterface dictionary, DictionaryInterface wordsFound, StringBuilder potentialWord, int row, int col){
		
		
		
		if(col < 0 || col > 3 || row < 0 || row > 3){ // || trackSpaces[row][col] == 1
			return false;
		}
		
		potentialWord.append(board[row][col]);
		
		int ans = dictionary.search(potentialWord); //THIS IS THE LINE THAT DOESN'T WORK
		
		System.out.println("row" + row + " " + "col" + col);    //test line
		System.out.println(potentialWord);						//test line	
		System.out.println(ans);								//test line
		
			
		if (ans == 3){
			wordsFound.add(potentialWord.toString());
			tom.add(potentialWord.toString());
			trackSpaces[row][col] = 1;
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1, col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1,   col);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1, col+1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row  , col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row  , col+1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1, col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1,   col);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1, col+1);
			return true;
		}
				
		if (ans == 2){
			wordsFound.add(potentialWord.toString());
			tom.add(potentialWord.toString());
			trackSpaces[row][col] = 1;
			return true;
		}
			
		if (ans == 1){
			trackSpaces[row][col] = 1;
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1, col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1,   col);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row-1, col+1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row  , col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row  , col+1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1, col-1);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1,   col);
			BoggleSearch(tom, board, trackSpaces, dictionary, wordsFound, potentialWord, row+1, col+1);
			return true;
		}
		
		if (ans == 0)
			return false;
		
		return false;
	}
}


