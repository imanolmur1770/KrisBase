//this page is to sort books

//all imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NewBooks2 extends JFrame {
    //all variables and keys
    private JComboBox sortCom;
    //sort combobox
    private JTable genTble;
    //table
    private JButton backButton;
    //back to ExistingBooks1
    private JButton exitButton;
    //exits application
    private JPanel rootpanel;
    //root panel for frame
    String[] header = new String[]{"Book Name","Author","Genre","Read","Rating","Barcode"};
    //header for table
    DefaultTableModel dtm;
    //table model
    int row,col;
    //row and comlumn
    private String bntf,autf,getf,retf,ratf,batf = "";
    //initialize the the text fields

    //main fucntions and objectives
    public NewBooks2(String title) {
        //set frame
        super(title);
        //puts title in the center of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit on close
        this.setContentPane(rootpanel);
        //makes root panel to frame
        this.setSize(840,720);
        //size of frame
        this.setLocationRelativeTo(null);
        //frame in middle of window

        //set table and call method for creating table
        dtm = new DefaultTableModel(header,0);
        genTble.setModel(dtm);
        cretable1();
        //creates table

        //back
        backButton.addActionListener(e -> {
            //back to Existing frame
            setVisible(false);
            new ExistingBooks1("EXISTING").setVisible(true);
        });
        //exit
        exitButton.addActionListener(e -> {
            //exit frame
            System.exit(0);
        });
        //Sort
        sortCom.addActionListener(e -> {
            //get selected index from comboBox
            int GTDB = sortCom.getSelectedIndex();
            if(GTDB==1){
                //sort Book Names
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(genTble.getModel());
                genTble.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
            if(GTDB==2){
                //sort read
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(genTble.getModel());
                genTble.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
            if(GTDB==3){
                //sort rating
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(genTble.getModel());
                genTble.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
            if(GTDB==4){
                //sort author
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(genTble.getModel());
                genTble.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
            if(GTDB==5){
                //sorts genre
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(genTble.getModel());
                genTble.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);
            }
            if(GTDB==6){
                //reset table
                dtm.setRowCount(0);
                cretable1();
            }
        });
        /*deleteButton.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Book?","Delete",dialogButton);
            if(dialogResult == 0) {
                // if they choose yes then deletes
                dtm.removeRow(row);
                //Show it was deleted
                //File name
                try {
                    //writes to file and continues to add
                    PrintWriter Writer = new PrintWriter("BookList.txt");
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
                } catch(Exception ex){
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure");
                }
                JOptionPane.showMessageDialog(null, "Deleted Book!");
            }
            //clear table and bring up new table
            dtm.setRowCount(0);
            cretable1();
        });*/
        //Mouse Listener, when mouse is clicked it listens
        genTble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                row = genTble.getSelectedRow();
                col = genTble.getSelectedColumn();
                bntf=(dtm.getValueAt(row,0).toString());
                autf=(dtm.getValueAt(row,1).toString());
                getf=(dtm.getValueAt(row,2).toString());
                retf=(dtm.getValueAt(row,3).toString());
                ratf=(dtm.getValueAt(row,4).toString());
                batf=(dtm.getValueAt(row,5).toString());
            }
        });
        /*editButton.addActionListener((ActionEvent e) -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure you want to rewrite this Book?","Delete",dialogButton);
            if(dialogResult == 0) {
                // if they choose yes then deletes
                //Show it was deleted
                //File name
                try {
                    //writes to file and continues to add
                    PrintWriter Writer = new PrintWriter("BookList.txt");
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
                } catch(Exception ex){
                    // if file could not be written
                    JOptionPane.showMessageDialog(null, "Failure");
                }
                JOptionPane.showMessageDialog(null, "Updated Book!");
            }
            //clear list and show updated version
            dtm.setRowCount(0);
            cretable1();
        });*/
    }

    //main
    public static void main(String[] args) {
        //run frame
        JFrame Frame = new NewBooks2("NEW BOOKS");
        Frame.setVisible(true);
    }

    //create table function
    public void cretable1() {
            try {
                //read in txt file
                BufferedReader br = new BufferedReader(new FileReader("BookList.txt"));
                //set it to table
                DefaultTableModel model = (DefaultTableModel)genTble.getModel();
                //header
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
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }