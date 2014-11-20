package passwords;

public class Passwords {
	public static void main(String[] args){
		InputParse input = new InputParse(args[0]);
		input.parse();
		
		input.printFollowerTable();
		long[] wordBegin = input.getFirstCharList();
		for (int i = 0; i < 26; i ++){
			System.out.println((char)(97+i) + "\t"+ wordBegin[i]);
		}
		
		
	}
}
