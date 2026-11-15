/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registerpart3final;

/**
 *
 * @author letha
 */
import java.util.Scanner;

public class RegisterPart3Final {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Creating an object for the Login class
        Login account = new Login();

        // Registration
        System.out.println("Register a new account");

        System.out.print("Enter your First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your Username (must contain an underscore and be 5 or less in characters): ");
        String username = input.nextLine();

        System.out.print("Enter your Cell Phone Number (should start with +27 and have 9 digits after it): ");
        String cellPhone = input.nextLine();

        System.out.print("Enter your Password (must include uppercase, lowercase, special character, and a number): ");
        String password = input.nextLine();

        String registrationMessage =
                account.registerUser(username, password, cellPhone, firstName, lastName);

        System.out.println(registrationMessage);

        // Continue only if registration worked
        if (registrationMessage.equals("Account successfully registered")) {

            System.out.println("\nLogin to your account");

            System.out.print("Enter your Username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your Password: ");
            String loginPassword = input.nextLine();

            boolean status = account.loginUser(loginUsername, loginPassword);

            System.out.println(account.returnLoginStatus());

            if (status) {

                boolean running = true;

                while (running) {

                    System.out.println("\nWELCOME TO QUICKCHAT");
                    System.out.println("Choose one option below");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");

                    System.out.print("Select Option: ");

                    int select = input.nextInt();
                    input.nextLine();

                    switch (select) {

                        case 1:

                            System.out.print("How many messages do you want to send? ");

                            int total = input.nextInt();
                            input.nextLine();

                            for (int i = 1; i <= total; i++) {

                                System.out.println("\nMESSAGE " + i);

                                System.out.print("Enter recipient number: ");
                                String recipient = input.nextLine();

                                System.out.print("Enter Message: ");
                                String messageText = input.nextLine();

                                if (messageText.length() > 250) {
                                    System.out.println("Enter a message with less than 250 characters.");
                                    i--;
                                    continue;
                                }

                                Message msg = new Message(i, recipient, messageText);

                                if (msg.checkMessageID()) {
                                    System.out.println("Message ID created successfully");
                                }

                                System.out.println(msg.checkRecipientCell());
                                System.out.println("Message Hash: " + msg.createMessageHash());
                                System.out.println(msg.sentMessage());
                                System.out.println(msg.printMessages());
                            }

                            System.out.println(
                                    "\nTotal number of messages sent: "
                                    + Message.returnTotalMessages());

                            break;

                        case 2:

                            boolean showOptions = true;

                            while (showOptions) {

                                System.out.println("\nSTORED MESSAGE OPTIONS");
                                System.out.println("a) Display Sender and Recipient of all stored messages");
                                System.out.println("b) Display the longest stored message");
                                System.out.println("c) Search for a message ID and display the corresponding recipient and message");
                                System.out.println("d) Search for all messages stored for a particular recipient");
                                System.out.println("e) Delete a message using its message hash");
                                System.out.println("f) Display a report that lists full details of all stored messages");
                                System.out.println("g) Return to Main Menu");

                                System.out.print("Select option: ");

                                String choiceSelection =
                                        input.nextLine().toLowerCase();

                                switch (choiceSelection) {

                                    case "a":
                                        MessageReport.displaySenderRecipient();
                                        break;

                                    case "b":
                                        MessageReport.displayLongestMessage();
                                        break;

                                    case "c":

                                        System.out.print("Enter Message ID: ");
                                        String id = input.nextLine();

                                        MessageReport.searchMessageID(id);
                                        break;

                                    case "d":

                                        System.out.print("Enter Recipient: ");
                                        String recipientSearch =
                                                input.nextLine();

                                        MessageReport.searchRecipient(
                                                recipientSearch);

                                        break;

                                    case "e":

                                        System.out.print("Enter Message Hash: ");
                                        String hash = input.nextLine();

                                        MessageReport.deleteMessage(hash);

                                        break;

                                    case "f":

                                        MessageReport.displayReport();
                                        break;

                                    case "g":

                                        showOptions = false;
                                        break;

                                    default:

                                        System.out.println("Invalid selection.");
                                }
                            }

                            break;

                        case 3:

                            System.out.println("Goodbye!");
                            running = false;
                            break;

                        default:

                            System.out.println(
                                    "Invalid selection. Please choose 1, 2, or 3.");
                    }
                }

            } else {

                System.out.println(
                        "Username or Password incorrect, please try again.");
            }
        }

        input.close();
    }
}