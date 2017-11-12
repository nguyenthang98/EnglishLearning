package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * represent for a database using xlsx workbook
 *
 * @author Nguyen Duc Thang
 */
public class XlsxDatabase implements IDatabase {

    // this variable represent for class File's instance
    File myFile;
    // this variable represent for a workbook as a xlsx file
    XSSFWorkbook myWorkBook;
    // this variable represent for an excel's sheet to store all words
    XSSFSheet wordSheet;
    // this variable represent for an excel's sheet to store all collections
    XSSFSheet collectionSheet;
    // this variable is used to store path to xlsx file
    private String xlsxFilePath;
    // this variable is used to implement singleton pattern
    private static XlsxDatabase instance = null;

    private XlsxDatabase() {
        this.xlsxFilePath = "src/Dictionary/dictionary.xlsx";
        this.onFilePathChange();
    }

    private void onFilePathChange() {
        this.myFile = new File(this.xlsxFilePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(myFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsxDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Finds the workbook instance for XLSX file
            this.myWorkBook = new XSSFWorkbook(fis);
        } catch (IOException ex) {
            Logger.getLogger(XlsxDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // first sheet of workbook is used to store word;
        this.wordSheet = this.myWorkBook.getSheet("Words");
        if(wordSheet == null) {
            wordSheet = this.myWorkBook.createSheet("Words");
        }
        // second sheet of workbook is used to store collection;
        this.collectionSheet = this.myWorkBook.getSheet("Collections");
        if(collectionSheet == null) {
            collectionSheet = this.myWorkBook.createSheet("Collections");
        }
    }

    /**
     * Singleton: get instance of this class
     *
     * @return instance of this class
     */
    public static XlsxDatabase getInstance() {
        if (instance == null) {
            instance = new XlsxDatabase();
        }
        return instance;
    }

    /**
     * getter: get xlsx file path
     *
     * @return xlsx file path
     */
    public String getXlsxFilePath() {
        return xlsxFilePath;
    }

    /**
     * setter: set up xlsx file path
     *
     * @param xlsxFilePath new file path
     */
    public void setXlsxFilePath(String xlsxFilePath) {
        this.xlsxFilePath = xlsxFilePath;
    }

    /**
     * read all word from database
     *
     * @return list of all word
     */
    @Override
    public ArrayList<Word> getAllWord() {
        ArrayList<Word> listWord = new ArrayList<Word>();

        Iterator<Row> rowIterator = wordSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            listWord.add(new Word(row));
        }

        return listWord;
    }

    /**
     * create row using word schema
     *
     * @param row iterator to row
     * @param word word to create row
     */
    void createRowWithWord(Row row, Word word) {
        int cellNum = 0;
        Field[] fieldSet = word.getClass().getDeclaredFields();
        for (cellNum = 0; cellNum < fieldSet.length; ++cellNum) {
            Cell cell = row.createCell(cellNum);
            switch (cellNum) {
                case 0:
                    cell.setCellValue(row.getRowNum());
                    break;
                case 1:
                    cell.setCellValue(word.getName());
                    break;
                case 2:
                    cell.setCellValue(word.getMeaning());
                    break;
                case 3:
                    cell.setCellValue(word.getPronunciation());
                    break;
                case 4:
                    cell.setCellValue(word.getLearnProgress());
                    break;
            }
        }
    }

    /**
     * update row with new word inside it
     *
     * @param row row reference
     * @param word new word properties
     */
    void updateRowWithWord(Row row, Word word) {
        int cellNum = 0;
        Field[] fieldSet = word.getClass().getDeclaredFields();
        for (cellNum = 0; cellNum < fieldSet.length; ++cellNum) {
            Cell cell = row.createCell(cellNum);
            switch (cellNum) {
                case 0:
                    cell.setCellValue(word.getId());
                    break;
                case 1:
                    cell.setCellValue(word.getName());
                    break;
                case 2:
                    cell.setCellValue(word.getMeaning());
                    break;
                case 3:
                    cell.setCellValue(word.getPronunciation());
                    break;
                case 4:
                    cell.setCellValue(word.getLearnProgress());
                    break;
            }
        }
    }

    /**
     * append list word to the database
     *
     * @param listWord
     */
    @Override
    public void writeToDatabase(ArrayList<Word> listWord) {
        int rowNum = wordSheet.getLastRowNum();
        for (Word word : listWord) {
            Row row = wordSheet.createRow(++rowNum);
            createRowWithWord(row, word);
        }
        this.writeToDatabase();
    }

    @Override
    public boolean createWordRecord(Word newWord) {
        for (Word word : this.getAllWord()) {
            if (word.equals(newWord)) {
                return false;
            }
        }
        int rowNum = wordSheet.getLastRowNum();
        Row row = wordSheet.createRow(++rowNum);
        createRowWithWord(row, newWord);
        this.writeToDatabase();
        return true;
    }

    @Override
    public Word readWordRecord(String word) {
        Word wordRecord = null;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(1).getStringCellValue().equals(word)) {
                wordRecord = new Word(row);
                break;
            }
        }
        return wordRecord;
    }

    @Override
    public Word readWordRecord(int id) {
        Word wordRecord = null;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                wordRecord = new Word(row);
                break;
            }
        }
        return wordRecord;
    }
    
    @Override
    public void updateWordList(ArrayList<Word> listWord) {
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        for(int i = 0; i < listWord.size(); ++i) {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(listWord.get(i).getId() == row.getCell(0).getNumericCellValue()) {
                    updateRowWithWord(row, listWord.get(i));
                    listWord.remove(i);
                    i--;
                    break;
                }
            }
            if(listWord.size() > 0) {
                rowIterator = wordSheet.iterator();
            } else {
                break;
            }
        }
        this.writeToDatabase();
    }
    
    @Override
    public void updateWordRecord(int id, Word newWord) {
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                updateRowWithWord(row, newWord);
                break;
            }
        }
        this.writeToDatabase();
    }

    private void removeWordRow(int rowIndex) {
        int lastRowNum = wordSheet.getLastRowNum();

        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            wordSheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = wordSheet.getRow(rowIndex);
            if (removingRow != null) {
                wordSheet.removeRow(removingRow);
            }
        }
    }

    @Override
    public boolean deleteWordRecord(String word) {
        boolean deleted = false;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(1).getStringCellValue().equalsIgnoreCase(word)) {
                removeWordRow(row.getRowNum());
                deleted = true;
                break;
            }
        }
        this.writeToDatabase();
        return deleted;
    }

    @Override
    public boolean deleteWordRecord(int id) {
        boolean deleted = false;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = wordSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                removeWordRow(row.getRowNum());
                deleted = true;
                break;
            }
        }
        this.writeToDatabase();
        return deleted;
    }

    @Override
    public ArrayList<Word> getWordInRange(int startId, int endId) {
        ArrayList<Word> listWord = new ArrayList<Word>();

        Iterator<Row> rowIterator = wordSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() >= startId && row.getRowNum() <= endId) {
                listWord.add(new Word(row));
            }
        }
        return listWord;
    }

    @Override
    public void writeToDatabase() {
        // open an OutputStream to save written data into XLSX file
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(myFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsxDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            myWorkBook.write(os);
        } catch (IOException ex) {
            Logger.getLogger(XlsxDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Writing on XLSX file Finished ...");
        try {
            os.close();
        } catch (IOException ex) {
            Logger.getLogger(XlsxDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.onFilePathChange();
    }
    
    private void createRowWidthCollection(Row row, Collection collection) {
        int cellNum = 0;
        int neededCell = collection.getListWord().size() + 2;
        for (cellNum = 0; cellNum < neededCell; ++cellNum) {
            Cell cell = row.createCell(cellNum);
            switch (cellNum) {
                case 0:
                    cell.setCellValue(row.getRowNum());
                    break;
                case 1:
                    cell.setCellValue(collection.getName());
                    break;
                default:
                    cell.setCellValue(collection.getListWord().get(cellNum - 2));
                    break;
            }
        }
    }
    
    public void updateRowWithCollection(Row row, Collection collection) {
        int cellNum = 0;
        int neededCell = collection.getListWord().size() + 2;
        for (cellNum = 0; cellNum < neededCell; ++cellNum) {
            Cell cell = row.createCell(cellNum);
            switch (cellNum) {
                case 0:
                    cell.setCellValue(collection.getId());
                    break;
                case 1:
                    cell.setCellValue(collection.getName());
                    break;
                default:
                    cell.setCellValue(collection.getListWord().get(cellNum - 2));
                    break;
            }
        }
    }
    
    @Override
    public boolean createCollection(Collection newCollection) {
        for (Collection collection : this.getAllCollection()) {
            if (collection.equals(newCollection)) {
                return false;
            }
        }
        int rowNum = collectionSheet.getLastRowNum();
        Row row = collectionSheet.createRow(++rowNum);
        createRowWidthCollection(row, newCollection);
        this.writeToDatabase();
        return true;
    }

    @Override
    public Collection readCollection(String nameCollection) {
        Collection collectionRecord = null;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = collectionSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(1).getStringCellValue().equals(nameCollection)) {
                collectionRecord = new Collection(row);
                break;
            }
        }
        return collectionRecord;
    }

    @Override
    public Collection readCollection(int id) {
        Collection collectionRecord = null;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = collectionSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                collectionRecord = new Collection(row);
                break;
            }
        }
        return collectionRecord;
    }
    
    
    @Override
    public void updateCollection(int id, Collection newCollection) {
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = collectionSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                updateRowWithCollection(row, newCollection);
                break;
            }
        }
        this.writeToDatabase();
    }

    private void removeCollectionRow(int rowIndex) {
        int lastRowNum = collectionSheet.getLastRowNum();

        if (rowIndex >= 0 && rowIndex < lastRowNum) {
            collectionSheet.shiftRows(rowIndex + 1, lastRowNum, -1);
        }
        if (rowIndex == lastRowNum) {
            Row removingRow = collectionSheet.getRow(rowIndex);
            if (removingRow != null) {
                collectionSheet.removeRow(removingRow);
            }
        }
    }
    
    @Override
    public boolean deleteCollection(String nameCollection) {
        boolean deleted = false;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = collectionSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(1).getStringCellValue().equals(nameCollection)) {
                removeCollectionRow(row.getRowNum());
                deleted = true;
                break;
            }
        }
        this.writeToDatabase();
        return deleted;
    }

    @Override
    public boolean deleteCollection(int id) {
        boolean deleted = false;
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = collectionSheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getCell(0).getNumericCellValue() == id) {
                removeCollectionRow(row.getRowNum());
                deleted = true;
                break;
            }
        }
        this.writeToDatabase();
        return deleted;
    }

    @Override
    public ArrayList<Collection> getAllCollection() {
        ArrayList<Collection> listCollection = new ArrayList<Collection>();

        Iterator<Row> rowIterator = collectionSheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            listCollection.add(new Collection(row));
        }

        return listCollection;
    }
}
