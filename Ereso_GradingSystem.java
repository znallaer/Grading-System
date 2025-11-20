import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

public class GradingSystem{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        int MenuChoice;
        String choice = "Yes";

        System.out.print("+--------------------------------+\n");
        System.out.println("  WELCOME TO THE GRADING SYSTEM!");
        System.out.println("+--------------------------------+");

        while(true){
            do{
                try{
                    System.out.print("\n---------MAIN MENU--------\n");
                    System.out.print("1. Add Student Record\n");
                    System.out.print("2. Remove Student Record\n");
                    System.out.print("3. Search Student Record\n");
                    System.out.print("4. View all Student Record\n");
                    System.out.print("5. Exit\n");
                    System.out.print("Enter your choice: ");
                    MenuChoice = sc.nextInt();

                    switch(MenuChoice){
    case 1:
        Course student = new Course();
        courses.add(student);

        try {
            student.savefile();     
        } catch (IOException e) {
            System.out.println("File not SAVED");
        }
        student.displayInfo(); 
        student.DisplayGrade();    
        break;

    case 2:
        
        break;

    case 3:
        SearchStudentRecord(courses, sc);
        break;

    case 4:
        
        break;

    case 5:
        ExitSystem(choice);
        System.exit(0);
        break;

    default:
        System.out.println("Invalid choice! Please select a number between 1 and 5.");
        sc.nextLine();
        break;
}
                    
                }catch(InputMismatchException e){
                    System.out.println("\nPlease enter a valid input type.\n");
                    continue;
                }catch(Exception e){
                    System.out.println("\nAn error occurred: " + e.getMessage() + "\n");
                    sc.nextLine();
                }
            }while(choice.equalsIgnoreCase("No") || choice.equalsIgnoreCase("N"));
        }
    }

    static void ExitSystem(String choice){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Are you sure you want to exit the system? (Y/N): ");
            choice = sc.nextLine();
                        
            if(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")){
                System.out.println("\nThank you for using the Grading System!");
                break;
            }else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")){
                break;
            }else{
                System.out.println("\nInvalid input! Please enter Y or N. Please try again\n");
                continue;
            }
        }
    }

    public static void SearchStudentRecord(ArrayList<Course> courses, Scanner sc) {
    System.out.print("\nEnter Student ID to search: ");
    int searchId = sc.nextInt();
    boolean found = false;

    for (Course c : courses) {
        if (c.getStudentId() == searchId) {
            System.out.println("\nStudent Record Found:");
            c.displayInfo();  // Show basic info
            c.DisplayGrade(); // Show grades
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("No student record found with ID: " + searchId);
    }
}

 /*
    SearchStudentRecord(){
        System.out.println("\n--------Search Student Record--------");
    }
    ViewAll(){
        System.out.println("\n--------View all Student Record--------");
    }
    ModifyStudentRecord(){
        System.out.println("\n--------Modify Student Record--------");
    }*/
}