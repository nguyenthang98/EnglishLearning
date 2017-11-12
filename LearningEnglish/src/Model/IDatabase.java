package Model;

import java.util.ArrayList;

/**
 *
 * @author Nguyen Duc Thang
 */
public interface IDatabase {
    public ArrayList<Word> getAllWord();
    public ArrayList<Collection> getAllCollection();
    public ArrayList<Word> getWordInRange(int startId, int endId);
    public void writeToDatabase(ArrayList<Word> listWord);
    public void writeToDatabase();
    // CRUD
    public boolean createWordRecord(Word newWord);
    public Word readWordRecord(String word);
    public Word readWordRecord(int id);
    public void updateWordList(ArrayList<Word> listWord);
    public void updateWordRecord(int id, Word newWord);
    public boolean deleteWordRecord(String word);
    public boolean deleteWordRecord(int id);
    
    // Collection
    public boolean createCollection(Collection newCollection);
    public Collection readCollection(String nameCollection);
    public Collection readCollection(int id);
    public void updateCollection(int id, Collection newCollection);
    public boolean deleteCollection(String nameCollection);
    public boolean deleteCollection(int id);
}
