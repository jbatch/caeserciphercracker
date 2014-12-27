public class CaesarCipherCracker
{
	public static void main(String args[])
	{
		int minShift = 0;
		double minDiff = 999, avgDiff = 0.0;

		int[] englishFreqs = {8167, 1492, 2782, 4253, 12702, 2228, 2015, 6094,
		 6966, 153, 772, 4025, 2406, 6749, 7507, 1929, 95, 5987, 6327, 9056,
		  2758, 978, 2360, 150, 1974, 74};
		int englishCount = 100000;

		LetterFrequency english = new LetterFrequency(englishFreqs, englishCount);
		LetterFrequency lf = new LetterFrequency("I say to the House as I said to ministers who have joined this government, I have nothing to offer but blood, toil, tears, and sweat.");
		lf = new LetterFrequency("Yk ymym mximke emup xurq ime xuwq m naj ar otaoaxmfqe. Kag zqhqd wzai itmf kag'dq sazzm sqf");
		int[] a = lf.getFreqArray();
		//System.out.println(lf.toString());
		//System.out.println(lf2.toString());
		//System.out.println("" + lf.getAverageProportionDifference(lf2));
		//System.out.println(english.toString());
		for(int i = 0; i < 26; i++)
		{
			avgDiff = english.getAverageProportionDifference(lf.shift(i));
			if(avgDiff < minDiff)
			{
				minShift = i;
				minDiff = avgDiff;
			}
		}

		minShift = 26 - minShift;

		System.out.println("Most likely shift val: " + minShift + " with averge diff of " + minDiff);
	}
}