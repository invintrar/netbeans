package clasegrafo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;



class MiBoton extends JToggleButton{
    int i;
    int j;
    
    MiBoton(int fil, int col){
        i = fil;
        j = col;
    }
}
public class Principal extends javax.swing.JFrame implements ActionListener {

    int size = 6;
    MiGrafo g;
    MiGrafo2 g2;
    MiBoton [][]botones;
    
    public Principal() {
        initComponents();
        int i, j;
        
        setLayout( new GridLayout(size+1, size+1) );
     
        botones = new MiBoton[size+1][size+1];
        
        for(i = 0; i <= size; ++i ){
            for(j = 0; j <= size; ++j ){
                botones[i][j] = new MiBoton(i,j);
                botones[i][j].setText("0");
                botones[i][j].addActionListener(this);
                
                add(botones[i][j]);
            }
        }
        
        for(i=1; i <= size; ++i ){
            botones[i][0].setText("" + i );
            botones[0][i].setText("" + i );
        }
        
        g2 = new MiGrafo2(size + 1);
            
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        MiBoton b = (MiBoton) e.getSource();
        if( b.getText().equals("0") ){
            b.setText("1");
            g2.agregarArista(b.i, b.j);
        }
        else{
            b.setText("0");
            g2.borrarArista(b.i, b.j);            
        }       
        g2.mostrar_lista();    
    }
}
