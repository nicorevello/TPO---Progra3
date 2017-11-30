package cmc;

import graficos.Punto;

public class Puntos {
	    
    float costo;
    Punto Predecesor = new Punto(0,0);
    Punto Actual = new Punto(0,0);
	    
    public Punto getActual() {
        return Actual;
    }
    public void setActual(Punto actual) {
        Actual = actual;
    }
    public Punto getPredecesor() {
        return Predecesor;
    }
    public void setPredecesor(Punto predecesor) {
        Predecesor = predecesor;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
}
