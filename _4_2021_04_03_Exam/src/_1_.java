import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _1_ {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine().trim();
        List<String> emailList = new ArrayList<>();
        String username = "";
        String domain = "";

        String line = "";
        while (!(line = scanner.nextLine()).equals("Complete")) {
            String[] tokens = Arrays.stream(line.split("\\s+")).map(t -> t.trim()).toArray(String[]::new);
            String command = tokens[0];

            if (email.contains("@")) {
                emailList = Arrays.stream(email.split("[@]")).collect(Collectors.toList());
                username = emailList.get(0);
                domain = emailList.get(1);
            }

            switch (command) {
                case "Make":
                    if (tokens[1].equals("Upper")) {
                        email = email.toUpperCase();
                    } else {
                        email = email.toLowerCase();
                    }
                    System.out.printf("%s\n", email);
                    break;


                case "GetDomain":
                    if (email.contains("@")) {
                        int index = Integer.parseInt(tokens[1]);
                        String subString = domain.substring(domain.length() - index);
                        System.out.printf("%s\n", subString);
                    }
                    break;

                case "GetUsername":
                    if (!email.contains("@")) {
                        System.out.printf("The email %s doesn't contain the @ symbol.\n", email);
                    } else {
                        System.out.printf("%s\n", username);
                    }
                    break;

                case "Replace":
                    email = email.replaceAll(tokens[1], "-");
                    System.out.printf("%s\n", email);
                    break;

                case "Encrypt":
                    char[] chars = email.toCharArray();
                    List<String> ints = new ArrayList<>();
                    for (int i = 0; i < chars.length; i++) {
                        int ascii = (int) chars[i];
                        String strAscii = ascii + "";
                        ints.add(strAscii);
                    }
                    String output = String.join(" ", ints);
                    System.out.println(output);
                    break;
            }
        }
    }
}
