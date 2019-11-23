package sample;

public class Chapters {
    private String idCurs;
    private String numeCapitol;
    private String numarCapitol;

    public Chapters(String idCurs, String numeCapitol, String numarCapitol) {
        this.idCurs = idCurs;
        this.numarCapitol = numarCapitol;
        this.numeCapitol = numeCapitol;
    }

    public Chapters(String[] args) {
        this(args[0],args[1],args[2]);
    }
}
