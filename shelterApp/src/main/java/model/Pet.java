package model;

public class Pet {


    private Long idpet;

    private String name;

    private String age;


    private String info;

    private String health;

    private String animal;

    private Basket basket;

    public Pet(){}

    public Pet(String name, String age, String info, String health, String animal) {
        this.name = name;
        this.age = age;
        this.info = info;
        this.health = health;
        this.animal = animal;
    }


    public Long getIdpet() {
        return idpet;
    }

    public void setIdpet(Long idpet) {
        this.idpet = idpet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "idpet=" + idpet +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", info='" + info + '\'' +
                ", health='" + health + '\'' +
                ", animal='" + animal + '\'' +
                ", basket=" + basket +
                '}';
    }
}
