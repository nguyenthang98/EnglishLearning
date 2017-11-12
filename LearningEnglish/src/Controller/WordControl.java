package Controller;

import Model.IDatabase;
import Model.Word;
import Model.XlsxDatabase;
import View.GUI.HomeScreen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this class represent for controller of words
 * 
 * @author Nguyen Duc Thang
 */
public class WordControl {
    private IDatabase xlsxDatabase;

    public WordControl() {
        this.xlsxDatabase = XlsxDatabase.getInstance();
    }
    
    public ArrayList<Word> getListWord() {
        return sortListWord(xlsxDatabase.getAllWord());
    }
    
    public boolean addNewWord(Word newWord) {
        return xlsxDatabase.createWordRecord(newWord);
    }
    
    public Word getWord(int id) {
        return xlsxDatabase.readWordRecord(id);
    }
    
    public void updateWord(ArrayList<Word> listWord) {
        xlsxDatabase.updateWordList(listWord);
    }
    
    public void updateWord(Word word) {
        xlsxDatabase.updateWordRecord(word.getId(), word);
    }
    
    public Word findWordByName(String word) {
        return xlsxDatabase.readWordRecord(word);
    }
    
    public boolean deleteWord(String word) {
        return xlsxDatabase.deleteWordRecord(word);
    }
    
    public ArrayList<Word> getLearnedWord() {
        ArrayList<Word> allWord = xlsxDatabase.getAllWord();
        allWord.removeIf(s -> s.getLearnProgress() == 0);
        return allWord;
    }
    
    public ArrayList<Word> sortListWord(ArrayList<Word> listWord) {
        Collections.sort(listWord, new Comparator<Word>() {
            @Override
            public int compare(Word word1, Word word2)
            {
                return  word1.getName().compareToIgnoreCase(word2.getName());
            }
        });
        return listWord;
    }
}
