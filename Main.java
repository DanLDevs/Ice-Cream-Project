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

        // Declare toppings map
        Map<Integer, String> toppings = new HashMap<>();
        toppings.put(1, "Sprinkles");
        toppings.put(2, "Gummies");
        toppings.put(3, "Strawberries");

        // Declare a map to hold max serving sizes for each topping
        Map<String, Integer> toppingServings = new HashMap<>();
        toppingServings.put("Sprinkles", 3); // Updated max quantity to 3 tablespoons
        toppingServings.put("Gummies", 3);
        toppingServings.put("Strawberries", 5);

        // Declare a map to hold measurement units for each topping
        Map<String, String> toppingUnits = new HashMap<>();
        toppingUnits.put("Sprinkles", "tablespoons");
        toppingUnits.put("Gummies", "pieces");
        toppingUnits.put("Strawberries", "pieces");

        // Declare user ice cream selections map
        Map<String, Integer> iceCreamSelections = new HashMap<>();

        // Declare user toppings selections list
        List<String> selectedToppings = new ArrayList<>();

        System.out.println("Welcome to the Custom Ice Cream Builder!\n");
        System.out.println("Select your base ice cream flavor:");

        // Output flavorBase map
        for (Map.Entry<Integer, String> entry : flavorBase.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        // Get user input for flavor
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
            System.exit(0); // Exit the program if flavor selection is invalid
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
            System.exit(0); // Exit the program if scoops selection is invalid
        }

        boolean selectingToppings = true;
        while (selectingToppings) {
            // Prompt user for toppings
            System.out.println("\nSelect a topping (or 0 to finish):");

            // Output toppings map
            for (Map.Entry<Integer, String> entry : toppings.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }

            // Get user input for topping
            userInput = scan.nextLine().trim();

            if (userInput.equals("0")) {
                selectingToppings = false;
                continue;
            }

            try {
                int toppingNumber = Integer.parseInt(userInput);
                if (toppings.containsKey(toppingNumber)) {
                    String selectedTopping = toppings.get(toppingNumber);

                    // Check if this topping is already selected
                    if (selectedToppings.contains(selectedTopping)) {
                        System.out.println(
                                "You've already added " + selectedTopping + ". Please select a different topping.");
                    } else {
                        // Get max servings for this topping
                        int maxServingsForTopping = toppingServings.get(selectedTopping);
                        String unit = toppingUnits.get(selectedTopping);

                        // Ask user how many servings they want
                        System.out.println("How many " + unit + "? (1-" + maxServingsForTopping + ")");
                        String servingInput = scan.nextLine().trim();

                        try {
                            int servings = Integer.parseInt(servingInput);

                            if (servings >= 1 && servings <= maxServingsForTopping) {
                                // Add topping with specified quantity
                                selectedToppings.add(selectedTopping);
                                iceCreamSelections.put(selectedTopping, servings);
                            } else {
                                System.out.println("Please enter a number between 1 and " + maxServingsForTopping);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }
                } else {
                    System.out.println("Invalid topping number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // After collecting flavor, scoops, and toppings
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
            }
        }

        // Update the output section to display both flavors and toppings from the same
        // map
        System.out.println("\nYour Custom Ice Cream Sundae:");

        // Print ice cream base (need to identify it's a base flavor)
        for (Map.Entry<String, Integer> entry : iceCreamSelections.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();

            // Check if it's a base flavor
            if (flavorBase.containsValue(item)) {
                System.out.println(item + " (" + quantity + " Scoop" + (quantity > 1 ? "s" : "") + ")");
            }
        }

        // Print toppings with quantities and units
        if (!selectedToppings.isEmpty()) {
            for (String topping : selectedToppings) {
                int quantity = iceCreamSelections.get(topping);
                String unit = toppingUnits.get(topping);

                System.out.println(topping + " (" + quantity + " " +
                        (quantity > 1 ? unit : unit.replaceAll("s$", "")) + ")");
            }
        } else {
            System.out.println("\nNo toppings selected.");
        }

        System.out.println(
                "\nAssembling Instructions: Layer the base, add toppings, finish with sauce. Serve immediately.");
    }
}