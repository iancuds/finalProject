package model;

import java.util.List;

public class Benefiter {



    private Long idbenefiter;

    private String email;

    private String name;

    private String passwd;

    private String token;

    private Long kind;

    private List<Basket> baskets;


    public Benefiter()
    {

    }

    public Benefiter(String email, String name, String passwd, String token, Long kind) {
        this.email = email;
        this.name = name;
        this.passwd = passwd;
        this.token = token;
        this.kind = kind;
    }


    public Long getIdbenefiter() {
        return idbenefiter;
    }

    public void setIdbenefiter(Long idbenefiter) {
        this.idbenefiter = idbenefiter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getKind() {
        return kind;
    }

    public void setKind(Long kind) {
        this.kind = kind;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    @Override
    public String toString() {
        return "Benefiter{" +
                "idbenefiter=" + idbenefiter +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", token='" + token + '\'' +
                ", kind=" + kind +
                ", baskets=" + baskets +
                '}';
    }
}
