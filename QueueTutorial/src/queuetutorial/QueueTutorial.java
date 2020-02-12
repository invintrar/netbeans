/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuetutorial;

import java.util.*;

/**
 *
 * @author Darwin
 */
public class QueueTutorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Queue<String> q1 = new LinkedList<>();

        q1.offer("Cat");
        q1.offer("Dog");
        q1.offer("Fish");
        q1.offer("Bear");
        if (q1.contains("Fish")) {
            q1.offer("cat1");
        } else {
            q1.offer("cat2");
        }
        while(!q1.isEmpty()){
            System.out.println(q1.poll());
        }
        
        /*int tamanoCola=q1.size();        
        for (int i = 0; i < tamanoCola; i++) {
            System.out.println(q1.poll());
        }*/
    }

}
