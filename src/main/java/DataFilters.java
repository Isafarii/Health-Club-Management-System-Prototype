import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class DataFilters {

    Database database;

    public DataFilters(Database database) {
        this.database = database;
    }

    public List<Member> ThirtyDaysFilter() {
        List<Member> memberList = new ArrayList<>();
        List<Member> entries = database.getMembers(); // Change the type to List<Member>
        LocalDate today = LocalDate.now();
        for (Member entry : entries) { // Change the type to Member
            LocalDate lastLocalDateLogin = LocalDate.parse(entry.getLastSignIn());

            long days = ChronoUnit.DAYS.between(lastLocalDateLogin, today);

            if (days >= 30) {
                memberList.add(entry);
            }
        }
        return memberList;
    }
}
