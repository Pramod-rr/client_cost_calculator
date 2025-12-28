import java.util.Scanner;

public class FitLifeApp {
    private static Scanner scanner = new Scanner(System.in);

    private static String getLevelInput(String prompt) {
        String input = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt + " (Beginner/Intermediate/Advanced): ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Beginner") || 
                input.equalsIgnoreCase("Intermediate") || 
                input.equalsIgnoreCase("Advanced")) {
                valid = true;
            } else {
                System.err.println("Invalid level entered. Please enter 'Beginner', 'Intermediate', or 'Advanced'.");
            }
        }
        return input;
    }

    private static String getEnrolledClass(String prompt) {
        String input = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt + " (Fitness/Training): ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Fitness") || 
                input.equalsIgnoreCase("Training"))
        {
                valid = true;
            } else {
                System.err.println("Invalid level entered. Please enter 'Fitness', or 'Training'.");
            }
        }
        return input;
    }

    
    private static int getIntInput(String prompt, int minVal) {
        int value = -1;
        while (value < minVal) {
            System.out.print(prompt + ": ");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value < minVal) {
                    System.err.println("Value cannot be negative. Please enter a non-negative number.");
                }
            } else {
                System.err.println("Invalid input. Please enter a whole number.");
            }
            scanner.nextLine(); 
        }
        return value;
    }
    
    
    private static double getDoubleInput(String prompt, double maxVal) {
        double value = -1.0;
        while (true) {
            System.out.print(prompt + " (Max " + maxVal + "): ");
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value >= 0 && value <= maxVal) {
                    break; // Success
                } else if (value < 0) {
                    System.err.println("Value cannot be negative. Please enter a non-negative number.");
                } else {
                    System.err.println("PT hours cannot exceed " + maxVal + " hours per week.");
                }
            } else {
                System.err.println("Invalid input. Please enter a number.");
            }
            scanner.nextLine(); 
        }
        scanner.nextLine(); 
        return value;
    }

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" FitLife Training Center Cost Calculator ");
        System.out.println("=========================================");
        
        Client newClient = new Client();

        // 1. Client Name
        System.out.print("Enter Client Name: ");
        newClient.setName(scanner.nextLine().trim());
        
        // 2. Enrolled Class/Training Session
        newClient.setEnrolledClass(getEnrolledClass("Enrolled Class or Training Session?"));

         // 3. Current Fitness Level
        String currentLevel = getLevelInput("Current Fitness Level");
        newClient.setCurrentLevel(currentLevel);

        if (currentLevel.toUpperCase().equalsIgnoreCase("Beginner")) {
            System.out.println("[NOTE]: Beginners are ineligible for workshops. Skipping this step.");
            newClient.setWorkshopsAttended(0); // Automatically set to 0
        } 
        else {
            // 4. This only runs for Intermediate or Advanced clients
            newClient.setWorkshopsAttended(getIntInput(
                "Enter Number of Workshops Attended This Month", 
                0
            ));
        }

        // 5. Number of Personal Training Hours per Week
        newClient.setPtHoursPerWeek(getDoubleInput(
                "Enter Number of Personal Training Hours Per Week", 
                (double)TrainingPrice.MAX_PT_HOURS_PER_WEEK 
            ));
        
        newClient.calculateAndOutputCosts();
    }
}