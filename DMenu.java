//The purpose of this page is to act as a menu system to allow the user to select where they want to go.

//all imports
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DMenu extends JFrame {
    //all variables and keys
    private JButton existingBooksButton;
    //Go to the database button. to search or sort
    private JButton newBooksButton;
    //add new book button
    private JButton exitButton;
    //exit application button
    private JButton logoutButton;
    //logout button
    private JLabel entLabel;
    //welcome label
    private JPanel rootPanel;
    //the root panel
    private JButton checkOutButton;
    //go to check out page button
    private JButton genbut;
    //go to generate book buttom

    //runs on the main
    public DMenu(String title) {
        //set frame
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);
        this.setSize(840,720);
        this.setLocationRelativeTo(null);
        //existingBooks
        existingBooksButton.addActionListener(e -> {
            //go to existingBooks frame
            setVisible(false);
            new ExistingBooks1("EXISTING").setVisible(true);
        });
        //newBooks
        newBooksButton.addActionListener(e -> {
            //go to newBooks
            setVisible(false);
            new NewBooks("New BOOKS").setVisible(true);
        });
        //exit
        exitButton.addActionListener(e -> {
            //exit
            System.exit(0);
        });
        //logout
        logoutButton.addActionListener(e -> {
            //logout
            setVisible(false);
            new login("LOGIN").setVisible(true);
        });
        //checkout
        checkOutButton.addActionListener(e -> {
            //Checkout
            setVisible(false);
            new CheckOut("CHECKOUT").setVisible(true);
        });
        genbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Book generator
                setVisible(false);
                new rand("BOOK GENERATOR").setVisible(true);
            }
        });
    }

    //main
    public static void main(String[] args) {
        //run frame
        JFrame frame = new DMenu("MENU");
        frame.setVisible(true);
    }
}
