/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import Controller.CollectionControl;
import Controller.Controller;
import Model.Collection;
import Model.Word;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * represent for manage words list of collection dialog
 * 
 * @author Nguyễn Đức Thắng
 */
public class ManageWordOfCollectionDialog extends javax.swing.JFrame {
    private Controller Controller;
    private CollectionControl collectionController;
    private Collection currCollection;
    private Word wordOnDictionary;
    private Word wordOnSelectedWords;
    private ArrayList<Word> selectedWords;
    
    /**
     * Creates new form AddWordToCollection
     */
    public ManageWordOfCollectionDialog(Controller controller, Collection currentCollection) {
        Controller = controller;
        collectionController = controller.getCollectionController();
        currCollection = currentCollection;
        initComponents();
        initData();
    }
    
    private DefaultListModel<Word> createListModel(ArrayList<Word> dict) {
        DefaultListModel<Word> listModel;
        listModel = new DefaultListModel<>();
        for (Word word : dict) {
            listModel.addElement(word);
        }
        return listModel;
    }
    
    private void setDictionary(ArrayList<Word> listWord) {
        DefaultListModel<Word> listModel = createListModel(listWord);
        JList<Word> wordsList = new JList<Word>(listModel);
        wordsList.setFont(new Font("Dialog", Font.PLAIN, 12));
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
                wordOnDictionary = selectedWord;
            }
        });
        jDictionary.setViewportView(wordsList);
    }
    
    private void setSelectedWords(ArrayList<Word> listWord) {
        DefaultListModel<Word> listModel = createListModel(listWord);
        JList<Word> wordsList = new JList<Word>(listModel);
        wordsList.setFont(new Font("Dialog", Font.PLAIN, 12));
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
                wordOnSelectedWords = selectedWord;
            }
        });
        jSelectedWord.setViewportView(wordsList);
    }
    
    private void initData() {
        setDictionary(Controller.getWordController().getListWord());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCollection = new javax.swing.JLabel();
        jDictionary = new javax.swing.JScrollPane();
        jSelectedWord = new javax.swing.JScrollPane();
        jAddWordButton = new javax.swing.JButton();
        jOkButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jDeleteWordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Collection: ");

        jCollection.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N

        jAddWordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/iconCaretRight.png"))); // NOI18N
        jAddWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddWordButtonActionPerformed(evt);
            }
        });

        jOkButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/ok.png"))); // NOI18N
        jOkButton.setText("Ok");
        jOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOkButtonActionPerformed(evt);
            }
        });

        jCancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/cancel.png"))); // NOI18N
        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jDeleteWordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/IconCaretLeft.png"))); // NOI18N
        jDeleteWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteWordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAddWordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jDeleteWordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSelectedWord, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jCollection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCollection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDictionary, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(jSelectedWord)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jAddWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDeleteWordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jOkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOkButtonActionPerformed
        if (this.currCollection == null) {
            JOptionPane.showMessageDialog(null, "invalid collection.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<Integer> listWordID = new ArrayList<>();
        for (Word word : this.selectedWords) {
            listWordID.add(word.getId());
        }

        Collection collection = new Collection(currCollection.getName(), listWordID);

        collectionController.updateCollection(collection);
        
        clearData();
        Controller.updateView();
        this.setVisible(false);
    }//GEN-LAST:event_jOkButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        clearData();
        this.setVisible(false);
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jAddWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddWordButtonActionPerformed
        try {
            for(Word w : this.selectedWords) {
                if(this.wordOnDictionary.equals(w)) {
                    return;
                }
            }
            this.selectedWords.add(wordOnDictionary);
            this.wordOnSelectedWords = this.wordOnDictionary;
            setSelectedWords(this.selectedWords);
        } catch (NullPointerException except) {
            System.out.println("Please select a word first");
        }
    }//GEN-LAST:event_jAddWordButtonActionPerformed

    private void jDeleteWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteWordButtonActionPerformed
        try {
            this.selectedWords.remove(wordOnSelectedWords);
            this.wordOnSelectedWords = null;
            setSelectedWords(this.selectedWords);
        } catch (NullPointerException except) {
            System.out.println("Please select a word first");
        }
    }//GEN-LAST:event_jDeleteWordButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddWordButton;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JLabel jCollection;
    private javax.swing.JButton jDeleteWordButton;
    private javax.swing.JScrollPane jDictionary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jOkButton;
    private javax.swing.JScrollPane jSelectedWord;
    // End of variables declaration//GEN-END:variables

    public void setCurrCollection(Collection currCollection) {
        this.currCollection = currCollection;
        this.selectedWords = collectionController.getAllWordOfCollection(currCollection.getId());
        jCollection.setText(currCollection.getName());
        setSelectedWords(this.selectedWords);
    }
    
    private void clearData() {
        this.selectedWords.clear();
        this.wordOnDictionary = null;
        this.wordOnSelectedWords = null;
    }
}
