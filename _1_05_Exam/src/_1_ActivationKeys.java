import java.util.Locale;
import java.util.Scanner;

public class _1_ActivationKeys {

    /* 01. Activation Keys */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String activationKeyRaw = scanner.nextLine();

        String row = "";

        while (!(row = scanner.nextLine()).equals("Generate")) {

            String[] rowArray = row.split(">>>");
            String command = rowArray[0];

            switch (command) {

                case "Contains":
                    String substringContains = rowArray[1];
                    boolean isContaining = activationKeyRaw.contains(substringContains);
                    if (isContaining) {
                        System.out.printf("%s contains %s\n", activationKeyRaw, substringContains);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;

                case "Flip":
                    String upperOrLower = rowArray[1];
                    StringBuffer bufFlip = new StringBuffer(activationKeyRaw);
                    int startIndexFlip = Integer.parseInt(rowArray[2]);
                    int endIndexFlip = Integer.parseInt(rowArray[3]);
                    String substringFlip = activationKeyRaw.substring(startIndexFlip, endIndexFlip);

                    if (upperOrLower.equals("Upper")) {
                        activationKeyRaw = bufFlip
                                .replace(startIndexFlip, endIndexFlip, substringFlip.toUpperCase(Locale.ROOT))
                                .toString();
                    } else {
                        activationKeyRaw = bufFlip
                                .replace(startIndexFlip, endIndexFlip, substringFlip.toLowerCase(Locale.ROOT))
                                .toString();
                    }
                    System.out.println(activationKeyRaw);
                    break;

                case "Slice":
                    int startIndexSlice = Integer.parseInt(rowArray[1]);
                    int endIndexSlice = Integer.parseInt(rowArray[2]);
                    StringBuffer bufSlice = new StringBuffer(activationKeyRaw);
                    activationKeyRaw = bufSlice
                            .replace(startIndexSlice, endIndexSlice, "")
                            .toString();
                    System.out.println(activationKeyRaw);
                    break;
            }
        }

        System.out.printf("Your activation key is: %s", activationKeyRaw);
    }
}
