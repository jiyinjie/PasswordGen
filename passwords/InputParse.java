package passwords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class InputParse {

	private long[][] followerTable = new long[26][26];
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
				modifyTable(parsedInstruction);
				strLine = br.readLine();
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.toString());
		}
	}
	
	public void modifyTable(String[] line){
		for (int i = 0; i <= line.length-1; i ++){
			line[i] = line[i].toLowerCase();
			for (int j = 0; j < line[i].charAt(line[i].length()-1); j++){
				char a = line[i].charAt(j);
				char b = line[i].charAt(j+1);
				
				if (a < 97 && a > 122){
					if (b < 97 && b > 122){
						int lead = a % 97;
						int follow = b % 97;
						
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
	
	
}
