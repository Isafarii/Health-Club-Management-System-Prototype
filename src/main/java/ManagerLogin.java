public class ManagerLogin {
    private Database database;

    public ManagerLogin(Database database) {
        this.database = database;
    }

    public boolean authenticate(String username, String password) {
        for (Manager manager : database.getManagers()) {
            if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                return true; // Authentication successful
            }
        }
        return false; // Authentication failed
    }
}
