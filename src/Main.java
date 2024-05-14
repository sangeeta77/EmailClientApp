//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        {
            Scanner scanner = new Scanner(System.in);
            ContactManager manager = new ContactManager();
            int choice;

            do {
                manager.displayMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1: // Add a new contact
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter state (active/inactive/blocked): ");
                        String state = scanner.nextLine();
                        manager.addContact(new Contact(name, email, phoneNumber, state));
                        break;

                    case 2: // Update an existing contact
                        System.out.print("Enter name of the contact to update: ");
                        name = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        email = scanner.nextLine();
                        System.out.print("Enter new phone number: ");
                        phoneNumber = scanner.nextLine();
                        manager.updateContact(name, email, phoneNumber);
                        break;

                    case 3: // Remove a contact
                        System.out.print("Enter name of the contact to remove: ");
                        name = scanner.nextLine();
                        manager.removeContact(name);
                        break;

                    case 4: // Block a contact
                        System.out.print("Enter name of the contact to block: ");
                        name = scanner.nextLine();
                        manager.blockContact(name);
                        break;

                    case 5: // Unblock a contact
                        System.out.print("Enter name of the contact to unblock: ");
                        name = scanner.nextLine();
                        manager.unblockContact(name);
                        break;

                    case 6: // Change contact state
                        System.out.print("Enter name of the contact: ");
                        name = scanner.nextLine();
                        System.out.print("Enter new state (active/inactive/blocked): ");
                        state = scanner.nextLine();
                        manager.changeContactState(name, state);
                        break;

                    case 7: // View contact details
                        System.out.print("Enter name of the contact: ");
                        name = scanner.nextLine();
                        Contact contact = manager.viewContact(name);
                        System.out.println(contact != null ? contact : "Contact not found.");
                        break;

                    case 8: // Search for contacts
                        System.out.print("Enter search query: ");
                        String query = scanner.nextLine();
                        List<Contact> foundContacts = manager.searchContacts(query);
                        foundContacts.forEach(System.out::println);
                        break;

                    case 9: // Import contacts
                        // Simulating import with a static list of contacts
                        List<Contact> newContacts = Arrays.asList(
                                new Contact("Alice Brown", "alice.brown@example.com", "2222222222", "active"),
                                new Contact("Bob White", "bob.white@example.com", "3333333333", "active")
                        );
                        manager.importContacts(newContacts);
                        System.out.println("Contacts imported successfully.");
                        break;

                    case 10: // Export contacts
                        List<Contact> exportedContacts = manager.exportContacts();
                        System.out.println("Exported contacts:");
                        exportedContacts.forEach(System.out::println);
                        break;

                    case 11: // Exit
                        System.out.println("Exiting the system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 11);

            scanner.close();
        }
    }
}
