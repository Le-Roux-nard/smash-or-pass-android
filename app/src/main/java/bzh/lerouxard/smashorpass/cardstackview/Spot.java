package bzh.lerouxard.smashorpass.cardstackview;

public class Spot {

    private static long counter = 0;

    private long id;
    private String name;
    private String city;
    private String url;

    public Spot(String name, String city, String url) {
        this.id = ++counter;
        this.name = name;
        this.city = city;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getUrl() {
        return url;
    }
}
