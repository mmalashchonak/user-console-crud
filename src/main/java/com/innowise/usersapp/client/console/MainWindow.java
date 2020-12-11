package com.innowise.usersapp.client.console;

import com.innowise.usersapp.dao.exception.ObjectNotFoundException;
import com.innowise.usersapp.entity.Roles;
import com.innowise.usersapp.utils.InputParser;
import com.innowise.usersapp.utils.Validator;
import com.innowise.usersapp.entity.User;
import com.innowise.usersapp.service.UserService;
import com.innowise.usersapp.service.factory.ServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainWindow {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private UserService service = ServiceFactory.getServiceFactory().getUserService();

    public void start() throws IOException {
        String option = "";

        do {
            System.out.println("1. View all Users");
            System.out.println("2. Add new User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Find User");
            System.out.println("6. Exit");
            System.out.println("===========================================");
            System.out.println("Enter your action");
            System.out.println("===========================================");
            option = br.readLine();
            clearScreen();
            System.out.println("\n");

            switch (option) {
                case "1":
                    viewUsers();
                    break;

                case "2":
                    addNewUser();
                    break;

                case "3":
                    updateUser();
                    break;

                case "4":
                    deleteUser();
                    break;

                case "5":
                    searchUser();
                    break;

                case "6":
                    System.out.println("******************************THANK YOU********************");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Action! Please enter again");
                    break;
            }
        } while (!option.equals("6"));
    }

    public void viewUsers() {
        System.out.println("-----------------------------------------------");

        List<User> users = service.loadAll();
        for (User user : users) {
            displayUser(user);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }

    public void addNewUser() throws IOException {
        System.out.println("------------------------------------------------");
        System.out.println("Enter First Name:");
        System.out.println("------------------------------------------------");
        String firstName = br.readLine();

        System.out.println("------------------------------------------------");
        System.out.println("Enter Last Name:");
        System.out.println("------------------------------------------------");
        String lastName = br.readLine();

        System.out.println("------------------------------------------------");
        System.out.println("Enter e-mail in ****@***.*** format:");
        System.out.println("------------------------------------------------");
        String email = br.readLine();
        while (!Validator.emailValidation(email)) {
            System.out.println("Email is not valid. Please, try again.");
            email = br.readLine();
        }

        System.out.println("------------------------------------------------");
        System.out.println("Choose User Roles (type digits separated with comma, like '1, 3').\n" +
                "You can peak only one role from each first and second level\n" +
                "or choose only third-level role.\n" +
                "\n" +
                "First level: 1 - USER, 2 -  CUSTOMER;\n" +
                "Second level: 3 - ADMIN, 4 - PROVIDER;\n" +
                "Third level: 5 - SUPER_ADMIN.");
        System.out.println("------------------------------------------------");
        String rolesString = "";
        List<Roles> roles;
        do {
            rolesString = br.readLine();
            roles = InputParser.parseRoles(rolesString);
            if (!Validator.rolesStringValidation(rolesString) || !Validator.rolesListValidation(roles)) {
                System.out.println("Roles input is incorrect. Please, try again.");
            }
        }
        while (!Validator.rolesStringValidation(rolesString) || !Validator.rolesListValidation(roles));

        System.out.println("------------------------------------------------");
        System.out.println("Enter phone numbers. Phone number format: 375*********.\n" +
                "You can set up to 3 phones, separated with comma, like: 375001234567, 375111234567");
        System.out.println("------------------------------------------------");
        String phonesString = "";
        List<String> phones;
        do {
            phonesString = br.readLine();
            phones = InputParser.parsePhones(phonesString);
            if (!Validator.phoneListValidation(phones) || !Validator.phonesStringValidation(phonesString)) {
                System.out.println("Phones input is incorrect. Please, try again.");
            }
        }
        while (!Validator.phoneListValidation(phones) || !Validator.phonesStringValidation(phonesString));

        User user = new User(firstName, lastName, email, roles, phones);
        int status = service.save(user);
        clearScreen();
        if (status == 1) {
            System.out.println("User added successfully");
        } else {
            System.out.println("ERROR while adding user");
        }
        System.out.println("\n");
    }

    public void updateUser() throws IOException {
        clearScreen();
        System.out.println("------------------------------------------------");
        System.out.println("Enter User ID:");
        System.out.println("------------------------------------------------");
        String idStr = br.readLine();
        while (!Validator.idValidation(idStr)) {
            System.out.println("Invalid ID input. Please, try again.");
            idStr = br.readLine();
        }

        clearScreen();

        User user = new User();

        try {
            user = service.loadById(Long.parseLong(idStr));
        } catch (ObjectNotFoundException e) {
            clearScreen();
            System.out.println("No User with such ID found.\n");
            return;
        }

        String option = "";
        do {
            System.out.println("------------------------------------------------");
            System.out.println("What field would You like to edit?");
            System.out.println("------------------------------------------------");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Email");
            System.out.println("4. Roles");
            System.out.println("5. Phones");
            System.out.println("6. Finish");
            System.out.println("===========================================");
            System.out.println("Enter your action");
            System.out.println("===========================================");
            option = br.readLine();
            clearScreen();
            System.out.println("\n");

            switch (option) {
                case "1":
                    System.out.println("------------------------------------------------");
                    System.out.println("Enter New First Name:");
                    System.out.println("------------------------------------------------");
                    String firstName = br.readLine();
                    user.setFirstName(firstName);
                    service.update(user);
                    clearScreen();
                    System.out.println("First Name updated successfully");
                    break;

                case "2":
                    System.out.println("------------------------------------------------");
                    System.out.println("Enter New Last Name:");
                    System.out.println("------------------------------------------------");
                    String lastName = br.readLine();
                    user.setLastName(lastName);
                    service.update(user);
                    clearScreen();
                    System.out.println("Last Name updated successfully");
                    break;

                case "3":
                    System.out.println("------------------------------------------------");
                    System.out.println("Enter new e-mail in ****@***.*** format:");
                    System.out.println("------------------------------------------------");
                    String email = br.readLine();
                    while (!Validator.emailValidation(email)) {
                        System.out.println("Email is not valid. Please, try again.");
                        email = br.readLine();
                    }
                    user.setEmail(email);
                    service.update(user);
                    clearScreen();
                    System.out.println("Email updated successfully");
                    break;

                case "4":
                    System.out.println("------------------------------------------------");
                    System.out.println("Enter User Roles (type digits separated with comma, like '1, 3').\n" +
                            "You can peak only one role from each first and second level\n" +
                            "or choose only third-level role.\n" +
                            "\n" +
                            "First level: 1 - USER, 2 -  CUSTOMER;\n" +
                            "Second level: 3 - ADMIN, 4 - PROVIDER;\n" +
                            "Third level: 5 - SUPER_ADMIN.");
                    System.out.println("------------------------------------------------");
                    String rolesString = "";
                    List<Roles> roles;
                    do {
                        rolesString = br.readLine();
                        roles = InputParser.parseRoles(rolesString);
                        if (!Validator.rolesStringValidation(rolesString) || !Validator.rolesListValidation(roles)) {
                            System.out.println("Roles input is incorrect. Please, try again.");
                        }
                    }
                    while (!Validator.rolesStringValidation(rolesString) || !Validator.rolesListValidation(roles));
                    user.setRoles(roles);
                    service.update(user);
                    clearScreen();
                    System.out.println("Roles updated successfully");
                    break;

                case "5":
                    System.out.println("------------------------------------------------");
                    System.out.println("Enter phone numbers. Phone number format: 375*********.\n" +
                            "You can set up to 3 phones, separated with comma, like: 375001234567, 375111234567");
                    System.out.println("------------------------------------------------");
                    String phonesString = "";
                    List<String> phones;
                    do {
                        phonesString = br.readLine();
                        phones = InputParser.parsePhones(phonesString);
                        if (!Validator.phoneListValidation(phones) || !Validator.phonesStringValidation(phonesString)) {
                            System.out.println("Phones input is incorrect. Please, try again.");
                        }
                    }
                    while (!Validator.phoneListValidation(phones) || !Validator.phonesStringValidation(phonesString));

                    user.setPhones(phones);
                    service.update(user);
                    clearScreen();
                    System.out.println("Phones updated successfully");
                    break;

                case "6":
                    clearScreen();
                    return;

                default:
                    System.out.println("Invalid Action! Please enter again");
                    break;
            }
        } while (!option.equals("6"));
    }

    public void deleteUser() throws IOException {
        System.out.println("------------------------------------------------");
        System.out.println("Enter User ID:");
        System.out.println("------------------------------------------------");
        String idStr = br.readLine();
        clearScreen();
        while (!Validator.idValidation(idStr)) {
            System.out.println("Invalid ID input. Please, try again.");
            idStr = br.readLine();
        }
        try {
            int status = service.delete(Long.parseLong(idStr));
            if (status == 1) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("ERROR while deleting User");
            }
            System.out.println("\n");
        } catch (ObjectNotFoundException e) {
            clearScreen();
            System.out.println("No User with such ID found.\n");
        }
    }

    public void searchUser() throws IOException {
        System.out.println("------------------------------------------------");
        System.out.println("Enter User ID:");
        System.out.println("------------------------------------------------");
        String idStr = br.readLine();
        clearScreen();
        while (!Validator.idValidation(idStr)) {
            System.out.println("Invalid ID input. Please, try again.");
            idStr = br.readLine();
        }
        try {
            User user = service.loadById(Long.parseLong(idStr));
            displayUser(user);
            System.out.println("\n");
        } catch (ObjectNotFoundException e) {
            clearScreen();
            System.out.println("No User with such ID found.\n");
        }
    }

    public static void displayUser(User user) {
        System.out.println(user.toString());
        System.out.println("\n");
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }
}
