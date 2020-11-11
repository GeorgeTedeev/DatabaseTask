package balancers;

import operations.Adder;
import operations.Deleter;
import operations.Shower;

import java.util.Scanner;

public class OperationBalancer {

    public static void chooseOperation(String operation, Scanner console){
        switch (operation){
            case "ADD":
                Adder.doOperation(console);
                break;
            case "DELETE":
                Deleter.doOperation(console);
                break;
            case "SHOW":
                Shower.doOperation(console);
                break;
        }
    }
}
