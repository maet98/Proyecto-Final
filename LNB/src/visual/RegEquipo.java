package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Equipo;
import logico.Liga;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Equipo miEquipo;
	private static Liga miLiga;
	private JTextField txtNombre;
	private JTextField txtCiudad;
	private JTextField txtEntrenador;
	private JTextField txtEstadio;


	/**
	 * Create the dialog.
	 */
	public RegEquipo(Liga liga, Equipo equipo) {
		this.miEquipo = equipo;
		this.miLiga = liga;
		setBounds(100, 100, 621, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel pnlEquipo = new JPanel();
			pnlEquipo.setBorder(new TitledBorder(null, "Informaci\u00F3n del Equipo:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlEquipo.setBounds(12, 13, 569, 180);
			panel.add(pnlEquipo);
			pnlEquipo.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 34, 56, 16);
			pnlEquipo.add(lblNombre);
			{
				JLabel lblCircuito = new JLabel("Circuito:");
				lblCircuito.setBounds(12, 92, 56, 16);
				pnlEquipo.add(lblCircuito);
			}
			{
				JLabel lblEntrenador = new JLabel("Entrenador:");
				lblEntrenador.setBounds(12, 150, 68, 16);
				pnlEquipo.add(lblEntrenador);
			}
			{
				JLabel lblEstadio = new JLabel("Estadio:");
				lblEstadio.setBounds(12, 121, 56, 16);
				pnlEquipo.add(lblEstadio);
			}
			{
				JLabel lblCiudad = new JLabel("Ciudad:");
				lblCiudad.setBounds(12, 63, 56, 16);
				pnlEquipo.add(lblCiudad);
			}
			
			txtNombre = new JTextField();
			txtNombre.setBounds(92, 31, 234, 22);
			pnlEquipo.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtCiudad = new JTextField();
			txtCiudad.setBounds(92, 60, 234, 22);
			pnlEquipo.add(txtCiudad);
			txtCiudad.setColumns(10);
			
			JComboBox cbxCircuito = new JComboBox();
			cbxCircuito.setBounds(92, 89, 234, 22);
			pnlEquipo.add(cbxCircuito);
			
			txtEntrenador = new JTextField();
			txtEntrenador.setBounds(92, 147, 234, 22);
			pnlEquipo.add(txtEntrenador);
			txtEntrenador.setColumns(10);
			
			txtEstadio = new JTextField();
			txtEstadio.setBounds(92, 118, 234, 22);
			pnlEquipo.add(txtEstadio);
			txtEstadio.setColumns(10);
			
			JPanel pnlJugadores = new JPanel();
			pnlJugadores.setBorder(new TitledBorder(null, "Jugadores:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlJugadores.setBounds(12, 206, 604, 183);
			panel.add(pnlJugadores);
			pnlJugadores.setLayout(null);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}