package Model;

import java.util.Objects;
import org.apache.poi.ss.usermodel.Row;

/**
 * represent for a word
 * 
 * @author Nguyen Duc Thang
 */
public class Word {
    // id of word
    private int id;
    // name of the word
    private String name;
    // meaning of the word
    private String meaning;
    // how to pronunce it
    private String pronunciation;
    // user learning progress
    private int learnProgress;

    public Word() {
    }

    public Word(String name, String meaning, String pronunciation) {
        this.name = name;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
    }
    
    public Word(int id, String name, String meaning, String pronunciation, int learnProgress) {
        this.id = id;
        this.name = name;
        this.meaning = meaning;
        this.pronunciation = pronunciation;
        this.learnProgress = learnProgress;
    }
    
    public Word(Row row){
        this.id = (int) row.getCell(0).getNumericCellValue();
        this.name = row.getCell(1).getStringCellValue();
        this.meaning = row.getCell(2).getStringCellValue();
        this.pronunciation = row.getCell(3).getStringCellValue();
        this.learnProgress = (int) row.getCell(4).getNumericCellValue();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public int getLearnProgress() {
        return learnProgress;
    }

    public void increaseProgress() {
        this.learnProgress ++;
        if(this.learnProgress > 5) {
            this.learnProgress = 5;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Word{" + "id=" + id + ", name=" + name + ", meaning=" + meaning + ", pronunciation=" + pronunciation + ", learnProgress=" + learnProgress + '}';
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
        final Word other = (Word) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.meaning, other.meaning)) {
            return false;
        }
        if (!Objects.equals(this.pronunciation, other.pronunciation)) {
            return false;
        }
        return true;
    }
    
}
