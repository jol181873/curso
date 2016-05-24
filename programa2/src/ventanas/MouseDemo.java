package ventanas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MouseDemo extends JFrame {
	JLabel etq1;
	JLabel etq2;
	JTextField texto1;
	JTextField texto2;
	
	public MouseDemo() {
		super();		
		initComponents();			
	}
	
	public MouseDemo(String titulo) {
		this();
		setTitle(titulo);
	}
	
	public void initComponents() {
		configurarVentana();
		configurarFormulario();			
		addComponentesAVentana();
		addListeners();
		
		setLayout(null);			
	}

	private void configurarVentana() {
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		setVisible(true);
	}
	
	private void configurarFormulario() {
		etq1=new JLabel("El raton está en : ");
		etq2=new JLabel("El ratón ha hecho: ");
		texto1=new JTextField();
		texto2=new JTextField();
		
		
		etq1.setBounds(10,40,200,40); texto1.setBounds(220, 40, 100, 40);
		etq2.setBounds(10,90,200,40); texto2.setBounds(220, 90, 100, 40);								
	}
	
	private void addComponentesAVentana() {
		this.add(etq1);
		this.add(etq2);
		this.add(texto1);
		this.add(texto2);
	}
	
	private void addListeners() {
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				texto2.setText("Released");
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				texto2.setText("Pressed");
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				texto2.setText("Exited");
				texto1.setText("Sin posición");
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				texto2.setText("Entered");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				texto2.setText("Clicked");
				
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				texto1.setText("("+e.getX()+","+e.getY()+")");
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				texto1.setText("Dragged");				
			}
		});
	}
}
