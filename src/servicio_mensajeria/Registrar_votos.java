/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package servicio_mensajeria;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import classes.get_connection;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Registrar_votos extends javax.swing.JFrame {

    DefaultTableModel model_1 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }

    };

    DefaultTableModel model_2 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }

    };

    public static String nombre_estudiante;
    public static int id_estudiante;
    private int votos;

    public Registrar_votos() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Registro de votos de estudiantes");

        ImageIcon imag_1 = new ImageIcon("src/images/banner_azulrey.png");

        Icon ic_1 = new ImageIcon(imag_1.getImage().getScaledInstance(jLabel_wallpaper.getWidth(),
                jLabel_wallpaper.getHeight(), Image.SCALE_DEFAULT));

        jLabel_wallpaper.setIcon(ic_1);

        repaint();

        try {

            jTable1 = new JTable(model_1);
            jScrollPane1.setViewportView(jTable1);

            model_1.addColumn("ID");
            model_1.addColumn("Nombre del Estudiante");
            model_1.addColumn("Estatus");
            model_1.addColumn("Mesa");

            Connection cn = get_connection.connect();

            PreparedStatement pst = cn.prepareStatement("select ID, nombre, estatus, mesa from est_no_voto");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Object[] rw_1 = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw_1[i] = rs.getObject(i + 1);

                }
                model_1.addRow(rw_1);
            }

        } catch (SQLException e) {

            System.err.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }

        try {

            jTable2 = new JTable(model_2);
            jScrollPane2.setViewportView(jTable2);

            model_2.addColumn("ID");
            model_2.addColumn("Nombre del Estudiante");
            model_2.addColumn("Estatus");
            model_2.addColumn("Mesa");

            Connection cn_2 = get_connection.connect();

            PreparedStatement pst_2 = cn_2.prepareStatement("select ID, nombre, estatus, mesa from est_vota");

            ResultSet rs_2 = pst_2.executeQuery();

            while (rs_2.next()) {

                Object[] rw_2 = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw_2[i] = rs_2.getObject(i + 1);

                }
                model_2.addRow(rw_2);
            }

        } catch (SQLException e) {

            System.err.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }

        votos = model_2.getRowCount();
        String votos_string = Integer.toString(votos);

        jTextField_votos.setText(votos_string);

        jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent ev) {

                int fila_tabla = jTable1.rowAtPoint(ev.getPoint());
                int columna_tabla = 1;

                if (fila_tabla > -1) {

                    nombre_estudiante = (String) model_1.getValueAt(fila_tabla, columna_tabla);
                    id_estudiante = (int) model_1.getValueAt(fila_tabla, 0);

                    try {

                        Connection cn_2 = get_connection.connect();
                        PreparedStatement pst_2 = cn_2.prepareStatement(
                                "select nombre, mesa from est_no_voto where nombre = '" + nombre_estudiante + "'");

                        ResultSet rs_2 = pst_2.executeQuery();

                        if (rs_2.next()) {

                            jTextField_nombre.setText(rs_2.getString("nombre"));
                            jTextField_mesa.setText(rs_2.getString("mesa"));

                        }

                    } catch (SQLException e) {

                        System.out.println("SQL ERROR! " + e);
                        JOptionPane.showMessageDialog(
                                null, "Error con la conexion en la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_registrarVoto = new javax.swing.JButton();
        jButton_actualizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField_mesa = new javax.swing.JTextField();
        jTextField_nombre = new javax.swing.JTextField();
        jTextField_votos = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton_registrarVoto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_registrarVoto.setText("REGISTRAR VOTO");
        jButton_registrarVoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registrarVotoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_registrarVoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 860, -1, -1));

        jButton_actualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_actualizar.setText("ACTUALIZAR LISTAS");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, -1, -1));

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mesa:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, -1, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 710, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estudiantes que ya votaron:");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estudiantes faltantes por votar:");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE VOTOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 440, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROCESO PARA REGISTRAR VOTOS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, -1, -1));

        jTextField_mesa.setEditable(false);
        jTextField_mesa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 730, 110, -1));

        jTextField_nombre.setEditable(false);
        jTextField_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 730, 360, -1));

        jTextField_votos.setEditable(false);
        jTextField_votos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(jTextField_votos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 790, 70, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("VOTOS:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 795, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Seleccione un estudiante de la lista de estudiantes que faltan por votar.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 675, -1, -1));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 940));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed

        try {

            model_1.setRowCount(0);

            Connection cn = get_connection.connect();

            PreparedStatement pst = cn.prepareStatement("select ID, nombre, estatus, mesa from est_no_voto");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Object[] rw_1 = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw_1[i] = rs.getObject(i + 1);

                }
                model_1.addRow(rw_1);
            }

        } catch (SQLException e) {

            System.err.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }

        try {

            model_2.setRowCount(0);

            Connection cn_2 = get_connection.connect();

            PreparedStatement pst_2 = cn_2.prepareStatement("select ID, nombre, estatus, mesa from est_vota");

            ResultSet rs_2 = pst_2.executeQuery();

            while (rs_2.next()) {

                Object[] rw_2 = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw_2[i] = rs_2.getObject(i + 1);

                }
                model_2.addRow(rw_2);
            }

        } catch (SQLException e) {

            System.err.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }
        
        votos = model_2.getRowCount();
        String votos_string = Integer.toString(votos);

        jTextField_votos.setText(votos_string);

    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jButton_registrarVotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registrarVotoActionPerformed

        String name_1, mesa_string;

        int mesa_int, validacion = 0;

        name_1 = jTextField_nombre.getText().trim();
        mesa_string = jTextField_mesa.getText().trim();

        if (name_1.equals("")) {

            validacion++;

        }

        if (mesa_string.equals("")) {

            validacion++;

        }

        try {

            if (validacion == 0) {

                int ok_option = JOptionPane.showConfirmDialog(null, """
                                                                    Advertencia: Una vez que registre el voto no podr\u00e1 anularlo, tendra que acudir al organizador para resolver cualquier problema. 
                                                                    
                                                                                                                                                               Desea continuar?""", "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ok_option == JOptionPane.OK_OPTION) {

                    try {

                        mesa_int = Integer.parseInt(mesa_string);

                        Connection cn_3 = get_connection.connect();

                        PreparedStatement pst_3 = cn_3.prepareStatement("insert into est_vota values(?,?,?,?)");

                        pst_3.setInt(1, id_estudiante);
                        pst_3.setString(2, name_1);
                        pst_3.setString(3, "Estudiante");
                        pst_3.setInt(4, mesa_int);

                        pst_3.executeUpdate();

                        cn_3.close();

                    } catch (NumberFormatException | SQLException e) {
                        System.err.println("SQL ERROR! " + e);
                        JOptionPane.showMessageDialog(
                                null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

                    }

                    try {

                        Connection cn_4 = get_connection.connect();

                        PreparedStatement pst_4 = cn_4.prepareStatement("delete from est_no_voto where nombre = '" + nombre_estudiante + "'");

                        pst_4.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Registro de voto realizado con exito. Actualize las listas para ver los cambios.");

                    } catch (HeadlessException | SQLException e) {

                        System.err.println("SQL ERROR! " + e);
                        JOptionPane.showMessageDialog(
                                null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Registro de voto cancelado", "Aviso", JOptionPane.WARNING_MESSAGE);

                }

            } else {

                JOptionPane.showMessageDialog(null, "Llene todos los campos requeridos para efectuar el registro.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (HeadlessException e) {

            System.err.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }

        
    }//GEN-LAST:event_jButton_registrarVotoActionPerformed

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
            java.util.logging.Logger.getLogger(Registrar_votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrar_votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrar_votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrar_votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrar_votos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_registrarVoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField_mesa;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_votos;
    // End of variables declaration//GEN-END:variables
}
