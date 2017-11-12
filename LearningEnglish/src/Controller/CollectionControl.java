package Controller;

import Model.Collection;
import Model.IDatabase;
import Model.Word;
import Model.XlsxDatabase;
import View.GUI.HomeScreen;
import java.util.ArrayList;

/**
 * represent for collection controller
 * 
 * @author Nguyễn Đức Thắng
 */
public class CollectionControl {
    private IDatabase xlsxDatabase;
    private WordControl wordControl;
    
    public CollectionControl(WordControl wordController) {
        this.xlsxDatabase = XlsxDatabase.getInstance();
        this.wordControl = wordController;
    }
    
    public ArrayList<Collection> getAllCollection() {
        return xlsxDatabase.getAllCollection();
    }
    
    public ArrayList<Word> getAllWordOfCollection(int collectionID) {
        Collection collection = xlsxDatabase.readCollection(collectionID);
        ArrayList<Word> returnedListWord = new ArrayList<>();
        for(Integer wordId : collection.getListWord()) {
            returnedListWord.add(xlsxDatabase.readWordRecord(wordId));
        }
        return returnedListWord;
    }
    
    public boolean deleteCollection(Collection collection) {
        return xlsxDatabase.deleteCollection(collection.getName());
    }
    
    public void updateCollection(Collection collection) {
        if(collection == null) return;
        xlsxDatabase.updateCollection(collection.getId(), collection);
    }

    public WordControl getWordControl() {
        return wordControl;
    }
    
    public boolean createCollection(Collection collection) {
        return this.xlsxDatabase.createCollection(collection);
    }
}
