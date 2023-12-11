import java.util.*;
import java.time.*;

public class MembershipRenewal {
    Member member;

    public MembershipRenewal(Member member) {
        this.member = member;
    }

    public void renewMembership(Scanner scanner) {
      int newMonths = 0;
      while (newMonths < 6 || newMonths > 36) {
          System.out.println("\nEnter the number of months you would like to renew your subscription for (6 to 36 months): ");
          if (scanner.hasNextInt()) {
              newMonths = scanner.nextInt();
              if (newMonths < 6 || newMonths > 36) {
                  System.out.println("Invalid input. Please enter a number between 6 and 36.");
              }
          } else {
              System.out.println("Invalid input. Please enter a valid integer number.");
              scanner.next(); // Consume the invalid input
          }
      }
      LocalDate oldDate = LocalDate.parse(member.getExpirationDate());
      LocalDate newExpirationDate = oldDate.plusMonths(newMonths);
      member.setExpirationDate(String.valueOf(newExpirationDate));
  }
  
}
