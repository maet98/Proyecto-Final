package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

import logico.Equipo;
import logico.Jugador;
import logico.Lesion;
import logico.Liga;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegLesion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private DefaultComboBoxModel<String> modelEquipos;
	private DefaultComboBoxModel<String> modelJugador;
	private JCalendar clndrInicio;
	private JCalendar clndrFinal;
	private Equipo equipoSeleccionado;
	private Jugador mijugador;
	private Equipo miEquipo;


	public RegLesion(Jugador jugador, Equipo equipo) {
		mijugador = jugador;
		miEquipo = equipo;
		setTitle("Registrar Lesi\u00F3n");
		setBounds(100, 100, 461, 582);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoDeLesin = new JLabel("Tipo de lesi\u00F3n:");
		lblTipoDeLesin.setBounds(6, 37, 101, 16);
		panel.add(lblTipoDeLesin);
		
		modelEquipos = new DefaultComboBoxModel<>();
		modelJugador = new DefaultComboBoxModel<>();
		
		txtTipo = new JTextField();
		txtTipo.setBounds(119, 34, 180, 22);
		panel.add(txtTipo);
		txtTipo.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio:");
		lblFechaDeInicio.setBounds(6, 135, 109, 16);
		panel.add(lblFechaDeInicio);
		
		clndrInicio = new JCalendar();
		clndrInicio.setBounds(119, 134, 198, 155);
		panel.add(clndrInicio);
		
		JLabel lblFechaFinal = new JLabel("Fecha final:");
		lblFechaFinal.setBounds(12, 319, 67, 16);
		panel.add(lblFechaFinal);
		
		clndrFinal = new JCalendar();
		clndrFinal.setBounds(119, 311, 198, 155);
		panel.add(clndrFinal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipo = txtTipo.getText();
						Date inicio = clndrInicio.getDate();
						Date fin = clndrFinal.getDate();
						
						Lesion lesion = new Lesion(tipo, jugador, inicio, fin);
						jugador.setLesion(lesion);
						jugador.setLesionado(true);
						JOptionPane.showMessageDialog(null,"Operación Satisfactoria","Información",JOptionPane.INFORMATION_MESSAGE);
						clean();
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
	private void LoadEquipos() {
		modelEquipos.addElement("<Seleccione>");
		for (Equipo equipo : Liga.getInstance().getEquipos()) {
			modelEquipos.addElement(equipo.getNombre());
		}
	}
	private void LoadJugadores() {
		modelJugador.addElement("<Seleccione>");
		for (Jugador jugador : equipoSeleccionado.getJugadores()) {
			modelJugador.addElement(jugador.getNombre()+" "+jugador.getApellido());
		}
	}
	private void clean() {
		txtTipo.setText("");
	}
}
