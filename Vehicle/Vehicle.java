package Vehicle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import ui.*;
public class Vehicle
{
    private String vehicleNumber;
    private String vehicleType;
    private int batteryPercentage;
    private String requiredChargerType;
    private LocalDateTime enteredDateTime;
    private double batteryCapacity;

    public Vehicle(String vehicleNumber, String vehicleType, int batteryPercentage, String requiredChargerType,double batteryCapacity)
    {

        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.batteryPercentage = batteryPercentage;
        this.requiredChargerType = requiredChargerType;
        enteredDateTime = LocalDateTime.now();
        this.batteryCapacity=batteryCapacity;
    }
    public String getVehicleNumber()
    {
        return vehicleNumber;
    }
    public String getVehicleType()
    {
        return vehicleType;
    }
    public int getBatteryPercentage()
    {
        return batteryPercentage;
    }
    public String getRequiredChargerType()
    {
        return requiredChargerType;
    }
     public LocalDateTime getEnteredDateTime()
    {
        return enteredDateTime;
    }
    public double getBatteryCapacity()
    {
        return batteryCapacity;
    }
     public static boolean validateVehicleNumber(String vehicleNumber)
    {
        vehicleNumber = vehicleNumber.replace(" ", "");
        vehicleNumber = vehicleNumber.toUpperCase();

        int i = 0;

        // First 2 characters → letters
        for(int j = 0; j < 2; j++)
        {
            if(i >= vehicleNumber.length() ||
            !Character.isLetter(vehicleNumber.charAt(i)))
            {
                return false;
            }

            i++;
        }

        // Next 1 or 2 characters → digits
        if(i >= vehicleNumber.length() ||
        !Character.isDigit(vehicleNumber.charAt(i)))
        {
            return false;
        }

        i++;

        // Check for second digit
        if(i < vehicleNumber.length() &&
        Character.isDigit(vehicleNumber.charAt(i)))
        {
            i++;
        }

        // Next 1 to 3 characters → letters
        int letters = 0;

        while(i < vehicleNumber.length() &&
            Character.isLetter(vehicleNumber.charAt(i)))
        {
            letters++;
            i++;
        }

        if(letters < 1 || letters > 3)
        {
            return false;
        }

        // Last 4 characters → digits
        int digits = 0;

        while(i < vehicleNumber.length() &&
            Character.isDigit(vehicleNumber.charAt(i)))
        {
            digits++;
            i++;
        }

        if(digits != 4)
        {
            return false;
        }
        return i == vehicleNumber.length();
    }
    public static boolean validateBattery(int batteryPercentage)
    {
            if(batteryPercentage==0)
            {
                ConsoleUI.error("Battery percentage cannot be empty");
                return false;
            }
            try
            {
                if(batteryPercentage>=0 && batteryPercentage<=100)
                {
                    ConsoleUI.success("Battery is valid");
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch(NumberFormatException e)
            {
                    ConsoleUI.error("Invalid input");
            }
        return false;
    }
    public static String getVehicleType(Scanner sc)
    {
        int attempts=0;
        boolean found=true;
        String vehicleType="";
        while(found)
        {
            
            System.out.println(ConsoleUI.CYAN+"1.Car"+ConsoleUI.RESET);
            System.out.println(ConsoleUI.CYAN+"2.Bike"+ConsoleUI.RESET);
            System.out.println(ConsoleUI.CYAN+"Select your EV Vehicle Type"+ConsoleUI.RESET);
            int vehicleChoice=sc.nextInt();
            switch(vehicleChoice)
            {
                case 1:
                    if(attempts!=0)
                    {
                        attempts=0;
                    }
                    vehicleType="CAR";
                    found=false;
                    break;
                case 2:
                    if(attempts!=0)
                    {
                        attempts=0; 
                    }
                    vehicleType="BIKE";
                    found=false;
                    break;
                default:
                    ConsoleUI.error("Invalid choice");
                    attempts++;
            }
            if(attempts>3)
            {
                ConsoleUI.error("Maximum attempts");
                found=false;
            }
        }
        return vehicleType;
    }
    public static String getChargerType(Scanner sc)
    {
        boolean found=true;
        String choice="";
        while(found)
        {
            int attempts=0;
            System.out.println(ConsoleUI.PURPLE+"Select your battery type"+ConsoleUI.RESET);
            System.out.println("1.NORMAL");
            System.out.println("2.FAST");
            System.out.print("Enter your choice: ");
            int chargerChoice=sc.nextInt();
            switch(chargerChoice)
            {
                case 1:
                    choice="NORMAL";
                    found=false;
                    break;
                case 2:
                    choice="FAST";
                    found=false;
                    break;
                default:
                    System.out.println("Invalid choice: ");
                    attempts++;
                    if(attempts==3)
                    {
                        System.out.println("Maximum attempts reached");
                        found=false;
                    }     
            }
        }
        return choice;
    }
}
