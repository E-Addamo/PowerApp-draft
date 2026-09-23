package com.powerapp;
public class Heir {

    private String name;
    private String surname;
    //private Date birthDate;
    private String cf;
    private String iban;
    private float share;

    public Heir(String name, String surname, String cf, String iban, float share) throws InvalidHeirException{
        if(name == null || name == ""){
            throw new InvalidHeirException("Heir's name cannot be null or empty");
        }

        if(surname == null || surname == ""){
            throw new InvalidHeirException("Heir's surname cannot be null or empty");
        }

        if(cf == null || cf == ""){
            throw new InvalidHeirException("Heir's cf cannot be null or empty");
        }

        // inserisci un controllo più preciso sul formato del codice fiscale
        if(cf.length() != 16){
            throw new InvalidHeirException("Invalid cf format");
        }

        if(iban == null || iban == ""){
            throw new InvalidHeirException("Heir's iban cannot be null or empty");
        }

        // inserisci un controllo più preciso sul formato dell'IBAN
        if(iban.length() != 27){
            throw new InvalidHeirException("Invalid IBAN format");
        }

        if(share > 1 || share < 0){
            throw new InvalidHeirException("Heir's share must be between 0 and 1");
        }
        
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.iban = iban;
        this.share = share;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCf() {
        return cf;
    }

    public String getIban() {
        return iban;
    }

    public float getShare() {
        return share;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        Heir h = (Heir) o;
        return name.equals(h.getName()) && surname.equals(h.getSurname()) &&
            cf.equals(h.getCf()) && iban.equals(h.getIban()) && share == h.getShare();
    }

}
