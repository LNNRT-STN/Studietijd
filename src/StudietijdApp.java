import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class StudietijdApp {

    private HashSet<Student> studenten;
    private HashMap<String, ArrayList<LeerActiviteit>> leerActiviteiten; // key is studentennummer

    public StudietijdApp(String csvNaam) {
        this.studenten = new HashSet<>();
        this.leerActiviteiten = new HashMap<>();
        try {
            Scanner inputStream = new Scanner(new File(csvNaam));
            String line = inputStream.nextLine();
            studenten.add(Student.parseStudent(line));
            while (inputStream.hasNextLine()) {
                line = inputStream.nextLine();
                studenten.add(Student.parseStudent(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + csvNaam);
        } catch (Exception e) {
            System.out.println("Problemen bij lezen bestand " + csvNaam);
        }
    }


    public Student getstudent(String studentennummer) throws Exception{
        for (Student s : studenten){
            if (s.getStudentenNummer().equals(studentennummer)){
                return s;
            }
        }
        throw new Exception("Student niet gevonden");
    }

    public boolean startLeerActiviteit(String studentennummer, String vakcode, LeerActiviteit.Werkvorm werkvorm) throws Exception {
        try {
            Student s = getstudent(studentennummer);
            if (s!=null){
                leerActiviteiten.get(studentennummer).add(new LeerActiviteit(s,vakcode,werkvorm));
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public LeerActiviteit zoekLeerActiviteit(String studentennummer, LocalDateTime startmoment){
        for (LeerActiviteit l : leerActiviteiten.get(studentennummer)){
            if (l.getStart().equals(startmoment)){
                return l;
            }
        }
        return null;
    }

    public void beeindigLeeractiviteit(String studentennummer, LocalDateTime startmoment, boolean oncampus, boolean online){
        LeerActiviteit l = zoekLeerActiviteit(studentennummer, startmoment);
        if (l != null){
            l.beeindigLeerActiviteit(oncampus,online);
        }
        else System.out.println("Leeractiviteit werd niet gevonden");
    }

    public String getLeerActiviteiten(String studentennummer) throws Exception {
        String output = "";
        for (LeerActiviteit l : leerActiviteiten.get(studentennummer)){
            output += l.toString() + "\n";
        }
        return output;
    }

    /*
    Mogelijk dat ik geen exception moet opvangen? ik werk niet met studenten.
     */
    public long bepaalLeertijd(String vakcode){
        long totaleleertijd = 0;
        int aantalvalues = 0;
        for (ArrayList<LeerActiviteit> array : leerActiviteiten.values()){
            for (LeerActiviteit l : array ){
                if (l.getVakcode().equals(vakcode) && l.isBeeindigd()){
                    totaleleertijd += l.bepaalDuurtijd();
                    aantalvalues++;
                }
            }
        }
        return totaleleertijd/aantalvalues;
    }

    public static void main(String[] args) {
        StudietijdApp s = new StudietijdApp("Studenten.csv");
        System.out.println(s.studenten.toString());

        try {
            s.getstudent("212003");
            System.out.println("succes");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            s.startLeerActiviteit("212003","1234", LeerActiviteit.Werkvorm.Werkcollege);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
