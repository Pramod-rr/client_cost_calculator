import java.text.DecimalFormat;

class Client {
    private String name;
    private String enrolledClass;
    private String currentLevel;
    private int workshopsAttended;
    private double ptHoursPerWeek;

   
    public void setName(String name) { this.name = name; }
    
    public void setEnrolledClass(String enrolledClass) {
        this.enrolledClass = enrolledClass;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    } 

    public void setWorkshopsAttended(int workshopsAttended) {
        this.workshopsAttended = workshopsAttended;
    }

    public void setPtHoursPerWeek(double ptHoursPerWeek) {
        this.ptHoursPerWeek = ptHoursPerWeek;
    }
   
    public void calculateAndOutputCosts() {
        DecimalFormat df = new DecimalFormat("###,##0.00 LKR");
        double totalCost = 0.00;
        
        System.out.println("\n*** Client Cost Summary for " + this.name + " ***");
        System.out.println("--- Itemized Costs ---");

        
        double classCost = 0.00;
        switch (enrolledClass.toUpperCase()) {
            case "FITNESS":
                switch (currentLevel.toUpperCase()) {
                    case "BEGINNER":
                        classCost = TrainingPrice.BEGINNER_CLASS;
                        break;
                    case "INTERMEDIATE":
                        classCost = TrainingPrice.INTERMEDIATE_CLASS;
                        break;
                    case "ADVANCED":
                        classCost = TrainingPrice.ADVANCED_CLASS;
                        break;
                    default:
                        // Should not happen with proper input validation
                        System.out.println("Error: Unknown fitness level.");
                        return;
                }
                break;
            case "TRAINING":
                classCost = TrainingPrice.PERSONAL_TRAINING_HOUR;
                break;
            default:
                // Should not happen with proper input validation
                System.out.println("Error: Unknown class enrollment.");
                return;
        }
        totalCost += classCost;
        System.out.println("Class Fee (" + enrolledClass + "): " + df.format(classCost));
        
        double effectivePTHours = Math.min(ptHoursPerWeek, TrainingPrice.MAX_PT_HOURS_PER_WEEK);
        double ptCost = effectivePTHours * TrainingPrice.PERSONAL_TRAINING_HOUR;
        totalCost += ptCost;
        System.out.println("Personal Training (" + effectivePTHours + " hours/week): " + df.format(ptCost));

        // --- 3. Workshop Fee Calculation ---
        double workshopCost = 0.00;
        if (currentLevel.toUpperCase().equals("INTERMEDIATE") || currentLevel.toUpperCase().equals("ADVANCED")) {
            workshopCost = workshopsAttended * TrainingPrice.WELLNESS_WORKSHOP;
            totalCost += workshopCost;
            System.out.println("Wellness Workshops (" + workshopsAttended + " sessions): " + df.format(workshopCost));
        } else {
           
            System.out.println("[NOTE]: Wellness Workshop fee is 0.00 LKR. Beginners are ineligible.");
        }
        
        System.out.println("----------------------");
        System.out.println("** TOTAL MONTHLY COST: " + df.format(totalCost) + " **");

        // --- 4. Level Comparison Output ---
        System.out.println("\n--- Level Comparison ---");
        System.out.println("Current Fitness Level: " + currentLevel);
        System.out.println("Enrolled Class/Training Level: " + enrolledClass);
        if (currentLevel.equalsIgnoreCase(enrolledClass)) {
            System.out.println("Assessment: Your current level matches your enrolled program. Excellent!");
        } else {
            System.out.println("Assessment: Your current level (" + currentLevel + ") does NOT match your enrolled class (" + enrolledClass + "). Consider reviewing your program.");
        }
        System.out.println("******************************************\n");
    }
}
