package sample;

public class Chapters {
    private String idCurs;
    private String numeCapitol;
    private String numarCapitol;

    public Chapters(String numarCapitol,String idCurs, String numeCapitol) {
        this.idCurs = idCurs;
        this.numarCapitol = numarCapitol;
        this.numeCapitol = numeCapitol;
    }

    public Chapters(String[] args) {
        this(args[0],args[1],args[2]);
    }

    @Override
    public String toString() {
        return "ID: "+idCurs+" Numar Capitol: "+numarCapitol + " Nume Capitol: "+numeCapitol;
    }

    public String getIdCurs() {
        return idCurs;
    }

    public String getNumarCapitol() {
        return numarCapitol;
    }

    public String getNumeCapitol() {
        return numeCapitol;
    }
}
