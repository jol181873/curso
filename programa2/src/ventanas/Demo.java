package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

//MOuseListener MouseMotionListener

public class Demo extends JFrame implements FocusListener{		
	JButton boton;
	JTextField texto;
	
	public Demo() {
		super();
		initComponents();
	}

	public Demo(String titulo) {
		this();
		setTitle(titulo);
	}
	
	private void initComponents() {
		setSize(500,500);
		setVisible(true);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		boton=new JButton("Púlsame");
		boton.setBounds(40, 90, 100, 40);
		texto=new JTextField();
		texto.setBounds(40, 40, 100, 40);
		add(boton);
		add(texto);
		
		boton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JButton) {
					texto.setText("click");
				}
				
			}
			
		});
		texto.addFocusListener(this);
		
		setLayout(null);
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		boton.setText("foco obtenido");
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		boton.setText("foco perdido");
		
	}
}
