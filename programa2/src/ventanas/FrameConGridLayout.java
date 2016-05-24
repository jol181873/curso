package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameConGridLayout extends JFrame{
	JButton botonN;
	JButton botonS;
	JButton botonE;
	JButton botonW;
	JButton botonC;
	JPanel jp;
	
	public FrameConGridLayout() {
		super();		
		initComponents();			
	}
	
	public FrameConGridLayout(String titulo) {
		this();
		setTitle(titulo);
	}
	
	public void initComponents() {
		jp=new JPanel(); 
		jp.setLayout(new GridLayout(2,2));
		//setLayout(new GridLayout(3,3));
		configurarVentana();
		configurarFormulario();			
		addComponentesAVentana();
		addListeners();							
	}

	private void configurarVentana() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setVisible(true);
	}
	
	private void configurarFormulario() {		
		botonN=new JButton("N");
		botonS=new JButton("S");
		botonE=new JButton("E");
		botonW=new JButton("W");
		botonC=new JButton("C");
		
		botonN.setSize(100, 20);
		botonS.setSize(100, 20);
		botonE.setSize(100, 20);
		botonW.setSize(100, 20);
		botonC.setSize(100, 20);
	}
	
	private void addComponentesAVentana() {
		add(jp);
		jp.add(botonN);
		jp.add(botonS);
		jp.add(botonE);
		jp.add(botonW);
		jp.add(botonC);
	}
	
	private void addListeners() {
		
	}
}
