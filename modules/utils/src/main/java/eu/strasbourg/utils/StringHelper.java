package eu.strasbourg.utils;

import java.text.Collator;

public class StringHelper {

	/**
	 * Retourne true si s1 et s2 sont les mêmes chaînes de caractère, sans
	 * prendre en compte les différences d'accentuation
	 */
	public static boolean compareIgnoringAccentuation(String s1, String s2) {
		Collator instance = Collator.getInstance();
		
		// This strategy mean it'll ignore the accents
	    instance.setStrength(Collator.NO_DECOMPOSITION);
	    
	    return instance.compare(s1, s2) == 0;
	}
}
