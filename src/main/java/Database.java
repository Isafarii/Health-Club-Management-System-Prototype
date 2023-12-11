import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

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
        String filePath = "C:/Users/Fireb/IdeaProjects/SEHealthClub/src/main/resources/50-sample-contacts-list-excel.xlsx";

        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming your data is in the first sheet

            // Skip the header row
            Iterator<Row> iterator = sheet.iterator();
            if (iterator.hasNext()) {
                iterator.next(); // Skip the header row
            }

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String[] parts = new String[8];
                for (int i = 0; i < 8; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    parts[i] = cell.toString();
                }

                // Assuming parts[0] is member_id, parts[1] is first_name, etc.
                String memberId = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String phone1 = parts[3];
                String email = parts[4];
                String age = parts[5];
                String membershipLength = parts[6];
                String membershipType = parts[7];

                // Handling age as a double, since it may contain a decimal point
                double ageValue = Double.parseDouble(age);
                int ageInt = (int) ageValue;

                // Now you can create a Member object and do whatever you need with the data
                Member member = new Member(firstName + " " + lastName, ageInt, memberId, membershipType, "...", "...", email, "...");

                // Add the member to your list or perform other actions
                members.add(member);
            }
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
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Fireb\\IdeaProjects\\SEHealthClub\\src\\main\\resources\\ManagerCreds.txt"))) {
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
    try (OutputStream outputStream = new FileOutputStream("C:/Users/Fireb/IdeaProjects/SEHealthClub/src/main/resources/50-sample-contacts-list-excel.xlsx")) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowNum = 0;
        for (Member member : members) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(member.getMemberName());
            row.createCell(1).setCellValue(member.getMemberAge());
            row.createCell(2).setCellValue(member.getMemberId());
            row.createCell(3).setCellValue(member.getPaymentMethod());
            row.createCell(4).setCellValue(member.getLastSignIn());
            row.createCell(5).setCellValue(member.getQrCodeId());
            row.createCell(6).setCellValue(member.getMemberEmail());
            row.createCell(7).setCellValue(member.getExpirationDate());
        }

        workbook.write(outputStream);
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

public Member searchMemberByName(String name) {
  for (Member member : members) {
      if (member.getMemberName().equalsIgnoreCase(name)) {
          return member;
      }
  }
  return null; // No member found
}

    private void writeManagerCreds() {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Fireb\\IdeaProjects\\SEHealthClub\\src\\main\\resources\\ManagerCreds.txt"))) {
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
