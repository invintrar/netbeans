/**
 *Esta clase simula la creación de grafos aleatorios de acuerdo al modelo G(n,m) para varios valores de n.
 *@author G�mer Gonz�lez
 */
public class Gnm{

	/**
	 *Este m�todo recibe sus par�metros a trav�s de args
	 *@param args Arreglo de objetos tipo String.  Dicho arreglo tendr� 4 datos que representan en su orden:
	 *un n�mero enterno n inicial, un n final, un tama�o de incremento para ir variando el n de su valor inicial
	 *hasta el final, y un tama�o entero que determina cu�ntos grafos aleatorios se generan para un valor de n fijo
	 */
	public static void main(String args[]){

		int i, j;	//variables contadoras
		float Em;	//acumuladora para guardar el tama�o del m m�s peque�o por cada grafo generado

		try{
			//Lee los valores pasados por la consola y los convierte a int
			int n_ini=new Integer(args[0]).intValue();
			int n_fin=new Integer(args[1]).intValue();
			int n_step=new Integer(args[2]).intValue();
			int size=new Integer(args[3]).intValue();
			//Se crea una instancia de esta clase
			Gnm c=new Gnm();
			//la variable j llevar el n de la iteracci�n actual
			j=n_ini;
			while (j<=n_fin){
				//Inicializa el miembro "n" de la clase
				c.n=j;

				Em=0;
				for (i=0;i<size;i++)
					Em+=c.minM();	//acumula los m m�s peque�os obtenidos en cada generaci�n de un grafo aleatorio

				//Imprime el valor del n�mero de nodos y el m m�nimo promedio
				System.out.println(j + "\t" + (Em/(float)size));

				//Avanza un paso
				j+=n_step;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 *Este m�todo va generando un grafo aleatorio seg�n el modelo G(n,m) hasta que sea conexo
	 *@return El n�mero de arcos que componen el grafo al terminar el procedimiento
	 */
	private int minM(){

		int m=0, i, j;	//contadores
		boolean terminar=false;	//determina si se deben seguir a�adiendo arcos

		//Reinicia el grafo G
		G=new boolean[n][n];
		while (!terminar){
			//genera una pareja al azar
			i=(int)Math.floor(n*Math.random());
			j=(int)Math.floor(n*Math.random());
			//sigue generando una pareja si ya exist�a o si i fue igual a j
			while ((i==j) || (G[i][j])){
				i=(int)Math.floor(n*Math.random());
				j=(int)Math.floor(n*Math.random());
			}
			//activa el arco
			G[i][j]=G[j][i]=true;	//La matriz tiene que estar sim�trica todo el tiempo
			m++;	//un arco m�s ha sido a�adido
			if (m>=n-1)	//revisar si es conexo solo si tiene m�s de n-2 arcos
				terminar=IsCurrentGraphConnected();
		}
		return m;
	}

	/**
	 *Este m�todo determina si el grafo G est� conexo en el momento en que se llama el m�todo
	 *@return true si est� conexo, false de lo contrario
	 */
	private boolean IsCurrentGraphConnected(){

		int i;	//recorre cada nodo existente

		//Hacer que ning�n nodo figure como visitado
		visited=new boolean[n];
		//Recorrer en profundidad empezando en el primer nodo
		Traverse(0);
		//Revisar si todos fueron visitados
		for (i=0;(i<n) && (visited[i]);i++);
		return (i==n);
	}

	/**
	 *Este m�todo visita un nodo y escoge un nodo adyacente para visitarlo
	 *@param node �ndice del nodo que se est� visitando
	 */
	private void Traverse(int node){

		int i;	//recorre cada nodo existente

		if (!visited[node]){	//�ya ha sido visitado?
			visited[node]=true;	//marcar a node como visitado
			for (i=0;i<n;i++)
				if ((i!=node) && (G[node][i]))	//visitar a i si es distinto a node y hay un arco en el grafo
					Traverse(i);
		}
	}

	private boolean G[][];		//Matriz booleana que representa el grafo no dirigido
	private boolean visited[];	//Arreglo de nodos que identifica si ya fueron visitados
	private int n;				//N�mero de nodos en la iteraci�n actual

}