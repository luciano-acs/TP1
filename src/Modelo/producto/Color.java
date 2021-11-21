package Modelo.producto;

public enum Color {

    ROJO("ROJO"),
    AZUL("AZUL"),
    VERDE("VERDE");
    
    private String colores;
    
    private Color(String c){
        this.colores = c;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }
    
    
}
