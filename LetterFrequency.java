public class LetterFrequency
{

	private int[] frequencies;

	public LetterFrequency(String string)
	{
		char[] stringArray = string.toCharArray();

		frequencies = new int[26];
		for(int i = 0; i < 26; i++)
		{
			frequencies[i] = 0;
		}

		for(char c : stringArray)
		{
			inc(c);
		}
	}

	public int[] getFreqArray()
	{
		return frequencies;
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
			s += (char)('a' + i) + "=" + frequencies[i] + ", ";
		}
		s += "z=" + frequencies[25] + "}";

		return s;
	}
}