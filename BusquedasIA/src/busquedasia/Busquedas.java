/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasia;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 *
 * @author Darwin
 */
public class Busquedas {

    Scanner input = new Scanner(System.in);
    private Node nodoOrigen, nodoDestino;
    private Graph grafo;
    private String idNodoOrigen, idNodoDestino;
    private LinkedList<Node> pila = new LinkedList();
    private LinkedList<Node> colaNodos = new LinkedList<>();
    private LinkedList<Node> cola = new LinkedList<>();
    private LinkedList<Node> hijos = new LinkedList<>();
    private List<Node> nodosRecorridos = new ArrayList<>();
    private LinkedList<Node> colaBidirecional = new LinkedList();
    private LinkedList<Node> padreBidireccional = new LinkedList();
    private LinkedList caminos = new LinkedList();
    private LinkedList sumar = new LinkedList();
    private String ruta;
    private String rutaDestino;
    boolean visitado;
    private boolean encontrado;
    private int numeroIteraciones;
    private int nivel;
    private int factorRamificacion;
    private int numeroNodos;
    private final String nombreVisitado = "visitado";
    private final String nombreNivel = "nivel";
    private final String nombreH = "heuristica";
    private boolean noCambia;
    private int nivelProfundidad;
    private String rutaNivel;
    private int numeroDeNivel;
    private int suma;
    private int posicion = 0;
    private int i = 0;
    private int costo = 0;

///////////////Metodo Constructor//////////////////////////////////////////////
    public Busquedas(Graph grafo) {
        this.grafo = grafo;
    }

//////Metodo ingresar nodo origen, nodo destino////////////////////////////////
    public void ingresarNodos() {
        try {
            //Ingresamos nodo Origen
            System.out.println("Ingrese nodo origen");
            idNodoOrigen = input.next();
            nodoOrigen = grafo.getNode(idNodoOrigen);

            //Ingresamos nodo Destino
            System.out.println("Ingrese nodo Destino");
            idNodoDestino = input.next();
            nodoDestino = grafo.getNode(idNodoDestino);

            //System.out.printf("%s---->%s%n", idNodoOrigen, idNodoDestino);
            //amplitud();
        } catch (InputMismatchException e) {
            System.out.printf("Exception: %s%n%n", e.getMessage());
            input.nextLine();
            System.out.println("Intente de nuevo.\n");
        }
    }

/////////////////////Metodo para buscar en amplitud////////////////////////////
    public void amplitud() {
        numeroIteraciones++;
        visitado = nodoOrigen.getAttribute("visitado");
        if (!visitado) {
            ruta = ruta + nodoOrigen.getId();
        }
        String idOrigen = nodoOrigen.getId();
        String idDestino = nodoDestino.getId();
        if (idOrigen.equalsIgnoreCase(idDestino)) {
            encontrado = true;
        }
        if (nodoOrigen.getDegree() > 0 & !visitado) {
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            while (it.hasNext()) {
                Node nodoPadre = it.next();
                if (nodoPadre != null) {
                    colaNodos.add(nodoPadre);
                }
            }
            nodoOrigen.setAttribute("visitado", true);
        }
        if (!colaNodos.isEmpty() & encontrado == false) {
            Node n = colaNodos.getFirst();
            Node nodoPadre = grafo.getNode(n.getId());
            colaNodos.removeFirst();
            nodoOrigen = nodoPadre;
            amplitud();

        }

    }

///////////////////Metdo para buscar en profundidad/////////////////////////////
    public void profundidad() {
        numeroIteraciones++;
        visitado = nodoOrigen.getAttribute(nombreVisitado);
        if (!visitado) {
            ruta = ruta + nodoOrigen.getId();
        }

        if (nodoOrigen.getId().equalsIgnoreCase(nodoDestino.getId())) {
            encontrado = true;
        }
        if (nodoOrigen.getDegree() > 0 & !visitado & !encontrado) {
            LinkedList<Node> aux = new LinkedList();
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            while (it.hasNext()) {
                Node nodop = it.next();
                if (nodop.getDegree() > 0) {
                    aux.add(nodop);//Obtencion de los hijos del nodo
                }
            }

            pila.addAll(0, aux);//Agrega los hijos al principio en la pila
            nodoOrigen.addAttribute(nombreVisitado, true);
        }
        if (!pila.isEmpty() & encontrado == false) {
            Node n = pila.getFirst(); //Obtencion del primer nodo
            nodoOrigen = n;
            pila.removeFirst();//Eliminacion del primer nodo
            profundidad();//Se repite el proceso para el nodo eliminado
        }
    }

//////////////////Metodo para buscar iterativamente/////////////////////////////
    public void iterativa() {
        numeroIteraciones++;
        visitado = nodoOrigen.getAttribute(nombreVisitado);
        if (!visitado) {
            ruta = ruta + nodoOrigen.getId();
            nodosRecorridos.add(nodoOrigen);
            nodoOrigen.setAttribute(nombreVisitado, true);
        }

        if (nodoOrigen.equals(nodoDestino)) {
            encontrado = true;
            rutaNivel = rutaNivel + "";
        } else if (nodoOrigen.getDegree() > 0 & nivelProfundidad != numeroDeNivel) {
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            while (it.hasNext()) {
                Node elemento = (Node) it.next();
                if (nivelProfundidad <= numeroDeNivel && !encontrado) { //Verifica si el nodo fue visitado y si el nodo aun no es encontrado
                    nivelProfundidad++;//Aumenta un nivel de profundidad
                    nivel = nivelProfundidad;
                    nodoOrigen = elemento;
                    iterativa();
                    nivelProfundidad--;//Disminuye un nivel de profundidad
                }
            }
        }

    }

/////////////////Metodo Bidireccional//////////////////////////////////////////
    public void bidireccional() {
        numeroIteraciones++;
        visitado = nodoOrigen.getAttribute(nombreVisitado);
        if (nodoOrigen.getDegree() > 0 & !visitado & !encontrado) {
            ruta = ruta + nodoOrigen.getId();
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            while (it.hasNext()) {
                Node nodop = it.next();
                if (nodop.getDegree() > 0) {
                    colaBidirecional.add(nodop);
                }
            }
            nodoOrigen.setAttribute(nombreVisitado, true);
        } else {
            encontrado = true;
        }
        if (nodoDestino != null) {
            visitado = nodoDestino.getAttribute(nombreVisitado);
            if (nodoDestino.getDegree() > 0 & !visitado & !encontrado) {
                rutaDestino = rutaDestino + nodoDestino.getId();
                Iterator<Node> it = nodoDestino.getNeighborNodeIterator();
                while (it.hasNext()) {
                    Node nodop = it.next();
                    if (nodop != null) {
                        padreBidireccional.add(nodop);
                    }
                }
                nodoDestino.setAttribute(nombreVisitado, true);
            } else {
                encontrado = true;
            }
        }

        if (!colaBidirecional.isEmpty()) {
            Node n = colaBidirecional.getFirst();
            nodoOrigen = n;
            colaBidirecional.removeFirst();
            if (!padreBidireccional.isEmpty()) {
                nodoDestino = padreBidireccional.getFirst();
                padreBidireccional.removeFirst();
                bidireccional();
            } //else {
            //System.out.println("Camino padre ---" + rutaDestino);
            //System.out.println("Camino raiz ---" + ruta);
            //}
        }
    }

///////////////Metodo CosteUniforme////////////////////////////////////////////    
    public void costoUniforme() {
        if (numeroIteraciones == 0) {
            ruta += nodoOrigen.getId();
        }
        numeroIteraciones++;
        if (nodoOrigen.equals(nodoDestino)) {
            encontrado = true;
        }
        if (nodoOrigen.getDegree() > 0 & encontrado == false) {
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            Iterator<Edge> aristas = nodoOrigen.getEdgeIterator();
            while (it.hasNext()) {
                Node nodop = it.next();
                Edge arista = aristas.next();
                if (arista != null) {
                    double peso = arista.getAttribute("peso");
                    int num = (int) peso;
                    sumar.add(num + suma);
                }
                if (nodop != null) {
                    cola.add(nodop);
                    caminos.add(ruta + nodop.getId());
                }
            }
            int pos = 0;

            if (nivelProfundidad > 0) {
                if (nivelProfundidad > 2)//Para poder comparar cual es el mayor en la suma y eliminar o si no los datos son comparados de manera erronea
                {
                    for (int n = 0; n < sumar.size(); n++) {
                        for (int j = 1; j < sumar.size(); j++) {
                            if ((int) sumar.get(n) > (int) sumar.get(j)) {
                                pos = n;//Para eliminar el nodo con mayor peso que existe

                            }
                        }
                    }
                    if (!cola.isEmpty()) {
                        Node n = cola.get(pos);
                        if (nodoOrigen.equals(nodoDestino)) {
                            suma = (int) sumar.get(pos);
                            ruta = (String) caminos.get(pos);
                            costo = suma;
                            encontrado = true;
                        }
                        cola.remove(pos);
                        sumar.remove(pos);
                        caminos.remove(pos);
                    }
                }
                i = i + 1;
                //ORDENAMIENTO
                int salto, aux, i;
                boolean cambios;
                for (salto = sumar.size() / 2; salto != 0; salto /= 2) {
                    cambios = true;
                    while (cambios) { // Mientras se intercambie algún elemento
                        cambios = false;
                        for (i = salto; i < sumar.size(); i++) // se da una pasada
                        {
                            if ((int) sumar.get(i - salto) > (int) sumar.get(i)) { // y si están desordenados
                                //DE ACUERDO A LA MENOR CANTIDAD EN HEURISTICA Y PESO
                                aux = (int) sumar.get(i); // se reordenan
                                sumar.set(i, (int) sumar.get(i - salto));
                                sumar.set(i - salto, aux);
                                cambios = true; // y se marca como cambio.
                                //ORDENA LA COLA
                                Node auxiliar = cola.get(i); // se reordenan
                                cola.set(i, cola.get(i - salto));
                                cola.set(i - salto, auxiliar);
                                //ORDENA LOS CAMINOS
                                String auxi = (String) caminos.get(i); // se reordenan
                                caminos.set(i, (String) caminos.get(i - salto));
                                caminos.set(i - salto, auxi);
                            }
                        }
                    }
                }

            }
            nodoOrigen.setAttribute(nombreVisitado, true);
        }

        if (!cola.isEmpty() & encontrado == false) {
            Node n = cola.getFirst();
            nodoOrigen = n;
            suma = (int) sumar.getFirst();
            ruta = (String) caminos.getFirst();
            costo = suma;
            cola.removeFirst();
            sumar.removeFirst();
            caminos.removeFirst();
            nivelProfundidad++;
            costoUniforme();
            nivelProfundidad--;

        }

    }

//////////////Metodo Ascenso a la Colina///////////////////////////////////////    
    public void ascenso() {
        numeroIteraciones++;
        ruta = ruta + nodoOrigen.getId();
        double n3 = nodoOrigen.getAttribute(nombreH);
        int heuristica = (int) n3;
        if (heuristica == 0) {
            encontrado = true;
        }
        visitado = nodoOrigen.getAttribute(nombreVisitado);
        if (nodoOrigen.getDegree() > 0 & !visitado & !encontrado) {
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();
            while (it.hasNext()) {
                Node nodop = it.next();
                boolean vp = nodop.getAttribute(nombreVisitado);
                if (!vp) {
                    pila.add(nodop);//Obtencion de los hijos del nodo
                }
            }
            posicion = 0;
            for (int n = 0; n < pila.size() - 1; n++) {
                for (int j = 1; j < pila.size(); j++) {
                    String idnd1 = pila.get(n).getId();
                    String idnd2 = pila.get(j).getId();
                    Node nd1 = grafo.getNode(idnd1);
                    Node nd2 = grafo.getNode(idnd2);

                    double n1 = nd1.getAttribute(nombreH);
                    double n2 = nd2.getAttribute(nombreH);
                    if (n1 > n2) {
                        posicion = j;//Nodo con 2menor heuristica
                    } else {
                        posicion = n;
                    }

                }
            }
        }
        if (!pila.isEmpty()) {
            nodoOrigen.setAttribute(nombreVisitado, true);
            nodoOrigen = pila.get(posicion);
            pila.clear();
            ascenso();
        }
    }

////////////////PRIMERO EL MEJOR///////////////////////////////////////////////
    public void primeroMejor() {
        numeroIteraciones++;
        if (posicion == 0) {
            pila.add(nodoOrigen);
        }
        double np = nodoOrigen.getAttribute(nombreH);
        int npi = (int) np;
        if (npi == 0) {
            encontrado = true;
        }
        visitado=nodoOrigen.getAttribute(nombreVisitado);
        if (!visitado) {
            ruta = ruta + nodoOrigen.getId();
        }
        if (nodoOrigen.getDegree()>0 & !visitado & encontrado == false) {
            nodoOrigen.setAttribute(nombreVisitado, true);
            LinkedList<Node> aux = new LinkedList();//Los hijos del nodo
            Iterator<Node> it = nodoOrigen.getNeighborNodeIterator();

            while (it.hasNext()) {
                Node nodop = it.next();
                visitado=nodop.getAttribute(nombreVisitado);
                if (!visitado) {
                    aux.add(nodop);//Obtencion de los hijos del nodo
                }
            }
            int posMenor = 0;
            for (int j = 1; j < aux.size(); j++) {
                String idN1=aux.get(0).getId();
                String idN2=aux.get(j).getId();
                Node nod1=grafo.getNode(idN1);
                Node nod2=grafo.getNode(idN2);
                double nd1=nod1.getAttribute(nombreH);
                double nd2=nod2.getAttribute(nombreH);
                int ni1=(int)nd1;
                int ni2=(int)nd2;
                if (ni1>ni2 ) {
                    posMenor = j;//Nodo con menor heuristica de los nodos hijos
                }else{
                    posMenor=0;
                }
            }
            Node menorPadre = null;
            if (!aux.isEmpty()) {
                menorPadre = aux.get(posMenor);
            }

            posicion = pila.indexOf(nodoOrigen);//Me devuelve la posicion del nodo en la pila

            if (!pila.isEmpty()) {
                pila.remove(posicion);
                if (pila.size() < posicion) {
                    pila.addAll(aux);
                } else {
                    pila.addAll(posicion, aux);//Agrega los hijos en la posicion del padre
                }
                if (menorPadre != null) {
                    posicion = pila.indexOf(menorPadre);
                    nodoOrigen=pila.get(posicion);
                    primeroMejor();
                }
            }
        }
    }

/////////////Agregar niveles///////////////////////////////////////////////////
    public void agregarNivel() {

        if (nodoOrigen.equals(nodoDestino)) {
            nodoOrigen.addAttribute(nombreNivel, nivel);
            encontrado = true;
        }
        if (nivel == 0) {
            nodoOrigen.addAttribute(nombreNivel, nivel);
            nivel++;
        }

        visitado = nodoOrigen.getAttribute(nombreVisitado);

        if (nodoOrigen.getDegree() > 0 & !encontrado & !visitado) {
            Iterator<Node> iterador = nodoOrigen.getNeighborNodeIterator();
            agregarNivelHijos(iterador);
            Iterator<Node> iterador1 = nodoOrigen.getNeighborNodeIterator();
            LinkedList<Node> aux = new LinkedList<>();
            while (iterador1.hasNext()) {
                Node nodoHijo = iterador1.next();
                aux.add(nodoHijo);
                //nodoOrigen = nodoHijo;
                //agregarNivel();
            }
            hijos.addAll(0, aux);
            nodoOrigen.addAttribute(nombreVisitado, true);

            while (!hijos.isEmpty() & encontrado == false) {
                Node n = hijos.getFirst();
                nodoOrigen = n;
                hijos.removeFirst();
                agregarNivel();
            }

        }

    }

//////Restablecer valores par volver a calcular/////////////////////////////////    
    public void restablecer() {
        for (Node n : grafo) {
            n.setAttribute("visitado", false);
            n.setAttribute(nombreNivel, -1);
        }
        ruta = "";
        encontrado = false;
        nodoOrigen = grafo.getNode(idNodoOrigen);
        nodoDestino = grafo.getNode(idNodoDestino);
        numeroIteraciones = 0;
        nivel = 0;
        factorRamificacion = 0;
        numeroNodos = 1;
        nivelProfundidad = 0;
        rutaNivel = "";
        rutaDestino = "";
        numeroDeNivel = 5;
        suma = 0;
        cola.clear();
        pila.clear();

        //nodoOrigen.setAttribute("nivel", 0);
    }

    public void agregarNivelHijos(Iterator<Node> it) {
        while (it.hasNext()) {
            Node nodoHijo = it.next();
            int numero = nodoHijo.getAttribute(nombreNivel);
            if (numero < 0) {
                nodoHijo.addAttribute(nombreNivel, nivel);
                numeroNodos++;
                factorRamificacion += nodoOrigen.getDegree();
                noCambia = true;
            } else {
                noCambia = false;
            }
        }
        if (noCambia) {
            nivel++;
        }

    }

    public void mostrar() {
        System.out.println();
        System.out.println("Nivel: " + nivel);
        System.out.println("promedio:" + factorRamificacion);
        System.out.println("numero Hijos: " + numeroNodos);
        System.out.println();

    }

    public String getRutaDestino() {
        return rutaDestino;
    }

    public int getFactorRamificacion() {
        return factorRamificacion;
    }

    public int getNivel() {
        return nivel;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public int getNumeroIteraciones() {
        return numeroIteraciones;
    }

    public void obtenerNivelGrafo() {

    }

    public Node getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(Node nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }

    public int getNumeroNodos() {
        return numeroNodos;
    }

}
