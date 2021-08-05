package initialproject2.helper;

import java.util.Random;

import javax.servlet.http.HttpSession;

public class TokenGenerator {
	
	static private Integer tokenSize = 45;
	 
/////////////////////////////////////////////////Generating Random Characters\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 public static String getAlphaNumericString()
	 {

		 // lower limit for LowerCase Letters
		 int lowerLimit = 97;

		 // lower limit for LowerCase Letters
		 int upperLimit = 122;

		 Random random = new Random();

		 // Create a StringBuffer to store the result
		 StringBuffer r = new StringBuffer(tokenSize);

		 for (int i = 0; i < tokenSize; i++) {

			 // take a random value between 97 and 122
			 int nextRandomChar = lowerLimit
					 + (int)(random.nextFloat()
							 * (upperLimit - lowerLimit + 1));

			 // append a character at the end of bs
			 r.append((char)nextRandomChar);
		 }

		 // return the resultant string
		 return r.toString();
	 }

}
