import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    // Add a new contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Update existing contact
    public void updateContact(String name, String email, String phoneNumber) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setEmail(email);
                contact.setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    // Remove a contact
    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    // Block a contact
    public void blockContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setState("blocked");
                break;
            }
        }
    }

    // Unblock a contact
    public void unblockContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setState("active");
                break;
            }
        }
    }

    // Change contact state
    public void changeContactState(String name, String state) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contact.setState(state);
                break;
            }
        }
    }

    // View contact details
    public Contact viewContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    // Search for contacts
    public List<Contact> searchContacts(String query) {
        return contacts.stream()
                .filter(contact -> contact.getName().contains(query) || contact.getEmail().contains(query))
                .collect(Collectors.toList());
    }

    // Import contacts from external source (e.g., List of contacts)
    public void importContacts(List<Contact> externalContacts) {
        contacts.addAll(externalContacts);
    }

    // Export contacts to external source (e.g., List of contacts)
    public List<Contact> exportContacts() {
        return new ArrayList<>(contacts);
    }

    // Display menu
    public void displayMenu() {
        System.out.println("Contact Management System");
        System.out.println("1. Add a new contact");
        System.out.println("2. Update an existing contact");
        System.out.println("3. Remove a contact");
        System.out.println("4. Block a contact");
        System.out.println("5. Unblock a contact");
        System.out.println("6. Change contact state");
        System.out.println("7. View contact details");
        System.out.println("8. Search for contacts");
        System.out.println("9. Import contacts");
        System.out.println("10. Export contacts");
        System.out.println("11. Exit");
    }
}