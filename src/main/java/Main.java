import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        ManagerLogin managerLogin = new ManagerLogin(database);

        System.out.println("\nWelcome to the Membership Portal");

        while (true) {
            
            System.out.print("\nEnter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            boolean isAuthenticated = managerLogin.authenticate(username, password);

            if (isAuthenticated) {
                System.out.println(ANSI_GREEN + "Login successful!" + ANSI_RESET);
                displayMenu(scanner, database); // Display the menu after successful login
                break;
            } else {
                System.out.println("\nLogin failed. Invalid username or password.");
                System.out.print("Do you want to change your password? (yes/no):");
                String response = scanner.nextLine();

                if ("yes".equalsIgnoreCase(response)) {
                    if (handlePasswordChange(username, scanner, database)) {
                        System.out.println(ANSI_GREEN + "Password changed successfully.\n" + ANSI_RESET);
                      
                    } else {
                        System.out.println("Password change failed. Please try again.\n");
                    }
                }
            }
            
        }

        scanner.close();
    }

    private static void displayMenu(Scanner scanner, Database database) {
        MemberSignUp memberSignUp = new MemberSignUp(database);
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Search Member");
            System.out.println("2. Print out database");
            System.out.println("3. Renew Member");
            System.out.println("4. Sign up Member");
            System.out.println("5. Filter out Members");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.println("\nSearching Member...\n");
                    searchForMember(scanner, database);
                    break;
                case 2:
                    System.out.println("\nPrinting Database... \n");
                    printAllMembers(database);
                    break;
                case 3:
                    System.out.println("\nRenewing Member... \n");
                    renewMember(scanner, database);
                    break;
                case 4:
                    System.out.println("\nSigning up Member...\n");
                    memberSignUp.form();
                    break;
                case 5:
                    System.out.println("\nFiltering Members...\n");
                    DataFilters dataFilters = new DataFilters(database);
                    dataFilters.thirtyDaysFilter();
                    break;
                case 0:
                    System.out.println("\nExiting...\n");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        } while (choice != 0);
    }

    private static boolean handlePasswordChange(String username, Scanner scanner, Database database) {
        System.out.print("\nEnter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your authentication key: ");
        String authKey = scanner.nextLine();

        Manager manager = database.getManagerByUsername(username);
        if (manager != null && manager.getEmail().equals(email) && manager.getAuthenticationKey().equals(authKey)) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();
            manager.setPassword(newPassword);
            database.updateManagerCredentials(manager);
            return true;
        }

        return false;
    }

    private static void printAllMembers(Database database) {
    List<Member> members = database.getMembers();
    for (Member member : members) {
        System.out.println(member.toString() + "\n");
    }
}

private static void searchForMember(Scanner scanner, Database database) {
    System.out.print("Enter the name of the member to search: ");
    String name = scanner.nextLine();
    List<Member> foundMembers = database.searchMembersByName(name);

    if (foundMembers.isEmpty()) {
        System.out.println("No member found with the name: " + name);
    } else if (foundMembers.size() == 1) {
        Member exactMember = foundMembers.get(0);
        System.out.println("Member found: \n" + exactMember.toString());
        promptForRenewal(scanner, database, exactMember);
    } else {
        System.out.println("\nMultiple members found with the name: " + name);
        for (Member member : foundMembers) {
            System.out.println("\n" + member.toString());
        }
        Member exactMember = null;
        while (exactMember == null) {
            System.out.print("\nEnter the member ID to identify the exact member: ");
            String memberId = scanner.nextLine();
            exactMember = database.getMemberById(memberId);
            if (exactMember == null) {
                System.out.println("No member found with the ID: " + memberId + ". Please try again.");
            }
        }
        System.out.println("\nMember found: \n" + exactMember.toString());
        promptForRenewal(scanner, database, exactMember);
    }
}



private static void promptForRenewal(Scanner scanner, Database database, Member member) {
  System.out.print("\nWould you like to renew " + member.getMemberName() + "'s membership? (yes/no): ");
  String response = scanner.nextLine();
  if ("yes".equalsIgnoreCase(response)) {
      MembershipRenewal renewal = new MembershipRenewal(member);
      renewal.renewMembership(scanner); // Pass the scanner object
      database.updateMember(member); // Update member in database
      System.out.println("Membership renewed until: " + member.getExpirationDate() + "\n");
  }
}

private static void renewMember(Scanner scanner, Database database) {
    System.out.print("Enter the member ID to renew: ");
    String memberId = scanner.nextLine();
    Member member = database.getMemberById(memberId);

    if (member != null) {
        System.out.println("\nMember found: \n" + member.toString());
        MembershipRenewal renewal = new MembershipRenewal(member);
        renewal.renewMembership(scanner);
        database.updateMember(member); // Update member in database
        System.out.println("Membership renewed until: " + member.getExpirationDate());
    } else {
        System.out.println("No member found with the ID: " + memberId);
    }
}

}
