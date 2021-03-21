import java.util.*;
import java.util.stream.Collectors;

public class _3_Pirates {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer[]> townDetails = new LinkedHashMap<>();
        String[] lineArr = Arrays.stream(scanner
                .nextLine()
                .split("\\|\\|"))
                .map(String::trim)
                .toArray(String[]::new);

        while (!lineArr[0].equals("Sail")) {
            String town = lineArr[0];
            int people = Integer.parseInt(lineArr[1]);
            int gold = Integer.parseInt(lineArr[2]);

            if (!townDetails.containsKey(town)) {
                townDetails.put(town, new Integer[]{people, gold});
            } else {
                townDetails.get(town)[0] += people;
                townDetails.get(town)[1] += gold;
            }
            lineArr = Arrays.stream(scanner
                    .nextLine()
                    .split("\\|\\|"))
                    .map(String::trim)
                    .toArray(String[]::new);
        }
        String[] eventsArr = Arrays.stream(scanner
                .nextLine()
                .split("=>"))
                .map(String::trim)
                .toArray(String[]::new);

        String event = null;
        String town = null;
        Integer people = null;
        Integer gold = null;

        while (!(event = eventsArr[0]).equals("End")) {
            switch (event) {
                case "Plunder":
                    town = eventsArr[1];
                    people = Integer.parseInt(eventsArr[2]);
                    gold = Integer.parseInt(eventsArr[3]);

                    townDetails.get(town)[0] -= people;
                    townDetails.get(town)[1] -= gold;

                    System.out.printf("%s plundered! %d gold stolen, %d citizens killed.\n", town, gold, people);

                    if (townDetails.get(town)[0] <= 0 || townDetails.get(town)[1] <= 0) {
                        townDetails.remove(town);
                        System.out.printf("%s has been wiped off the map!\n", town);
                    }
                    break;
                case "Prosper":
                    town = eventsArr[1];
                    gold = Integer.parseInt(eventsArr[2]);
                    if (gold < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                        break;
                    }
                    townDetails.get(town)[1] += gold;
                    System.out.printf(
                            "%d gold added to the city treasury. %s now has %d gold.\n",
                            gold, town, townDetails.get(town)[1]
                    );
                    break;
            }
            eventsArr = Arrays.stream(scanner
                    .nextLine()
                    .split("=>"))
                    .map(String::trim)
                    .toArray(String[]::new);
        }
        if (townDetails.size() > 0) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:\n", townDetails.size());
            LinkedHashMap<String, Integer[]> sortedMap = townDetails
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer[]>comparingByValue((v1, v2) -> v2[1].compareTo(v1[1])))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (Map.Entry<String, Integer[]> kv : sortedMap.entrySet()) {
                System.out.printf(
                        "%s -> Population: %d citizens, Gold: %d kg\n", kv.getKey(), kv.getValue()[0], kv.getValue()[1]);
            }
        } else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }
}
