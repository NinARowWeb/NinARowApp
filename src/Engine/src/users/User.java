package users;

public class User {
    private String m_Name;
    private boolean m_Computer;
    public User(String i_Name, boolean i_Computer){
        m_Computer = i_Computer;
        m_Name = i_Name;
    }

    public String getName() {
        return m_Name;
    }

    public boolean isComputer() {
        return m_Computer;
    }
}
