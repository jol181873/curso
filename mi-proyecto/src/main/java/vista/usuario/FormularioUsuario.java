package vista.usuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.validation.ConstraintViolation;

import modelo.usuario.NegocioUsuario;
import modelo.usuario.Usuario;
import vista.genericos.FormularioGenerico;
import vista.genericos.TipoFormulario;

public class FormularioUsuario extends FormularioGenerico<Usuario> {
	private JTextField textNombre;
	private JPasswordField textPassword;

	public FormularioUsuario(Usuario usuario) {
		super(NegocioUsuario.getInstance(), usuario);
	}

	public FormularioUsuario() {
		super(NegocioUsuario.getInstance());
	}

	@Override
	protected void iniciarComponentes() {
		super.iniciarComponentes();
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textNombre = new JTextField();
		textNombre.setEditable(getTipo() == TipoFormulario.NUEVO);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 4;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 4;
		gbc_textNombre.gridy = 1;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		textPassword = new JPasswordField();
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.gridwidth = 4;
		gbc_textTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_textTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefono.gridx = 4;
		gbc_textTelefono.gridy = 5;
		getContentPane().add(textPassword, gbc_textTelefono);
		textPassword.setColumns(10);
	}

	@Override
	public Usuario prepararObjeto() {
		Usuario usuario = new Usuario(textNombre.getText(), new String(textPassword.getPassword()));

		Set<ConstraintViolation<Usuario>> errores = usuario.validar();
		if (errores != null && !errores.isEmpty()) {
			mostrarErrores(errores);
			return null;
		}

		return usuario;
	}

	@Override
	public void recargar() {
		if (getTipo() == TipoFormulario.NUEVO) {
			textPassword.setText("");
			textNombre.setText("");
		} else {
			textNombre.setText(getBean().getNombre());
			textPassword.setText(getBean().getPassword());
		}
	}

}
