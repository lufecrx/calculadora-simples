package br.com.lufecrx.calc.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton {

	public Botao(String label, Color cor) {
		setText(label);
		setFont(new Font("Courier", Font.PLAIN, 25));
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(new Color(32,32,32,255)));
	}
}
