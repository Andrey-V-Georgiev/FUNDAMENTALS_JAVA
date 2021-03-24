import java.util.Scanner;

public class _1_WorldTour {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String stops =  scanner.nextLine() ;
        String line = "";

        while (!(line = scanner.nextLine()).equals("Travel")) {
            String[] tokens = line.split("[:]");
            String command = tokens[0];

            switch (command) {
                case "Add Stop":
                    int index = Integer.parseInt(tokens[1]);
                    String stopName = tokens[2];

                    if (index >= 0 && index <= (stops.length() - 1)) {

                        String front = stops.substring(0, index);
                        String end = stops.substring(index);
                        stops = front + stopName + end;
                    }
                    break;

                case "Remove Stop":
                    int startIndex = Integer.parseInt(tokens[1]);
                    int endIndex = Integer.parseInt(tokens[2]);

                    if ((startIndex >= 0 && startIndex <= (stops.length() - 1))
                            && (endIndex >= startIndex && endIndex <= (stops.length() - 1))) {

                        String front = stops.substring(0, startIndex);
                        String end = stops.substring(endIndex + 1);
                        stops = front + end;
                    }
                    break;

                case "Switch":
                    String oldString = tokens[1];
                    String newString = tokens[2];
                    stops = stops.replace(oldString, newString);
                break;
            }
            System.out.println(stops);
        }
        System.out.printf("Ready for world tour! Planned stops: %s\n", stops);
    }
}
