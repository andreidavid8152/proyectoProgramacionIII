import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import datechooser.beans.DateChooserCombo;
import org.jfree.chart.*;
import org.jfree.data.category.*;

public class app extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPaneMenu;
    private JTextField textFieldMontoIngreso;
    private JComboBox comboBoxIngreso;
    private JTextArea textAreaDescripcionIngreso;
    private JButton ingresarButtonIngreso;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTextField textFiedlPresupuestoTotal;
    private JButton asignarButton;
    private JButton mostrarButton;
    private JTextField textFieldMontoGasto;
    private JComboBox comboBoxGasto;
    private JTextArea JTextAreaDescripcionGasto;
    private JButton ingresarButtonGasto;
    private JTabbedPane tabbedPaneMostrarCategorias;
    private JTextField textFieldNombreCategoria;
    private JButton crearCategoriaButton;
    private JButton mostrarCategoriasButton;
    private JButton generarDatosButton;
    private JTextArea textAreaGenerarDatosCate;
    private JComboBox comboBoxTipoCategoria;
    private JTextArea textAreaMostrarCategorias;
    private JComboBox comboBoxMostrarCategoria;
    private JCheckBox aZCheckBox;
    private JCheckBox zACheckBox;
    private JTextField textFieldNombreEditarCategoria;
    private JButton buscarButton;
    private JTextField textFieldNuevoNombreEditarCategoria;
    private JButton editarCategoriaButton;
    private JTextField textFieldNombreEliminarCategoria;
    private JButton buscarButtonEliminarCategoria;
    private JLabel labelPresupuestoTotal;
    private JComboBox comboBoxPresupuestoGastos;
    private JButton asignarPresupuestoGastoButton;
    private JTextField textFieldMontoAsignarCatGasto;
    private JLabel presupuestoCategoriaLabel;
    private JComboBox comboBoxMostrarTransacciones;
    private JTextField textFieldEditarCategoria;
    private JButton buscarButtonEditarTransaccion;
    private JLabel idTransaccion;
    private JTextField idTransaccionLabel;
    private JTextField textFieldMontoEditarTransaccion;
    private JTextArea textAreaEditarDescripTransaccion;
    private JButton actualizarButton;
    private JTextArea textArea1;
    private JTextField nombreCategoriaEliminar;
    private JTextField eliminarIdTransaccion;
    private JButton eliminarButton;
    private JCheckBox ascendenteCheckBox;
    private JCheckBox descendenteCheckBox;
    private JButton aumentarPresupuestoButton;
    private JTextField textFieldPresupuestoActual;
    private JButton aumentarPresupuestoCategoriaButton;
    private JTextField textFieldTasaImpuesto;
    private JTextField textFieldTasaDeduccion;
    private JLabel ingresosIngreso;
    private JLabel ingresoImpuestos;
    private JPanel gastoImpuestos;
    private JLabel gastosImpuestosLabel;
    private JComboBox comboBoxMostrarTransaccionCampo;
    private JTextField textFieldTasaImpuestoDeduccion;
    private JButton generarInformeIngresosButton;
    private JButton generarInformeGastosButton1;
    private JComboBox comboBoxInformesIngresos;
    private JComboBox comboBoxInformesGastos;
    private JLabel usuarioLabel;
    private JPanel usuariosPanel;
    private JTabbedPane tabbedUsuarios;
    private JTextField textFieldNombreUsuarioCrear;
    private JButton crearUsuarioButton;
    private JPasswordField passwordFieldCrear;
    private JTextField textFieldUsuarioEditar;
    private JButton buscarUsuarioButton;
    private JTextField textFieldNombreNuevoEditar;
    private JButton actualizarUsuarioButton;
    private JPasswordField passwordFieldEditar;
    private JTextField textFieldNombreEliminar;
    private JButton eliminarUsuarioButton;
    private JButton mostrarUsuariosButton;
    private JTextArea textAreaMostrarUsuarios;
    private JButton salirButton;
    private JLabel dateLabel;
    private JLabel saldoLabel;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldMontoRegistrarPago;
    private JComboBox comboBoxMonedaRegistrar;
    private JTextField textFieldFrecuenciaRegistrar;
    private JTextArea textAreaDescripcionPagoRegistrar;
    private JCheckBox siPagadoRegistrarCheckBox;
    private JCheckBox noPagadoRegistrarCheckBox;
    private JButton registrarButton;
    private JTextField textFieldIdEditarPago;
    private JTextField textFieldMontoEditarPago;
    private JComboBox comboBoxMonedasEditar;
    private JButton buscarButton1;
    private JTextArea textAreaEditarPago;
    private JButton editarButton;
    private JTextField textFieldIdEliminarPago;
    private JButton eliminarButton1;
    private JComboBox comboBox3;
    private JTextArea textAreaMostrarPagosRecurrentees;
    private JTextField textFieldFrecuenciaMesesEditar;
    private DateChooserCombo fechaRegistro;
    private DateChooserCombo fechaEditarPago;
    private JButton diaButton;
    private JButton mesButton;
    private JButton MOSTRARButton;
    private JComboBox comboBox1;
    private JButton generarInformeButton;
    private JComboBox comboboxRegistrarPagoDeudasPr;
    private JComboBox comboBoxPrestamosInforme;
    private JButton generarInformeButtonPrestamos;
    private JComboBox comboBoxDeudasInforme;
    private JButton generarInformeButtonDeudas;
    private JTextField montoRDP;
    private JComboBox monedaRDP;
    private JTextField plazoRDP;
    private DateChooserCombo fechaRDP;
    private JTextArea descripcionRDP;
    private JCheckBox registroRDP;
    private JCheckBox noRDP;
    private JButton registrarRDP;
    private JComboBox comboBoxCategoriaEDP;
    private JComboBox comboBoxDEDP;
    private JComboBox comboBoxCategoriaMDP;
    private JTextField idEDP;
    private JButton buscarEDP;
    private JTextField montoEDP;
    private JComboBox monedaEDP;
    private DateChooserCombo fechaEDP;
    private JTextArea descripcionEDP;
    private JTextField plazosEDP;
    private JButton editarEDP;
    private JTextField idEEDP;
    private JButton eliminarEEDP;
    private JTextArea textAreaMostrarDeudasPrestamos;
    private JComboBox estadoPagoMDP;
    private JButton mostrarPagoMFP;
    private JButton quemarDatosButton;
    private JTextArea textAreaTransaccionesQuemado;
    private JComboBox comboBoxCategoriaEditarTransaccion;
    private GestionFinanciera sistema = new GestionFinanciera();
    private SistemaLogin sistemaLogin;
    public static LocalDate dia;

    public app(String user, SistemaLogin system) {

        dia = LocalDate.now();
        dateLabel.setText(dia.toString());

        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));


        sistemaLogin = system;
        usuarioLabel.setText("Usuario: " + user);
        setContentPane(panel1);

        textFieldMontoIngreso.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoIngreso.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        ingresarButtonIngreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarIngreso();
            }
        });
        mostrarCategoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCategoriasNombre();
            }
        });
        crearCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCategoria();
            }
        });
        comboBoxTipoCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxTipoCategoria.getSelectedItem().equals("Ingreso")) {
                    activarCamposCategorias(true);
                } else if (comboBoxTipoCategoria.getSelectedItem().equals("Gasto")) {
                    activarCamposCategorias(true);
                }else if(comboBoxTipoCategoria.getSelectedItem().equals("Prestamo")){
                    activarCamposCategorias(true);
                }else if(comboBoxTipoCategoria.getSelectedItem().equals("Deuda")){
                    activarCamposCategorias(true);
                }else {
                    activarCamposCategorias(false);
                }
            }
        });
        generarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarCategorias();
            }
        });
        ingresarButtonGasto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarGasto();
            }
        });
        aZCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (aZCheckBox.isSelected()) {
                    zACheckBox.setSelected(false);
                }
            }
        });
        zACheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (zACheckBox.isSelected()) {
                    aZCheckBox.setSelected(false);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCategoriaEditar();
            }
        });
        editarCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarNombreCategoria();
            }
        });
        asignarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarPresupuestoGeneral();
            }
        });
        textFiedlPresupuestoTotal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFiedlPresupuestoTotal.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        comboBoxPresupuestoGastos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (comboBoxPresupuestoGastos.getSelectedItem() != null) {
                    if (!comboBoxPresupuestoGastos.getSelectedItem().toString().equals("")) {
                        textFieldMontoAsignarCatGasto.setEditable(true);
                        asignarPresupuestoGastoButton.setEnabled(true);
                        aumentarPresupuestoCategoriaButton.setEnabled(true);
                    } else {
                        textFieldMontoAsignarCatGasto.setEditable(false);
                        asignarPresupuestoGastoButton.setEnabled(false);
                        aumentarPresupuestoCategoriaButton.setEnabled(false);
                    }
                }

            }
        });
        asignarPresupuestoGastoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarPresupuestoCategoria();
            }
        });
        textFieldMontoAsignarCatGasto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoAsignarCatGasto.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        comboBoxGasto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedCategory = (String) comboBoxGasto.getSelectedItem();
                if (selectedCategory != null && !selectedCategory.equals("")) {
                    CategoriaGasto selectedCategoriaGasto = sistema.getCategoriasGasto().get(selectedCategory);
                    if (selectedCategoriaGasto != null) {
                        presupuestoCategoriaLabel.setText(String.format("Presupuesto Categoria: %.2f", selectedCategoriaGasto.getPresupuesto()));
                        gastosImpuestosLabel.setText(String.format("%.2f", selectedCategoriaGasto.getImpuestos()));
                    }
                }else{
                    presupuestoCategoriaLabel.setText("Presupuesto Categoria: " + "0,0");
                    gastosImpuestosLabel.setText("0,0");
                }
            }
        });
        buscarButtonEliminarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });
        buscarButtonEditarTransaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEditarTransaccion();
            }
        });
        idTransaccionLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEditarTransaccion();
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTransaccionesCategoria();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTransaccion();
            }
        });
        eliminarIdTransaccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        textFieldMontoEditarTransaccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoEditarTransaccion.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        ascendenteCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ascendenteCheckBox.isSelected()) {
                    comboBoxMostrarTransaccionCampo.setEnabled(true);
                    descendenteCheckBox.setSelected(false);
                }else if(!descendenteCheckBox.isSelected()){
                    comboBoxMostrarTransaccionCampo.setEnabled(false);
                }
            }
        });
        descendenteCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendenteCheckBox.isSelected()) {
                    comboBoxMostrarTransaccionCampo.setEnabled(true);
                    ascendenteCheckBox.setSelected(false);
                }else if(!ascendenteCheckBox.isSelected()){
                    comboBoxMostrarTransaccionCampo.setEnabled(false);
                }
            }
        });
        aumentarPresupuestoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarPresupuestoGeneral();
            }
        });
        comboBoxPresupuestoGastos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedCategory = (String) comboBoxPresupuestoGastos.getSelectedItem();
                if (selectedCategory != null && !selectedCategory.equals("")) {
                    CategoriaGasto selectedCategoriaGasto = sistema.getCategoriasGasto().get(selectedCategory);
                    if (selectedCategoriaGasto != null) {
                        textFieldPresupuestoActual.setText(String.format("%.2f", selectedCategoriaGasto.getPresupuesto()));
                    }
                }else{
                    textFieldPresupuestoActual.setText("0,0");
                }
            }
        });
        aumentarPresupuestoCategoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarPresupuestoGasto();
            }
        });
        textFieldTasaImpuesto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldTasaImpuesto.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        comboBoxIngreso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedCategory = (String) comboBoxIngreso.getSelectedItem();
                if (selectedCategory != null && !selectedCategory.equals("")) {
                    CategoriaIngreso selectedCategoriaIngreso = sistema.getCategoriasIngreso().get(selectedCategory);
                    if (selectedCategoriaIngreso != null) {
                        ingresosIngreso.setText(String.format("%.2f", selectedCategoriaIngreso.getIngresos()));
                        ingresoImpuestos.setText(String.format("%.2f", selectedCategoriaIngreso.getImpuestos()));
                    }
                } else {
                    ingresosIngreso.setText("0,0");
                    ingresoImpuestos.setText("0,0");
                }
            }
        });
        generarInformeIngresosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInformeIngresos();
            }
        });
        generarInformeGastosButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInformeGastos();
            }
        });
        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirSesion();
            }
        });
        buscarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });
        actualizarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });
        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        mostrarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUsuarios();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPagoRecurrente();
            }
        });
        textFieldMontoRegistrarPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoRegistrarPago.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });

        textFieldFrecuenciaRegistrar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPagoRecurrente();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPagoRecurrente();
            }
        });
        textFieldIdEditarPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        siPagadoRegistrarCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (siPagadoRegistrarCheckBox.isSelected()) {
                    noPagadoRegistrarCheckBox.setSelected(false);
                }
            }
        });
        noPagadoRegistrarCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (noPagadoRegistrarCheckBox.isSelected()) {
                    siPagadoRegistrarCheckBox.setSelected(false);
                }
            }
        });
        diaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarDia();
            }
        });
        mesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarMes();
            }
        });
        eliminarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPagoRecurrente();
            }
        });
        MOSTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPagosRecurrentes();
            }
        });
        generarInformeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInformePagos();
            }
        });
        registrarRDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPagoDeudasPrestamos();
            }
        });
        buscarEDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPagoDeudasPrestamos();
            }
        });
        editarEDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDatosPrestamoDeuda();
            }
        });
        eliminarEEDP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDeudaPrestamo();
            }
        });
        mostrarPagoMFP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDeudasPrestamos();
            }
        });
        generarInformeButtonPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInformePrestamos();
            }
        });
        generarInformeButtonDeudas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarInformeDeudas();
            }
        });
        montoRDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && montoRDP.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        plazoRDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        registroRDP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (registroRDP.isSelected()) {
                    noRDP.setSelected(false);
                }
            }
        });
        noRDP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (noRDP.isSelected()) {
                    registroRDP.setSelected(false);
                }
            }
        });
        idEDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        montoEDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && montoEDP.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        plazosEDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        idEEDP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        textFieldMontoGasto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoGasto.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        textFieldTasaDeduccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldTasaDeduccion.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        textFieldTasaImpuestoDeduccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldTasaImpuestoDeduccion.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        textFieldMontoEditarPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) ||
                        (c == '.' && textFieldMontoEditarPago.getText().indexOf('.') == -1))) {
                    e.consume();  // ignore the event
                }
            }
        });
        textFieldFrecuenciaMesesEditar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        textFieldIdEliminarPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    //CATEGORIAS.-

    //Crear
    public void crearCategoria() {
        // Verificar si el campo de texto 'textFieldNombreCategoria' contiene texto válido
        if (verificarCampoTexto(textFieldNombreCategoria.getText())) {

            // Guardar el nombre de la categoría a partir del texto ingresado
            String nombreCategoria = textFieldNombreCategoria.getText();

            // Si el ítem seleccionado en 'comboBoxTipoCategoria' es "Ingreso"
            if (comboBoxTipoCategoria.getSelectedItem().equals("Ingreso")) {

                // Intentar crear una categoría de ingreso
                if (sistema.crearCategoriaIngreso(nombreCategoria)) {

                    // Actualizar las opciones del comboBox si la creación fue exitosa
                    actualizarComboBoxes();

                    // Mostrar un mensaje indicando que la categoría fue creada exitosamente
                    JOptionPane.showMessageDialog(null, "Categoria ingreso agregada correctamente.");

                    // Activar los campos de categorías
                    activarCamposCategorias(true);

                } else {
                    // Mostrar un mensaje de error si la categoría ya existe
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }

            } else if(comboBoxTipoCategoria.getSelectedItem().equals("Gasto")) {
                // El proceso para "Gasto" es similar al anterior, pero con su propio método para crear la categoría

                if (sistema.crearCategoriaGasto(nombreCategoria, 0)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria gasto agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }

            } else if(comboBoxTipoCategoria.getSelectedItem().equals("Prestamo")) {
                // Igualmente, el proceso para "Prestamo" sigue la misma estructura

                if (sistema.crearCategoriaPrestamo(nombreCategoria)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria Prestamo agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }

            } else{
                // Finalmente, si ninguna de las anteriores opciones fue seleccionada, se asume que es una "Deuda"

                if (sistema.crearCategoriaDeuda(nombreCategoria)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria Deuda agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            }

        } else {
            // Si el campo de texto no contenía texto válido, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }

    //Editar
    public void buscarCategoriaEditar() {
        if (verificarCampoTexto(textFieldNombreEditarCategoria.getText())) {
            // Verifica si el campo de texto contiene texto válido
            if (!sistema.validarCategoria(textFieldNombreEditarCategoria.getText())) {
                // Si la categoría no existe en el sistema
                textFieldNuevoNombreEditarCategoria.setEditable(true);
                editarCategoriaButton.setEnabled(true);
            } else {
                // Si la categoría existe en el sistema
                JOptionPane.showMessageDialog(null, "Error. No se ha encontrado ninguna categoria con ese nombre");
                textFieldNuevoNombreEditarCategoria.setEditable(false);
                editarCategoriaButton.setEnabled(false);
            }
        } else {
            // Si el campo de texto no contiene texto válido
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }

    public void editarNombreCategoria() {
        if (verificarCampoTexto(textFieldNuevoNombreEditarCategoria.getText())) {
            // Verifica si el campo de texto contiene texto válido
            if (sistema.editarNombreCategoria(textFieldNombreEditarCategoria.getText(), textFieldNuevoNombreEditarCategoria.getText())) {
                // Si se puede editar el nombre de la categoría
                textFieldNombreEditarCategoria.setText("");
                textFieldNuevoNombreEditarCategoria.setText("");
                textFieldNuevoNombreEditarCategoria.setEditable(false);
                editarCategoriaButton.setEnabled(false);

                actualizarComboBoxes();

                JOptionPane.showMessageDialog(null, "Categoria actualizada correctamente");
            } else {
                // Si no se puede editar el nombre de la categoría (nombre ya existente)
                JOptionPane.showMessageDialog(null, "Error. El nuevo nombre ya esta asignado a otra categoria");
            }
        } else {
            // Si el campo de texto no contiene texto válido
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }


    //Eliminar
    public void eliminarCategoria() {
        if (verificarCampoTexto(textFieldNombreEliminarCategoria.getText())) {
            // Verifica si el campo de texto contiene texto válido
            int resp = sistema.eliminarCategoria(textFieldNombreEliminarCategoria.getText());
            // Llama al método eliminarCategoria del sistema y guarda el resultado en resp
            if (resp == 0) {
                // Si la eliminación se realiza correctamente
                actualizarComboBoxes();
                // Actualiza las cajas de selección (comboBoxes)
                JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente");
                // Muestra un mensaje indicando que la categoría se eliminó exitosamente
            } else if (resp == 1) {
                // Si hay transacciones asignadas a la categoría
                JOptionPane.showMessageDialog(null, "Error. La categoría tiene transacciones asignadas");
                // Muestra un mensaje de error indicando que la categoría tiene transacciones asignadas
            } else {
                // Si la categoría no existe
                JOptionPane.showMessageDialog(null, "Error. La categoría no existe");
                // Muestra un mensaje de error indicando que la categoría no existe
            }
        } else {
            // Si el campo de texto no contiene texto válido
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres");
            // Muestra un mensaje de error indicando que el campo nombre solo acepta caracteres
        }
    }

    //Informes
    public void generarInformeIngresos() {
        double mediaImpuestos = 0;
        // Variable para calcular la media de impuestos

        if (comboBoxInformesIngresos.getSelectedItem() != null) {
            // Verifica si se ha seleccionado un elemento en el comboBox
            CategoriaIngreso cat = sistema.getCategoriasIngreso().get(comboBoxInformesIngresos.getSelectedItem().toString());
            // Obtiene la categoría de ingreso seleccionada del sistema

            for (Transaccion transaccion : cat.getTransacciones()) {
                // Recorre las transacciones de la categoría seleccionada
                mediaImpuestos += transaccion.getTasaImpuesto();
                // Suma la tasa de impuesto de cada transacción a la variable mediaImpuestos
            }

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Crea un conjunto de datos de categorías (dataset)

            dataset.addValue(cat.getIngresos(), "Ingresos", "Ingresos Netos");
            // Agrega el valor de los ingresos netos al dataset
            dataset.addValue(cat.getTransacciones().size(), "Transaccion", "Transacciones");
            // Agrega el número de transacciones al dataset
            dataset.addValue(cat.getImpuestos(), "Impuesto", "Impuestos");
            // Agrega el valor de los impuestos al dataset
            dataset.addValue(mediaImpuestos / cat.getTransacciones().size(), "Impuesto", "Tasa Impuesto (media)");
            // Calcula y agrega la media de impuestos al dataset

            // Crea un gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    comboBoxInformesIngresos.getSelectedItem().toString(),
                    "Datos",
                    "Cantidad",
                    dataset
            );

            // Muestra el gráfico en un marco
            ChartFrame frame = new ChartFrame("Informe " + comboBoxInformesIngresos.getSelectedItem().toString(), chart);
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria.");
            // Muestra un mensaje de error si no se ha seleccionado ninguna categoría
        }
    }

    public void generarInformeGastos() {
        double mediaImpuestos = 0;

        if (comboBoxInformesGastos.getSelectedItem() != null) {
            // Verifica si se ha seleccionado un elemento en el comboBox
            CategoriaGasto cat = sistema.getCategoriasGasto().get(comboBoxInformesGastos.getSelectedItem().toString());
            // Obtiene la categoría de gasto seleccionada del sistema

            for (Transaccion transaccion : cat.getTransacciones()) {
                // Recorre las transacciones de la categoría seleccionada
                mediaImpuestos += transaccion.getTasaImpuesto();
                // Suma la tasa de impuesto de cada transacción a la variable mediaImpuestos
            }

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            // Crea un conjunto de datos de categorías (dataset)

            dataset.addValue(cat.getPresupuesto(), "Presupuesto", "Presupuesto Categoria");
            // Agrega el valor del presupuesto al dataset
            dataset.addValue(cat.getTransacciones().size(), "Transaccion", "Transacciones");
            // Agrega el número de transacciones al dataset
            dataset.addValue(cat.getImpuestos(), "Impuestos", "Impuestos");
            // Agrega el valor de los impuestos al dataset
            dataset.addValue(mediaImpuestos / cat.getTransacciones().size(), "Impuestos", "Tasa Impuesto (media)");
            // Calcula y agrega la media de impuestos al dataset

            // Crea un gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    comboBoxInformesGastos.getSelectedItem().toString(),
                    "Datos",
                    "Cantidad",
                    dataset
            );

            // Muestra el gráfico en un marco
            ChartFrame frame = new ChartFrame("Informe " + comboBoxInformesGastos.getSelectedItem().toString(), chart);
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria.");
            // Muestra un mensaje de error si no se ha seleccionado ninguna categoría
        }
    }


    //Mostrar
    public void mostrarCategoriasNombre() {
        String texto = "";
        // Variable para almacenar el texto de categorías

        if (aZCheckBox.isSelected()) {
            texto = sistema.mostrarCategoriasOrdenadasAZ(comboBoxMostrarCategoria.getSelectedItem().toString());
            // Si la opción "A-Z" está seleccionada, se obtienen las categorías ordenadas alfabéticamente ascendente
        } else if (zACheckBox.isSelected()) {
            texto = sistema.mostrarCategoriasOrdenadasZA(comboBoxMostrarCategoria.getSelectedItem().toString());
            // Si la opción "Z-A" está seleccionada, se obtienen las categorías ordenadas alfabéticamente descendente
        } else {
            texto = sistema.mostrarCategorias(comboBoxMostrarCategoria.getSelectedItem().toString());
            // Si no se selecciona ninguna opción de orden, se obtienen las categorías sin orden específico
        }

        if (!texto.equals("")) {
            // Si el texto no está vacío (hay categorías para mostrar)
            textAreaMostrarCategorias.setText(texto);
            // Se establece el texto en el área de texto para mostrar las categorías

            if (comboBoxMostrarCategoria.getSelectedItem().toString().equals("Ingreso")) {
                // Si se selecciona la categoría "Ingreso"
                String[] categorias = texto.split("\n");
                // Se divide el texto en líneas para obtener cada categoría

                comboBoxIngreso.removeAllItems();
                // Se limpia el comboBox de ingreso

                comboBoxIngreso.addItem("");
                // Se agrega un item vacío al comboBox

                for (String categoria : categorias) {
                    comboBoxIngreso.addItem(categoria);
                    // Se agrega cada categoría al comboBox de ingreso
                }
            } else if (comboBoxMostrarCategoria.getSelectedItem().toString().equals("Gasto")) {
                // Si se selecciona la categoría "Gasto"
                String[] categorias = texto.split("\n");
                // Se divide el texto en líneas para obtener cada categoría

                comboBoxGasto.removeAllItems();
                // Se limpia el comboBox de gasto

                comboBoxGasto.addItem("");
                // Se agrega un item vacío al comboBox de gasto

                for (String categoria : categorias) {
                    comboBoxGasto.addItem(categoria);
                    // Se agrega cada categoría al comboBox de gasto
                }

                comboBoxPresupuestoGastos.removeAllItems();
                // Se limpia el comboBox de presupuesto de gastos

                comboBoxPresupuestoGastos.addItem("");
                // Se agrega un item vacío al comboBox de presupuesto de gastos

                for (String categoria : categorias) {
                    comboBoxPresupuestoGastos.addItem(categoria);
                    // Se agrega cada categoría al comboBox de presupuesto de gastos
                }
            }
        } else {
            // Si no hay categorías para mostrar
            textAreaMostrarCategorias.setText("");
            // Se borra el contenido del área de texto
            comboBoxMostrarCategoria.setSelectedIndex(0);
            // Se selecciona el primer item en el comboBox de mostrar categoría
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria que mostrar");
            // Se muestra un mensaje de error indicando que no hay categorías para mostrar
        }
    }


    //Generar Datos

    //---Principal---
    public void generarCategorias() {
        String texto = "Las categorias agregadas son:";
        // Variable para almacenar el texto de las categorías agregadas

        if (sistema.crearCategoriaIngreso("VentaSoftware")) {
            texto += "\n\n---INGRESO---\nVentaSoftware: 3 Transacciones\n";
            // Se crea una categoría de ingreso llamada "VentaSoftware" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria VentaSoftware ya ha sido agregada");
            // Si la categoría "VentaSoftware" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaIngreso("ConsultoriaTI")) {
            texto += "ConsultoriaTI: 3 Transacciones \n";
            // Se crea una categoría de ingreso llamada "ConsultoriaTI" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria ConsultoríaTI ya ha sido agregada");
            // Si la categoría "ConsultoriaTI" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaIngreso("Soporte")) {
            texto += "Soporte: 3 Transacciones \n\n---GASTO---\n";
            // Se crea una categoría de ingreso llamada "Soporte" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria Soporte ya ha sido agregada");
            // Si la categoría "Soporte" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaGasto("DesarrolloSoftware", 0)) {
            texto += "DesarrolloSoftware: 3 Transacciones [Prespuesto asignado: 100]\n";
            // Se crea una categoría de gasto llamada "DesarrolloSoftware" con un presupuesto asignado de 100 y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria DesarrolloSoftware ya ha sido agregada");
            // Si la categoría "DesarrolloSoftware" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaGasto("GastosOficina", 0)) {
            texto += "GastosOficina: 3 Transacciones [Prespuesto asignado: 100]\n";
            // Se crea una categoría de gasto llamada "GastosOficina" con un presupuesto asignado de 100 y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria GastosOficina ya ha sido agregada");
            // Si la categoría "GastosOficina" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaGasto("GastoMarketing", 0)) {
            texto += "GastoMarketing: 3 Transacciones [Prespuesto asignado: 100]\n\n---DEUDAS---\n";
            // Se crea una categoría de gasto llamada "GastoMarketing" con un presupuesto asignado de 100 y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria GastoMarketing ya ha sido agregada");
            // Si la categoría "GastoMarketing" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaDeuda("LineasDeCredito")) {
            texto += "LineasDeCredito \n";
            // Se crea una categoría de deuda llamada "LineasDeCredito" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria LineasDeCredito ya ha sido agregada");
            // Si la categoría "LineasDeCredito" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaDeuda("CuentasPorPagar")) {
            texto += "CuentasPorPagar \n\n---PRESTAMOS---\n";
            // Se crea una categoría de deuda llamada "CuentasPorPagar" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoriCuentasPorPagar ya ha sido agregada");
            // Si la categoría "CuentasPorPagar" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaPrestamo("LicenciasSoftware")) {
            texto += "LicenciasSoftware \n";
            // Se crea una categoría de préstamo llamada "LicenciasSoftware" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria LicenciasSoftware ya ha sido agregada");
            // Si la categoría "LicenciasSoftware" ya ha sido agregada, se muestra un mensaje de error
        }

        if (sistema.crearCategoriaPrestamo("ServiciosSoftware")) {
            texto += "ServiciosSoftware \n";
            // Se crea una categoría de préstamo llamada "ServiciosSoftware" y se agrega al texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria ServiciosSoftware ya ha sido agregada");
            // Si la categoría "ServiciosSoftware" ya ha sido agregada, se muestra un mensaje de error
        }

        actualizarComboBoxes();
        // Se actualizan los comboBoxes

        generarPresupuestos();
        // Se generan los presupuestos

        generarGastoseIngresos();
        // Se generan los gastos e ingresos

        textAreaGenerarDatosCate.setText(texto);
        // Se establece el texto en el área de texto para mostrar las categorías generadas

        generarDatosButton.setEnabled(false);
        // Se deshabilita el botón de generar datos

        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));
        // Se actualiza el saldo en la etiqueta correspondiente
    }

    public void generarPresupuestos() {
        sistema.asignarPresupuestoGeneral(300);
        // Se asigna un presupuesto general de 300 al sistema

        sistema.asignarPresupuestoACategoriaGasto("DesarrolloSoftware", 100);
        // Se asigna un presupuesto de 100 a la categoría de gasto "DesarrolloSoftware"

        sistema.asignarPresupuestoACategoriaGasto("GastosOficina", 100);
        // Se asigna un presupuesto de 100 a la categoría de gasto "GastosOficina"

        sistema.asignarPresupuestoACategoriaGasto("GastoMarketing", 100);
        // Se asigna un presupuesto de 100 a la categoría de gasto "GastoMarketing"

        labelPresupuestoTotal.setText(String.format("Presupuesto Total: %.2f", sistema.getPresupuestoTotal()));
        // Se actualiza el texto de la etiqueta "labelPresupuestoTotal" con el valor del presupuesto total del sistema
    }

    public void generarGastoseIngresos() {
        Random random = new Random();

        // Crear una instancia de Calendar y establecer el año como 2022
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2022);

        // Generar un mes aleatorio y establecerlo en el calendario
        int randomMonth = random.nextInt(12);
        calendar.set(Calendar.MONTH, randomMonth);

        // Obtener el máximo número de días para el mes generado
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Generar un día aleatorio y establecerlo en el calendario
        int randomDay = random.nextInt(maxDay) + 1;
        calendar.set(Calendar.DAY_OF_MONTH, randomDay);

        // Generar horas, minutos y segundos aleatorios
        int randomHour = random.nextInt(24);
        calendar.set(Calendar.HOUR_OF_DAY, randomHour);

        int randomMinute = random.nextInt(60);
        calendar.set(Calendar.MINUTE, randomMinute);

        int randomSecond = random.nextInt(60);
        calendar.set(Calendar.SECOND, randomSecond);

        // Obtener la fecha aleatoria del calendario
        Date fechaAleatoria = calendar.getTime();

        // Agregar ingresos a categorías específicas
        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "VentaSoftware", 25);
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "VentaSoftware", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "VentaSoftware", 25);

        sistema.agregarIngreso(8, fechaAleatoria, "pago 1", "ConsultoriaTI", 25);
        sistema.agregarIngreso(1, fechaAleatoria, "pago 2", "ConsultoriaTI", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "ConsultoriaTI", 25);

        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "Soporte", 25);
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "Soporte", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "Soporte", 25);

        // Agregar gastos a categorías específicas
        sistema.agregarGasto(9, fechaAleatoria, "pago 1", "DesarrolloSoftware", 25);
        sistema.agregarGasto(5, fechaAleatoria, "pago 2", "DesarrolloSoftware", 25);
        sistema.agregarGasto(52, fechaAleatoria, "pago 3", "DesarrolloSoftware", 25);

        sistema.agregarGasto(31, fechaAleatoria, "pago 1", "GastosOficina", 25);
        sistema.agregarGasto(48, fechaAleatoria, "pago 2", "GastosOficina", 25);
        sistema.agregarGasto(15, fechaAleatoria, "pago 3", "GastosOficina", 25);

        sistema.agregarGasto(13, fechaAleatoria, "pago 1", "GastoMarketing", 25);
        sistema.agregarGasto(25, fechaAleatoria, "pago 2", "GastoMarketing", 25);
        sistema.agregarGasto(35, fechaAleatoria, "pago 3", "GastoMarketing", 25);
    }

    //TRANSACCIONES.-

    //Ingreso
    public void ingresarIngreso() {
        if (!comboBoxIngreso.getSelectedItem().equals("")) {
            // Verificar si se ha seleccionado una categoría de ingreso en el comboBox

            if (!textFieldMontoIngreso.getText().isEmpty() && !textFieldMontoIngreso.getText().equals("0")) {
                // Verificar si el campo de monto no está vacío ni es igual a "0"

                if (!textFieldTasaImpuesto.getText().isEmpty()) {
                    // Verificar si el campo de tasa de impuesto no está vacío

                    if (!textAreaDescripcionIngreso.getText().isEmpty()) {
                        // Verificar si el campo de descripción no está vacío

                        Date fechaActual = new Date();
                        // Obtener la fecha actual

                        double monto = Double.parseDouble(textFieldMontoIngreso.getText());
                        double impuesto = Double.parseDouble(textFieldTasaImpuesto.getText());
                        // Obtener el monto y la tasa de impuesto ingresados

                        if (sistema.agregarIngreso(monto, fechaActual, textAreaDescripcionIngreso.getText(), comboBoxIngreso.getSelectedItem().toString(), impuesto)) {
                            // Agregar el ingreso al sistema

                            CategoriaIngreso selectedCategoriaIngreso = sistema.getCategoriasIngreso().get(comboBoxIngreso.getSelectedItem().toString());
                            // Obtener la categoría de ingreso seleccionada

                            ingresosIngreso.setText(String.format("%.2f", selectedCategoriaIngreso.getIngresos()));
                            ingresoImpuestos.setText(String.format("%.2f", selectedCategoriaIngreso.getImpuestos()));
                            // Actualizar los campos de ingresos e impuestos de la categoría seleccionada en la interfaz gráfica

                            textFieldMontoIngreso.setText("");
                            textAreaDescripcionIngreso.setText("");
                            // Limpiar los campos de monto y descripción

                            saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));
                            // Actualizar el saldo en la etiqueta correspondiente

                            comboBoxIngreso.setSelectedIndex(0);
                            // Restablecer la selección del comboBox de categorías de ingreso

                            JOptionPane.showMessageDialog(null, "El ingreso ha sido agregado correctamente");
                            // Mostrar un mensaje de éxito
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe la categoria");
                            // Mostrar un mensaje de error si la categoría no existe
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. La descripcion esta vacia");
                        // Mostrar un mensaje de error si el campo de descripción está vacío
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La tasa de impuesto no debe estar nula.");
                    // Mostrar un mensaje de error si el campo de tasa de impuesto está vacío
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
                // Mostrar un mensaje de error si el campo de monto está vacío o es igual a "0"
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
            // Mostrar un mensaje de error si no se ha seleccionado ninguna categoría de ingreso
        }
    }

    //Gasto
    public void ingresarGasto() {
        if (!comboBoxGasto.getSelectedItem().equals("")) {
            // Verificar si se ha seleccionado una categoría de gasto en el comboBox

            if (!textFieldMontoGasto.getText().isEmpty() && !textFieldMontoGasto.getText().equals("0")) {
                // Verificar si el campo de monto no está vacío ni es igual a "0"

                if (!textFieldTasaDeduccion.getText().isEmpty()) {
                    // Verificar si el campo de tasa de deducción no está vacío

                    if (!JTextAreaDescripcionGasto.getText().isEmpty()) {
                        // Verificar si el campo de descripción no está vacío

                        Date fechaActual = new Date();
                        // Obtener la fecha actual

                        int resp = sistema.agregarGasto(Double.parseDouble(textFieldMontoGasto.getText()), fechaActual, JTextAreaDescripcionGasto.getText(), comboBoxGasto.getSelectedItem().toString(), Double.parseDouble(textFieldTasaDeduccion.getText()));
                        // Agregar el gasto al sistema y obtener la respuesta

                        if (resp == 1) {
                            // Si el gasto se agregó correctamente

                            textFieldMontoGasto.setText("");
                            JTextAreaDescripcionGasto.setText("");
                            // Limpiar los campos de monto y descripción

                            CategoriaGasto selectedCategoriaGasto = sistema.getCategoriasGasto().get(comboBoxGasto.getSelectedItem().toString());
                            presupuestoCategoriaLabel.setText(String.format("Presupuesto Categoria: %.2f", selectedCategoriaGasto.getPresupuesto()));
                            gastosImpuestosLabel.setText(String.format("%.2f", selectedCategoriaGasto.getImpuestos()));
                            // Actualizar la etiqueta de presupuesto de categoría y la etiqueta de impuestos de la categoría seleccionada en la interfaz gráfica

                            comboBoxGasto.setSelectedIndex(0);
                            // Restablecer la selección del comboBox de categorías de gasto

                            JOptionPane.showMessageDialog(null, "El gasto ha sido agregado correctamente");
                            // Mostrar un mensaje de éxito
                        } else if (resp == -1) {
                            JOptionPane.showMessageDialog(null, "Error. No existe la categoria");
                            // Mostrar un mensaje de error si la categoría no existe
                        } else {
                            JOptionPane.showMessageDialog(null, "Error. El gasto excede el presupuesto de la categoría.");
                            // Mostrar un mensaje de error si el gasto excede el presupuesto de la categoría
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. La descripcion esta vacia");
                        // Mostrar un mensaje de error si el campo de descripción está vacío
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El impuesto no debe ser nulo");
                    // Mostrar un mensaje de error si el campo de tasa de deducción está vacío
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
                // Mostrar un mensaje de error si el campo de monto está vacío o es igual a "0"
            }
        } else {
            presupuestoCategoriaLabel.setText("Presupuesto Categoria: 0");
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
            // Actualizar la etiqueta de presupuesto de categoría y mostrar un mensaje de error si no se ha seleccionado ninguna categoría de gasto
        }
    }


    //Editar
    public void buscarEditarTransaccion() {
        if (verificarCampoTexto(textFieldEditarCategoria.getText())) {
            // Verificar si el campo de categoría contiene solo caracteres

            if (!idTransaccionLabel.getText().isEmpty() && !idTransaccionLabel.getText().equals("0")) {
                // Verificar si el campo de ID de transacción no está vacío y no es igual a "0"

                if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {
                    // Verificar si la categoría de gasto existe en el sistema

                    Transaccion t = sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText()));
                    // Buscar la transacción en la categoría de gasto

                    if (t != null) {
                        // Si se encontró la transacción

                        textFieldMontoEditarTransaccion.setEditable(true);
                        textFieldTasaImpuestoDeduccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);
                        // Habilitar los campos de edición y el botón de actualización

                        textFieldMontoEditarTransaccion.setText(String.valueOf(t.getMonto()));
                        textFieldTasaImpuestoDeduccion.setText(String.valueOf(t.getTasaImpuesto()));
                        textAreaEditarDescripTransaccion.setText(String.valueOf(t.getDescripcion()));
                        // Establecer los valores de la transacción en los campos de edición
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textFieldTasaImpuestoDeduccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);
                        // Deshabilitar los campos de edición y el botón de actualización

                        textFieldMontoEditarTransaccion.setText("");
                        textFieldTasaImpuestoDeduccion.setText("");
                        textAreaEditarDescripTransaccion.setText("");
                        JOptionPane.showMessageDialog(null, "Error. No se ha encontrado esa transaccion");
                        // Limpiar los campos y mostrar un mensaje de error
                    }
                } else if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {
                    // Verificar si la categoría de ingreso existe en el sistema

                    Transaccion t = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText()));
                    // Buscar la transacción en la categoría de ingreso

                    if (t != null) {
                        // Si se encontró la transacción

                        textFieldMontoEditarTransaccion.setEditable(true);
                        textFieldTasaImpuestoDeduccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);
                        // Habilitar los campos de edición y el botón de actualización

                        textFieldMontoEditarTransaccion.setText(String.valueOf(t.getMonto()));
                        textFieldTasaImpuestoDeduccion.setText(String.valueOf(t.getTasaImpuesto()));
                        textAreaEditarDescripTransaccion.setText(String.valueOf(t.getDescripcion()));
                        // Establecer los valores de la transacción en los campos de edición
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        textFieldTasaImpuestoDeduccion.setEditable(false);
                        actualizarButton.setEnabled(false);
                        // Deshabilitar los campos de edición y el botón de actualización

                        textFieldMontoEditarTransaccion.setText("");
                        textFieldTasaImpuestoDeduccion.setText("");
                        textAreaEditarDescripTransaccion.setText("");
                        JOptionPane.showMessageDialog(null, "Error. No se ha encontrado esa transaccion");
                        // Limpiar los campos y mostrar un mensaje de error
                    }
                } else {
                    textFieldMontoEditarTransaccion.setEditable(false);
                    textAreaEditarDescripTransaccion.setEditable(false);
                    actualizarButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Error. No se ha encontrado la categoria");
                    // Deshabilitar los campos de edición y el botón de actualización, y mostrar un mensaje de error si la categoría no existe
                }
            } else {
                textFieldMontoEditarTransaccion.setEditable(false);
                textAreaEditarDescripTransaccion.setEditable(false);
                actualizarButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error. El campo id solo acepta enteros positivos");
                // Deshabilitar los campos de edición y el botón de actualización, y mostrar un mensaje de error si el campo de ID de transacción está vacío o es igual a "0"
            }
        } else {
            textFieldMontoEditarTransaccion.setEditable(false);
            textAreaEditarDescripTransaccion.setEditable(false);
            actualizarButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres");
            // Deshabilitar los campos de edición y el botón de actualización, y mostrar un mensaje de error si el campo de categoría contiene caracteres no válidos
        }
    }

    public void actualizarEditarTransaccion() {
        if (!textFieldMontoEditarTransaccion.getText().isEmpty() && !textFieldMontoEditarTransaccion.getText().equals("0")) {
            // Verificar si el campo de monto no está vacío y no es igual a "0"

            if (!textFieldTasaImpuestoDeduccion.getText().isEmpty()) {
                // Verificar si el campo de impuesto no está vacío

                if (!textAreaEditarDescripTransaccion.getText().isEmpty()) {
                    // Verificar si el campo de descripción no está vacío

                    // Actualizar transacción en la categoría de gasto
                    if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {
                        // Verificar si la categoría de gasto existe en el sistema

                        int respGasto = sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                                Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                                textAreaEditarDescripTransaccion.getText(), Double.parseDouble(textFieldTasaImpuestoDeduccion.getText()));
                        // Editar la transacción en la categoría de gasto y obtener la respuesta

                        if (respGasto == 1) {
                            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente la transaccion");
                        } else if (respGasto == 0) {
                            JOptionPane.showMessageDialog(null, "Error. No se puede asignar porque sobrepasa el presupuesto.");
                        } else {
                            textFieldMontoEditarTransaccion.setEditable(false);
                            textAreaEditarDescripTransaccion.setEditable(false);
                            actualizarButton.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "Error. No existe ningun id asi en la categoria ingreso.");
                        }
                    }

                    // Actualizar transacción en la categoría de ingreso
                    if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {
                        // Verificar si la categoría de ingreso existe en el sistema

                        double ingresoAnterior = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).getIngresos();
                        int respIngreso = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                                Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                                textAreaEditarDescripTransaccion.getText(), Double.parseDouble(textFieldTasaImpuestoDeduccion.getText()));
                        // Editar la transacción en la categoría de ingreso y obtener la respuesta

                        if (respIngreso == 1) {
                            sistema.setSaldo((sistema.getSaldo() - ingresoAnterior) + sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).getIngresos());
                            saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));
                            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente la transaccion");
                        } else {
                            textFieldMontoEditarTransaccion.setEditable(false);
                            textAreaEditarDescripTransaccion.setEditable(false);
                            actualizarButton.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "Error. No existe ningun id asi.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. No debe ser nula la descripcion");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. No debe estar nulo el campo impuesto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. Asignar al monto unicamente enteros positivos");
        }
    }


    //Eliminar
    public void eliminarTransaccion() {
        if (verificarCampoTexto(nombreCategoriaEliminar.getText())) {
            // Verificar si el campo de nombre de categoría no está vacío y solo contiene caracteres

            if (!eliminarIdTransaccion.getText().isEmpty()) {
                // Verificar si el campo de ID de transacción no está vacío

                // Eliminar transacción de la categoría de gasto
                if (sistema.getCategoriasGasto().get(nombreCategoriaEliminar.getText()) != null) {
                    // Verificar si la categoría de gasto existe en el sistema

                    if (sistema.getCategoriasGasto().get(nombreCategoriaEliminar.getText()).eliminarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()))) {
                        JOptionPane.showMessageDialog(null, "Transaccion eliminada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. No existe ese id");
                    }
                }
                // Eliminar transacción de la categoría de ingreso
                else if (sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()) != null) {
                    // Verificar si la categoría de ingreso existe en el sistema

                    double ingresoEliminado = 0;
                    Transaccion t = sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()).buscarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()));
                    // Buscar la transacción en la categoría de ingreso y obtener el monto

                    if (t != null) {
                        ingresoEliminado = t.getMonto();
                    }

                    if (sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()).eliminarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()))) {
                        sistema.setSaldo(sistema.getSaldo() - ingresoEliminado);
                        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));
                        JOptionPane.showMessageDialog(null, "Transaccion eliminada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. No existe ese id");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. No existe esa categoria");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El campo id solo contiene numeros.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo contiene caracteres.");
        }
    }


    //Mostrar
    public void mostrarTransaccionesCategoria() {
        String text = "";
        String categoria = "";
        Comparator<Transaccion> c;

        if (comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Id")) {
            c = Comparator.comparing(Transaccion::getId);
        } else if (comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Monto")) {
            c = Comparator.comparing(Transaccion::getMonto);
        } else if (comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Impuesto")) {
            c = Comparator.comparing(Transaccion::getImpuesto);
        } else if (comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("TasaImpuesto")) {
            c = Comparator.comparing(Transaccion::getTasaImpuesto);
        } else if (comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Descripcion")) {
            c = Comparator.comparing(Transaccion::getDescripcion);
        } else {
            c = Comparator.comparing(Transaccion::getFecha);
        }

        if (ascendenteCheckBox.isSelected()) {
            // Ordenar las transacciones de forma ascendente según el campo seleccionado
            text = sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), true, c);
        } else if (descendenteCheckBox.isSelected()) {
            // Ordenar las transacciones de forma descendente según el campo seleccionado
            text = sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), false, c);
        } else {
            // Mostrar las transacciones sin ordenar según la categoría seleccionada
            categoria = comboBoxMostrarTransacciones.getSelectedItem().toString();

            if (sistema.getCategoriasIngreso().containsKey(categoria)) {
                // La categoría seleccionada es una categoría de ingreso

                List<Transaccion> transacciones = sistema.getCategoriasIngreso().get(categoria).getTransacciones();

                for (Transaccion transaccion : transacciones) {
                    text += transaccion + "\n";
                }
            } else {
                // La categoría seleccionada es una categoría de gasto

                List<Transaccion> transacciones = sistema.getCategoriasGasto().get(categoria).getTransacciones();

                for (Transaccion transaccion : transacciones) {
                    text += transaccion + "\n";
                }
            }
        }
        textArea1.setText(text);
    }

    //PRESUPUESTOS.-

    //Asignar o Aumentar
    public void asignarPresupuestoGeneral() {
        if (!textFiedlPresupuestoTotal.getText().isEmpty() && !textFiedlPresupuestoTotal.getText().equals("0")) {
            // Verificar que el campo de presupuesto total no esté vacío ni sea igual a "0"

            if (!sistema.existenTransaccionesGastos()) {
                // Verificar que no existan transacciones de gastos asignadas

                if (sistema.asignarPresupuestoGeneral(Double.parseDouble(textFiedlPresupuestoTotal.getText()))) {
                    // Asignar el presupuesto total llamando al método correspondiente del sistema

                    labelPresupuestoTotal.setText(String.format("Presupuesto Total: %.2f", sistema.getPresupuestoTotal()));
                    saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                    JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El presupuesto a asignar no puede ser mayor al saldo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El presupuesto no se puede asignar ya que existen transacciones asignadas");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total debe ser un entero positivo");
        }
    }

    public void aumentarPresupuestoGeneral() {
        if (!textFiedlPresupuestoTotal.getText().isEmpty() && !textFiedlPresupuestoTotal.getText().equals("0")) {
            // Verificar que el campo de presupuesto total no esté vacío ni sea igual a "0"

            if (sistema.aumentarPresupuestoGeneral(Double.parseDouble(textFiedlPresupuestoTotal.getText()))) {
                // Aumentar el presupuesto total llamando al método correspondiente del sistema

                labelPresupuestoTotal.setText(String.format("Presupuesto Total: %.2f", sistema.getPresupuestoTotal()));
                saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error. Ya no hay más saldo disponible para asignar al presupuesto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total debe ser un entero positivo");
        }
    }


    //Presupuesto Gastos
    public void asignarPresupuestoCategoria() {
        if (!textFieldMontoAsignarCatGasto.getText().isEmpty()) {
            // Verificar que el campo de monto a asignar no esté vacío

            if (sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getTransacciones().isEmpty()) {
                // Verificar que la categoría de gasto seleccionada no tenga transacciones asignadas

                if (sistema.asignarPresupuestoACategoriaGasto(comboBoxPresupuestoGastos.getSelectedItem().toString(), Double.parseDouble(textFieldMontoAsignarCatGasto.getText()))) {
                    // Asignar el presupuesto a la categoría de gasto llamando al método correspondiente del sistema

                    textFieldPresupuestoActual.setText(String.format("%.2f", sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getPresupuesto()));
                    labelPresupuestoTotal.setText(String.format("Presupuesto Total: %.2f", sistema.getPresupuestoTotal()));

                    JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error. No se puede asignar un monto mayor al presupuesto total");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. La categoría tiene transacciones asignadas");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo de monto está vacío");
        }
    }

    public void aumentarPresupuestoGasto() {
        if (!textFieldMontoAsignarCatGasto.getText().isEmpty()) {
            // Verificar que el campo de monto a asignar no esté vacío

            if (sistema.aumentarPresupuestoACategoriGasto(comboBoxPresupuestoGastos.getSelectedItem().toString(), Double.parseDouble(textFieldMontoAsignarCatGasto.getText()))) {
                // Aumentar el presupuesto de la categoría de gasto llamando al método correspondiente del sistema

                textFieldPresupuestoActual.setText(String.format("%.2f", sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getPresupuesto()));
                labelPresupuestoTotal.setText(String.format("Presupuesto Total: %.2f", sistema.getPresupuestoTotal()));
                JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error. El presupuesto total es menor de lo que se quiere asignar o aumentar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo de monto está vacío");
        }
    }


    //USUARIOS

    //Crear
    public void crearUsuario() {
        if (textFieldNombreUsuarioCrear.getText().length() >= 4) {
            // Verificar que el nombre de usuario tenga al menos 4 caracteres

            if (passwordFieldCrear.getPassword().length >= 4) {
                // Verificar que la contraseña tenga al menos 4 caracteres

                if (sistemaLogin.registerUser("", textFieldNombreUsuarioCrear.getText(), String.valueOf(passwordFieldCrear.getPassword()), false)) {
                    // Llamar al método registerUser() del sistema de login para crear un nuevo usuario

                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error. El usuario ya ha sido creado");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. La contraseña debe contener al menos 4 caracteres");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario debe contener al menos 4 caracteres");
        }
    }


    //Editar
    public void buscarUsuario() {
        if (!textFieldUsuarioEditar.getText().isEmpty()) {
            // Verificar que el campo de búsqueda no esté vacío

            if (sistemaLogin.getUsers().containsKey(textFieldUsuarioEditar.getText())) {
                // Verificar si el usuario existe en el sistema de login

                textFieldNombreNuevoEditar.setEditable(true);
                passwordFieldEditar.setEditable(true);
                actualizarUsuarioButton.setEnabled(true);
                // Habilitar la edición del nombre y contraseña del usuario, y activar el botón de actualización

            } else {
                JOptionPane.showMessageDialog(null, "Error. Usuario no encontrado");
                // Mostrar un mensaje de error indicando que el usuario no ha sido encontrado
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario no debe ser nulo");
            // Mostrar un mensaje de error indicando que el campo de búsqueda está vacío
        }
    }


    public void actualizarUsuario(){
        if(textFieldNombreNuevoEditar.getText().length() >= 4){
            if (passwordFieldEditar.getPassword().length >= 4) {

                if(sistemaLogin.actualizarUsuario(textFieldUsuarioEditar.getText(), textFieldNombreNuevoEditar.getText(),
                        String.valueOf(passwordFieldEditar.getPassword()))){
                    JOptionPane.showMessageDialog(null, "Usuario actualizado con exito");
                }else{
                    JOptionPane.showMessageDialog(null, "Error. El nombre de usuario ya esta en uso.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error. La contraseña debe contener al menos 4 caracteres");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario debe contener minimo 4 caracteres");
        }
    }

    //Eliminar
    public void eliminarUsuario(){
        if(!textFieldNombreEliminar.getText().isEmpty()){
            if(sistemaLogin.eliminarUsuario(textFieldNombreEliminar.getText())){
                JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null, "Error. Usuario no encontrado.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario no debe ser nula.");
        }
    }

    //Mostrar
    public void mostrarUsuarios(){
        textAreaMostrarUsuarios.setText(sistemaLogin.mostrarUsuarios());
    }

    //SALIR
    public void salirSesion(){
        tabbedUsuarios.setSelectedIndex(0);
        tabbedPaneMostrarCategorias.setSelectedIndex(0);
        tabbedPaneMenu.setSelectedIndex(0);
        setVisible(false);
        new LoginWindow(sistemaLogin, this);
    }

    //PAGOS RECURRENTES

    //Registrar
    public void registrarPagoRecurrente() {
        if (!textFieldMontoRegistrarPago.getText().isEmpty() && Double.parseDouble(textFieldMontoRegistrarPago.getText()) > 0) {
            // Verificar que el campo de monto no esté vacío y sea mayor a 0

            if (!textFieldFrecuenciaRegistrar.getText().isEmpty() && Double.parseDouble(textFieldFrecuenciaRegistrar.getText()) > 0) {
                // Verificar que el campo de frecuencia no esté vacío y sea mayor a 0

                if (!textAreaDescripcionPagoRegistrar.getText().isEmpty()) {
                    // Verificar que el campo de descripción no esté vacío

                    if (!siPagadoRegistrarCheckBox.isSelected() && !noPagadoRegistrarCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Error. Debes seleccionar al menos un checkbox");
                        // Mostrar un mensaje de error indicando que se debe seleccionar al menos un checkbox (siPagadoRegistrarCheckBox o noPagadoRegistrarCheckBox)
                    } else {
                        boolean pagado;

                        Calendar calendar = fechaRegistro.getSelectedDate();

                        // Convertir Calendar a LocalDate
                        LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(textFieldFrecuenciaRegistrar.getText()));

                        if (siPagadoRegistrarCheckBox.isSelected()) {
                            pagado = true;
                        } else {
                            pagado = false;
                        }

                        String mensaje = null;
                        int resp = -1;

                        if ((fRegistro.isAfter(dia) || fRegistro.equals(dia)) && !pagado) {
                            mensaje = "Pago Recurrente agregado exitosamente";
                            resp = sistema.registrarPagoRecurrente(Double.parseDouble(textFieldMontoRegistrarPago.getText()),
                                    comboBoxMonedaRegistrar.getSelectedItem().toString(), textFieldFrecuenciaRegistrar.getText(), fRegistro, pagado, textAreaDescripcionPagoRegistrar.getText());
                        } else if ((fSoloRegistro.isBefore(dia) || fSoloRegistro.isEqual(dia)) && pagado) {
                            mensaje = "Pago Recurrente agregado exitosamente";
                            resp = sistema.registrarPagoRecurrente(Double.parseDouble(textFieldMontoRegistrarPago.getText()),
                                    comboBoxMonedaRegistrar.getSelectedItem().toString(), textFieldFrecuenciaRegistrar.getText(), fRegistro, pagado, textAreaDescripcionPagoRegistrar.getText());
                        } else {
                            mensaje = fSoloRegistro.isAfter(dia) && pagado ? "Error. La última fecha de pago debe ser anterior o igual al día actual" : "Error. La fecha de pago debe ser posterior o igual al día actual";
                        }

                        if (mensaje != null) {
                            JOptionPane.showMessageDialog(null, mensaje);
                        }

                        if (resp != -1) {
                            mensaje = (resp == 1) ? "El pago se ha realizado para el mes 1" : "El pago no se ha realizado para el mes 1 por falta de saldo";
                            JOptionPane.showMessageDialog(null, mensaje);

                            saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                            sistema.mostrarPagoRecurrente();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error. La descripción no debe ser nula.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error. La frecuencia debe ser mayor a 0.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser mayor a 0.");
        }
    }


    //Editar
    public void buscarPagoRecurrente() {
        if (!textFieldIdEditarPago.getText().isEmpty() && Integer.parseInt(textFieldIdEditarPago.getText()) > 0) {
            // Verificar que el campo de id no esté vacío y sea mayor a 0

            int resp = sistema.buscarPagoRecurrente(Integer.parseInt(textFieldIdEditarPago.getText()));

            if (resp == 0) {
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se encontró un PagoRecurrente con ese id.");
                // Mostrar un mensaje de error indicando que no se encontró un PagoRecurrente con el id proporcionado y deshabilitar los campos de edición.
            } else if (resp == 1) {
                camposEditarPagoRecurrente(true);
                // Habilitar los campos de edición

                PagoRecurrente pago = sistema.getPagosRecurrentes().get(Integer.parseInt(textFieldIdEditarPago.getText()));
                // Obtener el objeto PagoRecurrente correspondiente al id proporcionado

                textFieldMontoEditarPago.setText(String.valueOf(pago.getMonto()));
                comboBoxMonedasEditar.setSelectedItem(pago.getMoneda());
                textFieldFrecuenciaMesesEditar.setText(String.valueOf(pago.getFrecuencia()));
                textAreaEditarPago.setText(String.valueOf(pago.getDescripcion()));
                // Mostrar la información del PagoRecurrente en los campos de edición

                sistema.mostrarPagoRecurrente();
                // Actualizar la visualización de los pagos recurrentes en la interfaz
            } else if (resp == -1) {
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se puede editar un pago que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                // Mostrar un mensaje de error indicando que no se puede editar un pago que ya se ha empezado a pagarse o que ya se ha pagado por completo y deshabilitar los campos de edición.
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El id no debe ser nulo y debe ser mayor a 0.");
            camposEditarPagoRecurrente(false);
            // Mostrar un mensaje de error indicando que el id debe ser válido y deshabilitar los campos de edición.
        }
    }


    public void editarPagoRecurrente() {
        if (!textFieldMontoEditarPago.getText().isEmpty() && Double.parseDouble(textFieldMontoEditarPago.getText()) > 0) {
            // Verificar que el campo de monto no esté vacío y sea mayor a 0

            if (!textFieldFrecuenciaMesesEditar.getText().isEmpty() && Double.parseDouble(textFieldFrecuenciaMesesEditar.getText()) > 0) {
                // Verificar que el campo de frecuencia no esté vacío y sea mayor a 0

                if (!textAreaEditarPago.getText().isEmpty()) {
                    // Verificar que el campo de descripción no esté vacío

                    boolean pagado;

                    Calendar calendar = fechaEditarPago.getSelectedDate();

                    // Convertir Calendar a LocalDate
                    LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(textFieldFrecuenciaMesesEditar.getText()));

                    if (sistema.getPagosRecurrentes().get(Integer.parseInt(textFieldIdEditarPago.getText())).isSoloRegistro()) {
                        pagado = true;
                    } else {
                        pagado = false;
                    }

                    String mensaje = null;
                    int resp = -1;

                    if ((fRegistro.isAfter(dia) || fRegistro.equals(dia)) && !pagado) {
                        mensaje = "Pago recurrente editado exitosamente.";
                        resp = sistema.actualizarPagoRecurrente(Integer.parseInt(textFieldIdEditarPago.getText()), Double.parseDouble(textFieldMontoEditarPago.getText()),
                                comboBoxMonedasEditar.getSelectedItem().toString(), textFieldFrecuenciaMesesEditar.getText(), fRegistro, textAreaEditarPago.getText());
                    } else if ((fSoloRegistro.isBefore(dia) || fSoloRegistro.isEqual(dia)) && pagado) {
                        mensaje = "Pago recurrente editado exitosamente.";
                        resp = sistema.actualizarPagoRecurrente(Integer.parseInt(textFieldIdEditarPago.getText()), Double.parseDouble(textFieldMontoEditarPago.getText()),
                                comboBoxMonedasEditar.getSelectedItem().toString(), textFieldFrecuenciaMesesEditar.getText(), fRegistro, textAreaEditarPago.getText());
                    } else {
                        mensaje = fSoloRegistro.isAfter(dia) && pagado ? "Error. La última fecha de pago debe ser anterior o igual al día actual" : "Error. La fecha de pago debe ser posterior o igual al día actual";
                    }

                    if (mensaje != null) {
                        JOptionPane.showMessageDialog(null, mensaje);
                    }

                    if (resp != -1) {
                        mensaje = (resp == 1) ? "El pago se ha realizado para el mes 1" : "El pago no se ha realizado para el mes 1 por falta de saldo";
                        JOptionPane.showMessageDialog(null, mensaje);

                        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                        sistema.mostrarPagoRecurrente();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error. La descripción no puede estar vacía.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error. La frecuencia debe ser mayor a 0.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser mayor a 0.");
        }
    }


    public void eliminarPagoRecurrente() {
        if (!textFieldIdEliminarPago.getText().isEmpty() && Integer.parseInt(textFieldIdEliminarPago.getText()) > 0) {
            // Verificar que el campo de id no esté vacío y sea mayor a 0

            int resp = sistema.buscarPagoRecurrente(Integer.parseInt(textFieldIdEliminarPago.getText()));
            // Buscar el pago recurrente con el id especificado

            if (resp == 0) {
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se encontró un PagoRecurrente con ese id.");
                // Si no se encuentra un pago recurrente con ese id, mostrar un mensaje de error
            } else if (resp == 1) {
                sistema.eliminarPagoRecurrente(Integer.parseInt(textFieldIdEliminarPago.getText()));
                JOptionPane.showMessageDialog(null, "Pago eliminado correctamente");
                sistema.mostrarPagoRecurrente();
                // Si se encuentra un pago recurrente con ese id, eliminarlo del sistema, mostrar un mensaje de éxito
                // y actualizar la visualización de los pagos recurrentes en la interfaz
            } else if (resp == -1) {
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se puede eliminar un pago que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                // Si no se puede eliminar el pago recurrente porque ya se ha empezado a pagar o ya se ha pagado por completo,
                // mostrar un mensaje de error
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El id debe ser mayor a 0.");
            // Si el campo de id está vacío o es menor o igual a 0, mostrar un mensaje de error
        }
    }


    public void generarInformePagos(){
        boolean pagados = false;

        double montoTotal = 0;
        int Usd = 0;
        int Gbp = 0;
        int Eur = 0;
        int frecuencia = 0;

        if(comboBox1.getSelectedItem().equals("Pagados")){
            pagados = true;
        }

        for (PagoRecurrente pagoRecurrente :  sistema.mostrarPagosCategorizados(pagados).values()) {
            montoTotal += pagoRecurrente.getMonto();
            if(pagoRecurrente.getMoneda().equals("USD")){
                Usd++;
            }else if(pagoRecurrente.getMoneda().equals("EUR")){
                Eur++;
            }else{
                Gbp++;
            }
            frecuencia += Integer.parseInt(pagoRecurrente.getFrecuencia());
        }

        if (frecuencia > 0 && sistema.mostrarPagosCategorizados(pagados).size() > 1){
            frecuencia = frecuencia/2;
        }

        // Crear un conjunto de datos de categorías
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(montoTotal, "Monto", "Monto Total");
        dataset.addValue(Usd, "Moneda", "USD");
        dataset.addValue(Eur, "Moneda", "EUR");
        dataset.addValue(Gbp, "Moneda", "GBP");
        dataset.addValue(frecuencia, "Frecuencia", "Frecuencia");

        // Crear un gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                comboBox1.getSelectedItem().toString(),
                "Datos",
                "Cantidad",
                dataset
        );

        // Mostrar el gráfico en un marco
        ChartFrame frame = new ChartFrame("Informe " + comboBox1.getSelectedItem().toString(), chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void mostrarPagosRecurrentes() {
        String texto = "";
        boolean pagados = false;

        if (comboBox3.getSelectedItem().equals("Pagados")) {
            pagados = true;
        }
        // Verificar si se ha seleccionado la opción "Pagados" en el combo box

        for (PagoRecurrente pagoRecurrente : sistema.mostrarPagosCategorizados(pagados).values()) {
            texto += pagoRecurrente.toString() + '\n';
        }
        // Recorrer los pagos recurrentes categorizados según el estado de pago seleccionado y obtener su representación como cadena de texto

        textAreaMostrarPagosRecurrentees.setText(texto);
        // Actualizar el texto del área de texto con los pagos recurrentes mostrados
    }


    public void registrarPagoDeudasPrestamos(){

        String tipo;

        if(comboboxRegistrarPagoDeudasPr.getSelectedItem() != null){

            if(sistema.esDeuda(comboboxRegistrarPagoDeudasPr.getSelectedItem().toString())){
                tipo = "Deuda";
            }else{
                tipo = "Prestamo";
            }


            if(!montoRDP.getText().isEmpty() && Double.parseDouble(
                    montoRDP.getText()) > 0){

                if(!plazoRDP.getText().isEmpty() && Double.parseDouble(
                        plazoRDP.getText()) > 0){

                    if(tipo.equals("Prestamo") &&  Integer.parseInt(plazoRDP.getText()) < 3){
                        JOptionPane.showMessageDialog(null, "Error. Debes seleccionar al menos un plazo de 3 meses");
                        return;
                    }

                    if(!descripcionRDP.getText().isEmpty()){

                        if (!registroRDP.isSelected() && !noRDP.isSelected()) {
                            JOptionPane.showMessageDialog(null, "Error. Debes seleccionar al menos un checkbox");
                        } else {
                            boolean pagado;

                            Calendar calendar = fechaRDP.getSelectedDate();

                            // Convertir Calendar a LocalDate
                            LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(plazoRDP.getText()));
                            System.out.println(fSoloRegistro);
                            if(registroRDP.isSelected()){
                                pagado = true;
                            }else{
                                pagado = false;
                            }

                            String mensaje = null;
                            int resp = -1;

                            System.out.println("P: " + pagado);
                            if ((fRegistro.isAfter(dia) || fRegistro.equals(dia)) && !pagado) {
                                mensaje = tipo + " agregado exitosamente";
                                resp = sistema.registrarDeudaOPrestamo(Double.parseDouble(montoRDP.getText()),
                                        comboBoxMonedaRegistrar.getSelectedItem().toString(), plazoRDP.getText(), fRegistro, pagado, descripcionRDP.getText(), comboboxRegistrarPagoDeudasPr.getSelectedItem().toString());
                            } else if ((fSoloRegistro.isBefore(dia) || fSoloRegistro.isEqual(dia)) && pagado) {
                                System.out.println("pase");
                                mensaje = tipo + " agregado exitosamente";
                                resp = sistema.registrarDeudaOPrestamo(Double.parseDouble(montoRDP.getText()),
                                        comboBoxMonedaRegistrar.getSelectedItem().toString(), plazoRDP.getText(), fRegistro, pagado, descripcionRDP.getText(), comboboxRegistrarPagoDeudasPr.getSelectedItem().toString());
                            } else {
                                mensaje = fSoloRegistro.isAfter(dia) && pagado? "Error. La ultima fecha de pago debe ser anterior o igual al dia actual" : "Error. La fecha de pago debe ser posterior o igual al dia actual";
                            }

                            if (mensaje != null) {
                                JOptionPane.showMessageDialog(null, mensaje);
                            }

                            if (resp != -1) {
                                System.out.println(resp);
                                mensaje = (resp == 1) ? "El pago se ha realizado para el mes 1" : "El pago no se ha realizado para el mes 1 por falta de saldo";
                                JOptionPane.showMessageDialog(null, mensaje);

                                saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                                sistema.mostrarPagoRecurrente();
                            }


                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Error. La descripcion no debe ser nula.");
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Error. La frecuencia debe ser mayor a 0.");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Error. El monto debe ser mayor a 0.");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria");
        }


    }

    public void buscarPagoDeudasPrestamos() {
        if (comboBoxCategoriaEDP.getSelectedItem() != null) {
            // Verificar si se ha seleccionado una categoría en el combo box

            String tipo;
            if (sistema.esDeuda(comboBoxCategoriaEDP.getSelectedItem().toString())) {
                tipo = "Deuda";
            } else {
                tipo = "Prestamo";
            }
            // Determinar el tipo de obligación financiera según la categoría seleccionada

            if (!idEDP.getText().isEmpty() && Integer.parseInt(idEDP.getText()) > 0) {
                // Verificar si se ha ingresado un ID válido y mayor a 0 en el campo de texto `idEDP`

                int resp = sistema.buscarPrestamoODeuda(Integer.parseInt(idEDP.getText()), comboBoxCategoriaEDP.getSelectedItem().toString());
                // Llamar al método del sistema para buscar la deuda o préstamo según el ID y la categoría seleccionados

                if (resp == 0) {
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. No se encontró ninguna obligación financiera con ese ID.");
                    // Si el método devuelve 0, significa que no se encontró ninguna obligación financiera con ese ID y categoría.
                    // Mostrar un mensaje de error indicando que no se encontró ninguna obligación financiera.

                } else if (resp == 1) {
                    camposEditarDeudaPrestamo(true);
                    PagoRecurrente pago;
                    if (tipo.equals("Deuda")) {
                        pago = sistema.getCategoriasDeudas().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
                    } else {
                        pago = sistema.getCategoriasPrestamos().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
                    }
                    // Obtener el objeto de PagoRecurrente según el tipo y la categoría seleccionados

                    montoEDP.setText(String.valueOf(pago.getMonto()));
                    monedaEDP.setSelectedItem(pago.getMoneda());
                    plazosEDP.setText(String.valueOf(pago.getFrecuencia()));
                    descripcionEDP.setText(String.valueOf(pago.getDescripcion()));
                    sistema.mostrarPagoRecurrente();
                    // Actualizar los campos de texto y combo box con la información del pago recurrente encontrado.
                    // Actualizar la visualización de los pagos recurrentes.

                } else if (resp == -1) {
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. No se puede editar ninguna obligación financiera que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                    // Si el método devuelve -1, significa que no se puede editar la obligación financiera porque ya se ha empezado a pagarse o ya se ha pagado completamente.
                    // Mostrar un mensaje de error indicando que no se puede editar la obligación financiera.

                } else {
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. Obligación financiera no encontrada con ese ID.");
                    // Si el método no devuelve 0, 1 o -1, significa que no se encontró una obligación financiera con ese ID y categoría.
                    // Mostrar un mensaje de error indicando que no se encontró ninguna obligación financiera.
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. Ingrese un ID mayor a 0.");
                // Si no se ha ingresado un ID válido y mayor a 0, mostrar un mensaje de error.
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoría.");
            // Si no se ha seleccionado una categoría en el combo box, mostrar un mensaje de error.
        }
    }


    public void actualizarDatosPrestamoDeuda(){
        String tipo;
        PagoRecurrente pago;

        if(sistema.esDeuda(comboBoxCategoriaEDP.getSelectedItem().toString())){
            tipo = "Deuda";
        }else{
            tipo = "Prestamo";
        }

        if (tipo.equals("Deuda")){
            pago = sistema.getCategoriasDeudas().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
        }else{
            pago = sistema.getCategoriasPrestamos().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
        }

        if(!montoEDP.getText().isEmpty() && Double.parseDouble(montoEDP.getText()) > 0){
            if(!plazosEDP.getText().isEmpty() && Integer.parseInt(plazosEDP.getText()) > 0){

                if(tipo.equals("Prestamo") &&  Integer.parseInt(plazosEDP.getText()) < 3){
                    JOptionPane.showMessageDialog(null, "Error. Debes seleccionar al menos un plazo de 3 meses");
                    return;
                }

                if(!descripcionEDP.getText().isEmpty()){

                    boolean pagado;

                    Calendar calendar = fechaEDP.getSelectedDate();

                    // Convertir Calendar a LocalDate
                    LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(plazosEDP.getText()));
                    System.out.println(fSoloRegistro);
                    if(pago.isSoloRegistro()){
                        pagado = true;
                    }else{
                        pagado = false;
                    }

                    String mensaje = null;
                    int resp = -1;

                    if ((fRegistro.isAfter(dia) || fRegistro.equals(dia)) && !pagado) {
                        mensaje = "Pago recurrente editado exitosamente.";
                        resp = sistema.actualizarDeudaOPrestamo(Integer.parseInt(idEDP.getText()), Double.parseDouble(montoEDP.getText()),
                                monedaEDP.getSelectedItem().toString(), plazosEDP.getText(), fRegistro, descripcionEDP.getText(), comboBoxCategoriaEDP.getSelectedItem().toString());
                    } else if ((fSoloRegistro.isBefore(dia) || fSoloRegistro.isEqual(dia)) && pagado) {
                        mensaje = "Pago recurrente editado exitosamente.";
                        resp =  sistema.actualizarDeudaOPrestamo(Integer.parseInt(idEDP.getText()), Double.parseDouble(montoEDP.getText()),
                                monedaEDP.getSelectedItem().toString(), plazosEDP.getText(), fRegistro, descripcionEDP.getText(), comboBoxCategoriaEDP.getSelectedItem().toString());
                    } else {
                        mensaje = fSoloRegistro.isAfter(dia) && pagado? "Error. La ultima fecha de pago debe ser anterior o igual al dia actual" : "Error. La fecha de pago debe ser posterior o igual al dia actual";
                    }

                    if (mensaje != null) {
                        JOptionPane.showMessageDialog(null, mensaje);
                    }

                    if (resp != -1) {
                        System.out.println(resp);
                        mensaje = (resp == 1) ? "El pago se ha realizado para el mes 1" : "El pago no se ha realizado para el mes 1 por falta de saldo";
                        JOptionPane.showMessageDialog(null, mensaje);

                        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

                        sistema.mostrarPagoRecurrente();
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Error. La descripcion no deber ser nula");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Error. El plazo debe ser mayor a 0");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser mayor a 0.");
        }
    }

    public void eliminarDeudaPrestamo() {
        if (comboBoxDEDP.getSelectedItem() != null) {
            // Verificar si se ha seleccionado una categoría en el combo box

            if (!idEEDP.getText().isEmpty() && Integer.parseInt(idEEDP.getText()) > 0) {
                // Verificar si se ha ingresado un ID válido y mayor a 0

                int resp = sistema.buscarPrestamoODeuda(Integer.parseInt(idEEDP.getText()), comboBoxDEDP.getSelectedItem().toString());
                // Llamar al método del sistema para buscar la deuda o préstamo según el ID y la categoría seleccionados

                if (resp == -3) {
                    camposEditarPagoRecurrente(false);
                    JOptionPane.showMessageDialog(null, "Error. No se encontraron obligaciones financieras con ese id.");
                    // Si el método devuelve -3, significa que no se encontraron obligaciones financieras con ese ID.
                    // Mostrar un mensaje de error indicando que no se encontró ninguna obligación financiera.

                } else if (resp == 1) {
                    sistema.eliminarDeudaPrestamo(Integer.parseInt(idEEDP.getText()), comboBoxDEDP.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Obligación financiera eliminada correctamente");
                    sistema.mostrarPagoRecurrente();
                    // Si el método devuelve 1, significa que se encontró una obligación financiera con ese ID y categoría.
                    // Llamar al método del sistema para eliminar la obligación financiera y mostrar un mensaje de éxito.
                    // Actualizar la visualización de las obligaciones financieras.

                } else if (resp == -1) {
                    camposEditarPagoRecurrente(false);
                    JOptionPane.showMessageDialog(null, "Error. No se puede eliminar una obligación financiera que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                    // Si el método devuelve -1, significa que no se puede eliminar la obligación financiera porque ya se ha empezado a pagarse o ya se ha pagado completamente.
                    // Mostrar un mensaje de error indicando que no se puede eliminar la obligación financiera.

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El ID debe ser mayor a 0.");
                // Si no se ha ingresado un ID válido y mayor a 0, mostrar un mensaje de error.
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoría.");
            // Si no se ha seleccionado una categoría en el combo box, mostrar un mensaje de error.
        }
    }


    public void mostrarDeudasPrestamos() {
        if (comboBoxCategoriaMDP.getSelectedItem() != null) {
            // Verificar si se ha seleccionado una categoría en el combo box

            boolean estadoPago = false;
            String mensaje;

            if (estadoPagoMDP.getSelectedItem().equals("Pagados")) {
                estadoPago = true;
            }
            // Determinar el estado de pago seleccionado: true si se seleccionó "Pagados", false en caso contrario

            mensaje = sistema.mostrarDeudasPrestamos(comboBoxCategoriaMDP.getSelectedItem().toString(), estadoPago);
            // Llamar al método del sistema para mostrar las deudas o préstamos según la categoría y estado de pago seleccionados

            if (mensaje.equals("")) {
                textAreaMostrarDeudasPrestamos.setText("No hay datos que mostrar");
            } else {
                textAreaMostrarDeudasPrestamos.setText(mensaje);
            }
            // Si el mensaje está vacío, mostrar un mensaje indicando que no hay datos que mostrar
            // De lo contrario, mostrar el mensaje con las deudas o préstamos en el área de texto
        } else {
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoría.");
            // Si no se ha seleccionado una categoría en el combo box, mostrar un mensaje de error
        }
    }


    public void generarInformePrestamos(){

        double montoTotal = 0;
        int cantidadPagos;
        int pagosPagados = 0;
        int noPagados = 0;

        if(comboBoxPrestamosInforme.getSelectedItem() != null){
            for (Map.Entry<Integer, PagoRecurrente> entry : sistema.getCategoriasPrestamos().get(comboBoxPrestamosInforme.getSelectedItem().toString()).getPagosRecurrentes().entrySet()) {
                montoTotal += entry.getValue().getMonto();
                if(entry.getValue().isSoloRegistro() || entry.getValue().isPagadoCompletamente()){
                    pagosPagados++;
                }else{
                    noPagados++;
                }
            }

            cantidadPagos = sistema.getCategoriasPrestamos().get(comboBoxPrestamosInforme.getSelectedItem().toString()).getPagosRecurrentes().size();

            // Crear un conjunto de datos de categorías
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(montoTotal, "Monto", "Monto Total");
            dataset.addValue(pagosPagados, "Estado Pago", "Pagados");
            dataset.addValue(noPagados, "Estado Pago", "No Pagados");
            dataset.addValue(cantidadPagos, "Numero Pagos", "Cantidad");

            // Crear un gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    comboBoxPrestamosInforme.getSelectedItem().toString(),
                    "Datos",
                    "Cantidad",
                    dataset
            );

            // Mostrar el gráfico en un marco
            ChartFrame frame = new ChartFrame("Informe " + comboBoxPrestamosInforme.getSelectedItem().toString(), chart);
            frame.pack();
            frame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria.");
        }

    }

    public void generarInformeDeudas(){
        double montoTotal = 0;
        int cantidadPagos;
        int pagosPagados = 0;
        int noPagados = 0;

        if(comboBoxDeudasInforme.getSelectedItem() != null){
            for (Map.Entry<Integer, PagoRecurrente> entry : sistema.getCategoriasDeudas().get(comboBoxDeudasInforme.getSelectedItem().toString()).getPagosRecurrentes().entrySet()) {
                montoTotal += entry.getValue().getMonto();
                if(entry.getValue().isSoloRegistro() || entry.getValue().isPagadoCompletamente()){
                    pagosPagados++;
                }else{
                    noPagados++;
                }
            }

            cantidadPagos = sistema.getCategoriasDeudas().get(comboBoxDeudasInforme.getSelectedItem().toString()).getPagosRecurrentes().size();

            // Crear un conjunto de datos de categorías
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(montoTotal, "Monto", "Monto Total");
            dataset.addValue(pagosPagados, "Estado Pago", "Pagados");
            dataset.addValue(noPagados, "Estado Pago", "No Pagados");
            dataset.addValue(cantidadPagos, "Numero Pagos", "Cantidad");

            // Crear un gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    comboBoxDeudasInforme.getSelectedItem().toString(),
                    "Datos",
                    "Cantidad",
                    dataset
            );

            // Mostrar el gráfico en un marco
            ChartFrame frame = new ChartFrame("Informe " + comboBoxDeudasInforme.getSelectedItem().toString(), chart);
            frame.pack();
            frame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria.");
        }
    }

    //FECHA
    public void aumentarDia(){
        dia = dia.plusDays(1);
        dateLabel.setText(dia.toString());
        String resultado = sistema.verificarPagosPendientes();
        if (!resultado.isEmpty()){
            JOptionPane.showMessageDialog(null, resultado);
        }

        String resp = sistema.verificarPagosPendientesDeudasPrestamos();

        if(!resp.isEmpty()){
            JOptionPane.showMessageDialog(null, resp);
        }

        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

    }

    public void aumentarMes(){
        dia = dia.plusMonths(1);
        dateLabel.setText(dia.toString());

        String resultado = sistema.verificarPagosPendientes();
        if (!resultado.isEmpty()){
            JOptionPane.showMessageDialog(null, resultado);
        }

        String resp = sistema.verificarPagosPendientesDeudasPrestamos();

        if(!resp.isEmpty()){
            JOptionPane.showMessageDialog(null, resp);
        }

        saldoLabel.setText(String.format("%.2f", sistema.getSaldo()));

    }

    //ACTIVACIONES Y ACTUALIZACIONES.-
    public void activarCamposCategorias(boolean bool) {
        textFieldNombreCategoria.setEditable(bool);
        crearCategoriaButton.setEnabled(bool);
        textFieldNombreCategoria.setText("");
    }

    public void camposEditarDeudaPrestamo(boolean bool){
        montoEDP.setEditable(bool);
        monedaEDP.setEnabled(bool);
        plazosEDP.setEditable(bool);
        descripcionEDP.setEditable(bool);
        editarEDP.setEnabled(bool);
    }

    public void camposEditarPagoRecurrente(boolean bool){
        textFieldMontoEditarPago.setEditable(bool);
        comboBoxMonedasEditar.setEnabled(bool);
        textFieldFrecuenciaMesesEditar.setEditable(bool);
        textAreaEditarPago.setEnabled(bool);
        editarButton.setEnabled(bool);
    }

    public boolean verificarCampoTexto(String textField) {
        // Verifica si el campo de texto está vacío
        if (textField == null || textField.isEmpty()) {
            return false;
        }

        // Verifica si el campo de texto contiene únicamente letras
        return textField.matches("[a-zA-Z]+");
    }
    public void actualizarComboBoxes() {
        // Limpiar ambos JComboBox
        comboBoxInformesIngresos.removeAllItems();
        comboBoxInformesGastos.removeAllItems();
        comboBoxIngreso.removeAllItems();
        comboBoxGasto.removeAllItems();
        comboBoxPresupuestoGastos.removeAllItems();
        comboBoxMostrarTransacciones.removeAllItems();
        comboboxRegistrarPagoDeudasPr.removeAllItems();
        comboBoxPrestamosInforme.removeAllItems();
        comboBoxDeudasInforme.removeAllItems();
        comboBoxCategoriaEDP.removeAllItems();
        comboBoxDEDP.removeAllItems();
        comboBoxCategoriaMDP.removeAllItems();

        // Obtener las categorías de ingreso y gasto del sistema
        Map<String, CategoriaIngreso> categoriasIngreso = sistema.getCategoriasIngreso();
        Map<String, CategoriaGasto> categoriasGasto = sistema.getCategoriasGasto();
        Map<String, CategoriaPrestamo> categoriasPrestamo = sistema.getCategoriasPrestamos();
        Map<String, CategoriaDeuda> categoriasDeudas = sistema.getCategoriasDeudas();

        // Actualizar comboBoxIngreso
        comboBoxIngreso.addItem("");
        for (String categoria : categoriasIngreso.keySet()) {
            comboBoxIngreso.addItem(categoria);
        }

        // Actualizar comboBoxGasto
        comboBoxGasto.addItem("");
        for (String categoria : categoriasGasto.keySet()) {
            comboBoxGasto.addItem(categoria);
        }

        // Actualizar comboBoxGastoPresupuesto
        comboBoxPresupuestoGastos.addItem("");
        for (String categoria : categoriasGasto.keySet()) {
            comboBoxPresupuestoGastos.addItem(categoria);
        }

        // Actualizar comboBoxMostrarTransacciones
        for (String categoria : categoriasGasto.keySet()) {
            comboBoxMostrarTransacciones.addItem(categoria);
        }
        for (String categoria : categoriasIngreso.keySet()) {
            comboBoxMostrarTransacciones.addItem(categoria);
        }

        // Actualizar comboBoxInformesIngresos
        for (String categoria : categoriasIngreso.keySet()) {
            comboBoxInformesIngresos.addItem(categoria);
        }

        // Actualizar comboBoxInformesGastos
        for (String categoria : categoriasGasto.keySet()) {
            comboBoxInformesGastos.addItem(categoria);
        }

        //Actualizar comboBox comboBoxPrestamosInforme
        for (String categoria : categoriasPrestamo.keySet()){
            comboBoxPrestamosInforme.addItem(categoria);
        }

        //Actualizar combobox comboBoxDeudasInforme
        for (String categoria : categoriasDeudas.keySet()){
            comboBoxDeudasInforme.addItem(categoria);
        }

        //Actualizar comboBoxDeudasyPrestamos
        for (String categoria : categoriasPrestamo.keySet()){
            comboboxRegistrarPagoDeudasPr.addItem(categoria);
        }

        for (String categoria : categoriasDeudas.keySet()){
            comboboxRegistrarPagoDeudasPr.addItem(categoria);
        }

        //Actualizar comboBoxCategoriaEDP
        for (String categoria : categoriasPrestamo.keySet()){
            comboBoxCategoriaEDP.addItem(categoria);
        }

        for (String categoria : categoriasDeudas.keySet()){
            comboBoxCategoriaEDP.addItem(categoria);
        }

        //Actualizar comboBoxDEDP
        for (String categoria : categoriasPrestamo.keySet()){
            comboBoxDEDP.addItem(categoria);
        }

        for (String categoria : categoriasDeudas.keySet()){
            comboBoxDEDP.addItem(categoria);
        }

        //Actualizar comboBoxCategoriaMDP
        for (String categoria : categoriasPrestamo.keySet()){
            comboBoxCategoriaMDP.addItem(categoria);
        }

        for (String categoria : categoriasDeudas.keySet()){
            comboBoxCategoriaMDP.addItem(categoria);
        }
    }

    public void cambiarUsuario(String user){
        if(user.equals("")){

        }else{
            tabbedUsuarios.setEnabled(false);
            textFieldNombreUsuarioCrear.setEnabled(false);
            passwordFieldCrear.setEnabled(false);
            crearUsuarioButton.setEnabled(false);
        }
        usuarioLabel.setText("Usuario: " + user);
    }

}
