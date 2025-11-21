import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.BufferedReader;



public class GradingSystem{

    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Grades> courses = new ArrayList<>();

        int MenuChoice;
        String choice = "Yes";

        System.out.print("+--------------------------------+\n");
        System.out.println("  WELCOME TO THE GRADING SYSTEM!");
        System.out.println("+--------------------------------+");

        do{
            try{
                System.out.print("\n---------MAIN MENU--------\n");
                System.out.print("1. Add Student Record\n");
                System.out.print("2. Remove Student Record\n");
                System.out.print("3. Search Student Record\n");
                System.out.print("4. View all Student Records\n");
                System.out.print("5. Delete all Student Records\n");
                System.out.print("6. Exit System\n");
                System.out.print("Enter your choice: ");
                MenuChoice = sc.nextInt();

                switch(MenuChoice){
                    case 1 -> {
                        Grades student = new Grades();
                        courses.add(student);

                        try {
                            student.savefile();
                        } catch (IOException e) {
                            System.out.println("File not SAVED");
                        }
                        student.displayInfo();
                        student.DisplayGrade();

                    }
                    case 2 -> {
                        System.out.println("\n--------Remove Student Record--------");
                        System.out.print("Enter Student ID to remove: ");
                        int studentId = sc.nextInt();
                        boolean found = false;
                        for (int i = 0; i < courses.size(); i++) {
                            if (courses.get(i).getStudentId() == studentId) {
                                courses.remove(i);
                                found = true;
                                System.out.println("Student ID " + studentId + " has been removed.");
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Student ID " + studentId + " not found.");
                        }else {


                        }

                    }
                    
                        case 3 -> {
                        System.out.println("\n--------Search Student Record--------");
                        System.out.print("\nEnter Student ID to search: ");
                        int searchId = sc.nextInt();
                        boolean found = false;

                        for (Grades g : courses) {
                        if (g.getStudentId() == searchId) {
                            System.out.println("\nStudent Record Found:");
                            g.displayInfo(); 
                            g.DisplayGrade(); 
                            found = true;
                            break;
                        }
                    }

                        if (!found) {
                        System.out.println("No student record found with ID: " + searchId);
                        }
                    }

                    case 4 -> {
                        System.out.print("\n--------View All Student Records--------");
                        try (BufferedReader br = new BufferedReader(new FileReader("StudentRecords.txt"))) {
                    String line;
                    boolean empty = true;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                        empty = false;
                    }
                    if (empty) {
                        System.out.println("No records found in " + "StudentRecords.txt");
                    }
                        } catch (FileNotFoundException e) {
                            System.out.println("Record file not found: " + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                    }
                }
                      
                
                case 5 -> {
                        System.out.print("\n--------Delete All Student Records--------");
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter("StudentRecords.txt"))) {
                            bw.write("");
                            System.out.println("\nAll student records have been deleted.");
                        } catch (IOException e) {
                            System.out.println("Error deleting records: " + e.getMessage());
                        }
                    }
                    
                    case 6 -> {
                        ExitSystem(choice);
                    }
                    
                    case 7 -> {
                        break;
                    }
                    default -> {
                        System.out.println("Invalid choice! Please select a number between 1 and 6.");
                        System.out.println("Please try again.");
                        sc.nextLine();
                    }
                }
                    


            }catch(InputMismatchException e){
                System.out.println("\nPlease enter a valid input type.\n");
                continue;
            }catch(Exception e){
                System.out.println("\nAn error occurred: " + e.getMessage() + "\n");
                sc.nextLine();
            }
            System.out.print("Want to try again? (Y/N): ");
            choice = sc.next();
        }while(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"));
        
        sc.close();
    }



    static void ExitSystem(String choice){
        try (Scanner sc = new Scanner(System.in)) {
            while(true){
                sc.nextLine();
                System.out.print("Are you sure you want to exit the system? (Y/N): ");
                choice = sc.nextLine();

                if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")){
                    System.out.println("\nThank you for using the Grading System!");
                    break;
                }else if(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")){
                    break;
                }else{
                    System.out.println("\nInvalid input! Please enter Y or N. Please try again\n");
                    continue;
                }
            }
        }
    }


}