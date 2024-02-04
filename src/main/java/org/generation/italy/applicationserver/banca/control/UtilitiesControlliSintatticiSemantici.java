package org.generation.italy.applicationserver.banca.control;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilitiesControlliSintatticiSemantici {

	
	public 
	static
	boolean checkFormatCodiceFiscale(String codiceFiscale) {
		
		Pattern pattern = Pattern.compile("^[A-Z]{6}[0-9]{10}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(codiceFiscale);
	    return matcher.find();
	    
	}
 
	public 
	static
	boolean checkFormatIban(String iban) {
		
		Pattern pattern = Pattern.compile("^[A-Za-z]{4}[0-9]{28}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(iban);
	    return matcher.find();
	    
	}
 	
	
	public 
	static
	boolean checkFormatValuta(String valuta) {
		return (valuta.length() == 3);
	}
	
	public 
	static
	boolean checkSemanticValuta(String valuta) {
		
		String[] valuteAmmesseArray = {"EUR", "GBP", "USD", "YEN"};
		List<String> valuteAmmesseList = Arrays.asList(valuteAmmesseArray);
		
		return (valuteAmmesseList.contains(valuta));
	}
	
	public static boolean checkSementicImporto (String importo) {
		 
		Float importoFloat = new Float(importo);
		return (importoFloat>0);
	}
}
