package vista;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.usuario.NegocioUsuario;
import modelo.usuario.Usuario;

public class LoginFrame {

	private JFrame frame;
	private JTextField txtNombreDeUsuario;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Nombre de usuario");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		txtNombreDeUsuario = new JTextField();
		GridBagConstraints gbc_txtNombreDeUsuario = new GridBagConstraints();
		gbc_txtNombreDeUsuario.fill = GridBagConstraints.BOTH;
		gbc_txtNombreDeUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_txtNombreDeUsuario.gridx = 2;
		gbc_txtNombreDeUsuario.gridy = 1;
		frame.getContentPane().add(txtNombreDeUsuario, gbc_txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		frame.getContentPane().add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 4;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usuario = new Usuario(txtNombreDeUsuario.getText(), new String(passwordField.getPassword()));

				try {
					NegocioUsuario negocioUsuario = NegocioUsuario.getInstance();

					negocioUsuario.conectar();
					Usuario usuarioLogueado = negocioUsuario.obtenerObjeto(negocioUsuario.getConnection(),
							"SELECT * FROM USUARIO WHERE USUA_NOMBRE=?", txtNombreDeUsuario.getText());

					if (usuarioLogueado == null) {
						// no es un nombre de usuario correcto

						JOptionPane.showMessageDialog(frame, "No existe un usuario con ese nombre");
					} else {
						if (!usuarioLogueado.esCorrecto(usuario.getPassword())) {
							JOptionPane.showMessageDialog(frame, "Password erróneo");
						} else {
							frame.dispose();
							FramePrincipal framePrincipal = new FramePrincipal();
							framePrincipal.setVisible(true);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

}
