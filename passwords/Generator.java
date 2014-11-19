package passwords;

public class Generator {
	private int[] begin_frequency;
	private int[][] diagram_frequency;
	private long total_diagrams;
	private long total_beginning;
	
	public Generator()
	{
		begin_frequency = new int[26];
		diagram_frequency = new int[26][26];
		total_diagrams = 0;
		total_beginning = 0;
	}
	
	public void update_diagram(int row, int col)
	{
		diagram_frequency[row][col]+=1;
		total_diagrams++;
	}
	
	public void update_begin(int row)
	{
		begin_frequency[row]+=1;
		total_beginning++;
	}
	
	public String createPassword(int length)
	{
		String password = "";
		
		int beg_letter = (int)(Math.random() * total_beginning);
		password+=getBegLetter(beg_letter);
		
		for(int k = 1; k < length; k++)
		{
			//password+=getDiagramLetter();
		}
		return password;
	}
	
	private String getBegLetter(int chosen)
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
		return ""+(char)letter_rep; 
	}
}
