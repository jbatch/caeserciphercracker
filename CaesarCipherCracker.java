public class CaesarCipherCracker
{
	public static void main(String args[])
	{
		LetterFrequency lf = new LetterFrequency("This is my message");
		LetterFrequency lf2 = LetterFrequency.shift(lf, 27);

		int[] a = lf.getFreqArray();
		System.out.println(lf.toString());
		System.out.println(lf2.toString());
		//System.out.println("" + lf.getAverageProportionDifference(lf2));
	}
}