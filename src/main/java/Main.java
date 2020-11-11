import balancers.OperationBalancer;
import checkers.OperationExistingChecker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        while(true) {
            System.out.println("Please, enter what you want to do: \"ADD\", \"DELETE\" or \"SHOW\"" +
                    " (notice, all with capital letters):");
            String operation = OperationExistingChecker.getOperationFromConsoleIfItExists(console);
            OperationBalancer.chooseOperation(operation, console);
        }

    }
}
