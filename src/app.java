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
    private JTextArea textArea2;
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
    private JButton quemarDatosButton;
    private JTextArea textAreaTransaccionesQuemado;
    private JComboBox comboBoxCategoriaEditarTransaccion;
    private GestionFinanciera sistema = new GestionFinanciera();
    private SistemaLogin sistemaLogin;
    public static LocalDate dia;

    public app(String user, SistemaLogin system) {

        dia = LocalDate.now();
        dateLabel.setText(dia.toString());

        saldoLabel.setText(String.valueOf(sistema.getSaldo()));

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
                        (c == '.' && textFieldMontoIngreso.getText().indexOf('.') == -1))) {
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
                        (c == '.' && textFieldMontoIngreso.getText().indexOf('.') == -1))) {
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
                        presupuestoCategoriaLabel.setText("Presupuesto Categoria: " + selectedCategoriaGasto.getPresupuesto());
                        gastosImpuestosLabel.setText(String.valueOf(selectedCategoriaGasto.getImpuestos()));
                    }
                }else{
                    presupuestoCategoriaLabel.setText("Presupuesto Categoria: " + "0.0");
                    gastosImpuestosLabel.setText("0.0");
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
                if (!Character.isDigit(c)) {
                    e.consume();
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
                        textFieldPresupuestoActual.setText(String.valueOf(selectedCategoriaGasto.getPresupuesto()));
                    }
                }else{
                    textFieldPresupuestoActual.setText("0.0");
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
                        (c == '.' && textFieldMontoIngreso.getText().indexOf('.') == -1))) {
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
                        ingresosIngreso.setText(String.valueOf(selectedCategoriaIngreso.getIngresos()));
                        ingresoImpuestos.setText(String.valueOf(selectedCategoriaIngreso.getImpuestos()));
                    }
                } else {
                    ingresosIngreso.setText("0.0");
                    ingresoImpuestos.setText("0.0");
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
                        (c == '.' && textFieldMontoIngreso.getText().indexOf('.') == -1))) {
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
    }

    //CATEGORIAS.-

    //Crear
    public void crearCategoria() {
        if (verificarCampoTexto(textFieldNombreCategoria.getText())) {
            String nombreCategoria = textFieldNombreCategoria.getText();
            if (comboBoxTipoCategoria.getSelectedItem().equals("Ingreso")) {
                if (sistema.crearCategoriaIngreso(nombreCategoria)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria ingreso agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            } else if(comboBoxTipoCategoria.getSelectedItem().equals("Gasto")){
                if (sistema.crearCategoriaGasto(nombreCategoria, 0)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria gasto agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            } else if(comboBoxTipoCategoria.getSelectedItem().equals("Prestamo")){
                if (sistema.crearCategoriaPrestamo(nombreCategoria)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria Prestamo agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            } else{
                if (sistema.crearCategoriaDeuda(nombreCategoria)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria Deuda agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }

    //Editar
    public void buscarCategoriaEditar() {
        if (verificarCampoTexto(textFieldNombreEditarCategoria.getText())) {
            if (!sistema.validarCategoria(textFieldNombreEditarCategoria.getText())) {
                textFieldNuevoNombreEditarCategoria.setEditable(true);
                editarCategoriaButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error. No se ha encontrado ninguna categoria con ese nombre");
                textFieldNuevoNombreEditarCategoria.setEditable(false);
                editarCategoriaButton.setEnabled(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }
    public void editarNombreCategoria() {
        if (verificarCampoTexto(textFieldNuevoNombreEditarCategoria.getText())) {
            if (sistema.editarNombreCategoria(textFieldNombreEditarCategoria.getText(), textFieldNuevoNombreEditarCategoria.getText())) {
                textFieldNombreEditarCategoria.setText("");
                textFieldNuevoNombreEditarCategoria.setText("");
                textFieldNuevoNombreEditarCategoria.setEditable(false);
                editarCategoriaButton.setEnabled(false);

                actualizarComboBoxes();

                JOptionPane.showMessageDialog(null, "Categoria actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error. El nuevo nombre ya esta asignado a otra categoria");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }

    //Eliminar
    public void eliminarCategoria() {
        if (verificarCampoTexto(textFieldNombreEliminarCategoria.getText())) {
            int resp = sistema.eliminarCategoria(textFieldNombreEliminarCategoria.getText());
            if (resp == 0) {
                actualizarComboBoxes();
                JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente");
            } else if (resp == 1) {
                JOptionPane.showMessageDialog(null, "Error. La categoría tiene transacciones asignadas");
            } else {
                JOptionPane.showMessageDialog(null, "Error. La categoría no existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres");
        }
    }

    //Informes
    public void generarInformeIngresos(){
        // Crear un conjunto de datos de categorías
        CategoriaIngreso cat = sistema.getCategoriasIngreso().get(comboBoxInformesIngresos.getSelectedItem().toString());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(cat.getIngresos(), "I.N", "Ingresos Netos");
        dataset.addValue(cat.getTransacciones().size(), "T", "Transacciones");
        dataset.addValue(cat.getImpuestos(), "I", "Impuestos");

        // Crear un gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                comboBoxInformesIngresos.getSelectedItem().toString(),
                "Datos",
                "Cantidad",
                dataset
        );

        // Mostrar el gráfico en un marco
        ChartFrame frame = new ChartFrame("Informe " + comboBoxInformesIngresos.getSelectedItem().toString(), chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void generarInformeGastos(){
        // Crear un conjunto de datos de categorías
        CategoriaGasto cat = sistema.getCategoriasGasto().get(comboBoxInformesGastos.getSelectedItem().toString());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(cat.getPresupuesto(), "P", "Presupuesto");
        dataset.addValue(cat.getTransacciones().size(), "T", "Transacciones");
        dataset.addValue(cat.getImpuestos(), "I", "Impuestos");

        // Crear un gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                comboBoxInformesGastos.getSelectedItem().toString(),
                "Datos",
                "Cantidad",
                dataset
        );

        // Mostrar el gráfico en un marco
        ChartFrame frame = new ChartFrame("Informe " + comboBoxInformesGastos.getSelectedItem().toString(), chart);
        frame.pack();
        frame.setVisible(true);
    }

    //Mostrar
    public void mostrarCategoriasNombre() {
        String texto = "";
        if (aZCheckBox.isSelected()) {
            texto = sistema.mostrarCategoriasOrdenadasAZ(comboBoxMostrarCategoria.getSelectedItem().toString());
        } else if (zACheckBox.isSelected()) {
            texto = sistema.mostrarCategoriasOrdenadasZA(comboBoxMostrarCategoria.getSelectedItem().toString());
        } else {
            texto = sistema.mostrarCategorias(comboBoxMostrarCategoria.getSelectedItem().toString());
        }

        if (!texto.equals("")) {
            textAreaMostrarCategorias.setText(texto);
            if (comboBoxMostrarCategoria.getSelectedItem().toString().equals("Ingreso")) {
                // Dividir el texto en líneas
                String[] categorias = texto.split("\n");

                // Limpiar el comboBox
                comboBoxIngreso.removeAllItems();

                comboBoxIngreso.addItem("");

                // Añadir cada categoría al comboBox
                for (String categoria : categorias) {
                    comboBoxIngreso.addItem(categoria);
                }
            } else if (comboBoxMostrarCategoria.getSelectedItem().toString().equals("Gasto")) {
                // Dividir el texto en líneas
                String[] categorias = texto.split("\n");

                // Limpiar el comboBox
                comboBoxGasto.removeAllItems();

                comboBoxGasto.addItem("");

                // Añadir cada categoría al comboBox
                for (String categoria : categorias) {
                    comboBoxGasto.addItem(categoria);
                }

                comboBoxPresupuestoGastos.removeAllItems();

                comboBoxPresupuestoGastos.addItem("");
                // Añadir cada categoría al comboBox
                for (String categoria : categorias) {
                    comboBoxPresupuestoGastos.addItem(categoria);
                }


            }
        } else {
            textAreaMostrarCategorias.setText("");
            comboBoxMostrarCategoria.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria que mostrar");
        }
    }

    //Generar Datos

    //---Principal---
    public void generarCategorias() {
        String texto = "Las categorias agregadas son:";
        if (sistema.crearCategoriaIngreso("VentaSoftware")) {
            texto += "\n\n---INGRESO---\nVentaSoftware \n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria VentaSoftware ya ha sido agregada");
        }

        if (sistema.crearCategoriaIngreso("ConsultoriaTI")) {
            texto += "ConsultoriaTI \n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria ConsultoríaTI ya ha sido agregada");
        }

        if (sistema.crearCategoriaIngreso("Soporte")) {
            texto += "Soporte \n\n---GASTO---\n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria Soporte ya ha sido agregada");
        }

        if (sistema.crearCategoriaGasto("DesarrolloSoftware", 0)) {
            texto += "DesarrolloSoftware \n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria DesarrolloSoftware ya ha sido agregada");
        }

        if (sistema.crearCategoriaGasto("GastosOficina", 0)) {
            texto += "GastosOficina \n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria GastosOficina ya ha sido agregada");
        }

        if (sistema.crearCategoriaGasto("GastoMarketing", 0)) {
            texto += "GastoMarketing \n";
        } else {
            JOptionPane.showMessageDialog(null, "Error. La categoria GastoMarketing ya ha sido agregada");
        }


        actualizarComboBoxes();

        generarPresupuestos();
        generarGastoseIngresos();


        textAreaGenerarDatosCate.setText(texto);
        generarDatosButton.setEnabled(false);
    }
    public void generarPresupuestos() {
        sistema.asignarPresupuestoGeneral(300);
        sistema.asignarPresupuestoACategoriaGasto("DesarrolloSoftware", 100);
        sistema.asignarPresupuestoACategoriaGasto("GastosOficina", 100);
        sistema.asignarPresupuestoACategoriaGasto("GastoMarketing", 100);
        labelPresupuestoTotal.setText(String.valueOf("Presupuesto Total: " + sistema.getPresupuestoTotal()));
    }
    public void generarGastoseIngresos() {

        Random random = new Random();

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2022);

        int randomMonth = random.nextInt(12);
        calendar.set(Calendar.MONTH, randomMonth);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int randomDay = random.nextInt(maxDay) + 1;
        calendar.set(Calendar.DAY_OF_MONTH, randomDay);

        // Añadiendo hora, minutos y segundos aleatorios
        int randomHour = random.nextInt(24);
        calendar.set(Calendar.HOUR_OF_DAY, randomHour);

        int randomMinute = random.nextInt(60);
        calendar.set(Calendar.MINUTE, randomMinute);

        int randomSecond = random.nextInt(60);
        calendar.set(Calendar.SECOND, randomSecond);

        Date fechaAleatoria = calendar.getTime();

        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "VentaSoftware", 25);
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "VentaSoftware", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "VentaSoftware", 25);

        sistema.agregarIngreso(8, fechaAleatoria, "pago 1", "ConsultoriaTI", 25);
        sistema.agregarIngreso(1, fechaAleatoria, "pago 2", "ConsultoriaTI", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "ConsultoriaTI", 25);

        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "Soporte", 25);
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "Soporte", 25);
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "Soporte", 25);

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
            if (!textFieldMontoIngreso.getText().isEmpty() && !textFieldMontoIngreso.getText().equals("0")) {
                if(!textFieldTasaImpuesto.getText().isEmpty()){
                    if (!textAreaDescripcionIngreso.getText().isEmpty()) {
                        Date fechaActual = new Date();
                        double monto = Double.parseDouble(textFieldMontoIngreso.getText());
                        double impuesto = Double.parseDouble(textFieldTasaImpuesto.getText());
                        if (sistema.agregarIngreso(monto, fechaActual, textAreaDescripcionIngreso.getText(), comboBoxIngreso.getSelectedItem().toString(),
                                impuesto)) {
                            CategoriaIngreso selectedCategoriaIngreso = sistema.getCategoriasIngreso().get(comboBoxIngreso.getSelectedItem().toString());
                            ingresosIngreso.setText(String.valueOf(selectedCategoriaIngreso.getIngresos()));
                            ingresoImpuestos.setText(String.valueOf(selectedCategoriaIngreso.getImpuestos()));
                            textFieldMontoIngreso.setText("");
                            textAreaDescripcionIngreso.setText("");
                            saldoLabel.setText(String.valueOf(sistema.getSaldo()));
                            comboBoxIngreso.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(null, "El ingreso ha sido agregado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe la categoria");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. La descripcion esta vacia");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Error. La tasa de impuesto no debe estar nula.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
        }
    }

    //Gasto
    public void ingresarGasto() {

        if (!comboBoxGasto.getSelectedItem().equals("")) {
            if (!textFieldMontoGasto.getText().isEmpty() && !textFieldMontoGasto.getText().equals("0")) {
                if(!textFieldTasaDeduccion.getText().isEmpty()){
                    if (!JTextAreaDescripcionGasto.getText().isEmpty()) {
                        Date fechaActual = new Date();
                        int resp = sistema.agregarGasto(Double.parseDouble(textFieldMontoGasto.getText()), fechaActual, JTextAreaDescripcionGasto.getText(), comboBoxGasto.getSelectedItem().toString(),
                                Double.parseDouble(textFieldTasaDeduccion.getText()));
                        if (resp == 1) {
                            textFieldMontoGasto.setText("");
                            JTextAreaDescripcionGasto.setText("");
                            CategoriaGasto selectedCategoriaGasto = sistema.getCategoriasGasto().get(comboBoxGasto.getSelectedItem().toString());
                            presupuestoCategoriaLabel.setText("Presupuesto Categoria: " + selectedCategoriaGasto.getPresupuesto());
                            gastosImpuestosLabel.setText(String.valueOf(selectedCategoriaGasto.getImpuestos()));
                            comboBoxGasto.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(null, "El gasto ha sido agregado correctamente");
                        } else if (resp == -1) {
                            JOptionPane.showMessageDialog(null, "Error. No existe la categoria");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error. El gasto excede el presupuesto de la categoría.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. La descripcion esta vacia");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Error. El impuesto no debe ser nulo");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
            }
        } else {
            presupuestoCategoriaLabel.setText("Presupuesto Categoria: 0");
            JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
        }
    }

    //Editar
    public void buscarEditarTransaccion() {
        if (verificarCampoTexto(textFieldEditarCategoria.getText())) {

            if (!idTransaccionLabel.getText().isEmpty() && !idTransaccionLabel.getText().equals("0")) {

                if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {

                    Transaccion t = sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText()));
                    if (t != null) {
                        textFieldMontoEditarTransaccion.setEditable(true);
                        textFieldTasaImpuestoDeduccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);

                        textFieldMontoEditarTransaccion.setText(String.valueOf(t.getMonto()));
                        textFieldTasaImpuestoDeduccion.setText(String.valueOf(t.getTasaImpuesto()));
                        textAreaEditarDescripTransaccion.setText(String.valueOf(t.getDescripcion()));
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textFieldTasaImpuestoDeduccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);

                        textFieldMontoEditarTransaccion.setText("");
                        textFieldTasaImpuestoDeduccion.setText("");
                        textAreaEditarDescripTransaccion.setText("");
                        JOptionPane.showMessageDialog(null, "Error. No se ha encontrado esa transaccion");
                    }


                } else if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {

                    Transaccion t = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText()));

                    if (t != null) {
                        textFieldMontoEditarTransaccion.setEditable(true);
                        textFieldTasaImpuestoDeduccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);

                        textFieldMontoEditarTransaccion.setText(String.valueOf(t.getMonto()));
                        textFieldTasaImpuestoDeduccion.setText(String.valueOf(t.getTasaImpuesto()));
                        textAreaEditarDescripTransaccion.setText(String.valueOf(t.getDescripcion()));
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        textFieldTasaImpuestoDeduccion.setEditable(false);
                        actualizarButton.setEnabled(false);

                        textFieldMontoEditarTransaccion.setText("");
                        textFieldTasaImpuestoDeduccion.setText("");
                        textAreaEditarDescripTransaccion.setText("");
                        JOptionPane.showMessageDialog(null, "Error. No se ha encontrado esa transaccion");
                    }

                } else {
                    textFieldMontoEditarTransaccion.setEditable(false);
                    textAreaEditarDescripTransaccion.setEditable(false);
                    actualizarButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Error. No se ha encontrado la categoria");
                }


            } else {
                textFieldMontoEditarTransaccion.setEditable(false);
                textAreaEditarDescripTransaccion.setEditable(false);
                actualizarButton.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error. El campo id solo acepta enteros positivos");
            }

        } else {
            textFieldMontoEditarTransaccion.setEditable(false);
            textAreaEditarDescripTransaccion.setEditable(false);
            actualizarButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres");
        }
    }
    public void actualizarEditarTransaccion() {
        if (!textFieldMontoEditarTransaccion.getText().isEmpty() && !textFieldMontoEditarTransaccion.getText().equals("0")) {

            if(!textFieldTasaImpuestoDeduccion.getText().isEmpty()){
                if (!textAreaEditarDescripTransaccion.getText().isEmpty()) {
                    //Gasto
                    if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {
                        int respGasto = sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                                Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                                textAreaEditarDescripTransaccion.getText(), Double.parseDouble(textFieldTasaImpuestoDeduccion.getText()));
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

                    //Ingreso
                    if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {
                        double ingresoAnterior =  sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).getIngresos();
                        int respIngreso = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                                Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                                textAreaEditarDescripTransaccion.getText(), Double.parseDouble(textFieldTasaImpuestoDeduccion.getText()));
                        if (respIngreso == 1) {
                            sistema.setSaldo((sistema.getSaldo()-ingresoAnterior)+sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).getIngresos());
                            saldoLabel.setText(String.valueOf(sistema.getSaldo()));
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
            }else{
                JOptionPane.showMessageDialog(null, "Error. No debe estar nulo el campo impuesto");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. Asignar al monto unicamente enteros positivos");
        }


    }

    //Eliminar
    public void eliminarTransaccion() {
        if (verificarCampoTexto(nombreCategoriaEliminar.getText())) {
            if (!eliminarIdTransaccion.getText().isEmpty()) {
                //Gasto
                if (sistema.getCategoriasGasto().get(nombreCategoriaEliminar.getText()) != null) {
                    if (sistema.getCategoriasGasto().get(nombreCategoriaEliminar.getText()).eliminarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()))) {
                        JOptionPane.showMessageDialog(null, "Transaccion eliminada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error. No existe ese id");
                    }
                }else if (sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()) != null) {

                    double ingresoEliminado = 0;
                    Transaccion t = sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()).buscarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()));
                    if(t != null){
                        ingresoEliminado = t.getMonto();
                    }

                    if (sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()).eliminarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()))) {
                        sistema.setSaldo(sistema.getSaldo()-ingresoEliminado);
                        saldoLabel.setText(String.valueOf(sistema.getSaldo()));
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

        if(comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Id")){
            c = Comparator.comparing(Transaccion::getId);
        }else if(comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Monto")){
            c = Comparator.comparing(Transaccion::getMonto);
        }else if(comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Impuesto")){
            c = Comparator.comparing(Transaccion::getImpuesto);
        }else if(comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("TasaImpuesto")){
            c = Comparator.comparing(Transaccion::getTasaImpuesto);
        }else if(comboBoxMostrarTransaccionCampo.getSelectedItem().toString().equals("Descripcion")){
            c = Comparator.comparing(Transaccion::getDescripcion);
        }else{
            c = Comparator.comparing(Transaccion::getFecha);
        }

        if(ascendenteCheckBox.isSelected()){
            text =sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), true, c);
        }else if(descendenteCheckBox.isSelected()){
            text =sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), false, c);
        }else{
            categoria = comboBoxMostrarTransacciones.getSelectedItem().toString();

            if (sistema.getCategoriasIngreso().containsKey(categoria)) {

                List<Transaccion> transacciones = sistema.getCategoriasIngreso().get(categoria).getTransacciones();

                for (Transaccion transaccion : transacciones) {
                    text += transaccion + "\n";
                }
            } else {
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

            if(!sistema.existenTransaccionesGastos()){

                if(sistema.asignarPresupuestoGeneral(Double.parseDouble(textFiedlPresupuestoTotal.getText()))){
                    labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
                    saldoLabel.setText(String.valueOf(sistema.getSaldo()));
                    JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Error. El presupuesto a asignar no se puede ser mayor al saldos");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Error. El presupuesto no se puede setear ya que existen transacciones asignadas");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total deber ser un entero positivo");
        }
    }
    public void aumentarPresupuestoGeneral(){
        if (!textFiedlPresupuestoTotal.getText().isEmpty() && !textFiedlPresupuestoTotal.getText().equals("0")) {

            if(sistema.aumentarPresupuestoGeneral(Double.parseDouble(textFiedlPresupuestoTotal.getText()))){
                labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
                saldoLabel.setText(String.valueOf(sistema.getSaldo()));
                JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error. Ya no hay mas saldo para asignar al presupuesto.");
            }


        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total deber ser un entero positivo");
        }
    }

    //Presupuesto Gastos
    public void asignarPresupuestoCategoria() {
        if (!textFieldMontoAsignarCatGasto.getText().isEmpty()) {

            if(sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getTransacciones().isEmpty()){
                if (sistema.asignarPresupuestoACategoriaGasto(comboBoxPresupuestoGastos.getSelectedItem().toString(), Double.parseDouble(textFieldMontoAsignarCatGasto.getText()))) {
                    textFieldPresupuestoActual.setText(String.valueOf(sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getPresupuesto()));
                    labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
                    JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error. No se puede asignar un monto mayor al presupuesto total.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error. La categoria tiene transacciones asignadas.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total esta vacio");
        }
    }

    public void aumentarPresupuestoGasto(){
        if (!textFieldMontoAsignarCatGasto.getText().isEmpty()) {

            if(sistema.aumentarPresupuestoACategoriGasto(comboBoxPresupuestoGastos.getSelectedItem().toString(), Double.parseDouble(textFieldMontoAsignarCatGasto.getText()))){
                textFieldPresupuestoActual.setText(String.valueOf(sistema.getCategoriasGasto().get(comboBoxPresupuestoGastos.getSelectedItem().toString()).getPresupuesto()));
                labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
                JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error. El presupuesto total es menor que lo que se quiere asignar o aumentar");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total esta vacio");
        }
    }

    //USUARIOS

    //Crear
    public void crearUsuario(){
        if(textFieldNombreUsuarioCrear.getText().length() >= 4){

            if (passwordFieldCrear.getPassword().length >= 4) {
                if(sistemaLogin.registerUser("", textFieldNombreUsuarioCrear.getText(), String.valueOf(passwordFieldCrear.getPassword()), false)){
                    JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Error. El usuario ya ha sido creado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. La contraseña debe contener al menos 4 caracteres");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario debe contener minimo 4 caracteres");
        }
    }

    //Editar
    public void buscarUsuario(){
        if(!textFieldUsuarioEditar.getText().isEmpty()){
            if(sistemaLogin.getUsers().containsKey(textFieldUsuarioEditar.getText())){
                textFieldNombreNuevoEditar.setEditable(true);
                passwordFieldEditar.setEditable(true);
                actualizarUsuarioButton.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Error. Usuario no encontrado");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El nombre de usuario no debe ser nulo");
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
    public void registrarPagoRecurrente(){
        if(!textFieldMontoRegistrarPago.getText().isEmpty() && Double.parseDouble(
                textFieldMontoRegistrarPago.getText()) > 0){

            if(!textFieldFrecuenciaRegistrar.getText().isEmpty() && Double.parseDouble(
                    textFieldFrecuenciaRegistrar.getText()) > 0){

                if(!textAreaDescripcionPagoRegistrar.getText().isEmpty()){

                    if (!siPagadoRegistrarCheckBox.isSelected() && !noPagadoRegistrarCheckBox.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Error. Debes seleccionar al menos un checkbox");
                    } else {
                        boolean pagado;

                        Calendar calendar = fechaRegistro.getSelectedDate();

                        // Convertir Calendar a LocalDate
                        LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(textFieldFrecuenciaRegistrar.getText()));
                        System.out.println(fSoloRegistro);
                        if(siPagadoRegistrarCheckBox.isSelected()){
                            pagado = true;
                        }else{
                            pagado = false;
                        }

                        String mensaje = null;
                        int resp = -1;

                        System.out.println("P: " + pagado);
                        if ((fRegistro.isAfter(dia) || fRegistro.equals(dia)) && !pagado) {
                            mensaje = "Pago Recurrente agregado exitosamente";
                            resp = sistema.registrarPagoRecurrente(Double.parseDouble(textFieldMontoRegistrarPago.getText()),
                                    comboBoxMonedaRegistrar.getSelectedItem().toString(), textFieldFrecuenciaRegistrar.getText(), fRegistro, pagado, textAreaDescripcionPagoRegistrar.getText());
                        } else if ((fSoloRegistro.isBefore(dia) || fSoloRegistro.isEqual(dia)) && pagado) {
                            System.out.println("pase");
                            mensaje = "Pago Recurrente agregado exitosamente";
                            resp = sistema.registrarPagoRecurrente(Double.parseDouble(textFieldMontoRegistrarPago.getText()),
                                    comboBoxMonedaRegistrar.getSelectedItem().toString(), textFieldFrecuenciaRegistrar.getText(), fRegistro, pagado, textAreaDescripcionPagoRegistrar.getText());
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

                            saldoLabel.setText(String.valueOf(sistema.getSaldo()));
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
    }

    //Editar
    public void buscarPagoRecurrente(){
        if(!textFieldIdEditarPago.getText().isEmpty() && Integer.parseInt(textFieldIdEditarPago.getText()) > 0){

            int resp = sistema.buscarPagoRecurrente(Integer.parseInt(textFieldIdEditarPago.getText()));
            if(resp == 0){
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se encontró un PagoRecurrente con ese id.");
            }else if(resp == 1){
                camposEditarPagoRecurrente(true);
                PagoRecurrente pago = sistema.getPagosRecurrentes().get(Integer.parseInt(textFieldIdEditarPago.getText()));
                textFieldMontoEditarPago.setText(String.valueOf(pago.getMonto()));
                comboBoxMonedasEditar.setSelectedItem(pago.getMoneda());
                textFieldFrecuenciaMesesEditar.setText(String.valueOf(pago.getFrecuencia()));
                textAreaEditarPago.setText(String.valueOf(pago.getDescripcion()));
                sistema.mostrarPagoRecurrente();
            }else if(resp == -1){
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se puede editar un pago que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Error. El id no debe ser nulo y debe ser mayor a 0.");
            camposEditarPagoRecurrente(false);
        }
    }

    public void editarPagoRecurrente(){
        if(!textFieldMontoEditarPago.getText().isEmpty() && Double.parseDouble(textFieldMontoEditarPago.getText()) > 0){

            if(!textFieldFrecuenciaMesesEditar.getText().isEmpty() && Double.parseDouble(textFieldFrecuenciaMesesEditar.getText()) > 0){


                if(!textAreaEditarPago.getText().isEmpty()){

                    boolean pagado;

                    Calendar calendar = fechaEditarPago.getSelectedDate();

                    // Convertir Calendar a LocalDate
                    LocalDate fRegistro = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fSoloRegistro = fRegistro.plusMonths(Integer.parseInt(textFieldFrecuenciaMesesEditar.getText()));
                    System.out.println(fSoloRegistro);
                    if(sistema.getPagosRecurrentes().get(Integer.parseInt(textFieldIdEditarPago.getText())).isSoloRegistro()){
                        pagado = true;
                    }else{
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
                        resp =  sistema.actualizarPagoRecurrente(Integer.parseInt(textFieldIdEditarPago.getText()), Double.parseDouble(textFieldMontoEditarPago.getText()),
                                comboBoxMonedasEditar.getSelectedItem().toString(), textFieldFrecuenciaMesesEditar.getText(), fRegistro, textAreaEditarPago.getText());
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

                        saldoLabel.setText(String.valueOf(sistema.getSaldo()));
                        sistema.mostrarPagoRecurrente();
                    }


                }else{
                    JOptionPane.showMessageDialog(null, "Error. La descripcion no puede estar vacia.");
                }



            }else{
                JOptionPane.showMessageDialog(null, "Error. La frecuencia debe ser mayor a 0.");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser mayor a 0.");
        }

    }

    public void eliminarPagoRecurrente(){
        if(!textFieldIdEliminarPago.getText().isEmpty() && Integer.parseInt(textFieldIdEliminarPago.getText()) > 0){
            int resp = sistema.buscarPagoRecurrente(Integer.parseInt(textFieldIdEliminarPago.getText()));
            if(resp == 0){
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se encontró un PagoRecurrente con ese id.");
            }else if(resp == 1){
                sistema.eliminarPagoRecurrente(Integer.parseInt(textFieldIdEliminarPago.getText()));
                JOptionPane.showMessageDialog(null, "Pago eliminado correctamente");
                sistema.mostrarPagoRecurrente();
            }else if(resp == -1){
                camposEditarPagoRecurrente(false);
                JOptionPane.showMessageDialog(null, "Error. No se puede eliminar un pago que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. El id debe ser mayor a 0.");
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

    public void mostrarPagosRecurrentes(){
        String texto = "";
        boolean pagados = false;
        if(comboBox3.getSelectedItem().equals("Pagados")){
            pagados = true;
        }
        System.out.println("Asd: " + pagados);
        for (PagoRecurrente pagoRecurrente : sistema.mostrarPagosCategorizados(pagados).values()) {
            texto += pagoRecurrente.toString() + '\n';
        }

        textAreaMostrarPagosRecurrentees.setText(texto);
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

                                saldoLabel.setText(String.valueOf(sistema.getSaldo()));
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

    public void buscarPagoDeudasPrestamos(){

        if(comboBoxCategoriaEDP.getSelectedItem() != null){
            String tipo;
            if(sistema.esDeuda(comboboxRegistrarPagoDeudasPr.getSelectedItem().toString())){
                tipo = "Deuda";
            }else{
                tipo = "Prestamo";
            }
            if(!idEDP.getText().isEmpty() && Integer.parseInt(idEDP.getText()) > 0){
                int resp = sistema.buscarPrestamoODeuda(Integer.parseInt(idEDP.getText()), comboBoxCategoriaEDP.getSelectedItem().toString());
                if(resp == 0){
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. No se encontró ninguna obligacion financiera con ese id.");
                }else if(resp == 1){
                    camposEditarDeudaPrestamo(true);
                    PagoRecurrente pago;
                    if (tipo.equals("Deuda")){
                        pago = sistema.getCategoriasDeudas().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
                    }else{
                        pago = sistema.getCategoriasPrestamos().get(comboBoxCategoriaEDP.getSelectedItem().toString()).getPagosRecurrentes().get(Integer.parseInt(idEDP.getText()));
                    }

                    montoEDP.setText(String.valueOf(pago.getMonto()));
                    monedaEDP.setSelectedItem(pago.getMoneda());
                    plazosEDP.setText(String.valueOf(pago.getFrecuencia()));
                    descripcionEDP.setText(String.valueOf(pago.getDescripcion()));
                    sistema.mostrarPagoRecurrente();
                }else if(resp == -1){
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. No se puede editar ninguna obligacion financiera que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                }else{
                    camposEditarDeudaPrestamo(false);
                    JOptionPane.showMessageDialog(null, "Error. Obligacion financiera no encontrada con ese id.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error. Ingrese un id mayor a 0.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria");
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

                        saldoLabel.setText(String.valueOf(sistema.getSaldo()));
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

    public void eliminarDeudaPrestamo(){
        String tipo;
        PagoRecurrente pago;

        if(sistema.esDeuda(comboBoxDEDP.getSelectedItem().toString())){
            tipo = "Deuda";
        }else{
            tipo = "Prestamo";
        }

        if(comboBoxDEDP.getSelectedItem() != null){

            if(!idEEDP.getText().isEmpty() && Integer.parseInt(idEEDP.getText()) > 0){

                int resp = sistema.buscarPrestamoODeuda(Integer.parseInt(idEEDP.getText()), comboBoxDEDP.getSelectedItem().toString());

                if(resp == -3){
                    camposEditarPagoRecurrente(false);
                    JOptionPane.showMessageDialog(null, "Error. No se encontraron obligaciones financieras con ese id.");
                }else if(resp == 1){
                    sistema.eliminarDeudaPrestamo(Integer.parseInt(idEEDP.getText()), comboBoxDEDP.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Obligacion financiera eliminada correctamente");
                    sistema.mostrarPagoRecurrente();
                }else if(resp == -1){
                    camposEditarPagoRecurrente(false);
                    JOptionPane.showMessageDialog(null, "Error. No se puede eliminar una obligaciones financiera que ya se ha empezado a pagarse o que ya se ha pagado por completo.");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Error. El id debe ser mayor a 0.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error. Seleccione una categoria.");
        }
    }

    public void camposEditarDeudaPrestamo(boolean bool){
        montoEDP.setEditable(bool);
        monedaEDP.setEnabled(bool);
        plazosEDP.setEditable(bool);
        descripcionEDP.setEditable(bool);
        editarEDP.setEnabled(bool);
    }

    //FECHA
    public void aumentarDia(){
        dia = dia.plusDays(1);
        dateLabel.setText(dia.toString());
        System.out.println(sistema.verificarPagosPendientes());

        System.out.println(sistema.verificarPagosPendientesDeudasPrestamos());

        saldoLabel.setText(String.valueOf(sistema.getSaldo()));
    }

    public void aumentarMes(){
        dia = dia.plusMonths(1);
        dateLabel.setText(dia.toString());
        System.out.println(sistema.verificarPagosPendientes());

        System.out.println(sistema.verificarPagosPendientesDeudasPrestamos());

        saldoLabel.setText(String.valueOf(sistema.getSaldo()));
    }

    //ACTIVACIONES Y ACTUALIZACIONES.-
    public void activarCamposCategorias(boolean bool) {
        textFieldNombreCategoria.setEditable(bool);
        crearCategoriaButton.setEnabled(bool);
        textFieldNombreCategoria.setText("");
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
