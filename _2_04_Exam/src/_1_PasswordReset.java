import java.util.Scanner;

public class _1_PasswordReset {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        StringBuilder sb = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("Done")) {

            String[] lineArr = line.split("\\s+");
            switch (lineArr[0]) {
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
                    int index = Integer.parseInt(lineArr[1]);
                    int length = Integer.parseInt(lineArr[2]);
                    String substringCut = password.substring(index, index + length);
                    password = password.replace(substringCut, "");
                    System.out.println(password);
                    break;

                case "Substitute":
                    String substringSubstitute = lineArr[1];
                    String substituteSubstitute = lineArr[2];

                    if (!password.contains(substringSubstitute)) {
                        System.out.println("Nothing to replace!");
                    } else {
                        while (true) {
                            if (password.contains(substringSubstitute)) {
                                password = password.replace(substringSubstitute, substituteSubstitute);
                            } else {
                                break;
                            }
                        }
                        System.out.println(password);
                    }
                    break;
            }
        }
        System.out.printf("Your password is: %s", password);
    }
}
