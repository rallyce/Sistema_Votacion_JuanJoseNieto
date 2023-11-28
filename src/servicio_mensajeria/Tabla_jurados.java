package servicio_mensajeria;

import classes.get_connection;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.NumberFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.text.DocumentFilter;

public class Tabla_jurados extends javax.swing.JFrame {

    public static String nombre_2;

    DefaultTableModel model_1 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }

    };

    public Tabla_jurados() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Tabla de jurados.");

        ImageIcon imag_1 = new ImageIcon("src/images/banner_azulrey.png");

        Icon ic_1 = new ImageIcon(imag_1.getImage().getScaledInstance(jLabel_wallpaper.getWidth(),
                jLabel_wallpaper.getHeight(), Image.SCALE_DEFAULT));

        ImageIcon imag_2 = new ImageIcon("src/images/banner_blancp.png");

        Icon ic_2 = new ImageIcon(imag_2.getImage().getScaledInstance(jLabel_whiteLine.getWidth(),
                jLabel_whiteLine.getHeight(), Image.SCALE_DEFAULT));

        jLabel_wallpaper.setIcon(ic_1);
        jLabel_whiteLine.setIcon(ic_2);

        jFormatted_RegitroMesa.setFont(new Font("Segoe UI", 1, 14));

        jTextField_RegistroEstatus.setText("Jurado");

        try {

            Connection cn_1 = get_connection.connect();
            PreparedStatement pst_1 = cn_1.prepareStatement("select ID, nombre, nombre_usuario, mesa from usuarios where estatus = 'Jurado'");

            jTable1 = new JTable(model_1);
            jScrollPane1.setViewportView(jTable1);

            model_1.addColumn("ID");
            model_1.addColumn("Nombre");
            model_1.addColumn("Nombre usuario");
            model_1.addColumn("Mesa");

            ResultSet rs_1 = pst_1.executeQuery();

            while (rs_1.next()) {

                Object[] rw = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw[i] = rs_1.getObject(i + 1);

                }
                model_1.addRow(rw);
            }

        } catch (SQLException e) {

            System.out.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }

        jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent ev) {

                int fila_tabla = jTable1.rowAtPoint(ev.getPoint());
                int columna_tabla = 1;

                if (fila_tabla > -1) {

                    nombre_2 = (String) model_1.getValueAt(fila_tabla, columna_tabla);

                    try {

                        Connection cn_2 = get_connection.connect();
                        PreparedStatement pst_2 = cn_2.prepareStatement(
                                "select nombre, nombre_usuario, estatus, mesa from usuarios where nombre = '" + nombre_2 + "'");

                        ResultSet rs_2 = pst_2.executeQuery();

                        if (rs_2.next()) {

                            jTextField_nombre.setText(rs_2.getString("nombre"));
                            jTextField_usuario.setText(rs_2.getString("nombre_usuario"));
                            jTextField_estatus.setText(rs_2.getString("estatus"));
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

        this.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_whiteLine = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jFormatted_RegitroMesa = new javax.swing.JFormattedTextField();
        jTextField_RegistroNombre = new javax.swing.JTextField();
        jTextField_RegistroUsuario = new javax.swing.JTextField();
        jTextField_RegistroEstatus = new javax.swing.JTextField();
        jTextField_mesa = new javax.swing.JTextField();
        jTextField_estatus = new javax.swing.JTextField();
        jTextField_usuario = new javax.swing.JTextField();
        jTextField_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("REGISTRO DE NUEVO JURADO");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, -1, -1));

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre de usuario:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Estatus:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 200, -1, -1));

        jLabel11.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Password:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mesa:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, -1, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Estatus:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de usuario:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TABLA DE JURADOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mesa:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 270, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("OPCIONES DE MODIFICACION");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));
        getContentPane().add(jLabel_whiteLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 10, 530));

        jButton5.setBackground(new java.awt.Color(153, 204, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setText("REGISTRAR JURADO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 430, -1, -1));

        jButton4.setBackground(new java.awt.Color(153, 255, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setText("ACTUALIZAR LISTA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, -1));

        jButton3.setBackground(new java.awt.Color(255, 204, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("REINICIAR CONTRASEÑA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 257, -1));

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("ELIMINAR JURADO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, 257, -1));

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("ACTUALIZAR JURADO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 257, -1));

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 375, 220, -1));
        getContentPane().add(jFormatted_RegitroMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 295, 220, -1));

        jTextField_RegistroNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_RegistroNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 85, 220, -1));

        jTextField_RegistroUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_RegistroUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 155, 220, -1));

        jTextField_RegistroEstatus.setEditable(false);
        jTextField_RegistroEstatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_RegistroEstatus.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jTextField_RegistroEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 225, 220, -1));

        jTextField_mesa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 295, 220, -1));

        jTextField_estatus.setEditable(false);
        jTextField_estatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_estatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_estatusActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 225, 220, -1));

        jTextField_usuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 155, 220, -1));

        jTextField_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 85, 250, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 660, 370));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            Connection cn_1 = get_connection.connect();
            PreparedStatement pst_1 = cn_1.prepareStatement("select ID, nombre, nombre_usuario, mesa from usuarios where estatus = 'Jurado'");

            model_1.setRowCount(0);

            ResultSet rs_1 = pst_1.executeQuery();

            while (rs_1.next()) {

                Object[] rw = new Object[4];

                for (int i = 0; i < 4; i++) {

                    rw[i] = rs_1.getObject(i + 1);

                }
                model_1.addRow(rw);
            }

        } catch (SQLException e) {

            System.out.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion a la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String name = jTextField_nombre.getText().trim();

        if (name.equals("")) {

            JOptionPane.showMessageDialog(
                    null, "No ha seleccionado ningún jurado.", "ERROR ESPACIOS VACIOS!", JOptionPane.ERROR_MESSAGE);

        } else {

            new Reinicio_jurado().setVisible(true);

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String name = jTextField_nombre.getText().trim();

        if (name.equals("")) {

            JOptionPane.showMessageDialog(
                    null, "No ha seleccionado ningún jurado.", "ERROR!", JOptionPane.ERROR_MESSAGE);

        } else {

            int option_ok = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este jurado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION);

            if (option_ok == JOptionPane.OK_OPTION) {

                try {

                    Connection cn_4 = get_connection.connect();
                    PreparedStatement pst_4 = cn_4.prepareStatement("delete from usuarios where nombre = '" + Tabla_votantes.nombre + "'");

                    pst_4.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Jurado eliminado de la base de datos.");

                } catch (SQLException e) {
                }

            } else {

                JOptionPane.showMessageDialog(null, "Proceso de eliminacion cancelado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name, user_name, est, mesa_2;

        int validacion = 0;

        name = jTextField_nombre.getText().trim();
        user_name = jTextField_usuario.getText().trim();
        est = jTextField_estatus.getText().trim();
        mesa_2 = jTextField_mesa.getText().trim();

        if (name.equals("")) {

            validacion++;
        }

        if (user_name.equals("")) {

            validacion++;
        }

        if (est.equals("")) {

            validacion++;
        }

        if (mesa_2.equals("")) {

            validacion++;
        }

        try {

            Connection cn_3 = get_connection.connect();
            PreparedStatement pst_3 = cn_3.prepareStatement("update usuarios set nombre = ?, nombre_usuario = ?, estatus = ?, mesa = ? where nombre = '" + nombre_2 + "'");

            if (validacion == 0) {

                pst_3.setString(1, jTextField_nombre.getText().trim());
                pst_3.setString(2, jTextField_usuario.getText().trim());
                pst_3.setString(3, jTextField_estatus.getText().trim());
                pst_3.setString(4, jTextField_mesa.getText().trim());

                pst_3.executeUpdate();
                cn_3.close();

                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente!");
                this.dispose();

            } else {

                JOptionPane.showMessageDialog(
                        null, "No ha seleccionado ningun jurado.", "ERROR ESPACIOS VACIOS!", JOptionPane.ERROR_MESSAGE);

            }

        } catch (SQLException e) {

            System.out.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion en la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombreActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        
        String re_nombre, re_usuario, re_estatus, re_mesa, re_password;
        int validacion = 0, validacion_mesa = 0, validacion_nombre = 0, mesa_int;

        re_nombre = jTextField_RegistroNombre.getText().trim();
        re_usuario = jTextField_RegistroUsuario.getText().trim();
        re_estatus = "Jurado";

        re_mesa = jFormatted_RegitroMesa.getText().trim();
        re_password = jPasswordField1.getText().trim();

        if (re_nombre.equals("")) {

            validacion++;

        }

        if (re_usuario.equals("")) {

            validacion++;

        }

        if (re_mesa.equals("")) {

            validacion++;

        }

        if (re_password.equals("")) {

            validacion++;

        }

        if (!validacion_mesa()) {

            validacion_mesa++;

        }

        if (!validacion_nombre_label()) {

            validacion_nombre++;

        }

        try {

            if (validacion == 0) {

                if (validacion_mesa != 0) {

                    JOptionPane.showMessageDialog(
                            null, "El valor del campo de mesa debe ser un numero entero y sin espacios en blanco!", "ERROR!", JOptionPane.ERROR_MESSAGE);

                } else if (validacion_nombre != 0) {

                    JOptionPane.showMessageDialog(
                            null, "El valor del campo de nombre debe ser solo de caracteres!", "ERROR!", JOptionPane.ERROR_MESSAGE);

                } else {

                    Connection cn_re = get_connection.connect();

                    PreparedStatement pst_re = cn_re.prepareStatement("insert into usuarios values(?,?,?,?,?,?)");

                    mesa_int = Integer.parseInt(re_mesa);

                    pst_re.setInt(1, 0);
                    pst_re.setString(2, re_nombre);
                    pst_re.setString(3, re_usuario);
                    pst_re.setString(4, re_password);
                    pst_re.setString(5, re_estatus);
                    pst_re.setInt(6, mesa_int);

                    pst_re.executeUpdate();
                    cn_re.close();

                    JOptionPane.showMessageDialog(null, "Registro de nuevo estudiante realizado exitosamente!");

                }

            } else {

                JOptionPane.showMessageDialog(
                        null, "Llene todos los campos requeridos para realizar el registro.", "ERROR ESPACIOS VACIOS!", JOptionPane.ERROR_MESSAGE);

            }

        } catch (HeadlessException | NumberFormatException | SQLException e) {

            System.out.println("SQL ERROR! " + e);
            JOptionPane.showMessageDialog(
                    null, "Error con la conexion en la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField_estatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_estatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_estatusActionPerformed

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
            java.util.logging.Logger.getLogger(Tabla_jurados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tabla_jurados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tabla_jurados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tabla_jurados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tabla_jurados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFormattedTextField jFormatted_RegitroMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JLabel jLabel_whiteLine;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_RegistroEstatus;
    private javax.swing.JTextField jTextField_RegistroNombre;
    private javax.swing.JTextField jTextField_RegistroUsuario;
    private javax.swing.JTextField jTextField_estatus;
    private javax.swing.JTextField jTextField_mesa;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_usuario;
    // End of variables declaration//GEN-END:variables

    private boolean validacion_mesa() {

        String registro_mesa = jFormatted_RegitroMesa.getText().trim();

        try {

            Integer.parseInt(registro_mesa);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }

    }

    private boolean validacion_nombre_label() {

        String test_1 = jTextField_nombre.getText().trim();

        for (int i = test_1.length(); i > 0; i--) {

            char c = test_1.charAt(i - 1);

            if (Character.isAlphabetic(c) | c == ' ') {

                return true;

            }

        }

        return false;

    }

}
