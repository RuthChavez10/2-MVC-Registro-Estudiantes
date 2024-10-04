/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import Modelo.PersonaArray;
import Vista.VistaPresentacion;


import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controlador {
    private VistaPresentacion vista;
    private PersonaArray modelo;

    public Controlador(VistaPresentacion vista, PersonaArray modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
        cargarTabla();
    }

    public void agregarPersona() {
        String codigo = vista.getCodigo();
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        Date fechaNacimiento = vista.getFechaNacimiento();

        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento == null) {
            System.out.println("Completar todos los campos");
            return;
        }

        Persona persona = new Persona(codigo, nombre, apellido, fechaNacimiento);
        modelo.agregarPersona(persona);
        cargarTabla();
        vista.limpiarCampos();
    }

    public void actualizarPersona(int selectedRow) {
        if (selectedRow == -1) {
            System.out.println("Seleccione una fila para actualizar.");
            return;
        }

        String codigo = vista.getCodigo();
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        Date fechaNacimiento = vista.getFechaNacimiento();

        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento == null) {
            System.out.println("Completar todos los campos");
            return;
        }

        Persona persona = new Persona(codigo, nombre, apellido, fechaNacimiento);
        modelo.actualizarPersona(selectedRow, persona);
        cargarTabla();
        vista.limpiarCampos();
    }

    public void eliminarPersona(int selectedRow) {
        if (selectedRow != -1) {
            modelo.eliminarPersona(selectedRow);
            cargarTabla();
            vista.limpiarCampos();
        } else {
            System.out.println("Selecciona un usuario para eliminar.");
        }
    }

    public void cargarTabla() {
        DefaultTableModel tableModel = vista.getTableModel();
        tableModel.setRowCount(0); // Limpiar la tabla
        for (Persona persona : modelo.getPersonas()) {
            String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(persona.getFechaDeNacimiento());
            tableModel.addRow(new Object[]{persona.getCodigo(), persona.getNombre(), persona.getApellido(), fechaFormateada});
        }
    }

    public void limpiarTabla() {
        modelo = new PersonaArray(); // Reiniciar el modelo
        cargarTabla();
    }
}