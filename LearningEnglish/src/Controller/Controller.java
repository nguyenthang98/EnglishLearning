package Controller;

import View.GUI.HomeScreen;

/**
 * represent for main controller of this application
 *
 * @author Nguyễn Đức Thắng
 */
public class Controller {
    private WordControl wordController;
    private CollectionControl collectionController;
    private HomeScreen GUI;
    
    public Controller() {
        this.wordController = new WordControl();
        this.collectionController = new CollectionControl(wordController);
        this.GUI = new HomeScreen(this);
    }

    public WordControl getWordController() {
        return wordController;
    }

    public void setWordController(WordControl wordController) {
        this.wordController = wordController;
    }

    public CollectionControl getCollectionController() {
        return collectionController;
    }

    public void setCollectionController(CollectionControl collectionController) {
        this.collectionController = collectionController;
    }
    
    public HomeScreen getGUI() {
        return this.GUI;
    }
    
    public void updateView() {
        GUI.updateFrame();
    }
}
