package cmc;
/**
 * Obtiene la lista de los puntos marcados en la matriz (mapa)
 * Itera los mismos de la siguiente forma:
 * Obtiene los 2 primeros y expande los contiguos entre ambos.
 * Primero expande eje x, segundo expande el eje y.
 * Reitera la lista expandiendo el siguiente (siempre expandiendo de a pares)
 * El recorrido es secuencial (conforme al orden de marcado de los puntos en el mapa)
 * Invoca la método dibujar en cada iteración.
 * Al finalizar la iteración expande los contiguos entre el último y el primero de la lista.
 * Vuelve a Invocar la método dibujar para cerrar el ciclo.
 * No contempla las densidades definidas en la matriz (mapa)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import graficos.Area;
import graficos.Punto;
import mapa.MapaInfo;

public class CmcDemo {
	private MapaInfo mapa;
	private CmcImple cmc;
	
	public CmcDemo(MapaInfo mapa, CmcImple cmc) {
		this.mapa = mapa;
		this.cmc = cmc;
		mostarColeccionDeAreas();
		mostarColeccionDePuntos();
		demoObtenerCamino();
	}
	
	private void demoObtenerCamino() {
		Punto a = null, b = null;	
		Iterator<Punto> iter = mapa.getPuntos().iterator();
		if (iter.hasNext()) {
			a = iter.next();
		
			while(iter.hasNext()) {
				b = iter.next();
				expandirPuntosContiguos(a, b);
				a = b;
			}
			//expandirPuntosContiguos(a, mapa.getPuntos().get(0));
			mapa.enviarMensaje("Ciclo entre " + mapa.getPuntos().size() + " puntos");
		}
	}
	
	private void expandirPuntosContiguos(Punto a, Punto b) {
		List<Camino> listaCaminos = new ArrayList<Camino>();
		int dist; 
		int i=1;
		listaCaminos.add(i, new Camino());
		listaCaminos.listIterator(i).next().getListaPuntos().add(i, new Punto(a.x,a.y));
		
		
		listaCaminos.listIterator(i).next().getListaPuntos().add(i, new Punto(a.x+1,a.y));
		listaCaminos.listIterator(i).next().getListaPuntos().add(i, new Punto(a.x,a.y+1));
		
		dist = calcularDistancia(new Punto(a.x+1,a.y),b);
		if (dist>calcularDistancia(new Punto(a.x-1,a.y),b)){
			dist = calcularDistancia(new Punto(a.x-1,a.y),b);
		}else if(dist>calcularDistancia(new Punto(a.x,a.y+1),b)){
			dist = calcularDistancia(new Punto(a.x,a.y+1),b);
		}else if(dist>calcularDistancia(new Punto(a.x,a.y-1),b)){
			dist = calcularDistancia(new Punto(a.x,a.y-1),b);
		}
		
		
	}
	
	private int calcularDistancia(Punto a, Punto b){
		int x,y;
		x=a.x-b.x;
		if(x<0)
			x=x*-1;
		y=a.y-b.y;
		if(y<0)
			y=y*-1;
		return x+y;
	}
	
	/*private void expandirPuntosContiguos(Punto a, Punto b) {
		List<Punto> listaPuntos = new ArrayList<Punto>();
		if (a.x < b.x) {
			for(int x = a.x ; x < b.x; x++) {
				listaPuntos.add(new Punto(x, a.y));
			}
		} else {
			for(int x = a.x ; x > b.x; x--) {
				listaPuntos.add(new Punto(x, a.y));
			}
		}
		if (a.y < b.y) {
			for(int y = a.y ; y < b.y; y++) {
				listaPuntos.add(new Punto(b.x, y));
			}
		} else {
			for(int y = a.y ; y > b.y; y--) {
				listaPuntos.add(new Punto(b.x, y));
			}
		}
		cmc.dibujarCamino(listaPuntos);
	}*/

	/** consulta clase MapaInfo */
	private void mostarColeccionDeAreas() {
		System.out.println("Mapa: " + MapaInfo.LARGO + " x " + MapaInfo.ALTO);
		for(Area a : mapa.getAreas()) {
			System.out.println(a);
		}
	}
	
	/** consulta clase MapaInfo */
	private void mostarColeccionDePuntos() {
		for(Punto c : mapa.getPuntos()) {
			int densidad = mapa.getDensidad(c);
			System.out.println(c + " D+: " + densidad);
		}
	}

}
