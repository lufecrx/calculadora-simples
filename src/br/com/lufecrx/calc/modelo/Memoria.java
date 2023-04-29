package br.com.lufecrx.calc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, NUMERO, DIV, PROD, SUB, SOMA, IGUAL, SINAL, VIRGULA;
	};

	private static final Memoria instancia = new Memoria();
	private static final List<MemoriaObservador> observadores = new ArrayList<>();

	private TipoComando operacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";

	private Memoria() {

	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void processarComando(String texto) {

		TipoComando tipoComando = detectarTipoComando(texto);
		System.out.println(tipoComando);

		if (tipoComando == null) {
			return;
		} else if (tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			operacao = null;
		// tirar sinal
		} else if (tipoComando == TipoComando.SINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		// colocar sinal
		} else if (tipoComando == TipoComando.SINAL) {
			textoAtual = "-" + textoAtual;
		} else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? texto : textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			operacao = tipoComando;
		}

		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if (operacao == null || operacao == TipoComando.IGUAL) {
			return textoAtual;
		}

		double resultado = 0;

		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));

		if (operacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		}
		if (operacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}
		if (operacao == TipoComando.PROD) {
			resultado = numeroBuffer * numeroAtual;
		}
		if (operacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}

		String texto = Double.toString(resultado).replace(".", ",");
		boolean inteiro = texto.endsWith(",0");

		return inteiro ? texto.replace(",0", "") : texto;
	}

	private TipoComando detectarTipoComando(String texto) {
		if (textoAtual.isEmpty() && texto.equals("0")) {
			return null;
		}

		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
//			Quando não for numero
			if ("AC".equals(texto)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComando.DIV;
			} else if ("x".equals(texto)) {
				return TipoComando.PROD;
			} else if ("-".equals(texto)) {
				return TipoComando.SUB;
			} else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			} else if (",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			} else if ("±".equals(texto)) {
				return TipoComando.SINAL;

			} else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			}
		}

		return null;
	}

}
