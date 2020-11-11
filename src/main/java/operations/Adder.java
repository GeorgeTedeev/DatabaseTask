package operations;

import configurations.DatabaseConfiguration;
import entities.Student;

import java.sql.*;
import java.util.Scanner;

public class Adder {

    public static void doOperation(Scanner console){
        Student student = new Student();

        setInformationAboutStudent(student,console);
        insertStudentIntoDatabase(student);

    }

    private static void setInformationAboutStudent(Student student, Scanner console){
        System.out.println("Enter student`s name:");
        student.setName(console.nextLine());
        System.out.println("Enter student`s surname:");
        student.setSurname(console.nextLine());
        System.out.println("Enter student`s patronymic:");
        student.setPatronymic(console.nextLine());
        System.out.println("Enter student`s birthday:");
        student.setBirthday(console.nextLine());
        System.out.println("Enter student`s group:");
        student.setGroupName(console.nextLine());
    }

    private static void insertStudentIntoDatabase(Student student){

        try (Connection connection = DriverManager.getConnection(DatabaseConfiguration.URL,
                                                                 DatabaseConfiguration.NAME,
                                                                 DatabaseConfiguration.PASSWORD)){

            Class.forName("org.postgresql.Driver");
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO STUDENT(NAME, SURNAME, PATRONYMIC, BIRTHDAY, GROUPNAME) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getPatronymic());
            preparedStatement.setString(4, student.getBirthday());
            preparedStatement.setString(5, student.getGroupName());

            preparedStatement.executeUpdate();
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Success!\n");


    }

}
