package selfstudy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Study04_RegexMatch {
	public static void main(String[] args) {
		String line = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";
		
		// Create pattern
		Pattern p = Pattern.compile(pattern);
		
		// Create Matcher
		Matcher matcher = p.matcher(line);
		
		if (matcher.find()) {
			System.out.println("Found value: " + matcher.group(0));
			System.out.println("Found value: " + matcher.group(1));
			System.out.println("Found value: " + matcher.group(2));
		} else {
			System.out.println("No match!");
		}
	}
}
