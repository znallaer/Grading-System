public class Midterm{
    private int classstand;
    private double midexam;
    
    public double CalculateMidterm(int classstand, double midexam){
        double MidtermGrade = (midexam * 0.40) + classstand;
        return MidtermGrade;
    }

}