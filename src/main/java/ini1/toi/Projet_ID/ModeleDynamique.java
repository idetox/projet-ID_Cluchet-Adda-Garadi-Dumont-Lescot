package ini1.toi.Projet_ID;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;


public class ModeleDynamique extends AbstractTableModel {
    
	private static final long serialVersionUID = 1L;

	private final ArrayList<Data> data = new ArrayList<Data>();
 
    private final String[] entetes = {"Titre","Artiste","Album","Style","Jouer"};
 
    public ModeleDynamique() {
        super();
    }
 
    public int getRowCount() {
        return data.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            Data d = data.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    d.setMorceau((String)aValue);
                    break;
                case 1:
                    d.setArtiste((String)aValue);
                    break;
                case 2:
                    d.setAlbum((String)aValue);
                    break;
                case 3:
                    d.setStyle((String)aValue);
                    break;
                case 4:
                    d.setBouton((JButton)aValue);
                    break;
            }
        }
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
        case 0:
            return data.get(rowIndex).getMorceau();
        case 1:
            return data.get(rowIndex).getArtiste();
        case 2:
            return data.get(rowIndex).getAlbum();
        case 3:
            return data.get(rowIndex).getStyle();
        case 4:
            return data.get(rowIndex).getBouton();
        default:
            return null; //Ne devrait jamais arriver
        }
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getColumnClass(int columnIndex){
    	switch(columnIndex){
    		case 4:
    			return JButton.class;
    		default:
    			return Object.class;
    	}
    }
    
	@Override
    public boolean isCellEditable(int row, int col){
    	  return false;
    	}
 
    public void addData(Data D) {
        data.add(D);
 
        fireTableRowsInserted(data.size() -1, data.size() -1);
    }
 
    public void removeData(int rowIndex) {
        data.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}