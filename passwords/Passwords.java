package passwords;

public class Passwords {
	public static void main(String[] args){
		InputParse input = new InputParse(args[0]);
		input.parse();
		
		input.printFollowerTable();
		
		
	}
}
