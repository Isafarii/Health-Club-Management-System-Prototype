public class Manager {
    private String name;
    private String username;
    private String password;
    private String email;
    private String authenticationKey;
    private String employeeId;

    // Constructor
    public Manager(String name, String username, String password, String email, String authenticationKey, String employeeId) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authenticationKey = authenticationKey;
        this.employeeId = employeeId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationKey() {
        return authenticationKey;
    }

    public void setAuthenticationKey(String authenticationKey) {
        this.authenticationKey = authenticationKey;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
