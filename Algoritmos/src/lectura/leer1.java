/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lectura;

/**
 *
 * @author Vero
 */
import algoritmos.Arista;
import algoritmos.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class leer1{

    File f = new File("C:\\Users\\Usuario\\Desktop\\Datos.txt");
    BufferedReader entrada;
    Nodo nodo;
    Nodo raiz;
    Nodo padre;
    Nodo origen;
    private LinkedList<Arista> aristas = new LinkedList();
    static List<Nodo> nodos = new ArrayList();
    private int inicio = 0;

    public List<Nodo> obtenerNodos() {
        try {
            entrada = new BufferedReader(new FileReader(f));
            String linea;
            String nombre = "";
            String heuristica = "";
            while (entrada.ready()) {
                linea = entrada.readLine();
                if (linea.split(",").length >= 0) {
                    if (linea.split(",").length > 0) {
      //                  String nodoHeuristica = linea.split(",")[0];
    //                    nombre = nodoHeuristica.split(":")[0];
                        nombre = linea.split(",")[0];
  //                      System.out.println("nombre"+nombre);
//                        heuristica = nodoHeuristica.split(":")[1];
                    } else {
                        //nombre = linea.split(":")[0];
                        //heuristica = linea.split(":")[1];
                    }
                    nodo = buscar(nombre);
                    if (nodo == null) {
                        nodo = new Nodo(nombre);
                    }
//                    nodo.setHeuristica(Integer.parseInt(heuristica));
                    nodos.add(nodo);

                    List<Nodo> hijos = new ArrayList();
                    //List<Arista> aristas = new ArrayList();
                    List<Nodo> padre = new ArrayList();
                    
                    if (linea.split(",").length > 0) {
                        for (int i = 0; i < linea.split(",").length; i++) {
                            String nombre1 = linea.split(",")[i];
                            //String nodoHeuristica = linea.split(",")[i];
                            //String nombre1 = nodoHeuristica.split(":")[0];
                            //String heuristica1 = nodoHeuristica.split(":")[1];
                            //String peso = nodoHeuristica.split(":")[2];
                            //Arista arista=new Arista(nombre1, Integer.parseInt(peso));
                            System.out.println("Nodo"+nombre1);
                            Nodo nodoAux = buscar(nombre1);
                            if (nodoAux != null) {
                                padre=nodoAux.getPadre();
                                if(padre==null){
                                    padre = new ArrayList();
                                }
                                padre.add(nodo);
                                //nodoAux.setHeuristica(Integer.parseInt(heuristica1));
                                nodoAux.setPadre(padre);
                                nodos.add(nodoAux);
                                hijos.add(nodoAux);
                                //aristas.add(arista);
                            } else {
                                nodoAux = new Nodo(nombre1);
                                padre=nodoAux.getPadre();
                                if(padre==null){
                                    padre = new ArrayList();
                                }
                                padre.add(nodo);
                        nodoAux.setPadre(padre);
                                
                                //nodoAux.setHeuristica(Integer.parseInt(heuristica1));
                                nodos.add(nodoAux);
                                hijos.add(nodoAux);
                                //aristas.add(arista);
                            }
                        }
                        nodo.setNodos(hijos);
                        nodo.setAristas(aristas);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println("------------" + nodos.get(0).getNombre());

        mostrarGrafo(nodos.get(0), 0);
        return nodos;
    }
    String datos = "";

    public String mostrarGrafo(Nodo nodo, int nivel) {
        if (nodo != null) {
            datos = datos + "Nivel " + nivel + ": " + nodo.getNombre() + "(" + nodo.getHeuristica() + ")" + "\n";
            System.out.println("Nivel " + nivel + ": " + nodo.getNombre() + "(" + nodo.getHeuristica() + ")" + "\n");
            if (nodo.getNodos() != null) {//Para saber que no es  una hoja
                Iterator<Nodo> it = nodo.getNodos().iterator();
                while (it.hasNext()) {
                    Nodo nodop = it.next();
                    nivel++;
                    mostrarGrafo(nodop, nivel);
                    nivel--;
                }
            } else {
                System.out.println("No tiene hijos");
            }
        }
        return datos;
    }

    public Nodo buscar(String nombre) {
        Nodo nodoBuscado = null;
        for (Iterator<Nodo> iterator = nodos.iterator(); iterator.hasNext();) {
            Nodo next = iterator.next();
            if (next.getNombre().equalsIgnoreCase(nombre)) {
                nodoBuscado = next;
            }
        }
        return nodoBuscado;
    }
    int bus = 0;

    public Nodo buscarorigen(Nodo nodo, String nodobuscado) {
        bus++;
//System.out.println("Nodo" + nodo.nombre );
        if (nodo.getNombre().equalsIgnoreCase(nodobuscado)) {
            //System.out.println("Nodo Encontrado" + nodo.nombre);
            origen = nodo;
            return nodo;
        }
        if (nodo.getNodos() != null & bus < 30) {
            Iterator<Nodo> it = nodo.getNodos().iterator();
            while (it.hasNext()) {
                Nodo nodop = it.next();
                if (nodop != null) {
                    buscarorigen(nodop, nodobuscado);
                }
            }
        }
        return null;
    }
}
