import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
	CaesarCiperCracker
	The main class for my CaesarCipherCracker program.
	It has a hardcoded LetterFrequency for the english language taken from
	http://en.wikipedia.org/wiki/Letter_frequency
	It gets a message from the user through a file specified from the cmd line
	and attempts to guess what the shift value used to encrypt the message was
*/
public class CaesarCipherCracker
{
	public static void main(String args[])
	{
		int minShift = 0;
		double minDiff = 999, avgDiff = 0.0;

		final int[] englishFreqs = {8167, 1492, 2782, 4253, 12702, 2228, 2015, 6094,
		 6966, 153, 772, 4025, 2406, 6749, 7507, 1929, 95, 5987, 6327, 9056,
		  2758, 978, 2360, 150, 1974, 74};
		final int englishCount = 100000;
		final LetterFrequency english = new LetterFrequency(englishFreqs, englishCount);

		/*Check that the user has specified a file and reads the text from it*/
		String message = null, decodedMessage;
		if(args.length == 1)
		{
			message = readUserFile(args[0]);
		}
		else
		{
			System.out.println("You must specify a file. ie - " +
				"java CaesarCipherCracker test.txt");
		}

		if(message != null) //Quits if no file given or file error occurs
		{
			LetterFrequency messageFrequency = new LetterFrequency(message);
		
			/*Try all shift values and find the one with the lowest
				average difference in letter proportions*/
			for(int i = 0; i < 26; i++)
			{
				avgDiff = english.getAverageProportionDifference(messageFrequency.shift(i));
				if(avgDiff < minDiff)
				{
					minShift = i;
					minDiff = avgDiff;
				}
			}

			minShift = 26 - minShift;

			System.out.println("Most likely shift val: " + minShift + 
				" with average diff of " + minDiff);
			decodedMessage = Cipher.decrypt(message, minShift);
			System.out.println("Message: " + decodedMessage);
		}
	}

	public static String readUserFile(String filename)
	{
		String ret = null, line;
		BufferedReader br = null;
		StringBuilder sb;
		try
		{
			br = new BufferedReader(new FileReader(filename));
			sb = new StringBuilder();
			line = br.readLine();

			while(line != null)
			{
				sb.append(line);
				line = br.readLine();
			}

			ret = sb.toString();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not open file");
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try{br.close();}
			catch(Exception e){}//System.out.println(e.getMessage());}
		}

		return ret;
	}
}