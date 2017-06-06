package hello;

import java.util.HashSet;
import java.util.Set;

public class NameGenerator {

	// class variable
	private static final String consonantes = "bcdfghjklmnpqrstvwxyz";
	private static final String vocales = "aeiou";

	private static final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	private static final Set<String> identifiers = new HashSet<String>();

	public static String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        builder.append(String.valueOf(consonantes.charAt(rand.nextInt(consonantes.length()))).toUpperCase());
	        for(int i = 0; i < length; i++) {
	            builder.append(vocales.charAt(rand.nextInt(vocales.length())));
		        builder.append(consonantes.charAt(rand.nextInt(consonantes.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}


}
