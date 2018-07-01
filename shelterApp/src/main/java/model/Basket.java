package model;

public class Basket {


    private Long idbasket;


    private String address;

    private String phone;


    private Pet pet;

    private Benefiter benefiter;

    public Basket()
    {

    }

    public Basket(/*Long idpet, */ String address, String phone) {
        this.address = address;
        this.phone=phone;
    }

    public Long getIdbasket() {
        return idbasket;
    }

    public void setIdbasket(Long idbasket) {
        this.idbasket = idbasket;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Benefiter getBenefiter() {
        return benefiter;
    }

    public void setBenefiter(Benefiter benefiter) {
        this.benefiter = benefiter;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Basket{" +
                "idbasket=" + idbasket +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", pet=" + pet +
                ", benefiter=" + benefiter +
                '}';
    }
}

