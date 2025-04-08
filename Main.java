import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Declare Scanner object
        final int MAX_INGREDIENTS = 6; // Declare constant variable
        String userInput; // Declare user input variable
        int numIngredients = 0; // Declare number of ingredients variable
        Map<String, Integer> userSelection = new HashMap<>(); // Holds user ice cream selections
        int numScoops = 0; // Declare number of scoops variable

        // Declare flavorBase map
        Map<Integer, String> flavorBase = new HashMap<>();
        flavorBase.put(1, "Vanilla");
        flavorBase.put(2, "Chocolate");
        flavorBase.put(3, "Strawberry");

        // Declare maxServings map
        Map<String, Integer> maxServings = new HashMap<>();
        maxServings.put("Vanilla", 3);
        maxServings.put("Chocolate", 3);
        maxServings.put("Strawberry", 3);

        // Declare user ice cream selections map
        Map<String, Integer> iceCreamSelections = new HashMap<>();

        System.out.println("Welcome to the Custom Ice Cream Builder!\n");
        System.out.println("Select your base ice cream flavor:");

        // Output flavorBase map
        for (Map.Entry<Integer, String> entry : flavorBase.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        // Get user input
        userInput = scan.nextLine().trim();

        // Check if user input is valid number
        boolean validSelection = false;
        for (Map.Entry<Integer, String> entry : flavorBase.entrySet()) {
            if (userInput.equals(entry.getKey().toString())) {
                userSelection.put(entry.getValue(), 1);
                numIngredients++;
                validSelection = true;
                break; // Exit loop once we've found a match
            }
        }

        if (!validSelection) {
            System.out.println("Please enter a valid number.");
        }

        // Prompt user for how many scoops they want
        System.out.println("How many scoops? (1-3)");
        userInput = scan.nextLine().trim();

        if (userInput.equals("1")) {
            numScoops = 1;
        } else if (userInput.equals("2")) {
            numScoops = 2;
        } else if (userInput.equals("3")) {
            numScoops = 3;
        } else {
            System.out.println("Please enter a valid number.");
        }

        // After collecting both flavor and scoops
        if (validSelection && numScoops > 0) {
            // Get the selected flavor from userSelection
            String selectedFlavor = null;
            for (String flavor : userSelection.keySet()) {
                selectedFlavor = flavor;
                break; // There should only be one entry at this point
            }

            // Add to iceCreamSelections map
            if (selectedFlavor != null) {
                iceCreamSelections.put(selectedFlavor, numScoops);
                System.out.println("Added " + numScoops + " scoops of " + selectedFlavor);
            }
        }

        // Output the selected ice cream
        System.out.println("\nYour Custom Ice Cream Sundae:");
        for (Map.Entry<String, Integer> entry : iceCreamSelections.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + " Scoop" + ")");
        }

        System.out.println(
                "\nAssembling Instructions: Layer the base, add toppings, finish with sauce. Serve immediately.");
    }
}