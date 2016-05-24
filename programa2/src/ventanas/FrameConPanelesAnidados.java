package ventanas;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class FrameConPanelesAnidados extends JFrame{
	JButton boton;
	JTextField texto;
	JLabel etq;
	JTextArea area;
	JCheckBox check;
	JPanel panelPrincipal;
	
	JPanel panelAnidado;
	JButton arriba;
	JButton abajo;
	JButton derecha;
	JButton izquierda;
	
	int pos=0;
	
	public FrameConPanelesAnidados() {
		super();		
		initComponents();			
	}
	
	public FrameConPanelesAnidados(String titulo) {
		this();
		setTitle(titulo);
	}
	
	public void initComponents() {
		panelPrincipal=new JPanel(); 
		panelPrincipal.setLayout(new GridLayout(2,3,10,10));
		panelAnidado=new JPanel();
		GridBagLayout layout=new GridBagLayout();
		panelAnidado.setLayout(layout);
		configurarVentana();
		configurarFormulario();			
		addComponentesAVentana();
		addListeners();
		boton.setBackground(Color.RED);				
		pack();
	}

	private void configurarVentana() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		setVisible(true);
	}
	
	private void configurarFormulario() {		
		boton=new JButton("Boton");		
				
		texto=new JTextField("TextoField");
				
		etq=new JLabel("Etiqueta");
		etq.setOpaque(true);
				
		area=new JTextArea("Area");
				
		check=new JCheckBox("CheckBox");
		
		
		arriba=new JButton("8"); 
		//arriba.setFocusable(false);
		
		abajo=new JButton("2");
		//abajo.setFocusable(false);
		
		izquierda=new JButton("4");
		//izquierda.setFocusable(false);
		
		derecha=new JButton("6");
		//derecha.setFocusable(false);
	}
	
	private void addComponentesAVentana() {			
		panelPrincipal.add(boton);
		panelPrincipal.add(texto);
		panelPrincipal.add(etq);
		panelPrincipal.add(area);
		panelPrincipal.add(check);
		panelPrincipal.add(panelAnidado);		
		
		GridBagConstraints restriccion=new GridBagConstraints();
		restriccion.fill = GridBagConstraints.HORIZONTAL;
		restriccion.gridx=1;
		restriccion.gridy=0;
		panelAnidado.add(arriba,restriccion);
		
		restriccion=new GridBagConstraints();
		restriccion.fill = GridBagConstraints.HORIZONTAL;
		restriccion.gridx=0;
		restriccion.gridy=1;
		panelAnidado.add(izquierda,restriccion);
		
		restriccion=new GridBagConstraints();
		restriccion.fill = GridBagConstraints.HORIZONTAL;
		restriccion.gridx=2;
		restriccion.gridy=1;
		panelAnidado.add(derecha,restriccion);
		
		restriccion=new GridBagConstraints();
		restriccion.fill = GridBagConstraints.HORIZONTAL;
		restriccion.gridx=1;
		restriccion.gridy=2;
		panelAnidado.add(abajo,restriccion);
		
		add(panelPrincipal);
	}
	
	private void addListeners() {
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getSource()==boton) {
					etq.setText("El boton es ahora rojo");
				} else if (arg0.getSource()==texto) {
					etq.setText("El textfield es ahora rojo");
				} else if (arg0.getSource()==area) {
					etq.setText("El textarea es ahora rojo");
				} else if (arg0.getSource()==check) {
					etq.setText("El checkbox es ahora rojo");
				} else if (arg0.getSource()==etq) {
					etq.setText("La etiqueta es ahora roja");
				} else if (arg0.getSource()==panelAnidado) {
					etq.setText("El panel de cursores es ahora rojo");
				} 				
			}
			
		};
		
		boton.addPropertyChangeListener("background", propertyChangeListener);
		texto.addPropertyChangeListener("background", propertyChangeListener);
		etq.addPropertyChangeListener("background", propertyChangeListener);
		area.addPropertyChangeListener("background", propertyChangeListener);
		check.addPropertyChangeListener("background", propertyChangeListener);
		panelAnidado.addPropertyChangeListener("background", propertyChangeListener);
		
		arriba.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos>=3) {
					mover(pos,pos-3);
				}				
			}
		});		
		
		abajo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos<=2) {
					mover(pos,pos+3);
				}				
			}
		});
		
		izquierda.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos>0) {
					mover(pos,pos-1);
				} else {					
					mover(pos,5);
				}
			}
		});
		
		derecha.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos<5) {					
					mover(pos,pos+1);
				} else {										
					mover(pos,0);
				}
			}
		});
		
		/*this.addKeyListener(new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: 
						if (pos>=3) {
							mover(pos,pos-3);
						}
						break;
					case KeyEvent.VK_DOWN:
						if (pos<=2) {
							mover(pos,pos+3);
						}
						break;
					case KeyEvent.VK_LEFT:
						if (pos>0) {
							mover(pos,pos-1);
						} else {					
							mover(pos,5);
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (pos<5) {					
							mover(pos,pos+1);
						} else {										
							mover(pos,0);
						}
						break;															
				}				
			}
		});
		*/
		
		panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),"arriba");
		panelPrincipal.getActionMap().put("arriba",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos>=3) {
					mover(pos,pos-3);
				}
			}
			
		});
		
		panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),"abajo");
		panelPrincipal.getActionMap().put("abajo",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos<=2) {
					mover(pos,pos+3);
				}
			}
			
		});
		
		panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"izquierda");
		panelPrincipal.getActionMap().put("izquierda",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos>0) {
					mover(pos,pos-1);
				} else {					
					mover(pos,5);
				}
			}			
		});
		
		panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"derecha");
		panelPrincipal.getActionMap().put("derecha",new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pos<5) {					
					mover(pos,pos+1);
				} else {										
					mover(pos,0);
				}
			}			
		});	
	}
	
	
	public void mover(int pos0,int pos1) {
		panelPrincipal.getComponent(pos0).setBackground(Color.WHITE);		
		panelPrincipal.getComponent(pos1).setBackground(Color.RED);
		pos=pos1;
	}
}
