
import java.lang.reflect.Field;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the no. of monsters you want to create:");
        int n = scanner.nextInt();
        scanner.nextLine();
        List<Monster> monsters = new ArrayList<>();

        // Create monsters with user input
        for (int i = 0; i < n; i++) {
            monsters.add(createMonster(scanner, "Monster " + (i + 1)));
        }

        // Create babies by combining traits
        Set<Monster> babies = createBabies(monsters);

        // Display traits of the baby monsters
        System.out.println("\nBaby Monsters Traits:");
        for (Monster babyMonster : babies) {
            displayMonsterTraits(babyMonster);
        }
    }

    private static Monster createMonster(Scanner scanner, String monsterName) {
        System.out.println("Enter traits for " + monsterName + ":");
        System.out.print("Eye Color: ");
        String eyeColor = scanner.nextLine();

        System.out.print("Feather Color: ");
        String featherColor = scanner.nextLine();

        System.out.print("Magical Abilities: ");
        String magicalAbilities = scanner.nextLine();

        System.out.print("Size: ");
        int size = scanner.nextInt();

        System.out.print("Strength: ");
        int strength = scanner.nextInt();
        System.out.print("Durability: ");
        int durability = scanner.nextInt();
        System.out.print("Weakness: ");
        scanner.nextLine();
        String weakness = scanner.nextLine();
        System.out.print("Aggression Level: ");
        int aggressionLevel = scanner.nextInt();
        scanner.nextLine();
        return new Monster(eyeColor, featherColor, magicalAbilities, size,
                strength, durability, weakness, aggressionLevel);
    }


    private static Set<Monster> createBabies(List<Monster> parents) {
        // set to ensure uniqueness for same set of parents if we want to create multiple babies.
        Set<Monster> babies = new HashSet<>();
        int n = parents.size();
        // Generate multiple unique babies out of many combinations of parent monsters.
        // eg no of parents =3 then combinations are - (1,2),(1,3),(2,3).
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Monster parent1 = parents.get(i);
                Monster parent2 = parents.get(j);

                Monster babyMonster = Monster.createBabyMonster(parent1, parent2);

                // Ensure uniqueness
                // if that baby is present, create another baby for same parents.
                while (babies.contains(babyMonster)) {

                    babyMonster = Monster.createBabyMonster(parent1, parent2);
                }

                babies.add(babyMonster);

            }
        }


        return babies;
    }


    private static void displayMonsterTraits(Monster monster) {
        Field[] fields = Monster.class.getDeclaredFields();

        System.out.println("Monster Traits:");
        for (Field field : fields) {
            field.setAccessible(true);

            try {
                System.out.println(field.getName() + ": " + field.get(monster));
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();
    }
}
