import java.lang.reflect.Array;
import java.util.Objects;

public class Student extends Persoon{

    private String studentenNummer;
    private String opleiding;

    public Student(String naam, String voornaam, String email, String woonplaats, String studentenNummer, String opleiding) {
        super(naam, voornaam, email, woonplaats);
        this.studentenNummer = studentenNummer;
        this.opleiding = opleiding;
    }

    public String getStudentenNummer() {
        return studentenNummer;
    }

    public void setStudentenNummer(String studentenNummer) {
        this.studentenNummer = studentenNummer;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentenNummer, student.studentenNummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentenNummer);
    }

    public static Student parseStudent(String input){
        //op voorwaarde dat [ niet wordt gebruikt in input van string wat ik veronderstel.
       String[] text = input.split(";");
       return new Student(text[0],text[1],text[2],text[3],text[4],text[5]);
    }

    @Override
    public String toString() {
        return super.toString() +studentenNummer+";" +opleiding;
    }
}
