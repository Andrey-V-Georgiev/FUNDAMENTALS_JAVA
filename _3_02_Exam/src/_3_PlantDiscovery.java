import java.util.*;

public class _3_PlantDiscovery {


    public static void main(String[] args) {

        class PlantInfo {
            private Integer rarity;
            private List<Integer> ratingList;

            public PlantInfo(Integer rarity) {
                this.rarity = rarity;
                this.ratingList = new ArrayList<>();
            }

            public Integer getRarity() {
                return rarity;
            }

            public void setRarity(Integer rarity) {
                this.rarity = rarity;
            }

            public List<Integer> getRatingList() {
                return ratingList;
            }

            public void setRatingList(List<Integer> ratingList) {
                this.ratingList = ratingList;
            }

            public Double avg() {
                Double average = this.ratingList.stream().mapToDouble(r -> r).average().orElse(0.0);
                return average;
            }
        }

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        Map<String, PlantInfo> plants = new LinkedHashMap<>();

        /* Obtain plats info */
        for (int i = 0; i < n; i++) {
            String[] tokens = Arrays.stream(scanner.nextLine().split("<->")).map(String::trim).toArray(String[]::new);
            String name = tokens[0];
            Integer rarity = Integer.parseInt(tokens[1]);
            plants.put(name, new PlantInfo(rarity));
        }

        /* Execute commands */
        String line = "";
        while (!(line = scanner.nextLine().trim()).equals("Exhibition")) {

            String[] tokens = line.split(": | - ");
            String command = tokens[0];
            String name = tokens[1];

            switch (command) {
                case "Rate":
                    Integer rating = Integer.parseInt(tokens[2]);
                    if (plants.containsKey(name)) {
                        plants.get(name).ratingList.add(rating);
                    } else {
                        System.out.println("error");
                    }
                    break;

                case "Update":
                    int newRarity = Integer.parseInt(tokens[2]);
                    if (plants.containsKey(name)) {
                        plants.get(name).rarity = newRarity;
                    } else {
                        System.out.println("error");
                    }
                    break;

                case "Reset":
                    if (plants.containsKey(name)) {
                        plants.get(name).ratingList = new ArrayList<>();
                    } else {
                        System.out.println("error");
                    }
                    break;

                default:
                    System.out.println("error");
            }
        }

        /* Print the output */
        System.out.println("Plants for the exhibition:");
        plants.entrySet().stream()
                .sorted((p1, p2) -> {
                    int result = p2.getValue().rarity - p1.getValue().rarity;
                    if (result == 0) {
                        result = p2.getValue().avg().compareTo(p1.getValue().avg());
                    }
                    return result;
                })
                .forEach(p -> {
                    System.out.printf(
                            " - %s; Rarity: %d; Rating: %.2f\n", p.getKey(), p.getValue().rarity, p.getValue().avg());
                });
    }
}