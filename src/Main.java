import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        int choice=0;

        do {
            manager.displayMenu();
            System.out.println("***************** ");
            System.out.println("***************** ");
            System.out.print("Enter your choice: ");


            try {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1: // Add a new contact
                        addNewContact(scanner, manager);
                        break;

                    case 2: // Update an existing contact
                        updateExistingContact(scanner, manager);
                        break;

                    case 3: // Remove a contact
                        removeContact(scanner, manager);
                        break;

                    case 4: // Block a contact
                        blockContact(scanner, manager);
                        break;

                    case 5: // Unblock a contact
                        unblockContact(scanner, manager);
                        break;

                    case 6: // Change contact state
                        changeContactState(scanner, manager);
                        break;

                    case 7: // View contact details
                        viewContactDetails(scanner, manager);
                        break;

                    case 8: // Search for contacts
                        searchContacts(scanner, manager);
                        break;

                    case 9: // Import contacts
                        importContacts(manager);
                        break;

                    case 10: // Export contacts
                        exportContacts(manager);
                        break;

                    case 11: // Exit
                        System.out.println("You Are Exiting the system. Thank You!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (ContactNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic error occurred: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array index out of bounds: " + e.getMessage());
            } catch (SecurityException e) {
                System.out.println("Security exception: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } while (choice != 11);

        scanner.close();
    }

    private static void addNewContact(Scanner scanner, ContactManager manager) {
        String name = getInput("Enter name: ", scanner, false);
        String email = getInput("Enter email: ", scanner, false, true);
        String phoneNumber = getInput("Enter phone number: ", scanner, false, false, true);
        String state = getInput("Enter state (active/inactive/blocked): ", scanner, false);
        manager.addContact(new Contact(name, email, phoneNumber, state));
        System.out.println("Contact added successfully.");
    }

    private static void updateExistingContact(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact to update: ", scanner, false);
        String email = getInput("Enter new email: ", scanner, false, true);
        String phoneNumber = getInput("Enter new phone number: ", scanner, false, false, true);
        manager.updateContact(name, email, phoneNumber);
        System.out.println("Contact updated successfully.");
    }

    private static void removeContact(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact to remove: ", scanner, false);
        manager.removeContact(name);
        System.out.println("Contact removed successfully.");
    }

    private static void blockContact(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact to block: ", scanner, false);
        manager.blockContact(name);
        System.out.println("Contact blocked successfully.");
    }

    private static void unblockContact(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact to unblock: ", scanner, false);
        manager.unblockContact(name);
        System.out.println("Contact unblocked successfully.");
    }

    private static void changeContactState(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact: ", scanner, false);
        String state = getInput("Enter new state (active/inactive/blocked): ", scanner, false);
        manager.changeContactState(name, state);
        System.out.println("Contact state changed successfully.");
    }

    private static void viewContactDetails(Scanner scanner, ContactManager manager) throws ContactNotFoundException {
        String name = getInput("Enter name of the contact: ", scanner, false);
        Contact contact = manager.viewContact(name);
        System.out.println(contact);
    }

    private static void searchContacts(Scanner scanner, ContactManager manager) {
        String query = getInput("Enter search query: ", scanner, false);
        List<Contact> foundContacts = manager.searchContacts(query);
        foundContacts.forEach(System.out::println);
    }

    private static void importContacts(ContactManager manager) {
        try {
            // Simulating import with a static list of contacts
            List<Contact> newContacts = Arrays.asList(
                    new Contact("Alice Brown", "alice.brown@example.com", "2222222222", "active"),
                    new Contact("Bob White", "bob.white@example.com", "3333333333", "active")
            );
            manager.importContacts(newContacts);
            System.out.println("Contacts imported successfully.");
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println("Error importing contacts: " + e.getMessage());
        }
    }

    private static void exportContacts(ContactManager manager) {
        try {
            List<Contact> exportedContacts = manager.exportContacts();
            System.out.println("Exported contacts:");
            exportedContacts.forEach(System.out::println);
        } catch (SecurityException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
        }
    }

    private static String getInput(String prompt, Scanner scanner, boolean allowEmpty) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!allowEmpty && (input == null || input.trim().isEmpty())) {
                System.out.println("This field cannot be empty. Please enter a valid input.");
            }
        } while (!allowEmpty && (input == null || input.trim().isEmpty()));
        return input;
    }

    private static String getInput(String prompt, Scanner scanner, boolean allowEmpty, boolean isEmail) {
        String input;
        do {
            input = getInput(prompt, scanner, allowEmpty);
            if (isEmail && !isValidEmail(input)) {
                System.out.println("Invalid email format. Please enter a valid email.");
                input = null;
            }
        } while (input == null);
        return input;
    }

    private static String getInput(String prompt, Scanner scanner, boolean allowEmpty, boolean isEmail, boolean isPhoneNumber) {
        String input;
        do {
            input = getInput(prompt, scanner, allowEmpty, isEmail);
            if (isPhoneNumber && !isValidPhoneNumber(input)) {
                System.out.println("Invalid phone number format. Please enter a valid phone number.");
                input = null;
            }
        } while (input == null);
        return input;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "\\d{10}";
        return phoneNumber.matches(phoneRegex);
    }
}
