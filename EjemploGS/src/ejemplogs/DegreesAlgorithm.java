/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplogs;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.*;


/**
 *
 * @author Darwin
 */
public class DegreesAlgorithm implements Algorithm{
    Graph theGraph;
    int minDegree, maxDegree,avDegree;
    public void init(Graph graph){
        theGraph=graph;
    }
    public void compute(){
        avDegree=0;
        minDegree=Integer.MAX_VALUE;
        maxDegree=0;
        
        for(Node n: theGraph.getEachNode()){
            int deg =n.getDegree();
            
            minDegree=Math.min(minDegree, deg);
            maxDegree=Math.max(maxDegree, deg);
            avDegree += deg;
        }
        avDegree /=theGraph.getNodeCount();
    }
    
    
}
