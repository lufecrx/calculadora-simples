package br.com.lufecrx.calc.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.lufecrx.calc.modelo.Memoria;
import br.com.lufecrx.calc.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{	
	
	// TODO fazer a fonte reduzir tamanho
	private final JLabel label;
	
	public Display() {
		Memoria.getInstancia().adicionarObservador(this);
		
		setBackground(new Color(32,32,32,255));
		label = new JLabel(Memoria.getInstancia().getTextoAtual());

		label.setForeground(Color.WHITE);
		label.setFont(new Font("Courier", Font.PLAIN, 30));

		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 15));
	
		add(label);
	}
	
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
	
}
