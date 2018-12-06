package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Jugador;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class modificarEstadistica extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private Jugador mijugador;
	private JTextField txtTirosLibre;
	private JTextField txtTirosDe2;
	private JTextField txtTirosDe3;
	private JTextField txtNombre;
	private JTextField txtPosicion;
	private JTextField txtEquipo;
	private JTextField txtNumero;
	private JTextField txtNacionalidad;
	private JTextField txtRebotes;
	private JTextField txtAsistencias;
	private JTextField txtFaltas;

	public modificarEstadistica(Jugador jugador) {
		mijugador = jugador;
		setTitle("Estadistica de "+jugador.getNombre()+" "+jugador.getApellido());
		setBounds(100, 100, 654, 415);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 637, 138);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(12, 23, 54, 16);
		panel.add(lblNombre);
		
		JLabel lblPosicion = new JLabel("Posicion");
		lblPosicion.setBounds(12, 62, 46, 16);
		panel.add(lblPosicion);
		
		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setBounds(12, 91, 43, 16);
		panel.add(lblEquipo);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(228, 23, 56, 16);
		panel.add(lblNumero);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setBounds(228, 52, 77, 16);
		panel.add(lblNacionalidad);
		
		JLabel lblFotoJugador = new JLabel("Foto Jugador");
		lblFotoJugador.setBounds(444, 23, 105, 105);
		panel.add(lblFotoJugador);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setEnabled(false);
		txtNombre.setBounds(78, 20, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPosicion = new JTextField();
		txtPosicion.setEnabled(false);
		txtPosicion.setEditable(false);
		txtPosicion.setBounds(78, 59, 116, 22);
		panel.add(txtPosicion);
		txtPosicion.setColumns(10);
		
		txtEquipo = new JTextField();
		txtEquipo.setEnabled(false);
		txtEquipo.setEditable(false);
		txtEquipo.setColumns(10);
		txtEquipo.setBounds(78, 88, 116, 22);
		panel.add(txtEquipo);
		
		txtNumero = new JTextField();
		txtNumero.setEnabled(false);
		txtNumero.setEditable(false);
		txtNumero.setColumns(10);
		txtNumero.setBounds(309, 20, 116, 22);
		panel.add(txtNumero);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setEnabled(false);
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(309, 49, 116, 22);
		panel.add(txtNacionalidad);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Estadisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 138, 637, 175);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTirosLibres = new JLabel("Tiros Libres");
		lblTirosLibres.setBounds(12, 36, 73, 16);
		panel_1.add(lblTirosLibres);
		
		JLabel lblTirosDe = new JLabel("Tiros De 2:");
		lblTirosDe.setBounds(12, 82, 73, 16);
		panel_1.add(lblTirosDe);
		
		JLabel lblTirosDe_1 = new JLabel("Tiros De 3:");
		lblTirosDe_1.setBounds(12, 129, 67, 16);
		panel_1.add(lblTirosDe_1);
		
		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setBounds(326, 129, 56, 16);
		panel_1.add(lblFaltas);
		
		JLabel lblRebotes = new JLabel("Rebotes");
		lblRebotes.setBounds(326, 36, 56, 16);
		panel_1.add(lblRebotes);
		
		JLabel lblAsistencias = new JLabel("Asistencias:");
		lblAsistencias.setBounds(326, 82, 73, 16);
		panel_1.add(lblAsistencias);
		
		txtTirosLibre = new JTextField();
		txtTirosLibre.setEditable(false);
		txtTirosLibre.setEnabled(false);
		txtTirosLibre.setBounds(90, 33, 116, 22);
		panel_1.add(txtTirosLibre);
		txtTirosLibre.setColumns(10);
		
		txtTirosDe2 = new JTextField();
		txtTirosDe2.setEditable(false);
		txtTirosDe2.setEnabled(false);
		txtTirosDe2.setBounds(90, 79, 116, 22);
		panel_1.add(txtTirosDe2);
		txtTirosDe2.setColumns(10);
		
		txtTirosDe3 = new JTextField();
		txtTirosDe3.setEnabled(false);
		txtTirosDe3.setEditable(false);
		txtTirosDe3.setBounds(91, 126, 116, 22);
		panel_1.add(txtTirosDe3);
		txtTirosDe3.setColumns(10);
		
		txtRebotes = new JTextField();
		txtRebotes.setBounds(405, 33, 116, 22);
		panel_1.add(txtRebotes);
		txtRebotes.setColumns(10);
		
		txtAsistencias = new JTextField();
		txtAsistencias.setBounds(405, 79, 116, 22);
		panel_1.add(txtAsistencias);
		txtAsistencias.setColumns(10);
		
		txtFaltas = new JTextField();
		txtFaltas.setBounds(405, 126, 116, 22);
		panel_1.add(txtFaltas);
		txtFaltas.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("");
				btnModificar.setIcon(new ImageIcon(modificarEstadistica.class.getResource("/imagenes/floppy-disk-interface-symbol-for-save-option-button.png")));
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int rebotes = Integer.parseInt(txtRebotes.getText());
						int faltas = Integer.parseInt(txtFaltas.getText());
						int asistencias = Integer.parseInt(txtAsistencias.getText());
						jugador.getDesempenno().setAsistencias(asistencias);
						jugador.getDesempenno().setFaltas(faltas);
						jugador.getDesempenno().setRebotes(rebotes);
						JOptionPane.showMessageDialog(null, "Las estadisticas del jugador "+jugador.getNombre()+" han sido cambiado.", "Ïnformacion", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnSalir = new JButton("");
				btnSalir.setIcon(new ImageIcon(modificarEstadistica.class.getResource("/imagenes/cross-close-or-delete-circular-interface-button-symbol.png")));
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadJugador();
	}
	private void loadJugador() {
		txtAsistencias.setText(String.valueOf(mijugador.getDesempenno().getAsistencias()));
		txtEquipo.setText(mijugador.getEquipo().getNombre());
		txtFaltas.setText(String.valueOf(mijugador.getDesempenno().getFaltas()));
		txtNacionalidad.setText(mijugador.getNacionalidad());
		txtNombre.setText(mijugador.getNombre()+" "+mijugador.getApellido());
		txtNumero.setText(String.valueOf(mijugador.getNumero()));
		txtPosicion.setText(mijugador.getPosicion());
		txtRebotes.setText(String.valueOf(mijugador.getDesempenno().getRebotes()));
		txtTirosDe2.setText(String.valueOf(mijugador.getDesempenno().getTirosDeDos()));
		txtTirosDe3.setText(String.valueOf(mijugador.getDesempenno().getTirosDeTres()));
		txtTirosLibre.setText(String.valueOf(mijugador.getDesempenno().getTirosLibres()));
	}
}
