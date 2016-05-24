package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vista.peluqueria.FormularioPeluqueria;
import vista.peluqueria.ListadoPeluquerias;
import vista.usuario.FormularioUsuario;
import vista.usuario.ListadoUsuarios;

public class FramePrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final FormularioPeluqueria internalNuevaPeluqueria = new FormularioPeluqueria();
		contentPane.add(internalNuevaPeluqueria, BorderLayout.CENTER);
		final ListadoPeluquerias internalListadoPeluquerias = new ListadoPeluquerias(internalNuevaPeluqueria);
		contentPane.add(internalListadoPeluquerias, BorderLayout.CENTER);

		final FormularioUsuario internalNuevoUsuario = new FormularioUsuario();
		contentPane.add(internalNuevoUsuario, BorderLayout.CENTER);
		final ListadoUsuarios internalListadoUsuarios = new ListadoUsuarios(internalNuevoUsuario);
		contentPane.add(internalListadoUsuarios, BorderLayout.CENTER);

		JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuArchivo.add(mntmNewMenuItem);

		JMenu menuModulos = new JMenu("M\u00F3dulos");
		menuBar.add(menuModulos);

		JMenu mnNewMenu = new JMenu("Peluquer\u00EDas");
		menuModulos.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nueva peluquer\u00EDa");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(internalNuevaPeluqueria, BorderLayout.CENTER);
				internalListadoPeluquerias.setVisible(false);
				internalListadoUsuarios.setVisible(false);
				internalNuevoUsuario.setVisible(false);
				internalNuevaPeluqueria.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado peluquer\u00EDas");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(internalListadoPeluquerias, BorderLayout.CENTER);
				internalNuevaPeluqueria.setVisible(false);
				internalNuevoUsuario.setVisible(false);
				internalListadoUsuarios.setVisible(false);
				internalListadoPeluquerias.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu menuAdmin = new JMenu("Administraci\u00F3n");
		menuBar.add(menuAdmin);

		JMenu mnNewMenu_1 = new JMenu("Usuarios");
		menuAdmin.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Nuevo usuario");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.add(internalNuevoUsuario, BorderLayout.CENTER);
				internalNuevaPeluqueria.setVisible(false);
				internalListadoPeluquerias.setVisible(false);
				internalListadoUsuarios.setVisible(false);
				internalNuevoUsuario.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado usuarios");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.add(internalListadoUsuarios, BorderLayout.CENTER);
				internalNuevaPeluqueria.setVisible(false);
				internalNuevoUsuario.setVisible(false);
				internalListadoPeluquerias.setVisible(false);
				internalListadoUsuarios.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
	}

}
