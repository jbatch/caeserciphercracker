public class CaesarCipherCracker
{
	public static void main(String args[])
	{
		LetterFrequency lf = new LetterFrequency("This is my message");
//		lf.inc('c');
		int[] a = lf.getFreqArray();
		System.out.println(lf.toString());
	}
}