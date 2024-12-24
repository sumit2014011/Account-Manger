//Sumit File

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        try (Scanner scanner = new Scanner(System.in)) {
            Account account = new Account();
            while (true) {
                System.out.println("1.Create Account");
                System.out.println("2.Get INFO");
                System.out.println("3.Exiting");
                System.out.println("4.Deposit Funds Account");
                System.out.println("5.Withdraw Funds");
                System.out.println("6.Set Pin or Change Pin");
                System.out.println("7.Send Funds to Account");
                System.out.println("8.Change Details");
                System.out.println("9.Print All User INFO");
                System.out.println("10.Delete User ");
                System.out.println("11 Totale User");
                try {
                    choice = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("invalid input, Please enter a number");
                    scanner.nextLine();
                    continue;
                }
                switch (choice) {
                    case 1:
                        account.CreateUser();
                        break;
                    case 2:
                        account.Info();
                        break;
                    case 3:
                        System.out.println("\t\t\tThink your \nHave a Nice Day");
                        System.exit(1);
                    case 4:
                        account.depositFunds();
                        break;
                    case 5:
                        account.withdrawalFunds();
                        break;
                    case 6:
                        account.checkPin();
                        break;
                    case 7:
                        account.sendMoney();
                        break;
                    case 8:
                        account.ChangeDetails();
                        break;
                    case 9:
                        account.PrintAllUser();
                        break;
                    case 10:
                        if (!account.DeleteUser()) {
                            System.out.println("User Found \n And Deleted");
                        } else {
                            System.out.println("User Not Found");
                        }
                        break;
                    case 11:
                        account.TotalUser();
                        break;
                    default :
                        System.out.println("Invalid Input");
                        break;
                }
            }
        }
    }
}
