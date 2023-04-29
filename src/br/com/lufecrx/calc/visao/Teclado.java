package br.com.lufecrx.calc.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.lufecrx.calc.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private final Color COR_CINZA_ESCURO = new Color(50, 50, 50, 255);
	private final Color COR_CINZA_CLARO = new Color(59, 59, 59, 255);
	private final Color COR_CINZA_BORDA = new Color(32, 32, 32, 255);
	private final Color COR_CINZA = new Color(89, 89, 89, 255);

	public Teclado() {

		setBackground(COR_CINZA_BORDA);

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(layout);

		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

//		Linha 1
		c.gridwidth = 3;
		adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1;
		adicionarBotao("/", COR_CINZA_ESCURO, c, 3, 0);

//		Linha 2
		adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);
		adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);
		adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);
		adicionarBotao("x", COR_CINZA_ESCURO, c, 3, 1);

//		Linha 3
		adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);
		adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);
		adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);
		adicionarBotao("-", COR_CINZA_ESCURO, c, 3, 2);

//		Linha 4
		adicionarBotao("1", COR_CINZA_CLARO, c, 0, 3);
		adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);
		adicionarBotao("3", COR_CINZA_CLARO, c, 2, 3);
		adicionarBotao("+", COR_CINZA_ESCURO, c, 3, 3);

//		Linha 5
		adicionarBotao("±", COR_CINZA_CLARO, c, 0, 4);
		adicionarBotao("0", COR_CINZA_CLARO, c, 1, 4);
		adicionarBotao(",", COR_CINZA_CLARO, c, 2, 4);
		adicionarBotao("=", COR_CINZA, c, 3, 4);
	}

	private void adicionarBotao(String label, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(label, cor);

		botao.addActionListener(this);

		add(botao, c);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			Memoria.getInstancia().processarComando(botao.getText());
		}

	}
}