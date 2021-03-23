import java.util.Scanner;

public class _1_PasswordReset {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("Done")) {

            String[] tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "TakeOdd":
                    for (int i = 0; i < password.length(); i++) {
                        if (i % 2 != 0) {
                            sb.append(password.charAt(i));
                        }
                    }
                    password = sb.toString();
                    System.out.println(password);
                    break;

                case "Cut":
                    int index = Integer.parseInt(tokens[1]);
                    int length = Integer.parseInt(tokens[2]);
                    String first = password.substring(0, index);
                    String last = password.substring(index + length);
                    password = first + last;
                    System.out.println(password);
                    break;

                case "Substitute":
                    String substrSubstitute = tokens[1];
                    String substitSubstitute = tokens[2];

                    if (!password.contains(substrSubstitute)) {
                        System.out.println("Nothing to replace!");
                    } else {
                        password = password.replaceAll(substrSubstitute, substitSubstitute);
                        System.out.println(password);
                    }
                    break;
            }
        }
        System.out.printf("Your password is: %s", password);
    }
}
