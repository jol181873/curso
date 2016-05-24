package ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameConFlowLayout extends JFrame{
	JButton botonN;
	JButton botonS;
	JButton botonE;
	JButton botonW;
	JButton botonC;
	
	public FrameConFlowLayout() {
		super();		
		initComponents();			
	}
	
	public FrameConFlowLayout(String titulo) {
		this();
		setTitle(titulo);
	}
	
	public void initComponents() {
		setLayout(new FlowLayout());
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
		add(botonN);
		add(botonS);
		add(botonE);
		add(botonW);
		add(botonC);
	}
	
	private void addListeners() {
		
	}
}
