package clasegrafo;

public class MiGrafo {
    int [][]matriz;
    int []visitados;
    
    MiGrafo(int n)
    {
        matriz = new int[n][n];
        
        visitados = new int[n];
    }
    
    public void agregarArista(int i, int j){
        matriz[i][j] = 1;
        matriz[j][i] = 1;
    }
    
    public void borrarArista(int i, int j){
        matriz[i][j] = 0;
        matriz[j][i] = 0;
    }
    
    public void mostrar_matriz(){
        int i,j;
        System.out.println();
        for(i = 1; i < matriz.length; ++i ){
            System.out.println();
            for(j = 1; j < matriz.length; ++j ){
                System.out.printf(" %d", matriz[i][j]);
            }
        }
    }
    
    public boolean BF(int nodo, int buscado){
        int i;        
        visitados[nodo] = 1;
        
        if( nodo == buscado ){
            return true;
        }
        
        //seleccionar el siguiente vecino, no visitado
        for(i = 1; i < matriz.length; ++i ){
            if( matriz[nodo][i] == 1 && visitados[i] == 0 ){
                if( BF  (i, buscado) )
                    return true;
            }
        }
  return false;
    }
}
