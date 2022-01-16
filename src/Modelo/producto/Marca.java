package Modelo.producto;

public class Marca {

      private int codMarca;
      private String descripcionM;

    public Marca(int codMarca, String descripcionM) {
        this.codMarca = codMarca;
        this.descripcionM = descripcionM;
    }     
      
//    Adidas("Adidas"),
//    Nike("Nike");
//    
//    private String marcas;
//
//    private Marca(String marcas) {
//        this.marcas = marcas;
//    }
//    
//    public String getMarcas() {
//        return marcas;
//    }
//
//    public void setMarcas(String marcas) {
//        this.marcas = marcas;
//    }

    public int getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(int codMarca) {
        this.codMarca = codMarca;
    }

    public String getDescripcionM() {
        return descripcionM;
    }

    public void setDescripcionM(String descripcionM) {
        this.descripcionM = descripcionM;
    }
    
    @Override
    public String toString() {
        return "Marca{" + "codMarca=" + codMarca + ", descripcionM=" + descripcionM + '}';
    }
}
