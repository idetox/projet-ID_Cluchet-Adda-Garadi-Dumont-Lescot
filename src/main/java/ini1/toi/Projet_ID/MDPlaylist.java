package ini1.toi.Projet_ID;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class MDPlaylist extends AbstractTableModel {
    
	private static final long serialVersionUID = 1L;

	private final ArrayList<DataPlaylist> data = new ArrayList<DataPlaylist>();
 
    private final String[] entetes = {"Nom","Nombre de morceau"};
 
    public MDPlaylist() {
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
            DataPlaylist d = data.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    d.setNomPlaylist((String)aValue);
                    break;
                case 1:
                    d.setNbMorceau((int)aValue);
                    break;
            }
        }
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
        case 0:
            return data.get(rowIndex).getNomPlayList();
        case 1:
            return data.get(rowIndex).getNbMorceau();
        default:
            return null; //Ne devrait jamais arriver
        }
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Class getColumnClass(int columnIndex){
    	switch(columnIndex){
    		default:
    			return Object.class;
    	}
    }
    
	@Override
    public boolean isCellEditable(int row, int col){
    	  return false;
    	}
 
    public void addData(DataPlaylist D) {
        data.add(D);
 
        fireTableRowsInserted(data.size() -1, data.size() -1);
    }
 
    public void removeData(int rowIndex) {
        data.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}