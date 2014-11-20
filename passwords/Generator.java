package passwords;

public class Generator {
	private long[] begin_frequency;
	//assume row values are the first letter and the column values are the second letter
	private long[][] diagram_frequency;
	//contains the total for each row
	private long[] total_diagrams;
	private long total_beginning;
	
	public Generator()
	{
		begin_frequency = new long[26];
		diagram_frequency = new long[26][26];
		total_diagrams = new long[26];
		total_beginning = 0;
	}
	
	public long[] getBegin()
	{
		return begin_frequency;
	}
	
	public long[][] getDiagram()
	{
		return diagram_frequency;
	}
	
	public void updateDiagram(int row, int col)
	{
		diagram_frequency[row][col]+=1;
		total_diagrams[row]++;
	}
	
	public void updateBegin(int row)
	{
		begin_frequency[row]+=1;
		total_beginning++;
	}
	
	public long getDiagramValue(int row, int col)
	{
		return diagram_frequency[row][col];
	}
	
	public long getBeginValue(int index)
	{
		return begin_frequency[index];
	}
	
	public String createPassword(int length)
	{
		String password = "";
		char char_index;

		int beg_letter = (int)(Math.random() * total_beginning);
		char_index = getBegLetter(beg_letter);
		password+= ""+char_index;
		
		for(int k = 1; k < length; k++)
		{
			int second_letter = (int)(Math.random() * total_diagrams[char_index-97]);
			char_index = getDiagramLetter(char_index-97, second_letter);
			password+= ""+char_index;			
		}
		return password;
	}

	public void printDiagrams(){
		for (int i = 0; i < 27; i ++){
			System.out.print("\t" + (char)(97+i));
			if (i == 26){
				System.out.println();
			}
		}
		for (int i = 0; i < diagram_frequency.length; i ++){
			System.out.print((char)(97+i));
			for(int j = 0; j < diagram_frequency.length; j++){
				System.out.print("\t"+diagram_frequency[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public void printBegin()
	{
		for (int i = 0; i < 26; i ++){
			System.out.println((char)(97+i) + "\t"+ begin_frequency[i]);
		}
	}
	
	private char getDiagramLetter(int index, int chosen)
	{
		int base = 0;
		int max = 0;
		int letter_rep = 0;
		
		for(int col = 0; col < diagram_frequency[index].length; col++)
		{
			base = max;
			max += diagram_frequency[index][col];
			if(chosen >= base && chosen <= max)
			{
				letter_rep = col;
				break;
			}
		}
		letter_rep+=97;
		return (char)letter_rep;
	}
	
	private char getBegLetter(int chosen)
	{
		int base = 0;
		int max = 0;
		int letter_rep = 0;
		
		/* Traverse the frequency array until you find the input between the
		   base and max where max-base represents the probability of the character
		   specified by k appears where (max-base)/total_beginning is the probability*/
		for(int k = 0; k < begin_frequency.length; k++)
		{
			base = max;
			max += begin_frequency[k];
			if(chosen >= base && chosen <= max)
			{
				letter_rep = k;
				break;
			}
		}
		letter_rep+=97; //get ascii code
		return (char)letter_rep; 
	}
}
