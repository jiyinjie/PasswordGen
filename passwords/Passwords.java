package passwords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Passwords {
	static Generator generator;
	static ArrayList<String> additional = new ArrayList<String>();
	
	public static void main(String[] args){
		String filename = args[0];
		int number_of_passwords = Integer.parseInt(args[1]);
		int password_length = Integer.parseInt(args[2]);
		
		//make sure to create a String[] that is then passed into generator constructor function so
		//that dictionary will be used.
		//generator = new Generator(dictionary);
		generator = new Generator();
		
		try{
			/* Open the file that is the first command line 
			parameter*/			
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			//Process each instruction
			while (strLine!= null) {
				//DO THE PROCESSING
				String[] parsedInstruction = strLine.split("\\s+");
				modifySecondEntropy(parsedInstruction);
				strLine = br.readLine();
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.toString());
		}
		
		
		generator.printDiagrams();
		generator.printBegin();
		
		for(int count = 0; count < number_of_passwords; count++)
		{
			String password = generator.createPassword(password_length, additional);
			System.out.println(password);
		}
		
	}
	
	public static void readAdditionalList(String filename){
		
		try{
			/* Open the file that is the first command line 
			parameter*/			
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			//Process each instruction
			while (strLine!= null) {
				//DO THE PROCESSING
				String[] parsedInstruction = strLine.split("\\s+");
				for(int i = 0; i < parsedInstruction.length; i++){
					additional.add(parsedInstruction[i]);
				}
				strLine = br.readLine();
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.toString());
		}
	}
	
	public static void modifyfollowerTable(String[] line){
		for (int i = 0; i <= line.length-1; i ++){
			line[i] = line[i].toLowerCase();

			for (int j = 0; j < line[i].length()-1; j++){
				
				char a = line[i].charAt(j);
				char b = line[i].charAt(j+1);
				if (j == 0 && (int)a >= 97 && (int)a <= 122){
					generator.updateBegin( ((int)a) % 97);
				}
				
				if (a >= 97 && a <= 122){
					if (b >= 97 && b <= 122){
						int lead = (int)a % 97;
						int follow = (int)b % 97;
						generator.updateDiagram(lead, follow);
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
	
	public static void modifySecondEntropy(String[] line){
		for (int x = 0; x < line.length; x++){
			for (int y = 0; y < line[x].length()-2; y++){
				
				char a = line[x].charAt(y);
				char b = line[x].charAt(y+1);
				char c = line[x].charAt(y+2);
				
				if (y == 0 && (int)a >= 97 && (int)a <= 122){
					generator.updateBegin( ((int)a) % 97);
				}
				
				if (y == 0 && (int)a >= 97 && (int)a <= 122){
					generator.updateBegin( ((int)a) % 97);
				}
				
				if (a >= 97 && a <= 122){
					if (b >= 97 && b <= 122){
						if (c >= 97 && c <= 122){
							int lead = (int)a % 97;
							int follow = (int)b % 97;
							int last = (int)c % 97;
							generator.updateTrigram(lead, follow, last);
						}
						else{
							if (y+3 >= line[x].length());
							break;
						}
					}
					else 
						y++;
				}
				else{
					y++;
				}
			}
		}
	}
}
