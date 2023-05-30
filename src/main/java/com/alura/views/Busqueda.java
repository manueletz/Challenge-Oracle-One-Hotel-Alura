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
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE B赟QUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
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
		modelo.addColumn("N鷐ero de Reserva");
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
		
        String colTitlesReserva[] = {"N鷐ero de Reserva", "Fecha Check In", "Fecha Check Out", "Valor", "Forma de Pago"};
        boolean[] isEditableReserva = {false,true,true,true,true};
        
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
		modeloHuesped.addColumn("N鷐ero de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Tel閒ono");
		modeloHuesped.addColumn("N鷐ero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Hu閟pedes", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		*/
		
		//Se modifico para no poder editar el id y id_reserva
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		
        String colTitlesHuespedes[] = {"N鷐ero de Huesped", "Nombre", "Apellido", "Fecha de Nacimiento", "Nacionalidad", "Tel閒ono", "N鷐ero de Reserva"};
        boolean[] isEditableHuespedes = {false,true,true,true,true,true,false};
        
        modeloHuesped = new DefaultTableModel(colTitlesHuespedes, 0) {
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return isEditableHuespedes[column];
             }
        };
        tbHuespedes.setModel(modeloHuesped);
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Hu閟pedes", new ImageIcon(Busqueda.class.getResource("/com/alura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		tbHuespedes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tablaSeleccionada = 2;
				//System.out.println("tablaSeleccionada: "+tablaSeleccionada);
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
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el bot贸n este cambiar谩 de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el bot贸n este volver谩 al estado original
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
					editarFilaReservaSeleccionada();
				}
				
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
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
	
//C贸digo que permite mover la ventana por la pantalla seg煤n la posici贸n de "x" y "y"
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
		 
		 //Llenar tabla
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
		 //Llenar tabla
		List<Reserva> reserva;
		 if (!verificarEsNumero(textoBusqueda)){
			 reserva = buscarReservasPorTextoBusqueda(textoBusqueda);
		 }else {
			 reserva = buscarReservasPorIdReservaBusqueda(Integer.valueOf(textoBusqueda));
		 }
		 
		 
		 try {
			 //modeloHuesped.setNumRows(0);
			 deleteAllTableRows(tbReservas);
			 //tbHuespedes.setModel(new DefaultTableModel(null, new String[]{"N鷐ero de Huesped","Nombre", "Apellido", "Fecha de Nacimiento","Nacionalidad", "Telefono","N鷐ero de Reserva"}));
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
			//modelo.setValueAt(reserva, yMouse, xMouse);
	
			
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
			
			valor = valorTotalReserva;//(Integer) tbReservas.getValueAt(filaSeleccionada, 3); //Valor
			tbReservas.setValueAt(valorTotalReserva, filaSeleccionada, 3);
			formaPago = (String) tbReservas.getValueAt(filaSeleccionada, 4); //Forma de Pago
			reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);

			try {
				editarReserva(reserva);
				JOptionPane.showMessageDialog(null, "Datos guardados con 閤ito, N鷐ero de Reserva: "+reserva.getId());
			} catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Lo sentimos no fue posible guardar los datos, N鷐ero de Reserva: "+reserva.getId());
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
		 
		 //Verificar que fecha no son vac韆s
		 if ( (tbReservas.getValueAt(filaSeleccionada, 1).toString().length() == 0 || tbReservas.getValueAt(filaSeleccionada, 2).toString().length() == 0) ) {
			 JOptionPane.showMessageDialog(null, "La fecha de Inicio y Fin no deben esta vac韆s");
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
		 
		//Verificar si los d韆s de estad韆 son mayores a 365 
		if (diasEstadia > 365) {
			JOptionPane.showMessageDialog(null, "Lo sentimos, no se permiten reservaciones de mas de 365 d韆s, favor verificar");
			return false;
		}
		
		//Verificar si los d韆s de estad韆 son cero o menores
		if (diasEstadia <= 0) {
			JOptionPane.showMessageDialog(null, "La fecha final debe ser mayor a la inicial, favor verificar");
			return false;
		} 
		
		if (!List.of("Tarjeta de Cr閐ito","Tarjeta de D閎ito","Dinero en Efectivo").contains(tbReservas.getValueAt(filaSeleccionada, 4).toString()) ) {
			JOptionPane.showMessageDialog(null, "Favor de verificar el m閠odo de pago, pagos permitidos: Tarjeta de Cr閐ito, Tarjeta de D閎ito o Dinero en Efectivo");
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
	 
	 private void LlenarTablaHuespedes() {
		 //Llenar tabla
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
		int eleccion = JOptionPane.showOptionDialog(this, "Realmente desea salir de la aplicaci髇", "Salir Aplicaci髇", 0, 0, null, botones, this);
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
		 //Llenar tabla
		List<Huesped> huesped;
		 if (!verificarEsNumero(textoBusqueda)){
			 huesped = buscarHuespedesPorTextoBusqueda(textoBusqueda);
		 }else {
			 huesped = buscarHuespedesPorIdReservaBusqueda(Integer.valueOf(textoBusqueda));
		 }
		 
		 
		 try {
			 //modeloHuesped.setNumRows(0);
			 deleteAllTableRows(tbHuespedes);
			 //tbHuespedes.setModel(new DefaultTableModel(null, new String[]{"N鷐ero de Huesped","Nombre", "Apellido", "Fecha de Nacimiento","Nacionalidad", "Telefono","N鷐ero de Reserva"}));
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
        //System.out.println("IsNumeric: " + isNumeric);
        return esNumero;
    }
	
	private void deleteAllTableRows(JTable table) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    while( model.getRowCount() > 0 ){
	        model.removeRow(0);
	    }
	}
	

	    
}
