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
	        List<Puntos> listaPuntos = new ArrayList<Puntos>(); // posibles puntos del camino
	        List<Punto> listaCamino = new ArrayList<Punto>(); // posible camino
	        int i=0, j;
	        float menorCosto;
	        if (a.x != b.x && a.y != b.y){
	            listaCamino.add(new Punto(a.x, a.y));
	            //listaCamino.add(p, new Puntos());
	            //listaCamino.listIterator(p).next().setActual(a);
	            //listaCamino.listIterator(p).next().setPredecesor(a);
	            //listaCamino.listIterator(p).next().setCosto(calcularPeso(a,a));
	        }
	        while (a.x != b.x && a.y != b.y){
	                if(a.x+1 != MapaInfo.LARGO && mapa.getDensidad(a.x+1, a.y)!=4){
	                    listaPuntos.add(i, new Puntos());
	                    listaPuntos.listIterator(i).next().setActual(new Punto(a.x+1,a.y));
	                    listaPuntos.listIterator(i).next().setPredecesor(a);
	                    listaPuntos.listIterator(i).next().setCosto(calcularDistancia(new Punto(a.x+1,a.y), b )+ calcularPeso(a, new Punto (a.x+1, a.y)));
	                    i++;
	                }
	                else if(a.x-1 != -1 && mapa.getDensidad(a.x+1, a.y)!=4) {
	                    listaPuntos.add(i, new Puntos());
	                    listaPuntos.listIterator(i).next().setActual(new Punto(a.x-1,a.y));
	                    listaPuntos.listIterator(i).next().setPredecesor(a);
	                    listaPuntos.listIterator(i).next().setCosto(calcularDistancia(new Punto(a.x-1,a.y), b) + calcularPeso(a, new Punto (a.x-1, a.y)));
	                    i++;
	                }
	                else if(a.y+1 != -1 && mapa.getDensidad(a.x+1, a.y)!=4){
	                    listaPuntos.add(i, new Puntos());
	                    listaPuntos.listIterator(i).next().setActual(new Punto(a.x,a.y+1));
	                    listaPuntos.listIterator(i).next().setPredecesor(a);
	                    listaPuntos.listIterator(i).next().setCosto(calcularDistancia(new Punto (a.x,a.y+1),b) + calcularPeso(a, new Punto (a.x, a.y+1)));
	                    i++;                
	                }
	                else if(a.y-1 != MapaInfo.ALTO && mapa.getDensidad(a.x+1, a.y)!=4){
	                    listaPuntos.add(i, new Puntos());
	                    listaPuntos.listIterator(i).next().setActual(new Punto(a.x,a.y-1));
	                    listaPuntos.listIterator(i).next().setPredecesor(a);
	                    listaPuntos.listIterator(i).next().setCosto(calcularDistancia(new Punto(a.x,a.y-1), b) + calcularPeso(a, new Punto (a.x, a.y-1)));
	                    i++;
	                }
	                //listaPuntos.sort();//ver como se escribe!! quiero ordenar de mayor a menor segun a.x!!
	                i=listaPuntos.size();
	                menorCosto= listaPuntos.listIterator(i).next().getCosto(); // busco punto de menor costo
	                i++;
	                j=i;
	                for (; i>0;i--){
	                    if (menorCosto>listaPuntos.listIterator(i).next().getCosto()){
	                        menorCosto = listaPuntos.listIterator(i).next().getCosto();
	                        j=i;
	                    }
	                }
	                listaCamino.add(listaPuntos.listIterator(j).next().getActual());
	                //listaCamino.add(p, new Puntos()); //agrego al camino el punto de menor peso
	                //listaCamino.listIterator(p).next().setActual(listaPuntos.listIterator(j).next().getActual());
	                //listaCamino.listIterator(p).next().setPredecesor(listaPuntos.listIterator(j).next().getPredecesor());
	                //listaCamino.listIterator(p).next().setCosto(menorCosto + listaCamino.listIterator(p--).next().getCosto());
	                a = listaPuntos.listIterator(j).next().getActual(); // me muevo al punto de menor peso
	                listaPuntos.remove(j); // elimino el pto de menor peso en la listaPuntos
	                i= listaPuntos.size()+1;
	                j=listaCamino.size()+1;
	                menorCosto=-1;
	        }
	            cmc.dibujarCamino(listaCamino);
	            System.out.println(listaCamino);
	}
	        
	    
	    
	private int calcularDistancia (Punto a, Punto b){
	    int cont=0;
	    if (a.x < b.x) {
	        for(int x = a.x ; x < b.x; x++) {
	            cont++;
	        }
	    } else {
	        for(int x = a.x ; x > b.x; x--) {
	            cont++;
	        }
	    }
	    if (a.y < b.y) {
	        for(int y = a.y ; y < b.y; y++) {
	            cont++;
	        }
	    } else {
	        for(int y = a.y ; y > b.y; y--) {
	            cont++;
	        }
	    }
	    return cont;
	}

	private float calcularPeso(Punto a, Punto a2){
	    if (a.x!=a2.x && a.y!=a2.y)
	        return 144 + mapa.getDensidad(a2)*100;
	    else
	        return 100 + mapa.getDensidad(a2)*100;
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
