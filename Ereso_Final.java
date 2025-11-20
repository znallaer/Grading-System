public class Final{
    private int classstand;
    private double finexam;

    public double CalculateFinal(int classstand, double finexam){
        double FinalGrade = (finexam * 0.40) + classstand;
        return FinalGrade;
    }

    public int getClassstand(){
        return classstand;
    }
    public double getFinexam(){
        return finexam;
    }
}