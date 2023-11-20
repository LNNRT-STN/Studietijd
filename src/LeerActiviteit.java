import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LeerActiviteit {

    enum Werkvorm {
        Hoorcollege,
        Werkcollege,
        Groepswerk,
        Werkstuk
    }

    private LocalDateTime start;
    private LocalDateTime end;
    private Student student;
    private String vakcode;
    private Werkvorm werkvorm;
    private boolean onCampus;
    private boolean onLine;

    public LeerActiviteit(Student student, String vakcode, Werkvorm werkvorm) {
        this.start = LocalDateTime.now();
        this.student = student;
        this.vakcode = vakcode;
        this.werkvorm = werkvorm;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Student getStudent() {
        return student;
    }

    public String getVakcode() {
        return vakcode;
    }

    public Werkvorm getWerkvorm() {
        return werkvorm;
    }

    public boolean isOnCampus() {
        return onCampus;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void beeindigLeerActiviteit(Boolean b1 , Boolean b2){
        this.onCampus = b1;
        this.onLine = b2;
        this.end = LocalDateTime.now();
    }

    public boolean isBeeindigd(){
        return this.end != null;
    }

    public long bepaalDuurtijd(){
        return start.until(end, ChronoUnit.HOURS);
    }

    @Override
    public String toString() {
        return "LeerActiviteit{" +
                "start=" + start +
                ", end=" + end +
                ", student=" + student +
                ", vakcode='" + vakcode  +
                ", werkvorm=" + werkvorm +
                ", onCampus=" + onCampus +
                ", onLine=" + onLine +
                '}';
    }
}
