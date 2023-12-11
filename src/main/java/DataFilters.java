import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class DataFilters {
    Database database;
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_YELLOW = "\u001B[33m";

    public DataFilters(Database database) {
        this.database = database;
    }

    public List<Member> thirtyDaysFilter() {
        List<Member> filteredMembers = new ArrayList<>();
        List<Member> members = database.getMembers(); // Assuming getMembers returns List<Member>
        LocalDate today = LocalDate.now();

        for (Member member : members) {
            LocalDate lastSignInDate = LocalDate.parse(member.getLastSignIn());
            LocalDate expirationDate = LocalDate.parse(member.getExpirationDate());
            long daysSinceLastSignIn = ChronoUnit.DAYS.between(lastSignInDate, today);
            long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);

            if (daysSinceLastSignIn >= 30 || daysUntilExpiration < 30) {
                String status = "";
                if (daysSinceLastSignIn >= 30) status += " " + ANSI_YELLOW + "[Not Visited for 30+ Days]" + ANSI_RESET;
                if (daysUntilExpiration < 30) status += " " + ANSI_RED + "[Membership Nearing End]" + ANSI_RESET;
                System.out.println(member.toString() + status + "\n");
                filteredMembers.add(member);
            }
        }
        return filteredMembers;
    }
}
