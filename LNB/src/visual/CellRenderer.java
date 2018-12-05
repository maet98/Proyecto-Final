package visual;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import logico.Jugador;
import logico.Liga;

final class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	void RenderRedGreen(){
		setHorizontalAlignment(SwingConstants.RIGHT);
	}
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(new Color(240, 230, 140));
        
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Jugador jugador = Liga.getInstance().buscarJugadorId(table.getValueAt(row, 0).toString());
        
        
        if(jugador.isLesionado() == true) {
        	l.setBackground(Color.red);
        }else {
        	if(isSelected) {
            	l.setBackground(Color.BLUE);
            	l.setForeground(Color.BLACK);
            }
        	else {
        		l.setBackground(Color.WHITE);
        		l.setForeground(Color.BLACK);
        	}
        }
        return l;
    }
     	
}