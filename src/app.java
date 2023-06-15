import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class app extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
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
    private JButton quemarDatosButton;
    private JTextArea textAreaTransaccionesQuemado;
    private JComboBox comboBoxCategoriaEditarTransaccion;
    private GestionFinanciera sistema = new GestionFinanciera();

    public app() {
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
                } else {
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
                        System.out.println("Asdf");
                        textFieldMontoAsignarCatGasto.setEditable(true);
                        asignarPresupuestoGastoButton.setEnabled(true);
                    } else {
                        System.out.println("sadfs");
                        textFieldMontoAsignarCatGasto.setEditable(false);
                        asignarPresupuestoGastoButton.setEnabled(false);
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
                    } else {
                        System.out.println("La categoría seleccionada no existe.");
                    }
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
                    descendenteCheckBox.setSelected(false);
                }
            }
        });
        descendenteCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (descendenteCheckBox.isSelected()) {
                    ascendenteCheckBox.setSelected(false);
                }
            }
        });
    }

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
                    if (sistema.getCategoriasIngreso().get(nombreCategoriaEliminar.getText()).eliminarTransaccion(Integer.parseInt(eliminarIdTransaccion.getText()))) {
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

    public void actualizarEditarTransaccion() {
        if (!textFieldMontoEditarTransaccion.getText().isEmpty() && !textFieldMontoEditarTransaccion.getText().equals("0")) {
            if (!textAreaEditarDescripTransaccion.getText().isEmpty()) {
                //Gasto
                if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {
                    int respGasto = sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                            Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                            textAreaEditarDescripTransaccion.getText());
                    if (respGasto == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha modificado correctamente la transaccion");
                    } else if (respGasto == 0) {

                        JOptionPane.showMessageDialog(null, "Error. No se puede asignar porque sobrepasa el presupuesto.");
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Error. No existe ningun id asi.");
                    }
                }

                //Ingreso
                if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {
                    int respIngreso = sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).editarTransaccion(Integer.parseInt(idTransaccionLabel.getText()),
                            Double.parseDouble(textFieldMontoEditarTransaccion.getText()),
                            textAreaEditarDescripTransaccion.getText());
                    if (respIngreso == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha modificado correctamente la transaccion");
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Error. No existe ningun id asi.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. No debe ser nulo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. Asignar unicamente enteros positivos");
        }


    }

    public void buscarEditarTransaccion() {
        if (verificarCampoTexto(textFieldEditarCategoria.getText())) {

            if (!idTransaccionLabel.getText().isEmpty() && !idTransaccionLabel.getText().equals("0")) {

                if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()) != null) {

                    if (sistema.getCategoriasGasto().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText())) != null) {
                        textFieldMontoEditarTransaccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Error. No se ha encontrado esa transaccion");
                    }


                } else if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()) != null) {

                    if (sistema.getCategoriasIngreso().get(textFieldEditarCategoria.getText()).buscarTransaccion(Integer.parseInt(idTransaccionLabel.getText())) != null) {
                        textFieldMontoEditarTransaccion.setEditable(true);
                        textAreaEditarDescripTransaccion.setEditable(true);
                        actualizarButton.setEnabled(true);
                    } else {
                        textFieldMontoEditarTransaccion.setEditable(false);
                        textAreaEditarDescripTransaccion.setEditable(false);
                        actualizarButton.setEnabled(false);
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

    public void asignarPresupuestoCategoria() {
        if (!textFieldMontoAsignarCatGasto.getText().isEmpty()) {
            if (sistema.asignarPresupuestoACategoriaGasto(comboBoxPresupuestoGastos.getSelectedItem().toString(), Double.parseDouble(textFieldMontoAsignarCatGasto.getText()))) {
                labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
                JOptionPane.showMessageDialog(null, "Presupuesto asignado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se puede asignar un monto mayor al presupuesto total.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total esta vacio");
        }
    }

    public void asignarPresupuestoGeneral() {
        if (!textFiedlPresupuestoTotal.getText().isEmpty() && !textFiedlPresupuestoTotal.getText().equals("0")) {
            sistema.setPresupuestoTotal(Double.parseDouble(textFiedlPresupuestoTotal.getText()));
            labelPresupuestoTotal.setText("Presupuesto Total: " + sistema.getPresupuestoTotal());
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto total deber ser un entero positivo");
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

    public void activarCamposCategorias(boolean bool) {
        textFieldNombreCategoria.setEditable(bool);
        crearCategoriaButton.setEnabled(bool);
        textFieldNombreCategoria.setText("");
    }

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
            } else {
                if (sistema.crearCategoriaGasto(nombreCategoria, 0)) {
                    actualizarComboBoxes();
                    JOptionPane.showMessageDialog(null, "Categoria gasto agregada correctamente.");
                    activarCamposCategorias(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La categoria ya existe");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El campo nombre solo acepta caracteres.");
        }
    }

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

    public void ingresarGasto() {
        if (!textFieldMontoGasto.getText().isEmpty() && !textFieldMontoGasto.getText().equals("0")) {
            if (!comboBoxGasto.getSelectedItem().equals("")) {
                if (!JTextAreaDescripcionGasto.getText().isEmpty()) {
                    Date fechaActual = new Date();
                    int resp = sistema.agregarGasto(Double.parseDouble(textFieldMontoGasto.getText()), fechaActual, JTextAreaDescripcionGasto.getText(), comboBoxGasto.getSelectedItem().toString());
                    if (resp == 1) {
                        textFieldMontoGasto.setText("");
                        JTextAreaDescripcionGasto.setText("");
                        CategoriaGasto selectedCategoriaGasto = sistema.getCategoriasGasto().get(comboBoxGasto.getSelectedItem().toString());
                        presupuestoCategoriaLabel.setText("Presupuesto Categoria: " + selectedCategoriaGasto.getPresupuesto());
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
            } else {
                presupuestoCategoriaLabel.setText("Presupuesto Categoria: 0");
                JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
        }
    }

    public void ingresarIngreso() {

        if (!textFieldMontoIngreso.getText().isEmpty() && !textFieldMontoIngreso.getText().equals("0")) {
            if (!comboBoxIngreso.getSelectedItem().equals("")) {
                if (!textAreaDescripcionIngreso.getText().isEmpty()) {
                    Date fechaActual = new Date();
                    if (sistema.agregarIngreso(Double.parseDouble(textFieldMontoIngreso.getText()), fechaActual, textAreaDescripcionIngreso.getText(), comboBoxIngreso.getSelectedItem().toString())) {
                        textFieldMontoIngreso.setText("");
                        textAreaDescripcionIngreso.setText("");
                        comboBoxIngreso.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(null, "El ingreso ha sido agregado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe la categoria");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error. La descripcion esta vacia");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error. No hay ninguna categoria seleccionada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error. El monto debe ser un entero positivo");
        }
    }

    //funcion que verifica los campos de texto
    public boolean verificarCampoTexto(String textField) {
        // Verifica si el campo de texto está vacío
        if (textField == null || textField.isEmpty()) {
            return false;
        }

        // Verifica si el campo de texto contiene únicamente letras
        return textField.matches("[a-zA-Z]+");
    }


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
        sistema.setPresupuestoTotal(1000);
        sistema.asignarPresupuestoACategoriaGasto("DesarrolloSoftware", 200);
        sistema.asignarPresupuestoACategoriaGasto("GastosOficina", 200);
        sistema.asignarPresupuestoACategoriaGasto("GastoMarketing", 200);
        labelPresupuestoTotal.setText(String.valueOf(sistema.getPresupuestoTotal()));
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

        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "VentaSoftware");
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "VentaSoftware");
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "VentaSoftware");

        sistema.agregarIngreso(8, fechaAleatoria, "pago 1", "ConsultoriaTI");
        sistema.agregarIngreso(1, fechaAleatoria, "pago 2", "ConsultoriaTI");
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "ConsultoriaTI");

        sistema.agregarIngreso(3, fechaAleatoria, "pago 1", "Soporte");
        sistema.agregarIngreso(4, fechaAleatoria, "pago 2", "Soporte");
        sistema.agregarIngreso(5, fechaAleatoria, "pago 3", "Soporte");

        sistema.agregarGasto(9, fechaAleatoria, "pago 1", "DesarrolloSoftware");
        sistema.agregarGasto(5, fechaAleatoria, "pago 2", "DesarrolloSoftware");
        sistema.agregarGasto(52, fechaAleatoria, "pago 3", "DesarrolloSoftware");

        sistema.agregarGasto(31, fechaAleatoria, "pago 1", "GastosOficina");
        sistema.agregarGasto(48, fechaAleatoria, "pago 2", "GastosOficina");
        sistema.agregarGasto(52, fechaAleatoria, "pago 3", "GastosOficina");

        sistema.agregarGasto(13, fechaAleatoria, "pago 1", "GastoMarketing");
        sistema.agregarGasto(25, fechaAleatoria, "pago 2", "GastoMarketing");
        sistema.agregarGasto(35, fechaAleatoria, "pago 3", "GastoMarketing");
    }

    public void actualizarComboBoxes() {
        // Limpiar ambos JComboBox
        comboBoxIngreso.removeAllItems();
        comboBoxGasto.removeAllItems();
        comboBoxPresupuestoGastos.removeAllItems();
        comboBoxMostrarTransacciones.removeAllItems();

        // Obtener las categorías de ingreso y gasto del sistema
        Map<String, CategoriaIngreso> categoriasIngreso = sistema.getCategoriasIngreso();
        Map<String, CategoriaGasto> categoriasGasto = sistema.getCategoriasGasto();

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

    }

    public void mostrarTransaccionesCategoria() {
        String text = "";
        String categoria = "";
        if(ascendenteCheckBox.isSelected()){
            text =sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), true);
        }else if(descendenteCheckBox.isSelected()){
            text =sistema.ordenarTransacciones(comboBoxMostrarTransacciones.getSelectedItem().toString(), false);
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


}
