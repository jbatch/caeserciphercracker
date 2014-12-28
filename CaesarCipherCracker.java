import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CaesarCipherCracker
{
	public static void main(String args[])
	{
		System.out.println(args.length);
		int minShift = 0;
		double minDiff = 999, avgDiff = 0.0;

		int[] englishFreqs = {8167, 1492, 2782, 4253, 12702, 2228, 2015, 6094,
		 6966, 153, 772, 4025, 2406, 6749, 7507, 1929, 95, 5987, 6327, 9056,
		  2758, 978, 2360, 150, 1974, 74};
		int englishCount = 100000;
		LetterFrequency english = new LetterFrequency(englishFreqs, englishCount);

		String message = null, decodedMessage;
		if(args.length == 1)
		{
			message = readUserFile(args[0]);
		}

		if(message != null)
		{
			LetterFrequency messageFrequency = new LetterFrequency(message);
		
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

			System.out.println("Most likely shift val: " + minShift + " with averge diff of " + minDiff);
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