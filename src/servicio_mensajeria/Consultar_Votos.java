package servicio_mensajeria;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import classes.get_connection;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class Consultar_Votos extends javax.swing.JFrame {

    DefaultTableModel model_1 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }

    };

    public static String nombre_estudiante_or;
    public static String nombre_estudiante_or_2;
    public static int id_estudiante_or;
    public static int id_estudiante_or_2;

    private int votos;

    DefaultTableModel model_2 = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {

            return false;
        }

    };

    public Consultar_Votos() {
        initComponents();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Consulta y Opciones de votación.");

        ImageIcon imag_1 = new ImageIcon("src/images/banner_azulrey.png");

        ImageIcon imag_2 = new ImageIcon("src/images/banner_blancp.png");

        Icon ic_1 = new ImageIcon(imag_1.getImage().getScaledInstance(jLabel_wallpaper.getWidth(),
                jLabel_wallpaper.getHeight(), Image.SCALE_DEFAULT));

        Icon ic_2 = new ImageIcon(imag_2.getImage().getScaledInstance(jLabel_whiteLine.getWidth(),
                jLabel_whiteLine.getHeight(), Image.SCALE_DEFAULT));

        jLabel_wallpaper.setIcon(ic_1);
        jLabel_whiteLine.setIcon(ic_2);

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

                    nombre_estudiante_or = (String) model_1.getValueAt(fila_tabla, columna_tabla);
                    id_estudiante_or = (int) model_1.getValueAt(fila_tabla, 0);

                    try {

                        Connection cn_2 = get_connection.connect();
                        PreparedStatement pst_2 = cn_2.prepareStatement(
                                "select nombre, mesa from est_no_voto where nombre = '" + nombre_estudiante_or + "'");

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

        jTextField_YaEstatus.setText("Estudiante");

        jTable2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent ev) {

                int fila_tabla = jTable2.rowAtPoint(ev.getPoint());
                int columna_tabla = 1;

                if (fila_tabla > -1) {

                    nombre_estudiante_or_2 = (String) model_2.getValueAt(fila_tabla, columna_tabla);
                    id_estudiante_or_2 = (int) model_2.getValueAt(fila_tabla, 0);

                    try {

                        Connection cn_5 = get_connection.connect();
                        PreparedStatement pst_2 = cn_5.prepareStatement(
                                "select nombre, mesa from est_vota where nombre = '" + nombre_estudiante_or_2 + "'");

                        ResultSet rs_2 = pst_2.executeQuery();

                        if (rs_2.next()) {

                            jTextField_YaNombre.setText(rs_2.getString("nombre"));
                            jTextField_YaMesa.setText(rs_2.getString("mesa"));

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton_actualizar = new javax.swing.JButton();
        jButton_AnularVoto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_mesa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_YaMesa = new javax.swing.JTextField();
        jTextField_YaEstatus = new javax.swing.JTextField();
        jTextField_YaNombre = new javax.swing.JTextField();
        jTextField_votos = new javax.swing.JTextField();
        jButton_registrarVoto = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel_whiteLine = new javax.swing.JLabel();
        jLabel_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO DE VOTOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Estudiantes faltantes por votar:");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Estudiantes que ya votaron:");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

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

        jButton_actualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_actualizar.setText("ACTUALIZAR LISTAS");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, -1, -1));

        jButton_AnularVoto.setBackground(new java.awt.Color(255, 153, 153));
        jButton_AnularVoto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_AnularVoto.setText("ANULAR VOTO");
        jButton_AnularVoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnularVotoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_AnularVoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 580, -1, -1));

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PROCESO PARA REGISTRAR VOTOS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Seleccione un estudiante de la lista de estudiantes que faltan por votar.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 675, -1, -1));

        jTextField_nombre.setEditable(false);
        jTextField_nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 730, 360, -1));

        jLabel5.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 710, -1, -1));

        jLabel6.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mesa:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, -1, -1));

        jTextField_mesa.setEditable(false);
        jTextField_mesa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_mesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 730, 110, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("VOTOS:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 795, -1, -1));

        jTextField_YaMesa.setEditable(false);
        jTextField_YaMesa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_YaMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 530, 250, -1));

        jTextField_YaEstatus.setEditable(false);
        jTextField_YaEstatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_YaEstatus.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(jTextField_YaEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 460, 250, -1));

        jTextField_YaNombre.setEditable(false);
        jTextField_YaNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(jTextField_YaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 390, 250, -1));

        jTextField_votos.setEditable(false);
        jTextField_votos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(jTextField_votos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 790, 70, -1));

        jButton_registrarVoto.setBackground(new java.awt.Color(153, 255, 204));
        jButton_registrarVoto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_registrarVoto.setText("REGISTRAR VOTO");
        jButton_registrarVoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registrarVotoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_registrarVoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 860, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Advertencia: Proceso solo admitido");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 640, -1, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mesa:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 510, -1, -1));

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Estatus:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 440, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("para organizadores.");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 660, -1, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("PROCESO DE ANULACIÓN");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 250, -1, -1));

        jLabel13.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nombre:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 370, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Seleccione un estudiante de la lista");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("que ya votaron.");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 310, -1, -1));
        getContentPane().add(jLabel_whiteLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 10, 940));
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 940));

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

                        pst_3.setInt(1, id_estudiante_or);
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

                        PreparedStatement pst_4 = cn_4.prepareStatement("delete from est_no_voto where nombre = '" + nombre_estudiante_or + "'");

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

    private void jButton_AnularVotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnularVotoActionPerformed

        String anu_nombre, anu_mesa, user_or, password_or = "";

        int validacion = 0, validacion_2 = 0;

        anu_nombre = jTextField_YaNombre.getText().trim();
        anu_mesa = jTextField_YaMesa.getText().trim();

        int ok_option = JOptionPane.showConfirmDialog(null, "                                          Aviso: Se necesitan permisos de Organizador para continuar. \n "
                + "Una vez que se anule el voto, tendrá que volver a registrar al estudiante para ejercer de nuevo el dereclho al voto. \n"
                + "                                                                                       Desea continuar? ",
                "Advertenia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Ingrese su contraseña de cuenta de organizador:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"Aceptar", "Cancelar"};

        if (ok_option == JOptionPane.OK_OPTION) {
            
           
            user_or = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario de su cuenta de Organizador", "Usuario de Organizador", JOptionPane.QUESTION_MESSAGE);

            int option = JOptionPane.showOptionDialog(null, panel, "Contraseña Organizador",
                    JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[1]);

            password_or = pass.getText().trim();

            try {
                
                
                Connection cn_anu = get_connection.connect();
                
                

                PreparedStatement pst_anu = cn_anu.prepareStatement("select estatus from usuarios where nombre_usuario = '" + user_or + "' and pass_usuario = '" + password_or + "'");

                
                
                ResultSet rs_anu = pst_anu.executeQuery();
                
                

                if (rs_anu.next()) {

                    String estatus_st = rs_anu.getString("estatus");
                    
                   

                    if (estatus_st.equals("Organizador")) {

                        int continue_op = JOptionPane.showConfirmDialog(null, "Proceder con el proceso de anulacion?", "Advertencia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (continue_op == JOptionPane.OK_OPTION) {
                            
                            cn_anu.close();

                            if (anu_nombre.equals("") | anu_mesa.equals("")) {

                                validacion_2++;

                            }

                            try {

                                if (validacion_2 == 0) {

                                    Connection cn_anu_2 = get_connection.connect();

                                    PreparedStatement pst_anu_2 = cn_anu_2.prepareStatement("delete from est_vota where nombre = '" + anu_nombre + "'");

                                    pst_anu_2.executeUpdate();

                                    JOptionPane.showMessageDialog(null, "Voto anulado, actualize las listas para ver los cambios.", "AVISO.", JOptionPane.INFORMATION_MESSAGE);

                                } else {

                                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningún estudiante de la lista de los que ya votaron.", "ERROR!", JOptionPane.ERROR_MESSAGE);

                                }

                            } catch (SQLException e) {

                                System.out.println("SQL ERROR! " + e);
                                JOptionPane.showMessageDialog(
                                        null, "Error con la conexion en la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Proceso de anulación de voto cancelado.", "AVISO!", JOptionPane.WARNING_MESSAGE);

                        }

                    } else {

                        JOptionPane.showMessageDialog(null, "Datos de usuario o contraseña incorrectos, o usted no es un Organizador. \n"
                                + "                                           Compruebe los datos.", "ERROR!", JOptionPane.ERROR_MESSAGE);

                    }

                } else {

                   JOptionPane.showMessageDialog(null, "Datos de usuario o contraseña incorrectos, o usted no es un Organizador. \n"
                                + "                                           Compruebe los datos.", "ERROR!", JOptionPane.ERROR_MESSAGE);

                }

            } catch (HeadlessException | SQLException e) {

                System.out.println("QUE PASA! " + e);
                JOptionPane.showMessageDialog(
                        null, "Error con la conexion en la base de datos, contacte al programador", "ERROR BASE DE DATOS!", JOptionPane.ERROR_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(null, "Proceso de anulación de voto cancelado.", "AVISO!", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_jButton_AnularVotoActionPerformed

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
            java.util.logging.Logger.getLogger(Consultar_Votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Votos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultar_Votos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AnularVoto;
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_registrarVoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField_YaEstatus;
    private javax.swing.JTextField jTextField_YaMesa;
    private javax.swing.JTextField jTextField_YaNombre;
    private javax.swing.JTextField jTextField_mesa;
    private javax.swing.JTextField jTextField_nombre;
    private javax.swing.JTextField jTextField_votos;
    // End of variables declaration//GEN-END:variables
}
