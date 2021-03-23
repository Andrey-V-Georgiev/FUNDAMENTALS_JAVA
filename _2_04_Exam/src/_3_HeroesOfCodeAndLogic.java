import java.util.*;

public class _3_HeroesOfCodeAndLogic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer[]> heroes = new LinkedHashMap<>();

        /* Retrieve heroes info */
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            String name = line[0];
            int hp = Math.min(Integer.parseInt(line[1]), 100);
            int mp = Math.min(Integer.parseInt(line[2]), 200);
            Integer[] points = new Integer[]{hp, mp};

            heroes.put(name, points);
        }

        /* Execute the commands */
        String[] tokens;
        String line = scanner.nextLine();
        while (!line.equals("End")) {

            tokens = line.split("\\s+-\\s+");
            String command = tokens[0];
            String name = tokens[1];

            switch (command) {
                case "CastSpell":
                    Integer mpNeeded = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];

                    if (heroes.get(name)[1] >= mpNeeded) {
                        heroes.get(name)[1] -= mpNeeded;
                        System.out.printf(
                                "%s has successfully cast %s and now has %d MP!\n",
                                name, spellName, heroes.get(name)[1]);
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!\n", name, spellName);
                    }
                    break;

                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    heroes.get(name)[0] -= damage;

                    if (heroes.get(name)[0] > 0) {
                        System.out.printf(
                                "%s was hit for %d HP by %s and now has %d HP left!\n",
                                name, damage, attacker, heroes.get(name)[0]);
                    } else {
                        heroes.remove(name);
                        System.out.printf("%s has been killed by %s!\n", name, attacker);
                    }
                    break;

                case "Recharge":
                    Integer rechargeAmount = Integer.parseInt(tokens[2]);
                    int rechargedValue = (heroes.get(name)[1] + rechargeAmount) > 200 ? (200 - heroes.get(name)[1]) : rechargeAmount;
                    heroes.get(name)[1] += rechargedValue;
                    System.out.printf("%s recharged for %d MP!\n", name, rechargedValue);
                    break;

                case "Heal":
                    Integer healAmount = Integer.parseInt(tokens[2]);
                    int healedValue = (heroes.get(name)[0] + healAmount) > 100 ? (100 - heroes.get(name)[0]) : healAmount;
                    heroes.get(name)[0] += healedValue;
                    System.out.printf("%s healed for %d HP!\n", name, healedValue);
                    break;

            }
            line = scanner.nextLine();
        }

        heroes.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int result = b.getValue()[0] - a.getValue()[0];
                    if (result == 0) {
                        result = a.getKey().compareTo(b.getKey());
                    }
                    return result;
                })
                .forEach(hero -> {
                    System.out.println(hero.getKey());
                    System.out.printf("  HP: %d\n", hero.getValue()[0]);
                    System.out.printf("  MP: %d\n", hero.getValue()[1]);
                });
    }
}
