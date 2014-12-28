/*
	LetterFrequency
	Nice simple little class to represent the frequencies and proportions
	of letters in a given string. The most important methods are shift
	and getAverageProportionDefference. These are used to compare the 
	proportions of two LetterFrequencies and see how similar they are
*/

public class LetterFrequency
{

	private int[] frequencies;
	private double[] proportions;
	private int count;

	public LetterFrequency(String string)
	{
		char[] stringArray = string.toCharArray();

		count = 0;

		frequencies = new int[26];
		proportions = new double[26];

		/*Initilise all frequencies to 0*/
		for(int i = 0; i < 26; i++)
		{
			frequencies[i] = 0;
		}
		/*Ignore any characters that aren't letters*/
		for(char c : stringArray)
		{
			inc(c);
			if('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z')
			{
				count++;
			}
		}

		for(int i = 0; i < 26; i++)
		{
			proportions[i] = frequencies[i]/(double)count;
		}
	}

	public LetterFrequency(int[] inFrequencies, int inCount)
	{
		proportions = new double[26];
		frequencies = inFrequencies;
		count = inCount;

		for(int i = 0; i < 26; i++)
		{
			proportions[i] = frequencies[i]/(double)count;
		}
	}

	public int[] getFreqArray()
	{
		return frequencies;
	}

	public double[] getProportions()
	{
		return proportions;
	}

	public int getCount()
	{
		return count;
	}

	/*Used by constuctor to take away the complication of adding one to 
	the frequency of a particular character*/
	private void inc(char c)
	{
		int index = -1;

		if('a' <= c && c <= 'z')
		{
			index = c - 'a';
		}
		else if('A' <= c && c <= 'Z')
		{
			index = c - 'A';
		}
		if(index > -1)
		{
			frequencies[index]++;
		}
	}

	/*Really only used in debugging. Worth keeping around*/
	public String toString()
	{
		String s = "";
		s += "{";
		for(int i = 0; i < 25; i++)
		{
			s += (char)('a' + i) + "=" + frequencies[i] + ":" + ((double)((int)(10000 * proportions[i]))/100) + "%"+ ", ";
		}
		s += "z=" + frequencies[25] + ":" + ((double)((int)(10000 * proportions[25]))/100) + "%"+ "}";

		return s;
	}

	/*Basically applies a caesar cipher shift to a whole LetterFrequency*/
	public LetterFrequency shift(int shift)
	{
		int[] newFrequencies = new int[26];

		for(int i = 0; i < 26; i++)
		{
			newFrequencies[i] = frequencies[(i + (Math.abs(26 - shift%26)))%26];
		}

		LetterFrequency newLetterFrequency = new LetterFrequency(newFrequencies, count);
		return newLetterFrequency;
	}

	/*Most important function. Lets you compare two LetterFrequencies.
	The lower the average difference the more similar they langauages are*/
	public double getAverageProportionDifference(LetterFrequency inLetterFrequency)
	{
		double totalDiff = 0, averageDiff;

		for(int i = 0; i < 26; i++)
		{
			totalDiff += Math.abs(proportions[i] - inLetterFrequency.getProportions()[i]);
		}

		averageDiff = totalDiff / 26;

		return averageDiff;
	}









}