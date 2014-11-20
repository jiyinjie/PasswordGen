package passwords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputParse {

	private long[][] followerTable = new long[26][26];
	private long[] firstChar = new long[26];
	
	String filename;
	public InputParse(String input){
		filename = input;
	}
	
	public void parse(){
		try{
			/* Open the file that is the first command line 
			parameter*/			
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine =br.readLine();
			//Process each instruction
			while (strLine!= null) {
				//DO THE PROCESSING
				String[] parsedInstruction = strLine.split("\\s+");
				modifyfollowerTable(parsedInstruction);
				strLine = br.readLine();
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.toString());
		}
	}
	
	public void modifyfollowerTable(String[] line){
		for (int i = 0; i <= line.length-1; i ++){
			line[i] = line[i].toLowerCase();

			for (int j = 0; j < line[i].length()-1; j++){
				
				char a = line[i].charAt(j);
				char b = line[i].charAt(j+1);
				if (j == 0){
					firstChar[((int)a)%97] ++;
				}
				
				if (a >= 97 && a <= 122){
					if (b >= 97 && b <= 122){
						int lead = (int)a % 97;
						int follow = (int)b % 97;
						
						followerTable[lead][follow] ++;
					}
					else{
						if(j+2 >= line[i].length())
							break;
						j++;
					}
				}
				else{
					j++;
				}
			}
		}
	}

	public long[][] getFollowerTable() {
		return followerTable;
	}
	
	public long[] getFirstCharList(){
		return firstChar;
	}

	public void printFollowerTable(){
		for (int i = 0; i < 27; i ++){
			System.out.print("\t" + (char)(97+i));
			if (i == 26){
				System.out.println();
			}
		}
		for (int i = 0; i < followerTable.length; i ++){
			System.out.print((char)(97+i));
			for(int j = 0; j < followerTable.length; j++){
				System.out.print("\t"+followerTable[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	
}
