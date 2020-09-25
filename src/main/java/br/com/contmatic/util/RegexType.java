package br.com.contmatic.util;

/**
 * The Class RegexType.
 * 
 * @author gabriel.santos
 */
public final class RegexType {

	/** The Constant URL. */
	public static final String URL = "^(www).[-a-zA-Z0-9+_|!:,.;]*[-a-zA-Z0-9+_|]";

	/** The Constant CEP. */
	public static final String CEP = "^[[0-9]{5}-[\\\\d]{3}]+$";

	/** The Constant NOME. */
	public static final String NOME = "(\\w|\\s|ç|[á-ú])+";

	/** The Constant EMAIL. */
	public static final String EMAIL = "[\\w-]+@([\\w-]+\\.)+[\\w-]+";

	/** The Constant LETRAS_NUMEROS. */
	public static final String LETRAS_NUMEROS = "^[A-Za-záÁ-úÚÇÑ0-9_ '\\\\s]+$";
	
	/** The Constant LETRAS. */
	public static final String LETRAS = "^[A-Za-záÁ-úÚÇÑ_ '\\\\s]+$";

	/** The Constant NUMERO. */
	public static final String NUMEROS = "^[0-9]*";
	
	public static final String ESPACOS_DUPLICADOS = "\\s\\s+";
	
	public static final String CARACTERES_ESPECIAIS = "[!@#$%¨&*()_+=|`´^~\\/{}]";

	/**
	 * Instantiates a new regex type.
	 */
	private RegexType() {
	}
	
}