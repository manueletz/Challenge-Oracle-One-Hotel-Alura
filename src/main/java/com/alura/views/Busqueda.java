package com.alura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alura.controller.HuespedController;
import com.alura.controller.ReservaController;
import com.alura.modelo.Huesped;
import com.alura.modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;
	private Integer tablaSeleccionada=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/com/alura/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 302, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		/*
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Número de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		*/
		
		//Se modifico para no poder editar el id
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		
        String colTitlesReserva[] = {"Número de Reserva", "Fecha Check In", "Fecha Check Out", "Valor", "Forma de Pago"};
        boolean[] isEditableReserva = {false,true,true,false,true};
        
        modelo = new DefaultTableModel(colTitlesReserva, 0) {
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return isEditableReserva[column];
             }
        };
        tbReservas.setModel(modelo);
        JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tablaSeleccionada = 1;
				//System.out.println("tablaSeleccionada: "+tablaSeleccionada);
			}
		});
		
        /*
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Teléfono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		*/
		
		//Se modifico para no poder editar el id y id_reserva
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		
        String colTitlesHuespedes[] = {"Número de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Teléfono", "Número de Reserva"};
        boolean[] isEditableHuespedes = {false,true,true,true,true,true,false};
        
        modeloHuesped = new DefaultTableModel(colTitlesHuespedes, 0) {
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return isEditableHuespedes[column];
             }
        };
        tbHuespedes.setModel(modeloHuesped);
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tablaSeleccionada = 2;
			}
		});
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botÃ³n este cambiarÃ¡ de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botÃ³n este volverÃ¡ al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
				if (txtBuscar.getText().equals(null) || txtBuscar.getText().equals("")) {
					LlenarTablaResevas();
					LlenarTablaHuespedes();
				} else {
					llenarTablaReservasPorTextoBuscado(txtBuscar.getText());
					llenarTablaHuespedesPorTextoBuscado(txtBuscar.getText());
				}
				panel.repaint();
			}

		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablaSeleccionada==1) {
					
					if (tbReservas.getSelectedRow()>=0) {
						editarFilaReservaSeleccionada();
					}else {
						JOptionPane.showMessageDialog(null, "Favor seleccione un fila para editar");
					}
				}
				
				if (tablaSeleccionada==2) {
					if (tbHuespedes.getSelectedRow()>=0) {
						editarFilaHuespedSeleccionada();
					}else {
						JOptionPane.showMessageDialog(null, "Favor seleccione un fila para editar");
					}
				}
				
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablaSeleccionada==1) {
					
					if (tbReservas.getSelectedRow()>=0) {
						eliminarFilaReservaSeleccionada();
						txtBuscar.setText(null);
						LlenarTablaResevas();
						LlenarTablaHuespedes();
						
					}else {
						JOptionPane.showMessageDialog(null, "Favor seleccione un fila para editar");
					}
				}
				
				if (tablaSeleccionada==2) {
					if (tbHuespedes.getSelectedRow()>=0) {
						eliminarFilaHuespedSeleccionada();
						txtBuscar.setText(null);
						LlenarTablaResevas();
						LlenarTablaHuespedes();
					}else {
						JOptionPane.showMessageDialog(null, "Favor seleccione un fila para editar");
					}
				}
				
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Codigo que permite mover la ventana por la pantalla segun la posicion de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	 private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	 }
	 
	 private List<Reserva> buscarReservas(){
		 return this.reservaController.buscar();
	 }
	 
	 private void LlenarTablaResevas() {
		 List<Reserva> reserva = buscarReservas();
		 
		 try {
			 deleteAllTableRows(tbReservas);
			 for (Reserva reservas : reserva) {
				 
				 modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(),
						 reservas.getValor(), reservas.getFormaPago() });
				 
			 }
		 } catch (Exception e) {
			 throw e;
		 }
	 }

	 private List<Huesped> buscarHuespedes() {
		 return this.huespedController.buscar();
	 }
	 
	 
	 private List<Reserva> buscarReservasPorTextoBusqueda(String textoBusqueda) {
		 return this.reservaController.buscarReservasPorTextoBusqueda(textoBusqueda);
	 }
		 
	 private List<Reserva> buscarReservasPorIdReservaBusqueda(int idReserva) {
		return this.reservaController.buscarReservasPorIdReservaBusqueda(idReserva);
	 }
	 
	 private void llenarTablaReservasPorTextoBuscado(String textoBusqueda) {
		List<Reserva> reserva;
		 if (!verificarEsNumero(textoBusqueda)){
			 reserva = buscarReservasPorTextoBusqueda(textoBusqueda);
		 }else {
			 reserva = buscarReservasPorIdReservaBusqueda(Integer.valueOf(textoBusqueda));
		 }
		 
		 
		 try {
			 deleteAllTableRows(tbReservas);
				 for (Reserva reservas : reserva) {
					 System.out.println(reservas);
					 
					 modelo.addRow(new Object[] { reservas.getId(), reservas.getFechaEntrada(), reservas.getFechaSalida(),
							 reservas.getValor(), reservas.getFormaPago() });
					 
				 }
			 } catch (Exception e) {
				 throw e;
			 }
	}
	 
	 
	 private void editarFilaReservaSeleccionada() {
		Integer id;
		Date fechaEntrada;
		Date fechaSalida;
		Integer valor=0;
		String formaPago;
		Reserva reserva;
		
		
		if ( validacionesCamposReservas() == true ) {
		
			Integer filaSeleccionada=tbReservas.getSelectedRow();
			System.out.println("Fila seleccionada: "+tbReservas.getSelectedRow());		 
			
			String fechaEntradaString = tbReservas.getValueAt(filaSeleccionada, 1).toString(); 
			String fechaSalidaString = tbReservas.getValueAt(filaSeleccionada, 2).toString(); 
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	 
	
			
			id = (Integer) tbReservas.getValueAt(filaSeleccionada, 0); //id
			
			try {
				fechaEntrada = formatter.parse(fechaEntradaString); //Fecha Entrada
				fechaSalida = formatter.parse(fechaSalidaString); //Fecha Salida
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			
			int diasEstadia = DiasEstadia(fechaEntrada,fechaSalida);
			int valorTotalReserva = calcularTarifaEstadia(diasEstadia);
			
			valor = valorTotalReserva;
			tbReservas.setValueAt(valorTotalReserva, filaSeleccionada, 3);
			formaPago = (String) tbReservas.getValueAt(filaSeleccionada, 4); //Forma de Pago
			reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);

			try {
				editarReserva(reserva);
				JOptionPane.showMessageDialog(null, "Datos guardados con éxito, Número de Reserva: "+reserva.getId());
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Lo sentimos no fue posible guardar los datos, Número de Reserva: "+reserva.getId());
				throw new RuntimeException(error);

			}
		}
	 } 
	 
	 private Boolean validacionesCamposReservas() {
		 Date fechaEntrada = null;
		 Date fechaSalida = null;
		 int diasEstadia=0;
		 int valorTotalReserva=0;
		 
		 Integer filaSeleccionada=tbReservas.getSelectedRow();
		 
		 //Verificar que fecha no son vacías
		 if ( (tbReservas.getValueAt(filaSeleccionada, 1).toString().length() == 0 || tbReservas.getValueAt(filaSeleccionada, 2).toString().length() == 0) ) {
			 JOptionPane.showMessageDialog(null, "La fecha de Inicio y Fin no deben esta vacías");
			 return false;
		 }
		 
		 //Verificar si el formato de las fechas es yyyy-MM-dd
		 if (tbReservas.getValueAt(filaSeleccionada, 1) != null &&  tbReservas.getValueAt(filaSeleccionada, 2) != null) {
			 
			try {
				String fechaEntradaString = tbReservas.getValueAt(filaSeleccionada, 1).toString(); 
				String fechaSalidaString = tbReservas.getValueAt(filaSeleccionada, 2).toString();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				formatter.setLenient(false); //indulgente o no con el formato
				fechaEntrada = formatter.parse(fechaEntradaString); //Fecha Entrada
				fechaSalida = formatter.parse(fechaSalidaString); //Fecha Salida
				diasEstadia = DiasEstadia(fechaEntrada,fechaSalida);
				valorTotalReserva = calcularTarifaEstadia(diasEstadia);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Verificar el formato de las fechas de inicio y fin, el formato debe ser yyyy-MM-dd");
				return false;
			}
		}
		 
		//Verificar si los días de estadía son mayores a 365 
		if (diasEstadia > 365) {
			JOptionPane.showMessageDialog(null, "Lo sentimos, no se permiten reservaciones de mas de 365 días, favor verificar");
			return false;
		}
		
		//Verificar si los días de estadía son cero o menores
		if (diasEstadia <= 0) {
			JOptionPane.showMessageDialog(null, "La fecha final debe ser mayor a la inicial, favor verificar");
			return false;
		} 
		
		if (!List.of("Tarjeta de Crédito","Tarjeta de Débito","Dinero en Efectivo").contains(tbReservas.getValueAt(filaSeleccionada, 4).toString()) ) {
			JOptionPane.showMessageDialog(null, "Favor de verificar el método de pago, pagos permitidos: Tarjeta de Crédito, Tarjeta de Débito o Dinero en Efectivo");
			return false;
		}
		
		return true;  
		 
	 }
	
	 private void editarReserva(Reserva reserva) {
		 this.reservaController.editarReserva(reserva);
	 }
	 
	public int DiasEstadia(Date fechaInicio, Date fechaFin) {
		java.sql.Date fechaEntrada =  new java.sql.Date(fechaInicio.getTime()) ;
		java.sql.Date fechaSalida = new java.sql.Date(fechaFin.getTime());
		
		int dias = (int)((fechaSalida.getTime()  - 
				fechaEntrada.getTime())/(1000 * 60 * 60 * 24));
		
		return dias;
	
	}
	
	public int calcularTarifaEstadia(int dias) {
		int valor;
		valor = dias * 20;
		return valor;
	}
	
	 private void eliminarFilaReservaSeleccionada() {
		Integer id;
		Integer filaSeleccionada;

		if (tbReservas.getSelectedRow()>=0) {
			filaSeleccionada=tbReservas.getSelectedRow();
			id = (Integer) tbReservas.getValueAt(filaSeleccionada, 0);
			
			try {
				eliminarReserva(id);
				JOptionPane.showMessageDialog(null, "Datos eliminados con éxito, Número de Reserva: "+ id);
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Lo sentimos no fue posible eliminar los datos, Número de Reserva: "+id);
				throw new RuntimeException(error);

			}
		}
	 } 
	
	private void eliminarReserva(int id) {
		this.reservaController.eliminarReserva(id);
	}
	
	 private void eliminarFilaHuespedSeleccionada() {
		Integer id;
		Integer filaSeleccionada;

		if (tbHuespedes.getSelectedRow()>=0) {
			filaSeleccionada=tbHuespedes.getSelectedRow();
			id = (Integer) tbHuespedes.getValueAt(filaSeleccionada, 0);
			
			try {
				eliminarHuesped(id);
				JOptionPane.showMessageDialog(null, "Datos eliminados con éxito, Número de Huesped: "+ id);
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Lo sentimos no fue posible eliminar los datos, Número de Huesped: "+id);
				throw new RuntimeException(error);

			}
		}
	 } 
	
	private void eliminarHuesped(int id) {
		this.huespedController.eliminarHuesped(id);
	}
	
	 private void editarFilaHuespedSeleccionada() {
		Integer id;
		String nombre;
		String apellido;
		Date fechaNacimiento;
		String nacionalidad;
		String telefono;
		Integer idReserva;
		Huesped huesped;
		
		if ( validacionesCamposHuespedes() == true ) {
		
			Integer filaSeleccionada=tbHuespedes.getSelectedRow();
			System.out.println("Fila seleccionada Huesped: "+tbHuespedes.getSelectedRow());		 
			
			id = (Integer) tbHuespedes.getValueAt(filaSeleccionada, 0);
			nombre = (String) tbHuespedes.getValueAt(filaSeleccionada, 1);
			apellido = (String) tbHuespedes.getValueAt(filaSeleccionada, 2);
			
			String fechaNacimientoString = tbHuespedes.getValueAt(filaSeleccionada, 3).toString();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	 
			try {
				fechaNacimiento = formatter.parse(fechaNacimientoString);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			
			nacionalidad = (String) tbHuespedes.getValueAt(filaSeleccionada, 4);
			telefono = (String) tbHuespedes.getValueAt(filaSeleccionada, 5);
			idReserva = (Integer) tbHuespedes.getValueAt(filaSeleccionada, 6);
			
			huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);

			try {
				editarHuesped(huesped);
				JOptionPane.showMessageDialog(null, "Datos guardados con éxito, Número de Huesped: "+huesped.getId());
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Lo sentimos no fue posible guardar los datos, Número de Huesped: "+huesped.getId());
				throw new RuntimeException(error);

			}
		}
	 } 
	
	private Boolean validacionesCamposHuespedes() {
		 Date fechaNacimiento = null;
		 
		 Integer filaSeleccionada=tbHuespedes.getSelectedRow();
		 
		 //Verificar que compos no se encuentren vacios
		 if ( tbHuespedes.getValueAt(filaSeleccionada, 1).toString().length() == 0 ||
			  tbHuespedes.getValueAt(filaSeleccionada, 2).toString().length() == 0 ||
			  tbHuespedes.getValueAt(filaSeleccionada, 3).toString().length() == 0 ||
			  tbHuespedes.getValueAt(filaSeleccionada, 4).toString().length() == 0 ||
			  tbHuespedes.getValueAt(filaSeleccionada, 5).toString().length() == 0 
			  ) {
			 JOptionPane.showMessageDialog(null, "Favor verificar todos los campos deben estar llenos");
			 return false;
		 }
		 
		 //Verificar si el formato de las fechas es yyyy-MM-dd
		 if (tbHuespedes.getValueAt(filaSeleccionada, 3) != null) {
			 
			try {
				String fechaNacimientoString = tbHuespedes.getValueAt(filaSeleccionada, 3).toString(); 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				formatter.setLenient(false); //indulgente o no con el formato
				fechaNacimiento = formatter.parse(fechaNacimientoString); //Fecha Nacimiento

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Verificar el formato de las fechas de fecha de nacimiento, el formato debe ser yyyy-MM-dd");
				return false;
			}
		}
		 
		
		if (!List.of("afgano-afgana", "alemán-alemana", "árabe-árabe", "argentino-argentina",
				"australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña",
				"camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", 
				"colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", 
				"cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", 
				"salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", 
				"estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa",
				"galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana",
				"holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa",
				"iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana",
				"japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa",
				"malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense",
				"noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", 
				"peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño",
				"dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", 
				"tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana",
				"uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita")
		.contains(tbHuespedes.getValueAt(filaSeleccionada, 4).toString())) {
			JOptionPane.showMessageDialog(null, "Favor de verificar el nacionalidad, ejemplo: salvadoreño-salvadoreña, estadounidense-estadounidense");
			return false;
		}
			
		return true;  
		 
	 }
	
	private void editarHuesped(Huesped huesped) {
		this.huespedController.editarHuesped(huesped);
	}
	
	private void LlenarTablaHuespedes() {
		 List<Huesped> huesped = buscarHuespedes();
		 
		 try {
			 deleteAllTableRows(tbHuespedes);
			 for (Huesped huespedes : huesped) {
				 System.out.println(huespedes);
				 
				 modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
						 huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReserva() });
				 
			 }
		 } catch (Exception e) {
			 throw e;
		 }
	 }
	 
	private void cerrar() {
		String botones[] = {"Salir", "Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(this, "Realmente desea salir de la aplicación", "Salir Aplicación", 0, 0, null, botones, this);
		if(eleccion==JOptionPane.YES_OPTION) {
			System.exit(0);
		}else if(eleccion==JOptionPane.NO_OPTION){
			System.out.println("Cierre cancelado");
		}
	}
	
	private List<Huesped> buscarHuespedesPorTextoBusqueda(String textoBusqueda) {
		 return this.huespedController.buscarHuespedesPorTextoBusqueda(textoBusqueda);
	}
	 
	private List<Huesped> buscarHuespedesPorIdReservaBusqueda(int idReserva) {
		return this.huespedController.buscarHuespedesPorIdReservaBusqueda(idReserva);
	}
	 
	private void llenarTablaHuespedesPorTextoBuscado(String textoBusqueda) {
		List<Huesped> huesped;
		 if (!verificarEsNumero(textoBusqueda)){
			 huesped = buscarHuespedesPorTextoBusqueda(textoBusqueda);
		 }else {
			 huesped = buscarHuespedesPorIdReservaBusqueda(Integer.valueOf(textoBusqueda));
		 }
		 
		 
		 try {
			 deleteAllTableRows(tbHuespedes);
			 for (Huesped huespedes : huesped) {
				 System.out.println(huespedes);
				 
				 modeloHuesped.addRow(new Object[] { huespedes.getId(), huespedes.getNombre(), huespedes.getApellido(),
						 huespedes.getFechaNacimiento(), huespedes.getNacionalidad(), huespedes.getTelefono(), huespedes.getIdReserva() });
				 
			 }
		 } catch (Exception e) {
			 throw e;
		 }
		
	}
	
	private boolean verificarEsNumero(String textoEvaluacion) {
        boolean esNumero = (textoEvaluacion != null && textoEvaluacion.matches("[0-9]+"));
        return esNumero;
    }
	
	private void deleteAllTableRows(JTable table) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    while( model.getRowCount() > 0 ){
	        model.removeRow(0);
	    }
	}
	    
}
