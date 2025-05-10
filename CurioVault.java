import java.util.ArrayList;

public class CurioVault {
    private static final String INVENTORY_FILE = "inventory.txt";
    private static final String RETIRED_FILE = "retired.txt";
    private static final int MAX_RETIRED = 100;

    private static ArrayList<Artifact> inventory = new ArrayList<>();
    private static Artifact[] retired = new Artifact[MAX_RETIRED];
    private static int retiredCount = 0;

    public static void main(String[] args) {
        inventory = FileHandler.loadArtifacts(INVENTORY_FILE);
        retired = FileHandler.loadArray(RETIRED_FILE, MAX_RETIRED);

        boolean running = true;
        while (running) {
            System.out.println("\n--- CurioVault Menu ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Artifact");
            System.out.println("3. Retire Artifact");
            System.out.println("4. View Retired Artifacts");
            System.out.println("5. Exit");

            int choice = InputValidator.getInt("Choose an option: ");

            switch (choice) {
                case 1 -> viewInventory();
                case 2 -> addArtifact();
                case 3 -> retireArtifact();
                case 4 -> viewRetired();
                case 5 -> {
                    FileHandler.saveArtifacts(inventory, INVENTORY_FILE);
                    FileHandler.saveArray(retired, RETIRED_FILE);
                    running = false;
                    System.out.println("Data saved. Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewInventory() {
        System.out.println("\n-- Inventory --");
        if (inventory.isEmpty()) {
            System.out.println("No artifacts in inventory.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i));
            }
        }
    }

    private static void addArtifact() {
        String name = InputValidator.getString("Enter artifact name: ");
        String description = InputValidator.getString("Enter description: ");
        inventory.add(new Artifact(name, description));
        System.out.println("Artifact added.");
    }

    private static void retireArtifact() {
        viewInventory();
        if (inventory.isEmpty()) return;

        int index = InputValidator.getInt("Enter artifact number to retire: ") - 1;

        try {
            Artifact artifact = inventory.remove(index);
            if (retiredCount < retired.length) {
                retired[retiredCount++] = artifact;
                System.out.println("Artifact retired.");
            } else {
                System.out.println("Retirement archive full.");
                inventory.add(index, artifact); // add it back
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index. No artifact retired.");
        }
    }

    private static void viewRetired() {
        System.out.println("\n-- Retired Artifacts --");
        boolean hasAny = false;
        for (Artifact artifact : retired) {
            if (artifact != null) {
                System.out.println(artifact);
                hasAny = true;
            }
        }
        if (!hasAny) {
            System.out.println("No artifacts retired yet.");
        }
    }
}