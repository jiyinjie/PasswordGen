package passwords;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Passwords {
	static Generator generator;
	
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
				modifyfollowerTable(parsedInstruction);
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
			String password = generator.createPassword(password_length);
			System.out.println(password);
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
}
