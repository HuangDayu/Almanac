package test_3;

public class Run {

	// ¼×¡¢ÒÒ¡¢±û¡¢¶¡¡¢Îì¡¢¼º¡¢¸ý¡¢ÐÁ¡¢ÈÉ¡¢¹ï
	public static final int[] skyBranch = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	// ×Ó¡¢³ó¡¢Òú¡¢Ã®¡¢³½¡¢ËÈ¡¢Îç¡¢Î´¡¢Éê¡¢ÓÏ¡¢Ðç¡¢º¥
	public static final char[] earthBranch = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l' };

	public static void main(String[] args) {

		for (int i = 0; i < 2020; i++) {
			try {
				caculate(i);
				if ((i % 20) == 0) {
					System.out.println("");
				} else {
					System.out.print(",");
				}

			} catch (RuntimeException e) {
				System.out.println("Year" + i + " met exception.");

			}
		}

	}

	private static void caculate(int i) {
		if (i < 4) {
			throw new IllegalArgumentException("The starting year must be greater than 4");
		}
		int realYear = i - 4;
		System.out.print("year" + i + " =[" + skyBranch[realYear % 10] + "][" + earthBranch[realYear % 12] + "]");

	}

}
