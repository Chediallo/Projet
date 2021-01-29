/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.ugb.ipsl.gui_gestion_etudiant;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public final class ListeEtudiantClasse extends javax.swing.JPanel {

    Connecter conn;
    Statement stm1, stm2;
    ResultSet Rs1, Rs2, Rs3, Rs4;
    final Return filiere;
    final Return niveau;
    int nbEtudiant;

    /**
     * Creates new form ListeEtudiantClasse
     * @throws java.sql.SQLException
     */
    @SuppressWarnings("empty-statement")
    public ListeEtudiantClasse() throws SQLException {

        initComponents();
        filiere = new Return("Toute les filières");
        niveau = new Return("Tout les niveaux");
        initComponents();
        stm1= conn.obtenirconnexion().createStatement();
        
        try {
            Rs1= stm1.executeQuery("select * from guiprojet.filiere");
        } catch (SQLException ex) {
            Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
      

        String data[] = new String[10];
        data[0] = "Toute les filliéres";
        int i = 1;
        try {
            while (Rs1.next()) {
                String nom = Rs1.getString("nom");
                data[i] = nom;
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
        }

        FiliereCombo.setModel(new javax.swing.DefaultComboBoxModel<>(data));

        afficherTable();
    }
    public void afficherTable() throws SQLException{
       
       stm2=conn.obtenirconnexion().createStatement();
       String querry = "";
       FiliereCombo.addActionListener((ActionEvent e) -> {
           filiere.value = FiliereCombo.getSelectedItem().toString();
           try {
               afficherTable();
           } catch (SQLException ex) {
               Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       NiveauCombo.addActionListener((ActionEvent e) -> {
           niveau.value = NiveauCombo.getSelectedItem().toString();
           try {
               afficherTable();
           } catch (SQLException ex) {
               Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
       
        try {
            Rs2 = stm2.executeQuery("select id from guiprojet.filiere where Nom = '"+filiere.value+"'");
            Rs2.next();
        } catch (SQLException ex) {
            Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       if (!(filiere.value.equalsIgnoreCase("Toute les filières")) && !(niveau.value.equalsIgnoreCase("Tout les niveaux"))){
           System.out.println(niveau);
           try {
               querry = "select * from guiprojet.etudiant where idfiliere = "+Rs2.getInt("id")+" and niveau = '"+niveau.value+"'";
           } catch (SQLException ex) {
               Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else if (!filiere.value.equals("Toute les filliéres") && niveau.value.equals("Tout les niveaux")){
           try {
               querry = "select * from guiprojet.etudiant where idfiliere = "+Rs4.getInt("id")+"";
           } catch (SQLException ex) {
               Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else if (filiere.value.equals("Toute les filliéres") && !niveau.value.equals("Tout les niveaux")){
           querry = "select * from guiprojet.etudiant where niveau = '"+niveau.value+"'";
       }else {querry = "select * from guiprojet.etudiant";}
      
        try {
            Rs3 = conn.obtenirconnexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(querry);
            Rs3.last(); nbEtudiant = Rs3.getRow(); Rs3.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        String colunm [] = {"Id","Prenom","Nom","Date de naissance","Filiere","Niveau"};
        String data[][] = new String[nbEtudiant][6];
       
        int i = 0;
        try {
            while (Rs3.next()) {
                String id = Rs3.getString("Id");
                String prenom = Rs3.getString("Prenom");
                String nom = Rs3.getString("Nom");
                String date_naissance = Rs3.getString("date_naissance");
                int fil = Rs3.getInt("filliere");
                String niveau = Rs3.getString("niveau");
                stm1 = conn.obtenirconnexion().createStatement();
                Rs4 = stm1.executeQuery(" select nom from guiprojet.filiere where id = "+fil+"  ");
                Rs4.next();
                String filliere = Rs4.getString("nom");
                data[i][0] =id ;
                data[i][1] = prenom;
                data[i][2] = nom;
                data[i][3] = date_naissance;
                data[i][4] = filliere;
                data[i][5] = niveau;
                i++;
            } } catch (SQLException ex) {
            Logger.getLogger(ListeEtudiantClasse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Table.setModel(new javax.swing.table.DefaultTableModel(data,colunm));
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        NiveauCombo = new javax.swing.JComboBox<>();
        FiliereCombo = new javax.swing.JComboBox<>();

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Table);

        NiveauCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        FiliereCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(FiliereCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NiveauCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NiveauCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FiliereCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> FiliereCombo;
    private javax.swing.JComboBox<String> NiveauCombo;
    private javax.swing.JTable Table;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
