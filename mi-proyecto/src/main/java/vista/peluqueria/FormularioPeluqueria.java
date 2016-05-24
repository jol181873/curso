package vista.peluqueria;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.validation.ConstraintViolation;

import modelo.peluqueria.NegocioPeluqueria;
import modelo.peluqueria.Peluqueria;
import vista.genericos.FormularioGenerico;
import vista.genericos.TipoFormulario;

public class FormularioPeluqueria extends FormularioGenerico<Peluqueria> {
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textTelefono;

	public FormularioPeluqueria(Peluqueria pelu) {
		super(NegocioPeluqueria.getInstance(), pelu);
	}

	public FormularioPeluqueria() {
		super(NegocioPeluqueria.getInstance());
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
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 4;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 4;
		gbc_textNombre.gridy = 1;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Direcci\u00F3n");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		textDireccion = new JTextField();
		GridBagConstraints gbc_textDireccion = new GridBagConstraints();
		gbc_textDireccion.gridwidth = 4;
		gbc_textDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_textDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDireccion.gridx = 4;
		gbc_textDireccion.gridy = 3;
		getContentPane().add(textDireccion, gbc_textDireccion);
		textDireccion.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 5;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		textTelefono = new JTextField();
		GridBagConstraints gbc_textTelefono = new GridBagConstraints();
		gbc_textTelefono.gridwidth = 4;
		gbc_textTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_textTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelefono.gridx = 4;
		gbc_textTelefono.gridy = 5;
		getContentPane().add(textTelefono, gbc_textTelefono);
		textTelefono.setColumns(10);
	}

	@Override
	public Peluqueria prepararObjeto() {
		Peluqueria pelu = new Peluqueria();

		if (getBean() != null) {
			pelu.setId(getBean().getId());
		}

		pelu.setNombre(textNombre.getText());
		pelu.setDireccion(textDireccion.getText());
		pelu.setTelefono(textTelefono.getText());

		Set<ConstraintViolation<Peluqueria>> errores = pelu.validar();
		if (errores != null && !errores.isEmpty()) {
			mostrarErrores(errores);
			return null;
		}

		return pelu;
	}

	@Override
	public void recargar() {
		if (getTipo() == TipoFormulario.NUEVO) {
			textDireccion.setText("");
			textNombre.setText("");
			textTelefono.setText("");
		} else {
			textDireccion.setText(getBean().getDireccion());
			textNombre.setText(getBean().getNombre());
			textTelefono.setText(getBean().getTelefono());
		}
	}

}
