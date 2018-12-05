package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.Desempenno;
import logico.Equipo;
import logico.Jugador;
import logico.Marcador;
import logico.Partido;

import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ControladorPartidos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMarcador;
	private JTable tablaLocal;
	private JTable Tablavisitante;
	private DefaultTableModel modelLocal;
	private DefaultTableModel modelVisitante;
	private Equipo Local;
	private Equipo Visitante;
	private int TanteoLocal;
	private int TanteoVisitante;
	private Partido partidoActual;
	
	public ControladorPartidos(Partido partido) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ControladorPartidos.class.getResource("/imagenes/partidoIcon.png")));
		partidoActual = partido;
		setLocationRelativeTo(null);
		setTitle("Controlador Partido");
		setBounds(100, 100, 951, 616);
		this.Local = partido.getLocal();
		this.Visitante = partido.getVisitante();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Partido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblMarcador = new JLabel("Marcador");
			lblMarcador.setBounds(441, 430, 58, 16);
			panel.add(lblMarcador);
			
			txtMarcador = new JTextField();
			txtMarcador.setHorizontalAlignment(SwingConstants.CENTER);
			txtMarcador.setEnabled(false);
			txtMarcador.setEditable(false);
			txtMarcador.setBounds(398, 457, 131, 22);
			panel.add(txtMarcador);
			txtMarcador.setColumns(10);

			JLabel lblEquipoLocal = new JLabel("Equipo Local");
			lblEquipoLocal.setBounds(160, 13, 84, 16);
			panel.add(lblEquipoLocal);
			
			JLabel lblEquipoVisitante = new JLabel("Equipo Visitante");
			lblEquipoVisitante.setBounds(646, 13, 120, 16);
			panel.add(lblEquipoVisitante);
			
			JScrollPane scrollPaneLocal = new JScrollPane();
			scrollPaneLocal.setBounds(6, 41, 425, 355);
			panel.add(scrollPaneLocal);
			
			tablaLocal = new JTable();
			tablaLocal.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					if(!e.isTemporary()) {
						TanteoLocal = calcLocal();
						cambiarMarcardor();
					}
					
				}
				public void focusLost(FocusEvent arg0) {
					TanteoLocal = calcLocal();
					cambiarMarcardor();
				}
			});
			tablaLocal.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent event) {
					int selectedIndex = tablaLocal.getSelectedRow();
					if(selectedIndex>=0) {
						TanteoLocal = calcLocal();
						cambiarMarcardor();
					}
				}
			});
			tablaLocal.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent event) {
					TanteoLocal = calcLocal();
					cambiarMarcardor();
				}
			});
			tablaLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaLocal.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Numero", "Nombre", "Tiros Libres", "Tiros de 2", "Tiros de 3"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class, Integer.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			modelLocal = (DefaultTableModel) tablaLocal.getModel();
			scrollPaneLocal.setViewportView(tablaLocal);
			
			JScrollPane scrollPaneVisitante = new JScrollPane();
			scrollPaneVisitante.setBounds(497, 41, 425, 355);
			panel.add(scrollPaneVisitante);
			
			Tablavisitante = new JTable();
			Tablavisitante.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					TanteoVisitante = calcVisitante();
					cambiarMarcardor();
				}
				public void focusLost(FocusEvent e) {
					TanteoVisitante = calcVisitante();
					cambiarMarcardor();
				}
			});
			Tablavisitante.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					TanteoVisitante = calcVisitante();
					cambiarMarcardor();
				}
			});
			Tablavisitante.setSurrendersFocusOnKeystroke(true);
			Tablavisitante.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			Tablavisitante.setColumnSelectionAllowed(true);
			Tablavisitante.setCellSelectionEnabled(true);
			Tablavisitante.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			Tablavisitante.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(Character.isDigit(e.getKeyChar())){
						TanteoVisitante = calcVisitante();
						cambiarMarcardor();
					}
					else {
						e.consume();
					}
				}
			});
			Tablavisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Tablavisitante.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Numero", "Nombre", "Tiros Libres", "Tiros de 2", "Tiros de 3"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class, Integer.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			modelVisitante = (DefaultTableModel) Tablavisitante.getModel();
			scrollPaneVisitante.setViewportView(Tablavisitante);
			{
				JButton btnTerminar = new JButton("");
				btnTerminar.setSelectedIcon(new ImageIcon(ControladorPartidos.class.getResource("/imagenes/FinPartido.png")));
				btnTerminar.setBounds(441, 492, 48, 48);
				panel.add(btnTerminar);
				btnTerminar.setIcon(new ImageIcon(ControladorPartidos.class.getResource("/imagenes/FinPartido.png")));
				btnTerminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(TanteoLocal!=TanteoVisitante) {
							ingresarValores();
							if(TanteoLocal>TanteoVisitante) {
								Local.aumentarPartidosGanados();
								Visitante.aumentarPartidosPerdidos();
								Local.aumentarPartidosJugados();
								Visitante.aumentarPartidosJugados();
							}
							else {
								Visitante.aumentarPartidosGanados();
								Local.aumentarPartidosPerdidos();
								Visitante.aumentarPartidosJugados();
								Local.aumentarPartidosJugados();
							}
							partidoActual.setMarcador(new Marcador(TanteoLocal, TanteoVisitante));
							partidoActual.setJugado(true);
							JOptionPane.showMessageDialog(null, "El marcador final ha sido "+txtMarcador.getText()+". El equipo ganador fue "+ganador(), "Partido finalizado", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "El partido no puede quedar empate", "Informacion", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				btnTerminar.setActionCommand("OK");
				getRootPane().setDefaultButton(btnTerminar);
			}
		}
		loadLocal();
		loadVisitante();
		calcLocal();
		calcVisitante();
		cambiarMarcardor();
	}
	protected String ganador() {
		if(TanteoLocal>TanteoVisitante) {
			return Local.getNombre();
		}else if(TanteoVisitante > TanteoLocal) {
			return Visitante.getNombre();
		}
		return null;
	}
	private void ingresarValores() {
		int i = 0;
		for (Jugador jugador : Local.getJugadores()) {
			if(!jugador.isLesionado()) {
				int tirosLibres,tiro2,tiro3;
				tirosLibres = Integer.parseInt(tablaLocal.getValueAt(i, 2).toString());
				tiro2 = Integer.parseInt(tablaLocal.getValueAt(i, 3).toString());
				tiro3 = Integer.parseInt(tablaLocal.getValueAt(i, 4).toString());
				if(tirosLibres>=0 || tiro2 >= 0 || tiro3>=0) {
					jugador.getDesempenno().setTirosLibres(tirosLibres);
					jugador.getDesempenno().setTirosDeDos(tiro2);
					jugador.getDesempenno().setTirosDeTres(tiro3);
					jugador.getDesempenno().aumentarPartidosJugados();
				}
				i++;
			}			
		}
		
	}
	private int calcLocal() {
		int t = 0;
		for(int i = 0;i < Local.getJugadores().size();i++) {
			t += Integer.parseInt(tablaLocal.getValueAt(i, 2).toString());
			t += Integer.parseInt(tablaLocal.getValueAt(i, 3).toString())*2;
			t += Integer.parseInt(tablaLocal.getValueAt(i, 4).toString())*3;
		}
		return t;
	}
	private int calcVisitante() {
		int t = 0;
		for(int i = 0;i < Visitante.getJugadores().size();i++) {
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 2).toString());
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 3).toString())*2;
			t += Integer.parseInt(Tablavisitante.getValueAt(i, 4).toString())*3;
		}
		return t;
	}
	private void cambiarMarcardor() {
		String marcardor = TanteoLocal+" - "+TanteoVisitante;
		txtMarcador.setText(marcardor);
	}
	private void loadLocal() {
		Object[] fila = new Object[modelLocal.getColumnCount()];
		modelLocal.setRowCount(0);
		for (Jugador actual : Local.getJugadores()) {
			fila[0] = actual.getNumero();
			fila[1] = actual.getNombre();
			fila[2] = 0;
			fila[3] = 0;
			fila[4] = 0;
			if(!actual.isLesionado()) {
				modelLocal.addRow(fila);
			}
		}
	}
	private void loadVisitante() {
		Object[] fila = new Object[modelVisitante.getColumnCount()];
		modelVisitante.setRowCount(0);
		for (Jugador actual : Visitante.getJugadores()) {
			fila[0] = actual.getNumero();
			fila[1] = actual.getNombre();
			fila[2] = 0;
			fila[3] = 0;
			fila[4] = 0;
			if(!actual.isLesionado()) {
				modelVisitante.addRow(fila);
			}
		}
	}
}



