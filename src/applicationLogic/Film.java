package applicationLogic;

public class Film extends Program {

    private int filmId;
    private String genre;
    private String language;
    private int age;

    public Film(String title, double duration, String genre, String language, int age) {
        super(title, duration);
        this.genre = genre;
        this.language = language;
        this.age = age;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public int getAge() {
        return age;
    }
}
