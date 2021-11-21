package Modelo.producto;

public enum Talle {
    XS("XS"),
    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XXL("XXL");
    
    private String talles;
    
    private Talle(String t){
        this.talles = t;
    }

    public String getTalles() {
        return talles;
    }

    public void setTalles(String talles) {
        this.talles = talles;
    }
    
}
