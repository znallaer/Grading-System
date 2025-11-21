import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
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
                System.out.print("2. Remove Specific Student Record\n");
                System.out.print("3. Search Specific Student Record\n");
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
                            student.displayInfo();
                            student.DisplayGrade();
                        } catch (IOException e) {
                            System.out.println("File not SAVED");
                        }
                    }

                    case 2 -> {
                        System.out.println("\n--------Remove Specific Student Record--------");
                        System.out.print("Enter Student ID to remove: ");
                        int studentId = sc.nextInt();
                        sc.nextLine();

                        boolean foundInList = false;
                        
                        for (int i = 0; i < courses.size(); i++) {
                            if (courses.get(i).getStudentId() == studentId) {
                                foundInList = true;
                                System.out.print("\nAre you sure you want to remove this record? (Y/N): ");
                                String confirm = sc.nextLine();
                                if (confirm.equalsIgnoreCase("Yes") || confirm.equalsIgnoreCase("Y")) {
                                    courses.remove(i);
                                    removeStudentFromFile(studentId);
                                    System.out.println("Student ID " + studentId + " record has been removed.");
                                } else {
                                    System.out.println("\nDeletion Cancelled.");
                                }
                                break;
                            }
                        }

                        if (!foundInList) {
                            System.out.print("Student not found in memory. Do you want to try removing from file? (Y/N): ");
                            String confirmFile = sc.nextLine();
                            if (confirmFile.equalsIgnoreCase("Yes") || confirmFile.equalsIgnoreCase("Y")) {
                                removeStudentFromFile(studentId);
                            } else {
                                System.out.println("\nDeletion Cancelled.");
                            }
                        }
                    }
                    
                    case 3 -> {
                        System.out.println("\n--------Search Specific Student Record--------");
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
                        searchStudentInFile(searchId);
                        }
                    }

                    case 4 -> {
                        System.out.println("\n--------View All Student Records--------");
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
                        }catch (FileNotFoundException e) {
                            System.out.println("\nRecord file not found: " + e.getMessage());
                        }catch (IOException e) {
                            System.out.println("\nError reading file: " + e.getMessage());
                        }
                    }
                      
                    case 5 -> {
                        System.out.println("\n--------Delete All Student Records--------");
                        sc.nextLine();
                        System.out.print("Are you sure you want to delete all student records? (Y/N): ");
                        String confirmAll = sc.nextLine();

                        if (confirmAll.equalsIgnoreCase("Yes") || confirmAll.equalsIgnoreCase("Y")) {
                            try (BufferedWriter bw = new BufferedWriter(new FileWriter("StudentRecords.txt"))) {
                                bw.write("");
                            } catch (IOException e) {
                                System.out.println("Error deleting records: " + e.getMessage());
                                break;
                            }

                            System.gc();
                            try { Thread.sleep(50); } catch (InterruptedException e) {}

                            courses.clear();
                            System.out.println("\nAll student records have been deleted.");
                        } else {
                            System.out.println("\nDeletion Cancelled.");
                        }
                    }
                    case 6 -> {
                        System.out.println("\nExiting the Grading System. Goodbye!");
                        sc.close();
                        System.exit(0);
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
            sc.nextLine();
        }while(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("Y"));
    }
    

    static void removeStudentFromFile(int studentId) {
        File inputFile = new File("StudentRecords.txt");
        File tempFile = new File("TempRecords.txt");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("-----Student Information-----")) {
                    ArrayList<String> record = new ArrayList<>();
                    record.add(line);

                    boolean shouldDelete = false;
                    while ((line = reader.readLine()) != null) {
                        record.add(line);

                        if (line.contains("Student ID: " + studentId)) {
                            shouldDelete = true;
                            found = true;
                        }

                        if (line.startsWith("Overall Grade:")) {
                            String blankLine = reader.readLine();
                            if (blankLine != null) record.add(blankLine);
                            break;
                        }
                    }

                    if (!shouldDelete) {
                        for (String recordLine : record) {
                            writer.write(recordLine);
                            writer.newLine();
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
            return;
        }

        
        if (found) {
            if (inputFile.delete()) {
                if (tempFile.renameTo(inputFile)) {
                    System.out.println("Student ID " + studentId + " record removed from file.");
                } else {
                    System.out.println("Error updating file.");
                }
            } else {
                System.out.println("Error updating file.");
            }
        } else {
            tempFile.delete();
            System.out.println("Student ID " + studentId + " not found in file.");
        }
    }

    static boolean searchStudentInFile(int studentId) {
            File file = new File("StudentRecords.txt");
            if (!file.exists()) {
                System.out.println("Record file not found: StudentRecords.txt");
                return false;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean found = false;
                while ((line = br.readLine()) != null) {
                    if (line.contains("Student ID: " + studentId)) {
                        found = true;
                        System.out.println("\nStudent Record Found in file:");
                        System.out.println(line);
                        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                            System.out.println(line);
                        }
                        break;
                    }
                }
                if (!found) {
                    System.out.println("No student record found with ID: " + studentId + " in file.");
                }
                return found;
            } catch (FileNotFoundException e) {
                System.out.println("Record file not found: " + e.getMessage());
                return false;
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                return false;
            }
    }
}