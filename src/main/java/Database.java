import java.io.*;
import java.util.*;

public class Database {
    private List<Manager> managers;
    private List<Member> members;

    public Database() {
      this.managers = new ArrayList<>();
      this.members = new ArrayList<>();
      readManagerCreds();
      readMemberDatabase();
  }

    private void readMemberDatabase() {
    try (BufferedReader br = new BufferedReader(new FileReader("MemberDatabase.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 8) {
                int memberAge = Integer.parseInt(parts[1]); // Assuming the age is the second field
                Member member = new Member(parts[0], memberAge, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
                members.add(member);
            }
        }//System.out.println("Total managers loaded: " + managers.size());
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }
}


    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member newMember) {
        members.add(newMember);
        writeMemberDatabase(); // Method to write the updated member list back to the file
    }



    // Method to get the list of managers
    public List<Manager> getManagers() {
        return managers;
    }

    private void readManagerCreds() {
        try (BufferedReader br = new BufferedReader(new FileReader("ManagerCreds.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
              //System.out.println("Reading line: " + line);
                String[] parts = line.split(",");
                // Assuming the file format is: name,username,password,email,authenticationKey,employeeId
                if (parts.length == 6) {
                    Manager manager = new Manager(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    managers.add(manager);
                }
            }
            //System.out.println("Total managers loaded: " + managers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMemberDatabase() {
  try (BufferedWriter bw = new BufferedWriter(new FileWriter("MemberDatabase.txt"))) {
      for (Member member : members) {
          String line = member.getMemberName() + "," +
                        String.valueOf(member.getMemberAge()) + "," +
                        member.getMemberId() + "," +
                        member.getPaymentMethod() + "," +
                        member.getLastSignIn() + "," +
                        member.getQrCodeId() + "," +
                        member.getMemberEmail() + "," +
                        member.getExpirationDate();
          bw.write(line);
          bw.newLine();
      }
  } catch (IOException e) {
      e.printStackTrace();
  }
}

public void updateMember(Member updatedMember) {
  for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getMemberId().equals(updatedMember.getMemberId())) {
          members.set(i, updatedMember); // Update the member's information
          break;
      }
  }
  writeMemberDatabase(); // Write the updated list back to the file
}

public List<Member> searchMembersByName(String name) {
    List<Member> matchingMembers = new ArrayList<>();
    for (Member member : members) {
        if (member.getMemberName().equalsIgnoreCase(name)) {
            matchingMembers.add(member);
        }
    }
    return matchingMembers; // Returns a list of members with the matching name
}


public Member getMemberById(String memberId) {
    for (Member member : members) {
        if (member.getMemberId().equals(memberId)) {
            return member;
        }
    }
    return null; // No member found with the provided ID
}

    private void writeManagerCreds() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("ManagerCreds.txt"))) {
          for (Manager manager : managers) {
              String line = manager.getName() + "," + manager.getUsername() + "," + manager.getPassword() + ","
                            + manager.getEmail() + "," + manager.getAuthenticationKey() + "," + manager.getEmployeeId();
              bw.write(line);
              bw.newLine();
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

    public void updateManagerCredentials(Manager updatedManager) {
      for (int i = 0; i < managers.size(); i++) {
          if (managers.get(i).getUsername().equals(updatedManager.getUsername())) {
              managers.set(i, updatedManager); // Update the manager in the list
              break;
          }
      }

      // Write the updated list of managers back to the file
      writeManagerCreds();
  }

    public Manager getManagerByUsername(String username) {
      for (Manager manager : this.managers) {
          if (manager.getUsername().equals(username)) {
              return manager;
          }
      }
      return null; // Return null if no matching manager is found
  }



    // You can add more methods here if needed, for example, to add, update, or delete managers
}
