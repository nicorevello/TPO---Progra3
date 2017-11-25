package cmc;

import java.util.ArrayList;
import java.util.List;

import graficos.Punto;

public class Camino {
	
	List<Punto> listaPuntos = new ArrayList<Punto>();
	
	float costo;
	float distancia;
	
	
	public List<Punto> getListaPuntos() {
		return listaPuntos;
	}
	public void setListaPuntos(List<Punto> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}
	public float getCostoTotal() {
		return costo+distancia;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	//public Punto PuntoActual(){} 
}
