package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logico.Jugador;
import logico.Liga;
import logico.Partido;
import logico.Tiros1Comparator;
import logico.Tiros3Comparator;
import logico.tiros2Comparator;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Principal {
	private JFrame frmLigaDeBaloncesto;
	private JTable TableTiros3;
	private JTable TableTiros2;
	private JTable tablePosicion;
	private ObjectOutputStream archivoSalida;
	private JLabel lblLocall1;
	private JLabel lblVisitante1;
	private JLabel lblfecha1;
	private JPanel PnlProximosPartidos;
	private DefaultTableModel modelTirosLibres;
	private DefaultTableModel modelTirosde2;
	private DefaultTableModel modelTirosde3;
	private DefaultTableModel modelPosiciones;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
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
		frmLigaDeBaloncesto.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/basketball.png")));
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
		mnJugadores.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/camiseta.png")));
		menuBar.add(mnJugadores);
		
		JMenuItem mntmRegistrarJugador = new JMenuItem("Registrar Jugador");
		mntmRegistrarJugador.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/planing.png")));
		mntmRegistrarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Liga.getInstance().getEquipos().isEmpty()) {
					JOptionPane.showMessageDialog(frmLigaDeBaloncesto, "No existe ningun equipo, registre un equipo.","Error", JOptionPane.INFORMATION_MESSAGE);
				}else {
					RegJugador nuevo;
					try {
						nuevo = new RegJugador(null);
						nuevo.setModal(true);
						nuevo.setVisible(true);
						loadTodo();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		mnJugadores.add(mntmRegistrarJugador);
		
		JMenuItem mntmListarJugadores = new JMenuItem("Listar Jugadores");
		mntmListarJugadores.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/table-grid.png")));
		mntmListarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisJugador nueva = new LisJugador();
				nueva.setModal(true);
				nueva.setVisible(true);
				loadTodo();
			}
		});
		mnJugadores.add(mntmListarJugadores);
		
		JMenu mnEquipos = new JMenu("Equipos");
		mnEquipos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/uniforms.png")));
		menuBar.add(mnEquipos);
		
		JMenuItem mntmRegistrarEquipo = new JMenuItem("Registrar Equipo");
		mntmRegistrarEquipo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/planing.png")));
		mntmRegistrarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEquipo nuevo = new RegEquipo(null);
				nuevo.setModal(true);
				nuevo.setVisible(true);
				loadTodo();
			}
		});
		mnEquipos.add(mntmRegistrarEquipo);
		
		JMenuItem mntmListarEquipos = new JMenuItem("Listar Equipos");
		mntmListarEquipos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/table-grid.png")));
		mntmListarEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisEquipo nueva = new LisEquipo();
				nueva.setModal(true);
				nueva.setVisible(true);
				loadTodo();
			}
		});
		mnEquipos.add(mntmListarEquipos);
		
		JMenuItem mntmPosiciones = new JMenuItem("Posiciones");
		mntmPosiciones.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/podium.png")));
		mntmPosiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Posiciones posiciones = new Posiciones();
				posiciones.setModal(true);
				posiciones.setVisible(true);
				loadTodo();
			}
		});
		mnEquipos.add(mntmPosiciones);
		
		JMenu mnPartidos = new JMenu("Partidos");
		mnPartidos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/cro.png")));
		menuBar.add(mnPartidos);
		
		JMenuItem mntmCrearPartido = new JMenuItem("Crear Partido");
		mntmCrearPartido.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/planing.png")));
		mntmCrearPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearPartido nuevo = new CrearPartido();
				nuevo.setModal(true);
				nuevo.setVisible(true);
				loadTodo();
			}
		});
		mnPartidos.add(mntmCrearPartido);
		
		JMenuItem mntmVerCalendario = new JMenuItem("Ver Calendario");
		mntmVerCalendario.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/table-grid.png")));
		mntmVerCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendario nuevo = new Calendario();
				nuevo.setModal(true);
				nuevo.setVisible(true);
				loadTodo();
			}
		});
		mnPartidos.add(mntmVerCalendario);
		
		JPanel panel = new JPanel();
		frmLigaDeBaloncesto.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JScrollPane Tiros2 = new JScrollPane();
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JScrollPane Tiros3 = new JScrollPane();
		tabbedPane.addTab("Tiros de 3", Tiros3);
		
		TableTiros3 = new JTable();
		TableTiros3.setLocation(785, 0);
		modelTirosde3 = new DefaultTableModel(new Object[][] {},new String[] {"Posicion", "Nombre", "Equipo", "Tiros Anotados"}) {Class[] columnTypes = new Class[] {Integer.class, String.class, String.class, Integer.class};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		TableTiros3.setModel(modelTirosde3);
		TableTiros3.getColumnModel().getColumn(0).setResizable(false);
		TableTiros3.getColumnModel().getColumn(0).setPreferredWidth(50);
		TableTiros3.getColumnModel().getColumn(0).setMinWidth(65);
		TableTiros3.getColumnModel().getColumn(0).setMaxWidth(50);
		TableTiros3.getColumnModel().getColumn(1).setResizable(false);
		TableTiros3.getColumnModel().getColumn(1).setPreferredWidth(150);
		TableTiros3.getColumnModel().getColumn(2).setResizable(false);
		TableTiros3.getColumnModel().getColumn(3).setResizable(false);
		TableTiros3.getColumnModel().getColumn(3).setPreferredWidth(84);
		TableTiros3.getColumnModel().getColumn(3).setMinWidth(18);
		Tiros3.setViewportView(TableTiros3);
		TableTiros3.setFillsViewportHeight(true);
		tabbedPane.addTab("Tiros de 2", Tiros2);
		
		TableTiros2 = new JTable();
		modelTirosde2 = new DefaultTableModel(
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
			};
		TableTiros2.setModel(modelTirosde2);
		TableTiros2.getColumnModel().getColumn(0).setPreferredWidth(71);
		TableTiros2.getColumnModel().getColumn(0).setMinWidth(71);
		TableTiros2.getColumnModel().getColumn(0).setMaxWidth(71);
		TableTiros2.getColumnModel().getColumn(3).setPreferredWidth(87);
		Tiros2.setViewportView(TableTiros2);
		tabbedPane.setBounds(1326, 109, 556, 594);
		panel.add(tabbedPane);
		JScrollPane TiroLibres = new JScrollPane();
		tabbedPane.addTab("Tiros Libres", null, TiroLibres, null);
		
		tableTirosLibres = new JTable();
		modelTirosLibres = new DefaultTableModel(new Object[][] {},new String[] {"Posicion", "Nombre", "Equipo", "Tiros Anotados"}) {Class[] columnTypes = new Class[] {Integer.class, String.class, String.class, Integer.class};public Class getColumnClass(int columnIndex) {return columnTypes[columnIndex];}};
		tableTirosLibres.setModel(modelTirosLibres);
		tableTirosLibres.getColumnModel().getColumn(0).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(0).setPreferredWidth(71);
		tableTirosLibres.getColumnModel().getColumn(0).setMinWidth(71);
		tableTirosLibres.getColumnModel().getColumn(0).setMaxWidth(71);
		tableTirosLibres.getColumnModel().getColumn(1).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(1).setPreferredWidth(112);
		tableTirosLibres.getColumnModel().getColumn(2).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setResizable(false);
		tableTirosLibres.getColumnModel().getColumn(3).setPreferredWidth(83);
		TiroLibres.setViewportView(tableTirosLibres);
		
		JLabel lblEstadisticas = new JLabel("Estadisticas");
		lblEstadisticas.setForeground(Color.WHITE);
		lblEstadisticas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEstadisticas.setBounds(1571, 58, 134, 16);
		panel.add(lblEstadisticas);
		
		JButton btnVerMasEstadisticas = new JButton("Ver mas estadisticas");
		btnVerMasEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LisEstadistica nueva = new LisEstadistica();
				nueva.setModal(true);
				nueva.setVisible(true);
				loadTodo();
			}
		});
		btnVerMasEstadisticas.setBounds(1571, 724, 152, 23);
		panel.add(btnVerMasEstadisticas);
		
		PnlProximosPartidos = new JPanel();
		PnlProximosPartidos.setForeground(Color.WHITE);
		PnlProximosPartidos.setBackground(Color.decode("#d8c1aa"));
		PnlProximosPartidos.setBorder(null);
		PnlProximosPartidos.setBounds(51, 300, 600, 460);
		panel.add(PnlProximosPartidos);
		PnlProximosPartidos.setLayout(null);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setForeground(Color.WHITE);
		lblLocal.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLocal.setBounds(100, 42, 59, 17);
		PnlProximosPartidos.add(lblLocal);
		
		JLabel lblVisitante = new JLabel("Visitante");
		lblVisitante.setForeground(Color.WHITE);
		lblVisitante.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblVisitante.setBounds(393, 38, 116, 25);
		PnlProximosPartidos.add(lblVisitante);
		
		JLabel lblProximosPartidos = new JLabel("Proximos Partidos");
		lblProximosPartidos.setBounds(208, 6, 180, 16);
		PnlProximosPartidos.add(lblProximosPartidos);
		lblProximosPartidos.setBackground(Color.BLACK);
		lblProximosPartidos.setForeground(Color.WHITE);
		lblProximosPartidos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.decode("#d8c1aa"));
		scrollPane.setBounds(51, 130, 600, 130);
		panel.add(scrollPane);
		
		
		tablePosicion = new JTable();
		tablePosicion.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(tablePosicion);
		scrollPane.setBorder(null);
		modelPosiciones = new DefaultTableModel();
		String[] columnNames = {"Posicion","Equipo","Jugados","Ganados","Perdidos"};
		modelPosiciones.setColumnIdentifiers(columnNames);
		tablePosicion.setModel(modelPosiciones);
		
		JLabel lblProximosPartidos_1 = new JLabel("Tabla de Posiciones");
		lblProximosPartidos_1.setForeground(Color.WHITE);
		lblProximosPartidos_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblProximosPartidos_1.setBounds(252, 92, 199, 26);
		panel.add(lblProximosPartidos_1);
		
		JLabel lblfondo = new JLabel("");
		lblfondo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/fondofinal.png")));
		lblfondo.setBounds(6, 6, (int)dim.getWidth(), (int)dim.getHeight());
		panel.add(lblfondo);
		loadTodo();
	}
	public void loadTodo() {
		loadPartidos();
		loadTirosLibres();
		loadTirosdeDos();
		loadTirosdeTres();
		loadPosiciones();
	}
	
	public void loadPartidos() {
		ArrayList<Partido>partidosNoJugados = new ArrayList<>();
		for (Partido partido : Liga.getInstance().getPartidos()) {
			if(!partido.isJugado()) {
				partidosNoJugados.add(partido);
			}
		}
		Collections.sort(partidosNoJugados);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		JLabel[] lblLocal = new JLabel[4];
		JLabel[] lblVisitante = new JLabel[4];
		JLabel[] lblcosita = new JLabel[4];
		JLabel[] Visitante = new JLabel[4];
		JLabel[] Local = new JLabel[4];
		JLabel[] fecha = new JLabel[4];
		JPanel[] paneles = new JPanel[4];
		int x = 6,y = 70;
		int width = 588,height = 85;
		for(int i = 0;i <4;i++,y+=97) {
			if(i==partidosNoJugados.size())
				break;
			else {
				paneles[i] = new JPanel();
				paneles[i].setBackground(Color.white);
				paneles[i].setBorder(new LineBorder(new Color(0, 0, 0)));
				paneles[i].setBounds(6, y, 588, 85);
				PnlProximosPartidos.add(paneles[i]);
				paneles[i].setLayout(null);
				
				lblcosita[i] = new JLabel();
				lblcosita[i].setText("vs");
				lblcosita[i].setFont(new Font("Dialog", Font.BOLD, 40));
				lblcosita[i].setBounds(290, 20, 50, 50);
				paneles[i].add(lblcosita[i]);
				
				lblLocal[i] = new JLabel();
				lblLocal[i].setBounds(68, 2, 160, 80);
				ImageIcon local = new ImageIcon(Liga.getInstance().getPartidos().get(i).getLocal().getLogo().getImage().getScaledInstance(lblLocal[i].getWidth(), lblLocal[i].getHeight(), Image.SCALE_DEFAULT));
				lblLocal[i].setIcon(local);
				paneles[i].add(lblLocal[i]);
				
				lblVisitante[i] = new JLabel();
				lblVisitante[i].setBounds(380, 2, 150, 80);
				ImageIcon visitante = new ImageIcon(Liga.getInstance().getPartidos().get(i).getVisitante().getLogo().getImage().getScaledInstance(lblLocal[i].getWidth(), lblLocal[i].getHeight(), Image.SCALE_DEFAULT));
				lblVisitante[i].setIcon(visitante);
				paneles[i].add(lblVisitante[i]);
				
				fecha[i] = new JLabel();
				fecha[i].setBounds(280, 63, 82, 16);
				fecha[i].setText(dateFormat.format(Liga.getInstance().getPartidos().get(i).getFecha()));
				paneles[i].add(fecha[i]);
			
				
			}
			
		}
		
	}
	
	private JTable tableTirosLibres;
	private JTable tablePosiciones;
	private void loadTirosLibres() {
		Collections.sort(Liga.getInstance().getJugadores(), new Tiros1Comparator());
		modelTirosLibres.setRowCount(0);
		Object[] fila = new Object[modelTirosLibres.getColumnCount()];
		int j= 1;
		for (int i = Liga.getInstance().getJugadores().size()-1; i>=0; i--,j++) {
			fila[0] = j;
			fila[1] = Liga.getInstance().getJugadores().get(i).getNombre()+" "+Liga.getInstance().getJugadores().get(i).getApellido();
			fila[2] = Liga.getInstance().getJugadores().get(i).getEquipo().getNombre();
			fila[3] = Liga.getInstance().getJugadores().get(i).getDesempenno().getTirosLibres();
			modelTirosLibres.addRow(fila);
		}
	}
	private void loadTirosdeDos() {
		Collections.sort(Liga.getInstance().getJugadores(), new tiros2Comparator());
		modelTirosde2.setRowCount(0);
		Object[] fila = new Object[modelTirosde2.getColumnCount()];
		int j= 1;
		for (int i = Liga.getInstance().getJugadores().size()-1; i>=0; i--,j++) {
			fila[0] = j;
			fila[1] = Liga.getInstance().getJugadores().get(i).getNombre()+" "+Liga.getInstance().getJugadores().get(i).getApellido();
			fila[2] = Liga.getInstance().getJugadores().get(i).getEquipo().getNombre();
			fila[3] = Liga.getInstance().getJugadores().get(i).getDesempenno().getTirosDeDos();
			modelTirosde2.addRow(fila);
		}
	}
	private void loadTirosdeTres() {
		Collections.sort(Liga.getInstance().getJugadores(), new Tiros3Comparator());
		modelTirosde3.setRowCount(0);
		Object[] fila = new Object[modelTirosde3.getColumnCount()];
		int j= 1;
		for (int i = Liga.getInstance().getJugadores().size()-1; i>=0; i--,j++) {
			fila[0] = j;
			fila[1] = Liga.getInstance().getJugadores().get(i).getNombre()+" "+Liga.getInstance().getJugadores().get(i).getApellido();
			fila[2] = Liga.getInstance().getJugadores().get(i).getEquipo().getNombre();
			fila[3] = Liga.getInstance().getJugadores().get(i).getDesempenno().getTirosDeTres();
			modelTirosde3.addRow(fila);
		}
	}
	private void loadPosiciones() {
		Collections.sort(Liga.getInstance().getEquipos());
		modelPosiciones.setRowCount(0);
		int j = 1;
		Object[] fila = new Object[modelPosiciones.getColumnCount()];
		for (int i = Liga.getInstance().getEquipos().size()-1; i >=0; i--,j++) {
			fila[0] = j;
			fila[1] = Liga.getInstance().getEquipos().get(i).getNombre();
			fila[2] = Liga.getInstance().getEquipos().get(i).getPartidosJugados();
			fila[3] = Liga.getInstance().getEquipos().get(i).getPartidosGanados();
			fila[4] = Liga.getInstance().getEquipos().get(i).getPartidosPerdidos();
			modelPosiciones.addRow(fila);
 		}
	}
}
