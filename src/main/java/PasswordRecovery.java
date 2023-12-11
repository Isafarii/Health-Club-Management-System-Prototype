import java.util.*;

public class PasswordRecovery
{
  Manager manager; 
  public PasswordRecovery(Manager manager)
  {
    this.manager = manager;
  }
  
  public boolean validateEmail(String managerEmail)
  {
    if(managerEmail.equals(manager.getEmail()))
    {
      return true;
    }
    return false; 
  }

  public void setNewPassword(String newPassword)
  {
    manager.setPassword(newPassword);
  }
}