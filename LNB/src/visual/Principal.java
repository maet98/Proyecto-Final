package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logico.Liga;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class Principal {
	private JFrame frmLigaDeBaloncesto;
	private JTable TableTiros1;
	private JTable TableTiros2;
	private ObjectOutputStream archivoSalida;
	private JLabel lblLocall1;
	private JLabel lblVisitante1;
	private JLabel lblfecha1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmLigaDeBaloncesto.setVisible(true);
					window.frmLigaDeBaloncesto.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() throws ClassNotFoundException {
		try {
			initialize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws FileNotFoundException, ClassNotFoundException {
		frmLigaDeBaloncesto = new JFrame();
		frmLigaDeBaloncesto.addWindowListener(new WindowAdapter() {
			

			public void windowClosing(WindowEvent arg0) {
				File salida = new File("liga.dat");
				FileOutputStream guardar;
				try {
					guardar = new FileOutputStream(salida);
					archivoSalida = new ObjectOutputStream(guardar);
					archivoSalida.writeObject(Liga.getInstance());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		Dimension dim = frmLigaDeBaloncesto.getToolkit().getScreenSize();
		frmLigaDeBaloncesto.setTitle("Liga de baloncesto");
		frmLigaDeBaloncesto.setBounds(100, 100, (int)dim.getWidth(), (int)dim.getHeight());
		frmLigaDeBaloncesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File archivoEntrada = new File("liga.dat");
		if(archivoEntrada.exists()) {
			FileInputStream file = new FileInputStream(archivoEntrada);
			try {
				ObjectInputStream entrada = new ObjectInputStream(file);
				Liga.setInstance((Liga) entrada.readObject());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		JMenuBar menuBar = new JMenuBar();
		frmLigaDeBaloncesto.setJMenuBar(menuBar);
		
		JMenu mnJugadores = new JMenu("Jugadores");
		menuBar.add(mnJugadores);
		
		JMenuItem mntmRegistrarJugador = new JMenuItem("Registrar Jugador");
		mntmRegistrarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Liga.getInstance().getEquipos().isEmpty()) {
					JOptionPane.showMessageDialog(frmLigaDeBaloncesto, "No existe ningun equipo, registre un equipo.","Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					RegJugador nuevo;
					try {
						nuevo = new RegJugador();
						nuevo.setModal(true);
						nuevo.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		mnJugadores.add(mntmRegistrarJugador);
		
		JMenuItem mntmListarJugadores = new JMenuItem("Listar Jugadores");
		mntmListarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisJugador nueva = new LisJugador();
				nueva.setModal(true);
				nueva.setVisible(true);
			}
		});
		mnJugadores.add(mntmListarJugadores);
		
		JMenu mnEquipos = new JMenu("Equipos");
		menuBar.add(mnEquipos);
		
		JMenuItem mntmRegistrarEquipo = new JMenuItem("Registrar Equipo");
		mntmRegistrarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEquipo nuevo = new RegEquipo(null);
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnEquipos.add(mntmRegistrarEquipo);
		
		JMenuItem mntmListarEquipos = new JMenuItem("Listar Equipos");
		mntmListarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisEquipo nueva = new LisEquipo();
				nueva.setModal(true);
				nueva.setVisible(true);
			}
		});
		mnEquipos.add(mntmListarEquipos);
		
		JMenuItem mntmPosiciones = new JMenuItem("Posiciones");
		mntmPosiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Posiciones posiciones = new Posiciones();
				posiciones.setModal(true);
				posiciones.setVisible(true);
			}
		});
		mnEquipos.add(mntmPosiciones);
		
		JMenu mnPartidos = new JMenu("Partidos");
		menuBar.add(mnPartidos);
		
		JMenuItem mntmCrearPartido = new JMenuItem("Crear Partido");
		mntmCrearPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearPartido nuevo = new CrearPartido();
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnPartidos.add(mntmCrearPartido);
		
		JMenuItem mntmVerCalendario = new JMenuItem("Ver Calendario");
		mntmVerCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendario nuevo = new Calendario();
				nuevo.setModal(true);
				nuevo.setVisible(true);
			}
		});
		mnPartidos.add(mntmVerCalendario);
		
		JMenuItem mntmJugarPartido = new JMenuItem("Jugar Partido");
		mnPartidos.add(mntmJugarPartido);
		
		JPanel panel = new JPanel();
		frmLigaDeBaloncesto.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JScrollPane Tiros2 = new JScrollPane();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JScrollPane Tiros1 = new JScrollPane();
		tabbedPane.addTab("Tiros de 1", Tiros1);
		
		TableTiros1 = new JTable();
		TableTiros1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TableTiros1.getColumnModel().getColumn(0).setResizable(false);
		TableTiros1.getColumnModel().getColumn(0).setPreferredWidth(50);
		TableTiros1.getColumnModel().getColumn(0).setMinWidth(50);
		TableTiros1.getColumnModel().getColumn(0).setMaxWidth(50);
		TableTiros1.getColumnModel().getColumn(1).setResizable(false);
		TableTiros1.getColumnModel().getColumn(1).setPreferredWidth(90);
		TableTiros1.getColumnModel().getColumn(2).setResizable(false);
		TableTiros1.getColumnModel().getColumn(3).setResizable(false);
		TableTiros1.getColumnModel().getColumn(3).setPreferredWidth(84);
		TableTiros1.getColumnModel().getColumn(3).setMinWidth(18);
		Tiros1.setViewportView(TableTiros1);
		tabbedPane.addTab("Tiros de 2", Tiros2);
		
		TableTiros2 = new JTable();
		TableTiros2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		TableTiros2.getColumnModel().getColumn(0).setMaxWidth(48);
		TableTiros2.getColumnModel().getColumn(3).setPreferredWidth(87);
		Tiros2.setViewportView(TableTiros2);
		tabbedPane.setBounds(1360, 49, 517, 594);
		panel.add(tabbedPane);
		JScrollPane TiroLibres = new JScrollPane();
		tabbedPane.addTab("Tiros Libres", null, TiroLibres, null);
		
		tableTirosLibres = new JTable();
		tableTirosLibres.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Posicion", "Nombre", "Equipo", "Tiros Anotados"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableTirosLibres.getColumnModel().getColumn(0).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(0).setMaxWidth(48);
		tableTirosLibres.getColumnModel().getColumn(1).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(1).setPreferredWidth(112);
		tableTirosLibres.getColumnModel().getColumn(2).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setPreferredWidth(83);
		TiroLibres.setViewportView(tableTirosLibres);
		
		JLabel lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setBounds(1567, 22, 82, 16);
		panel.add(lblEstadisticas);
		
		JLabel lblProximosPartidos = new JLabel("Proximos Partidos");
		lblProximosPartidos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProximosPartidos.setBounds(237, 53, 180, 16);
		panel.add(lblProximosPartidos);
		
		JButton btnVerMasEstadisticas = new JButton("Ver mas estadisticas");
		btnVerMasEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnVerMasEstadisticas.setBounds(1567, 674, 152, 23);
		panel.add(btnVerMasEstadisticas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(29, 88, 600, 460);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLocal.setBounds(91, 42, 59, 17);
		panel_1.add(lblLocal);
		
		JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVisitante.setBounds(382, 38, 116, 25);
		panel_1.add(lblVisitante);
		
		JPanel pnlLocal1 = new JPanel();
		pnlLocal1.setBounds(6, 70, 588, 85);
		panel_1.add(pnlLocal1);
		pnlLocal1.setLayout(null);
		
		JLabel label = new JLabel("-");
		label.setBounds(290, 34, 8, 16);
		pnlLocal1.add(label);
		
		lblLocall1 = new JLabel("New label");
		lblLocall1.setBounds(68, 34, 61, 16);
		pnlLocal1.add(lblLocall1);
		
		lblVisitante1 = new JLabel("New label");
		lblVisitante1.setBounds(415, 34, 61, 16);
		pnlLocal1.add(lblVisitante1);
		
		lblfecha1 = new JLabel("New label");
		lblfecha1.setBounds(263, 63, 61, 16);
		pnlLocal1.add(lblfecha1);
		
		JPanel partido2 = new JPanel();
		partido2.setBounds(6, 167, 588, 85);
		panel_1.add(partido2);
		partido2.setLayout(null);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(290, 34, 8, 16);
		partido2.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 264, 588, 85);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("-");
		label_2.setBounds(290, 34, 8, 16);
		panel_2.add(label_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 361, 588, 85);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label_3 = new JLabel("-");
		label_3.setBounds(290, 34, 8, 16);
		panel_3.add(label_3);
	}
	public void loadEstadisticas() {
		
	}
	
	public void loadPartidos() {
			lblLocall1.setText(Liga.getInstance().getPartidos().get(0).getLocal().toString());
			lblVisitante1.setText(Liga.getInstance().getPartidos().get(0).getVisitante().toString());
			lblfecha1.setText(Liga.getInstance().getPartidos().get(0).getFecha().toString());
			
			
	}
	private JTable tableTirosLibres;
}
