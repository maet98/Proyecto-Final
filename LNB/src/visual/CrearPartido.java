package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import com.toedter.calendar.JDateChooser;

import logico.Equipo;
import logico.Liga;
import logico.Partido;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class CrearPartido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String EquipoLocal = "";
	private String EquipoVisitante = "";
	private DefaultListModel<String> modelLocal;
	private DefaultListModel<String> modelVisitante;
	private JList<String> ListLocal;
	private JList<String> ListVisitante;
	private JDateChooser dateChooser;
	private JButton btnCrear;
	
	public CrearPartido() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearPartido.class.getResource("/imagenes/tablero.png")));
		setTitle("Crear Partido");
		setBounds(100, 100, 594, 346);
		//setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(83, 23, 93, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(414, 23, 99, 16);
			panel.add(lblEquipoVisitante);
			
			modelLocal = new DefaultListModel<String>();
			modelVisitante = new DefaultListModel<String>();
			
			ListLocal = new JList<String>();
			ListLocal.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					EquipoLocal = ListLocal.getSelectedValue().toString();
					if(ListLocal.getSelectedIndex()>=0 && ListVisitante.getSelectedIndex()>=0) {
						btnCrear.setEnabled(true);
					}
					loadVisitante();
				}
			});
			ListLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListLocal.setModel(modelLocal);
			ListLocal.setBounds(12, 51, 195, 151);
			panel.add(ListLocal);
			
			ListVisitante = new JList<String>();
			ListVisitante.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					EquipoVisitante = ListVisitante.getSelectedValue().toString();
					if(ListLocal.getSelectedIndex()>=0 && ListVisitante.getSelectedIndex()>=0) {
						btnCrear.setEnabled(true);
					}
					loadLocal();
				}
			});
			ListVisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			ListVisitante.setModel(modelVisitante);
			ListVisitante.setBounds(366, 50, 195, 152);
			panel.add(ListVisitante);
			
			JLabel lblFechaDelPartido = new JLabel("Fecha del Partido:");
			lblFechaDelPartido.setBounds(12, 220, 114, 16);
			panel.add(lblFechaDelPartido);
			
			dateChooser = new JDateChooser();
			dateChooser.setDate(new Date());
			dateChooser.setBounds(119, 215, 150, 30);
			panel.add(dateChooser);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCrear = new JButton("\r\n");
				btnCrear.setIcon(new ImageIcon(CrearPartido.class.getResource("/imagenes/planing.png")));
				btnCrear.setEnabled(false);
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Equipo equipoLocal = Liga.getInstance().buscarEquipo(EquipoLocal);
						Equipo equipoVisitante = Liga.getInstance().buscarEquipo(EquipoVisitante);
						Partido p = Liga.getInstance().BuscarPartido(equipoLocal.getNombre()+" vs "+equipoVisitante.getNombre());
						if(p == null) {
							Partido nuevo = new Partido(String.valueOf(Liga.getInstance().getIdsPartidos()+1), dateChooser.getDate(),equipoLocal, equipoVisitante);
							Liga.getInstance().addPartido(nuevo);
							JOptionPane.showMessageDialog(contentPanel, "El partido "+equipoLocal.getNombre()+" vs "+equipoVisitante.getNombre()+ " ha sido ingresado" , "Infromacion", JOptionPane.INFORMATION_MESSAGE);
							limpiar();
						}
						else {
							JOptionPane.showMessageDialog(contentPanel, "El partido "+equipoLocal.getNombre()+" vs "+equipoVisitante.getNombre()+ " ya existe" , "Informacion", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				JButton btnCancelar = new JButton("");
				btnCancelar.setIcon(new ImageIcon(CrearPartido.class.getResource("/imagenes/cross-close-or-delete-circular-interface-button-symbol.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadLocal();
		loadVisitante();
	}
	private void loadLocal() {
		int i = 0;
		modelLocal.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			if(!actual.getNombre().equalsIgnoreCase(EquipoVisitante)) {
				modelLocal.addElement(actual.getNombre());
				if(actual.getNombre().equalsIgnoreCase(EquipoLocal)) {
					ListLocal.setSelectedIndex(i);
				}
				i++;
			}
		}
	}
	private void loadVisitante() {
		int i = 0;
		modelVisitante.removeAllElements();
		for (Equipo actual : Liga.getInstance().getEquipos()) {
			if(!actual.getNombre().equalsIgnoreCase(EquipoLocal)) {
				modelVisitante.addElement(actual.getNombre());
				if(actual.getNombre().equalsIgnoreCase(EquipoVisitante)) {
					ListVisitante.setSelectedIndex(i);
				}
				i++;
				
				
			}
		}
	}
	private void limpiar() {
		dateChooser.setDate(new Date());
		EquipoLocal = "";
		EquipoVisitante = "";
		loadLocal();
		loadVisitante();
		btnCrear.setEnabled(false);
	}
}
