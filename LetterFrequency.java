public class LetterFrequency
{

	private int[] frequencies;
	private double[] proportions;
	private int count;

	public LetterFrequency(String string)
	{
		char[] stringArray = string.toCharArray();

		count = stringArray.length;

		frequencies = new int[26];
		proportions = new double[26];

		for(int i = 0; i < 26; i++)
		{
			frequencies[i] = 0;
		}

		for(char c : stringArray)
		{
			inc(c);
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

	public void inc(char c)
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

	public String toString()
	{
		String s = "";
		s += "{";
		for(int i = 0; i < 25; i++)
		{
			s += (char)('a' + i) + "=" + frequencies[i] + ":" + ((double)((int)(10000 * proportions[i]))/100) + "%"+ ", ";
		}
		s += "z=" + frequencies[25] + "}";

		return s;
	}

	public static LetterFrequency shift(LetterFrequency letterFrequency, int shift)
	{
		int[] newFrequencies = new int[26];

		for(int i = 0; i < 26; i++)
		{
			newFrequencies[i] = letterFrequency.getFreqArray()[(i + (Math.abs(26 - shift%26)))%26];
		}

		LetterFrequency newLetterFrequency = new LetterFrequency(newFrequencies, letterFrequency.getCount());
		return newLetterFrequency;
	}

	public double getAverageProportionDifference(LetterFrequency inLetterFrequency)
	{
		double totalDiff = 0, averageDiff;

		for(int i = 0; i < 26; i++)
		{
			totalDiff += Math.abs(frequencies[i] - inLetterFrequency.getFreqArray()[i]);
		}

		averageDiff = totalDiff / 26;

		return averageDiff;
	}









}