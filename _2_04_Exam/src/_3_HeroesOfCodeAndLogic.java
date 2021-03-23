import java.util.*;
import java.util.stream.Collectors;

public class _3_HeroesOfCodeAndLogic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer[]> heroes = new LinkedHashMap<>();

        /* Retrieve heroes info */
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            String name = line[0];
            Integer hitPoints = Math.min(Integer.parseInt(line[1]), 100);
            Integer manaPoints = Math.min(Integer.parseInt(line[2]), 200);
            Integer[] hInfo = {hitPoints, manaPoints};

            heroes.put(name, hInfo);
        }
        List<String> commandsLine = new ArrayList<>();
        commandsLine = Arrays.asList(scanner.nextLine().split("\\s+-\\s+").clone());

        /* Execute the commands */
        while (!commandsLine.get(0).equals("End")) {

            String command = commandsLine.get(0);
            String name = commandsLine.get(1);
            boolean validName = heroes.containsKey(name);
            if(!validName) {
                commandsLine = Arrays.asList(scanner.nextLine().split("\\s+-\\s+").clone());
                continue;
            }
            Integer hpPoints;
            Integer mpPoints;

            switch (command) {

                case "CastSpell":
                    Integer mpNeeded = Integer.parseInt(commandsLine.get(2));
                    String spellName = commandsLine.get(3);

                    if (heroes.get(name)[1] >= mpNeeded) {
                        heroes.get(name)[1] -= mpNeeded;
                        System.out.printf(
                                "%s has successfully cast %s and now has %d MP!\n",
                                name, spellName, heroes.get(name)[1]);
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!\n", name, spellName);
                    }
                    break;

                case "TakeDamage": {
                    Integer damage = Integer.parseInt(commandsLine.get(2));
                    String attacker = commandsLine.get(3);
                    heroes.get(name)[0] -= damage;
                    if (heroes.get(name)[0] > 0) {
                        System.out.printf(
                                "%s was hit for %d HP by %s and now has %d HP left!\n",
                                name, damage, attacker, heroes.get(name)[0]);
                    } else {
                        heroes.remove(name);
                        System.out.printf("%s has been killed by %s!\n", name, attacker);
                    }
                }
                break;

                case "Recharge":
                    Integer rechargeAmount = Integer.parseInt(commandsLine.get(2));
                    Integer rechargedValue = (heroes.get(name)[1] + rechargeAmount) > 200 ? (200 - heroes.get(name)[1]) : rechargeAmount;
                    heroes.get(name)[1] += rechargedValue;
                    System.out.printf("%s recharged for %d MP!\n", name, rechargedValue);
                    break;

                case "Heal":
                    Integer healAmount = Integer.parseInt(commandsLine.get(2));
                    Integer healedValue = (heroes.get(name)[0] + healAmount) > 100 ? (100 - heroes.get(name)[0]) : healAmount;
                    heroes.get(name)[0] += healedValue;
                    System.out.printf("%s healed for %d HP!\n", name, healedValue);
                    break;
            }
            commandsLine = Arrays.asList(scanner.nextLine().split("\\s+-\\s+").clone());
        }

        Map<String, Integer[]> sortedHeroes = heroes
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer[]>comparingByValue((v1, v2) -> v2[0].compareTo(v1[0])))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Integer[]> hero : sortedHeroes.entrySet()) {
            System.out.println(hero.getKey());
            System.out.printf("  HP: %d\n", hero.getValue()[0]);
            System.out.printf("  MP: %d\n", hero.getValue()[1]);
        }
    }
}
