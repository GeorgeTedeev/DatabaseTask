package operations;

import configurations.DatabaseConfiguration;

import java.sql.*;
import java.util.Scanner;

public class Deleter {

    public static void doOperation(Scanner console){
        Long id = getIdOfDeletingStudent(console);
        deleteStudentFromDatabaseById(id);

    }

    private static Long getIdOfDeletingStudent(Scanner console){
        System.out.println("Enter student`s id:");
        return Long.valueOf(console.nextLine());
    }

    private static void deleteStudentFromDatabaseById(Long id){

        try (Connection connection = DriverManager.getConnection(DatabaseConfiguration.URL,
                                                                 DatabaseConfiguration.NAME,
                                                                 DatabaseConfiguration.PASSWORD)){

            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(
                    "DELETE FROM STUDENT WHERE ID = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Success!\n");

    }
}
