package operations;

import configurations.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shower {

    public static void doOperation(Scanner console) {
        showAllStudents(console);
    }

    private static void showAllStudents(Scanner console) {
        List<List<String>> allStudents = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfiguration.URL,
                                                                 DatabaseConfiguration.NAME,
                                                                 DatabaseConfiguration.PASSWORD)){

            Class.forName("org.postgresql.Driver");
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM STUDENT");

            while (result.next()) {
                allStudents.add(List.of(result.getString("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("patronymic"),
                        result.getString("birthday"),
                        result.getString("groupname")));


            }

            showInConsole(console, allStudents);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Success!\n");

    }

    private static void showInConsole(Scanner console, List<List<String>> result) {

        List<Integer> widthsOfColumns = new ArrayList<>();
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(0).length()).max(Integer::compareTo).get()), "ID".length()));
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(1).length()).max(Integer::compareTo).get()), "Name".length()));
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(2).length()).max(Integer::compareTo).get()), "Surname".length()));
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(3).length()).max(Integer::compareTo).get()), "Patronymic".length()));
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(4).length()).max(Integer::compareTo).get()), "Birthday".length()));
        widthsOfColumns.add(Math.max((result.stream().map(student -> student.get(5).length()).max(Integer::compareTo).get()), "GroupName".length()));

        System.out.println("-".repeat(widthsOfColumns.stream().reduce((length1, length2) -> length1 + length2).get() + 20));
        System.out.format("| %-" + widthsOfColumns.get(0) + "s |" +
                " %-" + widthsOfColumns.get(1) + "s |" +
                " %-" + widthsOfColumns.get(2) + "s |" +
                " %-" + widthsOfColumns.get(3) + "s |" +
                " %-" + widthsOfColumns.get(4) + "s |" +
                " %-" + widthsOfColumns.get(5) + "s |\n", "ID", "Name", "Surname", "Patronymic", "Birthday", "GroupName");
        System.out.println("-".repeat(widthsOfColumns.stream().reduce((length1, length2) -> length1 + length2).get() + 20));

        for (List<String> row : result) {

            System.out.format("| %-" + widthsOfColumns.get(0) + "s |" +
                    " %-" + widthsOfColumns.get(1) + "s |" +
                    " %-" + widthsOfColumns.get(2) + "s |" +
                    " %-" + widthsOfColumns.get(3) + "s |" +
                    " %-" + widthsOfColumns.get(4) + "s |" +
                    " %-" + widthsOfColumns.get(5) + "s |\n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5));
            System.out.println("-".repeat(widthsOfColumns.stream().reduce((length1, length2) -> length1 + length2).get() + 20));

        }
    }
}

