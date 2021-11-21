package Modelo.cliente;

public enum CondTributaria {

    Responsable_Inscripto("Responsable_Inscripto"),
    Monotributo("Monotributo"),
    Exento("Exento"),
    No_Responsable("No_Responsable"),
    Consumidor_Final("Consumidor_Final");
    
    private String condicion;
    
    private CondTributaria(String ct){
        this.condicion = ct;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    
    
}
