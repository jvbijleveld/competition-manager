package nl.vanbijleveld.cm.players;


public EnumSex {
    M("Man","Dhr");
    F("Vrouw", "Mevr");
    U("Onbekend", "");
    
    private final String sex;
    private final String salutation;
    
    EnumSex(final String sex, final String salutation){
        this.sex = sex;
        this.salutation = salutation;
    }
    
    public String getSex(){
        return sex;
    }
    
    public String getSalutation(){
        return salutation;
    }

}