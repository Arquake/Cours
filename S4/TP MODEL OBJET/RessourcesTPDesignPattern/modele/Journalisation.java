package modele;


import java.time.LocalDate;

public class Journalisation {
    private static Journalisation instance;

    private boolean lastLog = false;
    private String log = "";
    private String logErr= "";

    private static Journalisation Journalisation() {
        if (instance == null){
            instance = new Journalisation();
        }
        return instance;
    }

    public boolean getLastLog(){
        return lastLog;
    }

    public String getLog() {
        return log;
    }

    public String getLogErr() {
        return logErr;
    }

    public void ajouterLog(String leLog) {
        log += " | " + leLog +" "+ LocalDate.now();
        lastLog = true;
    }

    public void ajouterLogErr(String leLog) {
        logErr += " | " + leLog +" "+ LocalDate.now();
        lastLog = false;
    }

    public static Journalisation getInstance(){
        return instance;
    }
}
