package View.GUI;

import Controller.CollectionControl;
import Controller.Controller;
import Controller.WordControl;
import Model.Collection;
import Model.Word;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author LoG
 */
public class CollectionsDialog extends javax.swing.JFrame {
    private Controller mainController;
    private CollectionControl Controller;
    private ArrayList<Collection> collections;
    private Collection currentCollection;
    
    private AddCollectionDialog addCollectionDialog;
    private DeleteCollectionDialog deleteCollectionDialog;
    private ManageWordOfCollectionDialog manageWordsDialog;
    private MergeCollectionDialog mergeCollectionDialog;
    
    /**
     * Creates new form CollectionsDialog
     */
    public CollectionsDialog(Controller controller) {
        this.mainController = controller;
        this.Controller = controller.getCollectionController();
        initComponents();
        initCollections();
        setIcon();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.setVisible(true);
            }
        });
        
        addCollectionDialog = new AddCollectionDialog(controller);
        deleteCollectionDialog = new DeleteCollectionDialog(controller);
        manageWordsDialog = new ManageWordOfCollectionDialog(controller, null);
        mergeCollectionDialog = new MergeCollectionDialog(controller);
    }
    
    private DefaultListModel<Word> createListModel(ArrayList<Word> dict) {
        DefaultListModel<Word> listModel;
        listModel = new DefaultListModel<>();
        for (Word word : dict) {
            listModel.addElement(word);
        }
        return listModel;
    }
    
    private void createWordList(ArrayList<Word> listWord) {
        DefaultListModel<Word> listModel = createListModel(listWord);
        JList<Word> wordsList = new JList<Word>(listModel);
        wordsList.setFont(new Font("Arial", Font.PLAIN, 12));
        wordsList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Word) {
                    ((JLabel) renderer).setText(((Word) value).getName());
                }
                return renderer;
            }
        });
        wordsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Word selectedWord = wordsList.getSelectedValue();
                if (selectedWord != null) {
                    jWordMeaning.setText(selectedWord.getMeaning());
                    jWordPronunce.setText(selectedWord.getPronunciation());
                }
            }
        });
        jWordList.setViewportView(wordsList);
    }
    
    public void initCollections() {
        this.collections = Controller.getAllCollection();
        for(Collection col : this.collections) {
            jCollections.addItem(col);
        }
        currentCollection = collections.get(0);
        createWordList(Controller.getAllWordOfCollection(collections.get(0).getId()));
        
        jCollections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collection col = (Collection) jCollections.getSelectedItem();
                createWordList(Controller.getAllWordOfCollection(col.getId()));
                currentCollection = col;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCollections = new javax.swing.JComboBox<>();
        jfFindWord = new javax.swing.JTextField();
        jFindWordButton = new javax.swing.JButton();
        jbBack = new javax.swing.JButton();
        jWordList = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jWordMeaning = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jWordPronunce = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jAddCollection = new javax.swing.JButton();
        jDeleteCollection = new javax.swing.JButton();
        jManageWords = new javax.swing.JButton();
        jMergeCollections = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setTitle("Collections");

        jfFindWord.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jfFindWord.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jfFindWordKeyTyped(evt);
            }
        });

        jFindWordButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jFindWordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/IconFind.png"))); // NOI18N
        jFindWordButton.setToolTipText("Find word");
        jFindWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFindWordButtonActionPerformed(evt);
            }
        });

        jbBack.setText("Back");
        jbBack.setToolTipText("Back to homescreen");
        jbBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBackActionPerformed(evt);
            }
        });

        jWordMeaning.setColumns(20);
        jWordMeaning.setRows(5);
        jScrollPane1.setViewportView(jWordMeaning);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Meaning:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Pronunciation:");
        jLabel2.setToolTipText("");

        jWordPronunce.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Collections:");

        jAddCollection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/TitleIcons/IconAdd.png"))); // NOI18N
        jAddCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddCollectionActionPerformed(evt);
            }
        });

        jDeleteCollection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/TitleIcons/IconDelete.png"))); // NOI18N
        jDeleteCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteCollectionActionPerformed(evt);
            }
        });

        jManageWords.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/IconEdit.png"))); // NOI18N
        jManageWords.setText("Manage Words");
        jManageWords.setToolTipText("");
        jManageWords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jManageWordsActionPerformed(evt);
            }
        });

        jMergeCollections.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/IconMerge.png"))); // NOI18N
        jMergeCollections.setText("Merge Collections");
        jMergeCollections.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMergeCollectionsActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Options:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jWordList)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jfFindWord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFindWordButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCollections, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jAddCollection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDeleteCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jManageWords, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jWordPronunce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jMergeCollections, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCollections, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDeleteCollection, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jfFindWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFindWordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jWordList, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMergeCollections, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jManageWords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jWordPronunce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbBack)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jFindWordButton, jfFindWord});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cài đặt sự kiện cho jbBack
     *
     * @param evt sự kiện
     */
    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        this.setVisible(false);
        HomeScreen homeScreen = new HomeScreen(this.mainController);
        homeScreen.setVisible(true);
    }//GEN-LAST:event_jbBackActionPerformed

    private void jfFindWordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jfFindWordKeyTyped
        String input = jfFindWord.getText() + ((int)evt.getKeyChar()!= 8 ? evt.getKeyChar():"");
        ArrayList<Word> matchesWord = new ArrayList<Word>();
        for(Word word : Controller.getAllWordOfCollection(currentCollection.getId())) {
            if(word.getName().regionMatches(true, 0, input, 0, input.length())) {
                matchesWord.add(word);
            }
        }
        this.createWordList(matchesWord);
    }//GEN-LAST:event_jfFindWordKeyTyped

    private void jFindWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFindWordButtonActionPerformed
        String input = jfFindWord.getText();
        ArrayList<Word> matchesWord = new ArrayList<Word>();
        for(Word word : Controller.getAllWordOfCollection(currentCollection.getId())) {
            if(word.getName().regionMatches(true, 0, input, 0, input.length())) {
                matchesWord.add(word);
            }
        }
        this.createWordList(matchesWord);
    }//GEN-LAST:event_jFindWordButtonActionPerformed

    private void jAddCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddCollectionActionPerformed
        this.addCollectionDialog.setVisible(true);
    }//GEN-LAST:event_jAddCollectionActionPerformed

    private void jDeleteCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteCollectionActionPerformed
        this.deleteCollectionDialog.setVisible(true);
    }//GEN-LAST:event_jDeleteCollectionActionPerformed

    private void jManageWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jManageWordsActionPerformed
        manageWordsDialog.setCurrCollection(currentCollection);
        manageWordsDialog.setVisible(true);
    }//GEN-LAST:event_jManageWordsActionPerformed

    private void jMergeCollectionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMergeCollectionsActionPerformed
        this.mergeCollectionDialog.setVisible(true);
    }//GEN-LAST:event_jMergeCollectionsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddCollection;
    private javax.swing.JComboBox<Collection> jCollections;
    private javax.swing.JButton jDeleteCollection;
    private javax.swing.JButton jFindWordButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jManageWords;
    private javax.swing.JButton jMergeCollections;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jWordList;
    private javax.swing.JTextArea jWordMeaning;
    private javax.swing.JLabel jWordPronunce;
    private javax.swing.JButton jbBack;
    private javax.swing.JTextField jfFindWord;
    // End of variables declaration//GEN-END:variables

    /**
     * Cài đặt icon
     */
    private void setIcon() {
        ImageIcon icon = new ImageIcon("src/View/TitleIcons/IconCollectionDialog.png");
        this.setIconImage(icon.getImage());
    }
    
    public void updateGUI() {
        jCollections.removeActionListener(jCollections.getActionListeners()[0]);
        jCollections.removeAllItems();
        initCollections();
        this.deleteCollectionDialog = new DeleteCollectionDialog(mainController);
        this.manageWordsDialog = new ManageWordOfCollectionDialog(mainController, currentCollection);
        this.mergeCollectionDialog = new MergeCollectionDialog(mainController);
    }
}