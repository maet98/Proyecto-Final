package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class LisEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;


	public static void main(String[] args) {
		try {
			LisEquipo dialog = new LisEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public LisEquipo() {
		setTitle("Listar Equipos");
		setBounds(100, 100, 580, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Equipos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox cbxEquipos = new JComboBox();
		cbxEquipos.setBounds(12, 26, 163, 22);
		contentPanel.add(cbxEquipos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 58, 562, 237);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblJugadores = new JLabel("Jugadores");
		lblJugadores.setBounds(224, 29, 73, 16);
		contentPanel.add(lblJugadores);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	void loadJugadores() {
		
	}
}
