//The purpose of this page is to check out a book from the inventory system

//All Imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

public class CheckOut extends JFrame{
    //All variables and keys
    private JPanel rootPanel;
    //rootpanel
    private JTable table1;
    //table where you can see all the data
    private JButton backButton;
    //button to go back to the previous page
    private JButton exitButton;
    //button to exit the application
    private JButton COButton;
    //Check out button checks out the book
    private JButton viewAllCheckOutsButton;
    //Views all CURRENT check outs
    private JTextField batf1;
    //This is the Bar Code text box. The bar code goes in this text box.
    private JButton returnButton;
    //This button allows you to return the book. Clears that book from the Check out list
    private JButton checkoutLogButton;
    //This button shows a log of all time book check outs
    String[] headers =new String[]{"Book Name","Author","Genre","Read","Rating","Barcode","Date Checked out"};
    //header of the table
    DefaultTableModel dtm;
    //Table structure
    int row,col;
    //Rows and columns for searching

    //the check out page with all its function called from main
    public CheckOut(String title){
        //set frame
        super(title);
        //puts title passed on top of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit on command
        this.setContentPane(rootPanel);
        //the root panel is in the frame
        this.setSize(1050,720);
        //size
        this.setLocationRelativeTo(null);
        //sets in the middle of screen
        //set table
        dtm = new DefaultTableModel(headers,0);
        table1.setModel(dtm);

        //back
        backButton.addActionListener(e -> {
            //DMenu
            setVisible(false);
            //sets page to not diplay
            new DMenu("MENU").setVisible(true);
            //makes The menu open again
        });
        //exit
        exitButton.addActionListener(e -> {
            //exit
            System.exit(0);
        });
        //Check out Book
        COButton.addActionListener(e -> {
            //Checks out book when button is pressed
            File BL = new File("COList.txt");
            //Check out list open
            File BU = new File("AllCOList.txt");
            //All time check list open
            int chk = dtm.getRowCount();
            // get row count
            //if empty do this
            if(chk==0){
                JOptionPane.showMessageDialog(null,"No Book selected to checkout.");
                //if row count is 0 then the user did look up a book.
                // User needs to have abook selected in order to check out
            }
            //if not do this
            else{
                //if the table has an object
                //The try this
                try {
                    //looks for file
                    if (!BL.exists()) {
                        //BL is the COlist or the check out list
                        //if it does not then it creates it
                        JOptionPane.showMessageDialog(null, "We had to make a new file. It is saved under COList.txt");
                        BL.createNewFile();
                    }
                    //writes to file and continues to add
                    FileWriter fileWriter = new FileWriter(BL, true);
                    //writes from table to text file
                    for (int i = 0; i < dtm.getRowCount(); ++i) {
                        for (int j = 0; j < dtm.getColumnCount(); ++j) {
                            String s = dtm.getValueAt(i, j).toString();
                            fileWriter.write(s); //one word per time
                            fileWriter.write("\t\t"); //seperates the words
                        }
                        fileWriter.write(System.getProperty("line.separator"));// seperates the books
                    }
                    //closes writing
                    fileWriter.close();
                } catch (Exception ex) {
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure could not check out. Please try again");
                }
                // writes to the all time log
                try {
                    //looks for file
                    if (!BU.exists()) {
                        //BUlist is the all time log list or AllCOlist
                        //if it does not then it creates it
                        JOptionPane.showMessageDialog(null, "We had to make a new file. It is saved under AllCOList.txt");
                        BU.createNewFile();
                    }
                    //writes to file and continues to add
                    FileWriter fileWriter1 = new FileWriter(BU, true);
                    //writes to table in text file
                    for (int i = 0; i < dtm.getRowCount(); ++i) {
                        for (int j = 0; j < dtm.getColumnCount(); ++j) {
                            String s = dtm.getValueAt(i, j).toString();
                            fileWriter1.write(s);//one word per time
                            fileWriter1.write("\t\t"); //seperates the words
                        }
                        fileWriter1.write(System.getProperty("line.separator"));//seperates the books
                    }
                    //closes writing
                    fileWriter1.close();
                } catch (Exception ex) {
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure, could not insert book in log. Please try again.");
                }
                dtm.setRowCount(0);
                // clear the table for new books
                JOptionPane.showMessageDialog(null,"Book Checked Out!");
                //message saying all went well
            }
        });
        //view all checked out books
        viewAllCheckOutsButton.addActionListener(e -> {
            dtm.setRowCount(0);
            //clear text field
            clearField();
            //call table
            //Puts this list we are viewing from
            cretable1("COList.txt");
            //gets row count
            int chk =dtm.getRowCount();
            //not books are currently checked out if 0
            if(chk==0){
                // if no books checked out print statement
                JOptionPane.showMessageDialog(null,"No Books Checked out!");
            }
        });
        //return books
        returnButton.addActionListener(e -> {
            //gets row count
            int chk = dtm.getRowCount();
            //if there is no books currently checked out then you cannot return. Must look at the check out books before can return
            if(chk==0){
                JOptionPane.showMessageDialog(null,"No Books Checked Out!");
            }
            else{
                //deletes selected row
                dtm.removeRow(row);
                //rewrites file with the book that was selected
                try {
                    //writes to file and continues to add
                    PrintWriter Writer = new PrintWriter("COList.txt");
                    //writes to table in text file
                    for (int i = 0; i < dtm.getRowCount(); ++i) {
                        for (int j = 0; j < dtm.getColumnCount(); ++j) {
                            String s = dtm.getValueAt(i, j).toString();
                            Writer.print(s);
                            Writer.print("\t\t");
                        }
                        Writer.write(System.getProperty("line.separator"));
                    }
                    //closes writing
                    Writer.close();
                    JOptionPane.showMessageDialog(null, "Returned Book!");
                    //clear table
                    dtm.setRowCount(0);
                    //set table and show updated table
                    cretable1("COList.txt");
                } catch(Exception ex){
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure could not return book. Please try again.");
                }
            }
        });
        //selected rows and column
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //get row selected
                row = table1.getSelectedRow();
                //get column selected
                col = table1.getSelectedColumn();
            }
        });
        //enter to search
        batf1.addActionListener(e -> {
            //The purpose of this function is to search for a book when the text box is pressed or you hit the
            //enter button.

            //time stamp to see when checked out
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            //get text
            String result1 = batf1.getText();
            //to collect data and check it
            //Search
            JOptionPane.showMessageDialog(null, "Searching");
            //temporary variable
            String[] data = new String[0];
            try {
                //read in file
                BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                //set it default table model
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                //make headers
                model.setColumnIdentifiers(headers);
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
                    if (data1.equalsIgnoreCase(result1)) {
                        //if it equals input
                        data = new String[]{arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], String.valueOf(ts)};
                        dtm.addRow(data);
                    }
                }
            } catch (Exception t) {
                JOptionPane.showMessageDialog(null, "No Book Found.");
            }
            if (data.length == 0) {
                JOptionPane.showMessageDialog(null, "No Book Found");
            } else
                JOptionPane.showMessageDialog(null, "Here is what we found!");
            //clear txt field
            clearField();
        });
        //check out log button
        checkoutLogButton.addActionListener(e -> {
            dtm.setRowCount(0);
            //clear text field
            clearField();
            //call table
            cretable1("AllCOList.txt");
        });
    }

    //main
    public static void main(String[] args) {
        //run frame
        JFrame frame = new CheckOut("CHECKOUT");
        frame.setVisible(true);
    }
    //creates table
    public void cretable1(String title) {
        try {
            //read in file
            BufferedReader br = new BufferedReader(new FileReader(title));
            //set it default table model
            DefaultTableModel model = (DefaultTableModel)table1.getModel();
            //make headers
            model.setColumnIdentifiers(headers);

            // get lines from txt file
            Object[] tableLines = br.lines().toArray();

            // extratct data from lines
            // set data to jtable model
            for (Object tableLine : tableLines) {
                String line = tableLine.toString().trim();
                String[] arr = line.split("\t\t");
                String[] data = {arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]};
                dtm.addRow(data);
            }
        } catch (Exception t) {
            JOptionPane.showMessageDialog(null, "Table could not be created because either file is not in the right directory or never was established.");
        }
    }
    //clears the text box
    private void clearField(){
        //clear fields
        batf1.setText("");
    }
}
