package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * represent for a collection
 * @author Nguyễn Đức Thắng
 */
public class Collection {
    private int id;
    private String name;
    private ArrayList<Integer> listWord;

    public Collection(Row row) {
        this.listWord = new ArrayList<>();
        Iterator<Cell> cellIterator = row.iterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch(cell.getColumnIndex()) {
                case 0:
                    this.id = (int)cell.getNumericCellValue();
                    break;
                case 1:
                    this.name = cell.getStringCellValue();
                    break;
                default:
                    this.listWord.add((int)cell.getNumericCellValue());
            }
        }
    }

    public Collection(Collection col) {
        this.id = col.id;
        this.name = col.name;
        this.listWord = col.listWord;
    }
    
    public Collection(String name, ArrayList<Integer> listWord) {
        this.name = name;
        this.listWord = listWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getListWord() {
        return listWord;
    }

    public void setListWord(ArrayList<Integer> listWord) {
        this.listWord = listWord;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Collection other = (Collection) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.listWord, other.listWord)) {
            return false;
        }
        return true;
    }
}
