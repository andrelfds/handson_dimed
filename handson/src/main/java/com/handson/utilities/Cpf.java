package com.handson.utilities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Transient;

public class Cpf {

	/*
	 * ################################## Static Start
	 * #####################################
	 */
	/**
	 * Retorna uma {@link String} contendo os 2 digitos Validadores para o
	 * {@link Cpf} Enviado. Para tal usa-se do teste estipulado pela Receita
	 * Federal. <BR>
	 * <BR>
	 * mais informa��es em: http://www.receita-fazenda.gov.br
	 * 
	 * @see #getDigits()
	 * @see #isValid(Cpf)
	 * @param cpf
	 *            O cpf ao qual deseja-se saber quais s�o os 2 digitos
	 *            validadores.
	 * @return {@link String} contendo os 2 digitos que validam o CPF enviado.
	 */
	public static String digitValidator(Cpf cpf) {
		String cpfString = cpf.toString();
		long firstDigitSum = 0;
		long secondDigitSum = 0;
		for (int i = 0; i < 10; i++) {
			String s = cpfString.substring(i, i + 1);
			firstDigitSum += (10 - i) * Integer.parseInt(s);
			secondDigitSum += (11 - i) * Integer.parseInt(s);
		}
		// firstDigitSum%11 � o mesmo que
		// "Resto da divis�o de firstDigitSum por 11"
		long digit1 = ((firstDigitSum % 11) > 1) ? 11 - (firstDigitSum % 11) : 0;
		long digit2 = ((secondDigitSum % 11) > 1) ? 11 - (secondDigitSum % 11) : 0;

		return "" + digit1 + digit2;
	}

	/**
	 * Verifica se o objeto {@link Cpf} enviado � um CPF v�lido, de acordo com o
	 * teste estipulado pela receita federal. Desconcidera para tal o CPF
	 * 000.000.000-00 �nico que atende os requisitos do teste, porem � dado como
	 * inv�lido, estipulado pela Receita Federal:
	 * http://www.receita-fazenda.gov.br
	 * 
	 * @see #digitValidator(Cpf)
	 * @param cpf
	 *            {@link Cpf} a ser testado.
	 * @return <tt>true</tt> caso o CPF seja v�lido <BR>
	 *         <tt>false</tt> caso o CPF seja inv�lido
	 */
	public static boolean isValid(Cpf cpf) {
		return (cpf.equals("00000000000")) ? false : digitValidator(cpf).equals(cpf.getDigits());
	}

	/**
	 * Apenas um atalho, o mesmo que um dos dois comando abaixo: <BR>
	 * <tt>(new {@link Cpf}(cpfString)).isValid()</tt>
	 * 
	 * @see #isValid(Cpf)
	 * @param cpf
	 *            String contendo o CPF a ser testado
	 * @return <tt>true</tt> caso o CPF seja v�lido <BR>
	 *         <tt>false</tt> caso o CPF seja inv�lido
	 */
	public static boolean isValid(String cpf) {
		return (new Cpf(cpf)).isValid();
	}

	/**
	 * Poem mascaras de formata��o no objeto CPF, retornando no formato
	 * <tt>___.___.-__</tt> <BR>
	 * Por exemplo: <BR>
	 * <tt>cpf = new Cpf("12345678909") 
	 * <BR>Cpf.toFormated(cpf) //String de retorno = "123.456.789-09"</tt>
	 * 
	 * @see #toFormated()
	 * @param cpf
	 * @return
	 */
	public static String toFormated(Cpf cpf) {
		String cpfString = cpf.toString();
		cpfString = String.format("%s.%s.%s-%s", cpfString.substring(0, 3), cpfString.substring(3, 6), cpfString.substring(6, 9), cpfString.substring(9, 11));
		return cpfString;
	}

	/**
	 * Apenas um atalho, o mesmo que objetCpf.toLong()
	 * 
	 * @see #toLong()
	 * @param cpf
	 * @return
	 */
	public static long toLong(Cpf cpf) {
		return cpf.toLong();
	}

	/**
	 * Retira toda formata��o existente na String enviada, removendo todos os
	 * caracters inv�lidos, e deichando apenas n�meros. <BR>
	 * <BR>
	 * Caso a string restante seja inferior a 11 digitos, preenche os caracters
	 * restantes com "0"s a esquerda. <BR>
	 * <BR>
	 * Caso a string seja superio a 11 digitos ela ser� truncada para 11
	 * digitos.
	 * 
	 * @see StringUtil#toNumericDigitsOnly(String)
	 * @param cpf
	 *            {@link String} a ser removida as formata��es.
	 * @return {@link String} sem formata��o, apeas com os digitos
	 */
	public static String toUnFormated(String cpf) {
		cpf = (cpf == null) ? "" : StringUtil.toNumericDigitsOnly(cpf);
		if (cpf.length() > 11)
			cpf = cpf.substring(0, 11);
		else if (cpf.length() < 11) // insert (11-length) "0"s before the cpf
									// number
			cpf = "00000000000".substring(cpf.length(), 11) + cpf;
		return cpf;
	}

	/*
	 * ################################# Instance Start
	 * ####################################
	 */
	/**
	 * O cpf deste objeto.
	 */
	@Column(unique = true)
	private String	cpf		= null;
	/**
	 * Transiente value, n�o persistido no banco de dados, responde se este cpf
	 * � valido. � preciso ser resgatado via isValid(), para que possa ser
	 * inicializado o teste. <BR>
	 * O teste s� � realizado uma �nica vez.
	 */
	@Transient
	private Boolean	isValid	= null;

	/**
	 * Construtor nulo, cria um CPF invalido cujo o valor � 000.000.000-00
	 */
	public Cpf() {
	}

	/**
	 * Constroi um Cpf guardando seu valor em {@link #cpf}, qualquer que seja a
	 * string passada, ela ser� devidamente tratada. <BR>
	 * Todos os caracters invalidos ser�o removidos, sobrando apenas n�meros, se
	 * faltar digitos para completar 11, ser�o incluidos "0" a esquerda da
	 * string. <BR>
	 * <BR>
	 * Invoca implicitamente {@link #toFormated(Cpf)}. <BR>
	 * <BR>
	 * O teste quanto a sua valida��o s� � ralizado ap�s {@link #isValid()} ser
	 * invocado, este teste s� � realizado uma vez, e seu valor � guardado
	 * dentro do campo {@link #isValid}.
	 * 
	 * @see #toUnFormated(String)
	 * @param cpf
	 *            Objeto {@link Cpf} j� construido.
	 */
	public Cpf(String cpf) {
		this.cpf = cpf;
		cpfInitializeted();
	}

	/**
	 * Constroi um objeto {@link Cpf} a partir de um {@link Long}
	 * 
	 * @param cpf
	 *            {@link Long} valor do cpf.
	 */
	public Cpf(Long cpf) {
		this.cpf = (cpf == null) ? null : cpf.toString();
		cpfInitializeted();
	}

	/**
	 * Constroi um objeto {@link Cpf} a partir de um {@link BigInteger}
	 * 
	 * @param cpf
	 *            {@link BigInteger} valor do cpf
	 */
	public Cpf(BigInteger cpf) {
		this.cpf = (cpf == null) ? null : cpf.toString();
		cpfInitializeted();
	}

	/**
	 * Inicializa o valor do CPF, formantando-o adequadamente.
	 */
	private void cpfInitializeted() {
		if (cpf == null)
			cpf = "";
		else
			cpf = cpf.trim();
		this.cpf = toUnFormated(cpf);
	}

	/**
	 * Garante que {@link #cpfInitializeted()} j� foi invocado previamente,
	 * invocando-o caso preciso.
	 */
	private void ensuresCpfInitializeted() {
		if (cpf == null)
			cpfInitializeted();
	}

	/**
	 * Garante que a vari�vel {@link #isValid} j� foi setada, via teste do cpf.
	 */
	private void ensuresWasValidated() {
		if (isValid == null)
			validateInitialize();
	}

	/**
	 * Testa se o cpf � valido.
	 */
	private void validateInitialize() {
		isValid = isValid(this);
	}

	/**
	 * Verifica se 2 Cpfs s�o igauis, � capaz de testar se um objeto {@link Cpf}
	 * � igual a uma string contendo um valor de um Cpf n�o formato, apenas com
	 * numeros digitos.
	 * 
	 * @return <tt>true</tt> caso o objeto enviado tenha o mesmo cpf deste <BR>
	 *         <tt>false</tt> caso os Cpfs sejam distintos.
	 */
	public boolean equals(Object obj) {
		ensuresCpfInitializeted();
		boolean equals = false;
		if (obj instanceof String)
			equals = obj.equals(cpf);
		else if (obj instanceof Cpf)
			equals = ((Cpf) obj).toString().equals(cpf);
		return equals;
	}

	/**
	 * Retorna os 2 ultimos digitos do {@link Cpf}. <BR>
	 * <BR>
	 * � importante lembrar que esse m�todo retorna os 2 �ltimos digitos reais
	 * do CPF enviado, onde estes n�o s�o necess�riamente os mesmos de
	 * <tt>{@link Cpf#digitValidator(Cpf)}</tt>, quando estes 2 s�o
	 * coincidentes, indica que este CPF � valido, caso contr�rio o CPF �
	 * inv�lido.
	 * 
	 * @see #digitValidator(Cpf)
	 * @return {@link String} contendo os 2 ultimos d�gitos do CPF.
	 */
	public String getDigits() {
		ensuresCpfInitializeted();
		return cpf.substring(9, 11);
	}

	/**
	 * Indica se este cpf � ou n�o valido. Este teste s� � verificado
	 * internamente uma vez, podendo este m�todo ser invocado sem perda de
	 * desempenho.
	 * 
	 * @return <tt>true</tt> - Quando o cpf � valido <BR>
	 *         <tt>false</tt> - Caso contrario.
	 */
	public boolean isValid() {
		ensuresWasValidated();
		return isValid;
	}

	/**
	 * Converte esse CPF em um {@link BigInteger}
	 * 
	 * @return
	 */
	public BigInteger toBigInteger() {
		ensuresCpfInitializeted();
		return new BigInteger(cpf);
	}

	/**
	 * Poem mascaras de formata��o no objeto CPF, retornando no formato
	 * <tt>___.___.-__</tt> <BR>
	 * Por exemplo: <BR>
	 * <tt>cpf = new Cpf("12345678909") 
	 * <BR>Cpf.toFormated(cpf) //String de retorno = "123.456.789-09"</tt>
	 * 
	 * @see #toFormated()
	 * @param cpf
	 * @return
	 */
	public String toFormated() {
		ensuresCpfInitializeted();
		return toFormated(this);
	}

	/**
	 * Retorna um <tt>long</tt> que representa o valor deste cpf, porem todos os
	 * "0"s a esquerda do n�mero do cpf ser� apagado <BR>
	 * <BR>
	 * Por exemplo: <BR>
	 * <tt>long teste = (new Cpf("001.034.232-00")).toLong(); 
	 * <BR>{@link System#out}.println(teste); //saido do console = 103423200</tt>
	 * 
	 * @return {@link Long} com o valor deste cpf.
	 */
	public long toLong() {
		ensuresCpfInitializeted();
		return Long.parseLong(cpf);
	}

	/**
	 * Retorna o valor deste cpf em forma de {@link String}, sem formata��es.
	 * 
	 * @return
	 */
	public String toString() {
		ensuresCpfInitializeted();
		return cpf;
	}
}