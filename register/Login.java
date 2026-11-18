package register;
import java.util.*;
public class Login extends Register {
    static Scanner sc=new Scanner(System.in);
    public Login(String username, long phoneNumber,String emailId) {
        super(username, phoneNumber,emailId);
    }

     public static boolean validatePhoneNumber(long phoneNum)
	{
        try
			{
				String phone = String.valueOf(phoneNum);

				if(phone.length() != 10)
				{
					System.out.println("Phone number must contain exactly 10 digits");
                    return false;
				}
				else if(!(phone.startsWith("6") ||
						phone.startsWith("7") ||
						phone.startsWith("8") ||
						phone.startsWith("9")))
				{
					System.out.println("Phone number must start with 6, 7, 8 or 9");
                    return false;
				}
				else
				{
					return true;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Phone number must contain only digits");
				sc.nextLine();
			}
        return false;
	}
    public static boolean validateEmail(String email)
    {
        if(email.isEmpty())
        {
            System.out.println("Email cannot be empty");
            return false;
        }

        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');

        if(at == -1)
        {
            System.out.println("Email must contain @");
            return false;
        }

        if(dot == -1)
        {
            System.out.println("Email must contain .");
            return false;
        }

        if(at == 0)
        {
            System.out.println("Email cannot start with @");
            return false;
        }

        if(dot < at)
        {
            System.out.println("Invalid email format");
            return false;
        }

        if(dot == email.length() - 1)
        {
            System.out.println("Email cannot end with .");
            return false;
        }

        if(at + 1 == dot)
        {
            System.out.println("Email must contain domain name");
            return false;
        }

        return true;
    }
    public boolean validateOTP(int enteredOTP, int generatedOTP) {

        return enteredOTP == generatedOTP;
    }
}

