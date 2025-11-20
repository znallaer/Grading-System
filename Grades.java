import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Grades extends Student{
    private ArrayList<String> subjects;
    private ArrayList<Integer> classstand;
    private ArrayList<Double> midexam;
    private ArrayList<Double> finexam;
    private ArrayList<Double> Average;
    private ArrayList<Double> MidtermGrade;
    private ArrayList<Double> FinalGrade;

    public Grades(){
        super();
        this.subjects = new ArrayList<>();
        this.classstand = new ArrayList<>();
        this.Average = new ArrayList<>();
        this.MidtermGrade = new ArrayList<>();
        this.FinalGrade = new ArrayList<>();
        this.midexam = new ArrayList<>();
        this.finexam = new ArrayList<>();

        for(int i = 0; i < 1; i++){
            System.out.print("\nEnter Subject " + (i + 1) + ": ");
            this.subjects.add(sc.nextLine());
            
            while(true){
                System.out.print("Enter Class Standing (0-60): ");
                int cs = sc.nextInt();
                if(cs >= 0 && cs <= 100){
                    this.classstand.add(cs);
                    break;
                }else{
                    System.out.println("Invalid grade. Please enter a grade between 0 and 60.");
                }
            }
            while(true){
                System.out.print("Enter Midterm Exam (0-100): ");
                double me = sc.nextDouble();
                if(me >= 0 && me <= 100){
                    this.midexam.add(me);
                    break;
                }else{
                    System.out.println("Invalid grade. Please enter a grade between 0 and 60.");
                }
            }

            while(true){
                System.out.print("Enter Final Exam (0-100): ");
                double fe = sc.nextDouble();
                if(fe >= 0 && fe <= 100){
                    this.finexam.add(fe);
                    break;
                }else{
                    System.out.println("Invalid grade. Please enter a grade between 0 and 60.");
                }
            }

            Midterm mid = new Midterm();
            this.MidtermGrade.add(mid.CalculateMidterm(this.classstand.get(i), this.midexam.get(i)));
            Final fin = new Final();
            this.FinalGrade.add(fin.CalculateFinal(this.classstand.get(i), this.finexam.get(i)));
                
            double Ave = (this.MidtermGrade.get(i) + this.FinalGrade.get(i)) / 2;
            this.Average.add(Ave);
            
        }
    }

    @Override
    public ArrayList<String> GetStudentData(){
        ArrayList<String> StudentData = new ArrayList<String>();
        
        StudentData.add(subjects.toString());
        StudentData.add(classstand.toString());
        StudentData.add(midexam.toString());
        StudentData.add(finexam.toString());
        StudentData.add(Average.toString());
        StudentData.add(MidtermGrade.toString());
        StudentData.add(FinalGrade.toString());
        return StudentData;
    }

    //Display Grades = Mdterm, Finals, and Average
    public void DisplayGrade(){
        for(int i = 0; i < subjects.size(); i++){
                System.out.printf("%-16s %-15.2f %-14.2f %-9.2f %n",
                subjects.get(i),
                MidtermGrade.get(i),
                FinalGrade.get(i), 
                Average.get(i));
            }
        System.out.println("------------------------------------------------------------\n");
        double overallAverage = 0;
        for(double Ave: Average){
            overallAverage += Ave;
        }
        overallAverage = overallAverage / Average.size();
        System.out.print("Overall Grade: " + overallAverage + "\n");
    }

    //File Write
    public void savefile() throws IOException{
        String File = "StudentRecords.txt";
        try(FileWriter output = new FileWriter(File, true)){
            output.write("\n-----Student Information-----\n");
            output.write("Name: " + getName() + "\n");
            output.write("Student ID: " + getStudentId() + "\n");
            output.write("Course: " + getCourse() + "\n");
            output.write("Section: " + getSection() + "\n");
            output.write("\nSUBJECT \t MIDTERM \t FINAL \t\t AVERAGE");
            output.write("\n------------------------------------------------------------\n");
            for(int i = 0; i < subjects.size(); i++){
                output.write(String.format("%-16s %-15.2f %-15.2f %.2f %n",
                subjects.get(i),
                MidtermGrade.get(i),
                FinalGrade.get(i), 
                Average.get(i)));
            }
        output.write("------------------------------------------------------------\n");
        double overallAverage = 0;
        for(double Ave: Average){
            overallAverage += Ave;
        }
        overallAverage = overallAverage / Average.size();
        output.write("\nOverall Grade: " + overallAverage + "\n");
        }catch(IOException e){
            System.out.println("File not SAVED");
        }
    }
}