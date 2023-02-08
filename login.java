//The purpose of this page is login in to the inventory system. The opening.

//All the imports
import javax.swing.*;

public class login extends JFrame {
    //All variables and keys
    private JPasswordField passwordField1;
    //button for passwordfield
    private JButton loginButton;
    //button to login
    private JButton exitButton;
    //exit the application
    private JLabel autLabel;
    //Label saying password
    private JLabel entLabel;
    //label as the header of the page. Saying KrisBase
    private JPanel rootPanel;
    //root panel of page
    private JLabel anLabel;
    //lets you know if you put the password in correctly or incorrectly
    private final String key = "Kriscake.7";
    //password

    //login page with all it functions
    public login(String title){
        //setting the frame for the gui
        super(title);
        //sets title on top of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exit when the x is pressed
        this.setContentPane(rootPanel);
        //frame is set to the panel created
        this.setSize(840,720);
        //size of frame
        this.setLocationRelativeTo(null);
        //in the middle of screen
        //login button is pressed. same as passwordfield1 function
        loginButton.addActionListener(e -> {
            //getting password input
            String result1 = passwordField1.getText();
            //checking if equals key
            if(result1.equals(key)){
                anLabel.setText("Good Job!");
                //on to next frame if correct
                setVisible(false);
                new DMenu("MENU").setVisible(true);
            }
            else {
                //display wrong to try again if key does not equal input
                anLabel.setText("Wrong! Try Again");
                JOptionPane.showMessageDialog(null, "Wrong! Try again!");
            }
        });
        //exit
        exitButton.addActionListener(e -> {
            //close frame
            System.exit(0);
        });
        //passwordField1 is pressed. same ad login button
        passwordField1.addActionListener(e -> {
            //getting password input
            String result1 = passwordField1.getText();
            //checking if equals key
            if(result1.equals(key)){
                anLabel.setText("Good Job!");
                //on to next frame if correct
                setVisible(false);
                new DMenu("MENU").setVisible(true);

            }
            else {
                //display wrong to try again if key does not equal input
                anLabel.setText("Wrong! Try Again");
                JOptionPane.showMessageDialog(null, "Wrong! Try again!");
            }
        });
    }

    //main
    public static void main(String[] args) {
        //running the frame
        JFrame Frame = new login("LOGIN");
        Frame.setVisible(true);
    }
}
