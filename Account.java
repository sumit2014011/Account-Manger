
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

class Account {

    private int userNumber = 1;
    private final User user[] = new User[10];
    Scanner scanner = new Scanner(System.in);

    public void CreateUser() {
        if (userNumber >= user.length) {
            System.out.println("Limit excide");
            return;
        }
        user[userNumber] = new User();
        System.out.println("Enter the Name");
        user[userNumber].setName(scanner.nextLine().trim());
        while (true) {
            System.out.println("Enter the Mobile Number");
            String mobile = scanner.nextLine().trim();
            try {
                String mobileRegex = "^\\+?[0-9]{10,15}$";
                if (mobile == null || !Pattern.matches(mobileRegex, mobile)) {
                    throw new Exception("Invalid mobile number: " + mobile);
                }
                user[userNumber].setMob_NUM(mobile);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.println("Enter the Email");
            String Email = scanner.nextLine().trim();
            try {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);
                if (Email == null || !pattern.matcher(Email).matches()) {
                    throw new Exception("Invalid email address: " + Email);
                }
                user[userNumber].setEmail(Email);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Enter the Balance");
        while (true) {
            try {
                user[userNumber].setBalance(scanner.nextLong());
                scanner.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid balance.");
                scanner.nextLine();
            }
        }
        user[userNumber].setUser_id(generateUniqueString());
        user[userNumber].setCardNumber(generateCardString());
        System.out.println("User created successfully!");
        System.out.println("\n\t\t\tUser Id = " + user[userNumber].getUser_id());
        userNumber++;
    }

    public void Info() {
        if (userNumber <= 1) {
            System.out.println("There are no User !");
            return;
        }
        boolean found = false;
        System.out.println("Enter the UserId:");
        String userId = scanner.nextLine().trim();
        for (int i = 1; i < userNumber; i++) {
            if (user[i] != null && userId.equalsIgnoreCase(user[i].getUser_id())) {
                System.out.println("User Found");
                System.out.println("\t\t\tMobile No. = " + user[i].getMob_NUM());
                System.out.println("\t\t\tUser ID = " + user[i].getUser_id());
                System.out.println("\t\t\tEmail = " + user[i].getEmail());
                System.out.println("\t\t\tCard Number = " + user[i].getCardNumber());
                System.out.println("\n\t\t\tBalance = " + user[i].getBalance());
                System.out.println("\n\t\t\tUser Pin = " + user[i].getPin());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("User Not in Database");
        }
    }

    public void depositFunds() {
        if (userNumber <= 1) {
            System.out.println("No users available for deposit.");
            return;
        }

        System.out.print("Enter the Name\n-> ");
        String name = scanner.nextLine().trim();
        System.out.print("\nEnter the User ID\n-> ");
        String userId = scanner.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < userNumber; i++) {
            if (user[i] != null && name.equalsIgnoreCase(user[i].getName()) && userId.equalsIgnoreCase(user[i].getUser_id())) {
                System.out.println("User Name: " + user[i].getName());
                System.out.println("Mobile Number: " + user[i].getMob_NUM());
                System.out.println("Confirm deposit (Y or N):");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("y")) {
                    while (true) {
                        try {
                            System.out.println("Enter the amount to deposit:");
                            double amount = scanner.nextDouble();
                            scanner.nextLine(); // Clear the newline

                            if (amount > 0 && (user[i].getBalance() + amount) < Long.MAX_VALUE) {
                                user[i].setBalance((long) (user[i].getBalance() + amount));
                                System.out.println("Deposit Successful!");
                                System.out.println("Updated Balance: " + user[i].getBalance());
                                found = true;
                                break;
                            } else {
                                System.out.println("Invalid amount. Must be greater than zero and not exceed the maximum allowed balance.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid numeric amount.");
                            scanner.nextLine(); // Clear the invalid input
                        }
                    }
                } else if (confirmation.equalsIgnoreCase("n")) {
                    System.out.println("Deposit canceled.");
                    found = true;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }

        if (!found) {
            System.out.println("User not found.");
        }
    }

    public void withdrawalFunds() {
        if (userNumber <= 1) {
            System.out.println("There are no User !");
            return;
        }
        System.out.println("Enter the Name and User ID:\n->");
        String name = scanner.nextLine().trim();
        System.out.println("->");
        String userId = scanner.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < userNumber; i++) {
            if (user[i] != null && user[i].getUser_id().equalsIgnoreCase(userId) && user[i].getName().equalsIgnoreCase(name)) {
                System.out.println("User Name: " + user[i].getName());
                System.out.println("Mobile Number: " + user[i].getMob_NUM());
                System.out.println("Confirm withdrawal (Y or N):");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("y")) {
                    while (true) {
                        try {
                            System.out.println("Enter the amount to withdraw:");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();
                            if (amount > 0 && amount < user[i].getBalance()) {
                                user[i].setBalance((long) (user[i].getBalance() - amount));
                                System.out.println("Withdrawal Successful!");
                                System.out.println("Updated Balance: " + user[i].getBalance());
                                found = true;
                                break;
                            } else {
                                System.out.println("Invalid amount or insufficient balance.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid amount. Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                }
            }

        }
        if (!found) {
            System.out.println("User not found");
        }
    }

    public static String generateUniqueString() {
        long uniqueLong = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        return String.valueOf(uniqueLong);
    }

    public static String generateCardString() {
        long uniqueLong = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE) / 2;
        return String.valueOf(uniqueLong);
    }

    public void checkPin() {
        if (userNumber <= 1) {
            System.out.println("There are no User !");
            return;
        }
        System.out.println("Enter the user_id ");
        String userid = scanner.nextLine().trim();
        for (int i = 1; i < userNumber; i++) {
            if (userid.endsWith(user[i].getUser_id()) && user[i].getPin() == null) {
                CreatePin();
            } else {
                SetPin();
            }
        }
    }

    public void SetPin() {
        System.out.println("enter Card Number");
        String Card_Number = scanner.nextLine().trim();
        boolean temp = false;
        for (int i = 1; i < userNumber; i++) {
            if (Card_Number.equalsIgnoreCase(user[i].getCardNumber())) {
                System.out.println("Enter The Old Pin");
                String oldPin = scanner.nextLine().trim();
                System.out.println("enter the new pin");
                String newPin = scanner.nextLine().trim();
                if (oldPin.contentEquals(user[i].getPin())) {
                    user[i].setPin(newPin);
                    System.out.println("Pin Updated");
                    temp = true;
                    break;
                }
            }
        }
        if (!temp) {
            System.out.println("Error updating");
        }
    }

    public void CreatePin() {
        System.out.println("Enter the Name");
        String name = scanner.nextLine().trim();
        System.out.println("Enter Mobile Number");
        String Mob_Number = scanner.nextLine().trim();
        System.out.println("enter Card Number");
        String Card_Number = scanner.nextLine().trim();
        boolean temp = false;
        for (int i = 1; i < userNumber; i++) {
            if ((user[i] != null) && (user[i].getCardNumber().equalsIgnoreCase(Card_Number)) && (user[i].getName().equalsIgnoreCase(name)) && (user[i].getMob_NUM().equalsIgnoreCase(Mob_Number))) {
                try {
                    System.out.println("Enter the Pin");
                    String pin = scanner.nextLine().trim();
                    if (pin.matches("\\d{4}")) {
                        user[i].setPin(pin);
                        System.out.println("Pin Created");
                        temp = true;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Pin number ");
                }
            }
        }
        if (!temp) {
            System.out.println("User not found");
        }
    }

    public void sendMoney() {
        if (userNumber <= 2) {
            System.out.println("There are no User !");
            return;
        }
        System.out.println("Enter The User Name");
        String name = scanner.nextLine().trim();
        System.out.println("Enter The User ID");
        String userId = scanner.nextLine().trim();
        System.out.println("Enter The Amount To Send");
        double amount = scanner.nextDouble();
        boolean found = false;
        for (int i = 1; i < userNumber; i++) {
            if ((user[i] != null) && (user[i].getName().equalsIgnoreCase(name)) && (user[i].getUser_id().equalsIgnoreCase(userId))) {
                if (amount > user[i].getBalance()) {
                    break;
                } else {
                    try {
                        scanner.nextLine();
                        System.out.println("enter the Name ");
                        String ReceiverName = scanner.nextLine().trim();
                        System.out.println("enter the Receiver user id");
                        String ReceiverUser_ID = scanner.nextLine().trim();
                        for (int j = 1; j < userNumber; j++) {
                            if ((user[j] != null) && (user[j].getUser_id().equalsIgnoreCase(ReceiverUser_ID)) && (user[j].getName().equalsIgnoreCase(ReceiverName))) {
                                user[j].setBalance((long) (user[j].getBalance() + amount));
                                user[i].setBalance((long) (user[i].getBalance() - amount));
                                System.out.println("Money sent successfully");
                                found = true;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("error: " + e.getMessage());
                    }
                }
            }
        }
        if (!found) {
            System.out.println("User Not Found");
        }
    }

    public void ChangeDetails() {
        if (userNumber <= 1) {
            System.out.println("There are no User !");
            return;
        }
        int i;
        System.out.println("Enter the User ID ");
        String User_id = scanner.nextLine().trim();
        for (i = 1; i < userNumber; i++) {
            if (user[i].getUser_id().equalsIgnoreCase(User_id)) {
                break;
            }
        }
        System.out.println("Enter Choice ");
        System.out.println("1.Change Mobile Number");
        System.out.println("2.Change Email Address");
        System.out.println("3.Both Change");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 ->
                ChangeMobileNumber(i);
            case 2 ->
                ChangeEmail(i);
            case 3 ->
                ChangeEmail(i);
            default -> {
            }
        }
    }

    public void ChangeEmail(int i) {
        scanner.nextLine();
        System.out.println("Enter the new Email Address");
        String newEmail = scanner.nextLine().trim();
        user[i].setEmail(newEmail);
        System.out.println("Email Updated Successfully");
    }

    public void ChangeMobileNumber(int i) {
        scanner.nextLine();
        System.out.println("Enter the new Email Address");
        String newMob_No = scanner.nextLine().trim();
        user[i].setMob_NUM(newMob_No);
        System.out.println("Mobile Number Updated Successfully");
    }

    public void PrintAllUser() {
        if (userNumber <= 1) {
            System.out.println("There are no User !");
            return;
        }
        for (int i = 1; i < userNumber; i++) {
            System.out.println("\t\t\t\tUSER " + i);
            System.out.println("==================================================================");
            System.out.println("Name = " + user[i].getName());
            System.out.println("User ID = " + user[i].getUser_id());
            System.out.println("Mobile Number = " + user[i].getMob_NUM());
            System.out.println("Email = " + user[i].getEmail());
            System.out.println("==================================================================");
            System.out.println("Card Number = " + user[i].getCardNumber());
            System.out.println("Pin = " + user[i].getPin());
            System.out.println("==================================================================");
            System.out.println("------------------------------------------------------------------");
            System.out.println();
        }
    }
}
