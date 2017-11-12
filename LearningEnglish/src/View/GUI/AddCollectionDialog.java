/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import Controller.CollectionControl;
import Controller.Controller;
import Controller.WordControl;
import Model.Collection;
import Model.Word;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;
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
 * represent for add new collection Dialog
 * 
 * @author Nguyễn Đức Thắng
 */
public class AddCollectionDialog extends javax.swing.JFrame {

    private Controller mainController;
    private WordControl wordController;
    private CollectionControl collectionController;

    private ArrayList<Word> dictionary;
    private ArrayList<Word> selectedWords;
    private Word wordOnDictionary;
    private Word wordOnSelectedWords;

    /**
     * Creates new form AddCollectionDialog
     */
    public AddCollectionDialog(Controller controller) {
        mainController = controller;
        wordController = controller.getWordController();
        collectionController = controller.getCollectionController();
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
        jSelectedWords.setViewportView(wordsList);
    }

    private void initData() {
        this.dictionary = wordController.getListWord();
        this.selectedWords = new ArrayList<>();
        setDictionary(this.dictionary);

        this.wordOnDictionary = null;
        this.wordOnSelectedWords = null;
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
        jCollectionName = new javax.swing.JTextField();
        jOkButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDictionary = new javax.swing.JScrollPane();
        jSelectedWords = new javax.swing.JScrollPane();
        jAddWord = new javax.swing.JButton();
        jRemoveWord = new javax.swing.JButton();
        jFilterTextfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Collection");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Collection's name:");

        jCollectionName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jOkButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jOkButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/ok.png"))); // NOI18N
        jOkButton.setText("OK");
        jOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOkButtonActionPerformed(evt);
            }
        });

        jCancelButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/cancel.png"))); // NOI18N
        jCancelButton.setText("Cancel");
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Choose words for collection:");

        jAddWord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/iconCaretRight.png"))); // NOI18N
        jAddWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddWordActionPerformed(evt);
            }
        });

        jRemoveWord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/AppIcons/IconCaretLeft.png"))); // NOI18N
        jRemoveWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemoveWordActionPerformed(evt);
            }
        });

        jFilterTextfield.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jFilterTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFilterTextfieldKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setText("Filter:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCollectionName))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jOkButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jDictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jAddWord, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(jRemoveWord, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSelectedWords, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFilterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCollectionName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jAddWord)
                        .addGap(25, 25, 25)
                        .addComponent(jRemoveWord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jOkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFilterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDictionary, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSelectedWords, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 50, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOkButtonActionPerformed
        if (jCollectionName.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên collection.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<Integer> listWordID = new ArrayList<>();
        for (Word word : this.selectedWords) {
            listWordID.add(word.getId());
        }

        Collection collection = new Collection(jCollectionName.getText(), listWordID);

        if (collectionController.createCollection(collection)) {
            JOptionPane.showMessageDialog(null, "Tạo collection thành công.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "tạo collection thất bại.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        clearData();
        mainController.updateView();
        this.setVisible(false);
    }//GEN-LAST:event_jOkButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        clearData();
        this.setVisible(false);
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jFilterTextfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFilterTextfieldKeyTyped
        String input = jFilterTextfield.getText() + ((int) evt.getKeyChar() != 8 ? evt.getKeyChar() : "");
        ArrayList<Word> matchesWord = new ArrayList<Word>();
        for (Word word : this.dictionary) {
            if (word.getName().regionMatches(true, 0, input, 0, input.length())) {
                matchesWord.add(word);
            }
        }
        this.setDictionary(matchesWord);
    }//GEN-LAST:event_jFilterTextfieldKeyTyped

    private void jAddWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddWordActionPerformed
        try {
            for (Word w : this.selectedWords) {
                if (this.wordOnDictionary.equals(w)) {
                    return;
                }
            }
            this.selectedWords.add(wordOnDictionary);
            this.wordOnSelectedWords = this.wordOnDictionary;
            setSelectedWords(this.selectedWords);
        } catch (NullPointerException except) {
            System.out.println("Please select a word first");
        }
    }//GEN-LAST:event_jAddWordActionPerformed

    private void jRemoveWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoveWordActionPerformed
        try {
            this.selectedWords.remove(wordOnSelectedWords);
            this.wordOnSelectedWords = null;
            setSelectedWords(this.selectedWords);
        } catch (NullPointerException except) {
            System.out.println("Please select a word first");
        }
    }//GEN-LAST:event_jRemoveWordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddWord;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JTextField jCollectionName;
    private javax.swing.JScrollPane jDictionary;
    private javax.swing.JTextField jFilterTextfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jOkButton;
    private javax.swing.JButton jRemoveWord;
    private javax.swing.JScrollPane jSelectedWords;
    // End of variables declaration//GEN-END:variables

    private void clearData() {
        this.selectedWords.clear();
        this.wordOnDictionary = null;
        this.wordOnSelectedWords = null;
    }
}
