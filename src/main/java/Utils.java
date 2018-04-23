import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	/*validate email - Check to make sure email follows the form
	 * "some string" + "@" + "some string" + "." + "some string"
	 */
	public static boolean validateEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if(email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}
	
	/*convert phone number into 10 digit phone number
	 * check to see if 10 digit phone number can be created
	 * from input. Returns null if error in original number
	 */
	public static String convertPhone(String number) {
		int length = number.length();
		String newPhone = "";
		for(int i = 0; i < length; i++) {
			if(Character.isDigit(number.charAt(i))) {
				newPhone += number.charAt(i);
			}
			if(newPhone.length() > 10) {
				return null;
			}
		}
		if(newPhone.length() != 10) {
			return null;
		}
		return newPhone;
	}
	
	public static void main(String[] args) {
	}
	
}
