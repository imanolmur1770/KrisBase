//This page is to search for books and look through all the books that we have

//all imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Locale;

public class ExistingBooks1 extends JFrame{
    //all variables and keys
    private JPanel rootPanel;
    //main panel
    private JLabel entLabel;
    //entry label describing page. Title
    private JComboBox comboBox1;
    //combo box to search in the database
    private JTextField textField1;
    //to enter int he text field
    private JButton backButton;
    //back to the Menu
    private JButton exitButton;
    //exit application
    private JLabel autLabel;
    //Shows the selection made from the combo box
    private JButton databaseButton;
    //gos to the database
    private JTable table1;
    //table where all data is organized
    private JButton showAllButton;
    //shows all books in data base
    private JLabel totalLabel;
    //total amount of books in the database
    private JLabel readtotLabel;
    //total amount of books read
    private JLabel nonreadtotLabel;
    //total amount of books not read
    private JButton saveButton;
    //save button when editing
    private JButton deleteButton;
    //delete button when editing
    String[] header = new String[]{"Book Name","Author","Genre","Read","Rating","Barcode"};
    //header of the JTable
    DefaultTableModel dtm;
    //Default table model

    //the page and all the functions
    public ExistingBooks1(String title){
        //set frame
        super(title);
        //puts title on top of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit on close
        this.setContentPane(rootPanel);
        //root panel is content of frame
        this.setSize(840,720);
        //size of frame
        this.setLocationRelativeTo(null);
        //The frame is in the middle of screen

        //set table and call method
        dtm = new DefaultTableModel(header,0);
        table1.setModel(dtm);
        //comboBox1
        comboBox1.addActionListener(e -> {
            //get selected index from comboBox
            int GTDB = comboBox1.getSelectedIndex();
            //if it does not equal 7 then print selected index in a label
            if(GTDB!=7) {
                //print label
                String result = (String) comboBox1.getSelectedItem();
                autLabel.setText(result);
            }
            else{
                //go to database
                setVisible(false);
                new NewBooks2("DATABASE").setVisible(true);
            }
        });
        //Searching
        textField1.addActionListener(e -> {
            //clear totals
            totalLabel.setText("");
            readtotLabel.setText("");
            nonreadtotLabel.setText("");
            //get selected index
            String result1 = textField1.getText();
            int GTDB = comboBox1.getSelectedIndex();
            //searches for keywords in the database. Depending on what was selected in the combo box it search through.
            if(GTDB==1){
                int count = 0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[0];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Book Name is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that name In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
            if(GTDB==2){
                int count =0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[1];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Author Name is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that author In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
            if(GTDB==3){
                int count = 0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[2];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Genre is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that genre In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
            if(GTDB==4){
                int count =0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[3];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Read Setting is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that read setting In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
            if(GTDB==5){
                int count =0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[4];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Rating is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that rating In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
            if(GTDB==6){
                int count =0;
                JOptionPane.showMessageDialog(null,"Searching");
                try {
                    BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                    DefaultTableModel model = (DefaultTableModel)table1.getModel();
                    model.setColumnIdentifiers(header);

                    // get lines from txt file
                    Object[] tableLines = br.lines().toArray();

                    // extratct data from lines
                    // set data to jtable model
                    dtm.setRowCount(0);
                    for (Object tableLine : tableLines) {
                        String line = tableLine.toString().trim();
                        String[] arr = line.split("\t\t");
                        String data1 = arr[5];
                        if ((data1.toLowerCase(Locale.ROOT)).contains(result1.toLowerCase(Locale.ROOT))) {
                            String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                            dtm.addRow(data);
                            count++;
                        }
                    }
                    totalLabel.setText("Total With This Barcode is: "+count);
                    clearField();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                int chk = dtm.getRowCount();
                if(chk==0){
                    JOptionPane.showMessageDialog(null,"No Books found with that barcode In the database");
                }
                else
                    JOptionPane.showMessageDialog(null,"Heres What we found");
            }
        });
        //exit
        exitButton.addActionListener(e -> {
            //exit frame
            System.exit(0);
        });
        //back
        backButton.addActionListener(e -> {
            //back to previuous frame
            setVisible(false);
            new DMenu("MENU").setVisible(true);
        });
        // go to database
        databaseButton.addActionListener(e -> {
            //go to database
            setVisible(false);
            new NewBooks2("DATABASE").setVisible(true);
        });
        //show all
        showAllButton.addActionListener(e -> {
            //clear table and tf
            dtm.setRowCount(0);
            clearField();
            //try to bring up table
            cretable1();
            int chk = dtm.getRowCount();
            //if table row count is 0 then there are no books in the database, thus show message
            if(chk == 0){
                JOptionPane.showMessageDialog(null,"No Books in the data base!");
            }
        });
        //save when editing
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //delete when editing
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    //creates table and labels
    public void cretable1() {
        //variables for the read/non/total
        int count = 0;
        int countre =0;
        int countnonre=0;
        try {
            //read in file
            BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
            //set it default table model
            DefaultTableModel model = (DefaultTableModel)table1.getModel();
            //make headers
            model.setColumnIdentifiers(header);

            // get lines from txt file
            Object[] tableLines = br.lines().toArray();

            // extratct data from lines
            // set data to jtable model
            for (Object tableLine : tableLines) {
                String line = tableLine.toString().trim();
                String[] arr = line.split("\t\t");
                String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]};
                dtm.addRow(data);
                count++;
                String res = arr[3];
                if (res.equalsIgnoreCase("yes")) {
                    countre++;
                } else if (res.equalsIgnoreCase("no")) {
                    countnonre++;
                }
            }
            totalLabel.setText("Total: "+count);
            readtotLabel.setText("Total Read: "+countre);
            nonreadtotLabel.setText("Total Not Read: "+countnonre);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //clears text box
    private void clearField(){
        //clear fields
        textField1.setText("");
    }

    //main
    public static void main(String[] args) {
        //run frame
        JFrame frame = new ExistingBooks1("EXISTING");
        frame.setVisible(true);
    }
}
