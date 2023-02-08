//This page adds books into the database

//all imports
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class NewBooks extends JFrame {
    //all variables and keys
    private JPanel rootpanel;
    //root panel of the page
    private JTextField bntf;
    //book name text field
    private JTextField autf;
    //author text field
    private JTextField getf;
    //genre text field
    private JTextField ratf;
    //rating text field
    private JButton addButton;
    //add book to table
    private JTextField retf;
    //read status text field
    private JButton backButton;
    //back button to previuous page
    private JButton exitButton;
    //exit the application
    private JTable bookListTable;
    //Table for page
    private JButton saveButton;
    //save onto .txt file
    private JButton updateButton;
    //updates the table incase you had to edit
    private JButton deleteButton;
    //deltes the book selected
    private JTextField batf;
    //barcode text field
    private JLabel nbLabel;
    //New books label. title
    ArrayList<Book> BookList;
    //array list of books
    private String result1;
    //result for books name
    private String result2;
    //result for author
    private String result3;
    //result for genre
    private String result4;
    //result for read
    private double jml;
    //result for rating
    private String bar;
    //result for barocde
    String[] header = new String[]{"Book Name","Author","Genre","Read","Rating","Barcode"};
    //header for table
    DefaultTableModel dtm;
    //table for swapping
    int row,col;
    //column and row

    //functions and main page found here
    public NewBooks(String title){
        //setting screen
        super(title);
        //makes title in middle top of frame
        dtm = new DefaultTableModel(header,0);
        //sets table
        bookListTable.setModel(dtm);
        //converts table to frame table
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit on close
        this.setContentPane(rootpanel);
        //root panel in on frame
        this.setSize(840,720);
        //size of frame
        this.setLocationRelativeTo(null);
        //frame in middle of screen

        //initalizing array with its objects
        BookList = new ArrayList<>();
        result1 = "";
        result2 = "";
        result3 = "";
        result4 = "";
        jml = 0.0;
        bar = "";

        //exit
        exitButton.addActionListener(e -> {
            //exit frame
            System.exit(0);
        });
        //back
        backButton.addActionListener(e -> {
            //back to previous screen
            setVisible(false);
            new DMenu("MENU").setVisible(true);
        });
        //add
        addButton.addActionListener(e -> {
            try{
                //assign variables from the inputs to temporary variable
                result1 = bntf.getText();
                result2 = autf.getText();
                result3 = retf.getText();
                result4 = getf.getText();
                jml = Double.parseDouble(ratf.getText());
                bar = ((batf.getText()));

                //searches if the barcode exists in the text file
                boolean check = search(bar);
                //searches if barcode exists in the table already
                boolean check2 = searchL(bar);

                //adds the book in the arraylist
                //adds before function so it has capablity to be added to table
                BookList.add(new Book(result1,result2,result4,result3,jml,bar));

                //clear table
                dtm.setRowCount(0);

                //checks if barcodes are already on .txt and on the table
                if(!check && !check2) {
                    //checks if read and ratings are correct
                    if ((result3.equalsIgnoreCase("Yes") || result3.equalsIgnoreCase("No") || result3.equalsIgnoreCase("DNF") || result3.equalsIgnoreCase("Reading")) && (jml >= 0 && jml <= 6)) {
                        // if yes then adds to table
                        JOptionPane.showMessageDialog(null, "Added New Book!");
                        for (Book book : BookList) {
                            Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                            dtm.addRow(objs);
                        }
                        //clears text field
                        clearField();
                    } else {
                        //wrong displays and removes entry
                        JOptionPane.showMessageDialog(null, "Wrong Try Again");
                        BookList.remove(BookList.size()-1);
                        for (Book book : BookList) {
                            Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                            dtm.addRow(objs);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Book with this barcode already exists. Please try again.");
                    BookList.remove(BookList.size() - 1);
                    //removes most recent
                    for (Book book : BookList) {
                        Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                        dtm.addRow(objs);
                    }
                    //generates table
                }

            }catch(Exception t){
                JOptionPane.showMessageDialog(null,"Not enough information/check the rating or read status");
            }
    });
        //save
        saveButton.addActionListener(e -> {
            //File name
            File BL = new File("BookList.txt");
            int chk = dtm.getRowCount();
            //gets row count and if row count is 0 then becuase table is empty.
            if(chk == 0){
                JOptionPane.showMessageDialog(null,"File could not be saved. No information added to the table.");
            }
            else {
                //row count is > 0 then add to list
                try {
                    //looks for file
                    if (!BL.exists()) {
                        //if it does not then it creates it
                        JOptionPane.showMessageDialog(null, "We had to make a new file. It is saved under BookList.txt");
                        BL.createNewFile();
                    }
                    //writes to file and continues to add
                    FileWriter fileWriter = new FileWriter(BL, true);
                    //writes to table in text file
                    for (int i = 0; i < dtm.getRowCount(); ++i) {
                        for (int j = 0; j < dtm.getColumnCount(); ++j) {
                            String s = dtm.getValueAt(i, j).toString();
                            fileWriter.write(s);//writes word
                            fileWriter.write("\t\t");//space between word
                        }
                        fileWriter.write(System.getProperty("line.separator"));//line seperator between books
                    }
                    //closes writing
                    fileWriter.close();
                    //clear list and table
                    dtm.setRowCount(0);
                    BookList.clear();
                } catch (Exception ex) {
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure");
                }
                //display all good!
                JOptionPane.showMessageDialog(null, "Success! File saved to BookList.txt");
            }
        });
        //coursetable
        bookListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if mouse is clicked on a selected in the table, displays to text field
                super.mouseClicked(e);
                row = bookListTable.getSelectedRow();
                col = bookListTable.getSelectedColumn();
                bntf.setText(dtm.getValueAt(row,0).toString());
                autf.setText(dtm.getValueAt(row,1).toString());
                getf.setText(dtm.getValueAt(row,2).toString());
                retf.setText(dtm.getValueAt(row,3).toString());
                ratf.setText(dtm.getValueAt(row,4).toString());
                batf.setText(dtm.getValueAt(row,5).toString());
            }
        });
        //delete
        deleteButton.addActionListener(e -> {
            int chk = dtm.getRowCount();
            //gets row count, if == 0 then there are no books to delete
            if(chk==0){
                JOptionPane.showMessageDialog(null,"No Books to delete");
            }
            else{
                //Asks user if they are sure they want to delete
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Book?","Delete",dialogButton);
                if(dialogResult == 0){
                    // if they choose yes then deletes
                    dtm.removeRow(row);
                    BookList.remove(row);
                    dtm.setRowCount(0);
                    //Show it was deleted
                    JOptionPane.showMessageDialog(null,"Deleted Book!");
                    //Show table
                    for (Book book : BookList) {
                        Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                        dtm.addRow(objs);
                    }
                    //clears text boxes
                    clearField();
                }
            }
        });
        //update
        updateButton.addActionListener(e -> {
            int chk = dtm.getRowCount();
            if(chk==0){
                JOptionPane.showMessageDialog(null,"No Books to update");
            }
            else{
                //transfer information into new text
                String updatedbntf = bntf.getText();
                String updatedautf = autf.getText();
                String updatedgetf = getf.getText();
                String updatedretf = retf.getText();
                Double updatedratf = Double.parseDouble(ratf.getText());
                String updatedbatf = (batf.getText());
                //set the text field with new information
                BookList.get(row).setBookName(updatedbntf);
                BookList.get(row).setAuthor(updatedautf);
                BookList.get(row).setGenre(updatedgetf);
                BookList.get(row).setRead(updatedretf);
                BookList.get(row).setRating(updatedratf);
                BookList.get(row).setBarcode(updatedbatf);
                //update
                JOptionPane.showMessageDialog(null,"Updated!");
                //clear table
                dtm.setRowCount(0);
                //show new table
                for (Book book : BookList) {
                    Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                    dtm.addRow(objs);
                }
                //clear texts fields
                clearField();
            }
        });
        //same as the add button but accomplished with enter on keyboard
        batf.addActionListener(e -> {
            try{
                //assign variables for the inputs
                result1 = bntf.getText();
                result2 = autf.getText();
                result3 = retf.getText();
                result4 = getf.getText();
                jml = Double.parseDouble(ratf.getText());
                bar = ((batf.getText()));
                boolean check = search(bar);
                boolean check2 = searchL(bar);
                //set them in book and arrayList
                BookList.add(new Book(result1,result2,result4,result3,jml,bar));

                //clear table
                dtm.setRowCount(0);
                if(!check && !check2) {
                    //checks if read and ratings are correct
                    if ((result3.equalsIgnoreCase("Yes") || result3.equalsIgnoreCase("No") || result3.equalsIgnoreCase("DNF") || result3.equalsIgnoreCase("Reading")) && (jml >= 0 && jml <= 6)) {
                        // if yes then adds to table
                        JOptionPane.showMessageDialog(null, "Added New Book!");
                        for (Book book : BookList) {
                            Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                            dtm.addRow(objs);
                        }
                        //clears text field
                        clearField();
                    } else {
                        //wrong displays and removes entry
                        JOptionPane.showMessageDialog(null, "Wrong Try Again");
                        BookList.remove(BookList.size()-1);
                        for (Book book : BookList) {
                            Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                            dtm.addRow(objs);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Book with this barcode already exists. Please try again.");
                    BookList.remove(BookList.size() - 1);
                    for (Book book : BookList) {
                        Object[] objs = {book.getBookName(), book.getAuthor(), book.getGenre(), book.getRead(), book.getRating(), book.getBarcode()};
                        dtm.addRow(objs);
                    }
                }

            }catch(Exception t){
                JOptionPane.showMessageDialog(null,"Not enough information/check the rating or read status");
            }
        });
    }
    //clears the text fields
    private void clearField(){
        //clear fields
        bntf.setText("");
        autf.setText("");
        getf.setText("");
        retf.setText("");
        ratf.setText("");
        batf.setText("");
    }
    //searchs if barcode is in the database(.txt file)
    private boolean search(String barr){
        //Search
        boolean result = false;
        try {
            //read in file
            BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
            //set it default table model
            DefaultTableModel model = (DefaultTableModel) bookListTable.getModel();
            //make headers
            model.setColumnIdentifiers(header);
            // get lines from txt file
            Object[] tableLines = br.lines().toArray();

            // extratct data from lines
            // set data to jtable and save to list
            dtm.setRowCount(0);
            for (Object tableLine : tableLines) {
                String line = tableLine.toString().trim();
                String[] arr = line.split("\t\t");
                //Barcode
                String data1 = arr[5];
                if (data1.equalsIgnoreCase(barr)) {
                    //if it equals input
                    result = true;
                }
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null,"no");
        }
        return result;
    }
    //searches if barcode is in the JTable
    private boolean searchL(String barr){
        boolean result = false;
        for(int i = 0; i<BookList.size();i++){
            Book datat = BookList.get(i);
            String data1 = datat.getBarcode();
            if(data1.equalsIgnoreCase(barr)){
                result = true;
            }
        }
        return result;
    }

    //main
    public static void main(String[] args) {
        //run frame
        JFrame Frame = new NewBooks("NEW BOOKS");
        Frame.setVisible(true);
    }
}
