package register;
public class Register {

    private String username;
    private long phoneNumber;
    private String emailId;

    public Register(String username, long phoneNumber,String emailId) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.emailId=emailId;
    }
    public String getUserName()
    {
        return username;
    }
    public long getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmailId()
    {
        return emailId;
    }
}
