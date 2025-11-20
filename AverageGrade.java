public class AverageGrade{
    private double MidtermGrade;
    private double FinalGrade;
    private int classstand;
    
    public AverageGrade(double MidtermGrade, double FinalGrade, int classstand){
        this.MidtermGrade = MidtermGrade;
        this.FinalGrade = FinalGrade;
        this.classstand = classstand;
    }

    public int getclassstand(){
        return classstand;
    }
    public double getMidterm(){
        return MidtermGrade;
    }
    public double getFinal(){
        return FinalGrade;
    }

    public double Average(double Midterm, double Final, int classstand){
        return MidtermGrade + classstand + FinalGrade;
    }
}