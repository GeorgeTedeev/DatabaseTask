package checkers;

import java.util.Scanner;
import java.util.Set;

public class OperationExistingChecker {
    private static final Set<String> operations = Set.of("ADD", "DELETE", "SHOW");


    public static String getOperationFromConsoleIfItExists(Scanner console) {
        boolean isOperationExists = false;
        String operation = null;

        while (!isOperationExists) {
            operation = console.nextLine();
            if (operations.contains(operation)) {
                isOperationExists = true;
            } else {
                System.out.println("There is no such operation. Please, enter \"ADD\", \"DELETE\" or \"SHOW\" " +
                        " (notice, all with capital letters):");
            }
        }
        return operation;
    }
}
