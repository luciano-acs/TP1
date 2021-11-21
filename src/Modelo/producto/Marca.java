package Modelo.producto;

public enum Marca {

    Adidas("Adidas"),
    Nike("Nike");
    
    private String marcas;

    private Marca(String marcas) {
        this.marcas = marcas;
    }
    
    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }
}
