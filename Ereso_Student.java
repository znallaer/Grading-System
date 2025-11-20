import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Student{
    private String name;
    private String course;
    private int studentId;
    private String section;
    private ArrayList<String> subjects;
    private ArrayList<Double> midexam;
    private ArrayList<Double> finexam;
    private ArrayList<Integer> classstand;
    private ArrayList<Double> Average;
    private ArrayList<Double> MidtermGrade;
    private ArrayList<Double> FinalGrade;
    Scanner sc = new Scanner(System.in);
    
    public Student(){
        this.subjects = new ArrayList<>();
        this.midexam = new ArrayList<>();
        this.finexam = new ArrayList<>();
        this.classstand = new ArrayList<>();
        this.Average = new ArrayList<>();
        this.MidtermGrade = new ArrayList<>();
        this.FinalGrade = new ArrayList<>();
        System.out.println("\n--------Add Student Record--------");
        System.out.print("Enter name: ");
        this.name = sc.nextLine();

        System.out.print("Enter student ID: ");
        this.studentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter course: ");
        this.course = sc.nextLine();

        System.out.print("Enter section: ");
        this.section = sc.nextLine();
    }

    public ArrayList<String> GetStudentData(){
        ArrayList<String> StudentData = new ArrayList<String>();
        
        StudentData.add(this.name);
        StudentData.add(Integer.toString(this.studentId));
        StudentData.add(this.course);
        StudentData.add(this.section);
        return StudentData;
    }

    public String getName(){ 
        return name; 
    }
    public int getStudentId(){
        return studentId; 
    }
    public String getCourse(){ 
        return course; 
    }
    public String getSection(){ 
        return section; 
    }

    public void displayInfo(){
        System.out.println("\n------------Student Information------------\n");
        System.out.println("Name: " + this.name);
        System.out.println("Student ID: " + this.studentId);
        System.out.println("Course: " + this.course);
        System.out.println("Section: " + this.section);
        System.out.println("\nSUBJECT \t MIDTERM \t FINAL \t\t AVERAGE");
        System.out.println("-----------------------------------------------");
    }
}