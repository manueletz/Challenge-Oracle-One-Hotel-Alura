package com.alura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;

import com.alura.controller.ReservaController;
import com.alura.modelo.Reserva;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private ReservaController reservaController;
	boolean inicio = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");

		this.reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ReservasView.class.getResource("/com/alura/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		// CÃ³digo que crea los elementos de la interfÃ¡z grÃ¡fica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 187, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 211, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 248, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/com/alura/imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/com/alura/imagenes/reservas-img-3.png")));

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 211, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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
		panel.add(header);

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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);

		// Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.setDateFormatString("MM/dd/yyyy");
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/com/alura/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 161, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.setDateFormatString("MM/dd/yyyy");

		txtFechaSalida.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/com/alura/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 246, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));

		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);
		
		txtValor = new JTextField();// Se traslado para poderlo utilizar
		JPanel btnsiguiente = new JPanel(); // se traslado para poder desactivar
		
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//Acciones para activar el evento de fecha Final cuando se cambia la fecha de inicio
				//y calcule el nuevo ValorTotalReserva
				Date fechaSalidaActual = ReservasView.txtFechaSalida.getDate();
				ReservasView.txtFechaSalida.setDate(null);
				ReservasView.txtFechaSalida.setDate(fechaSalidaActual);
			}
		});
		
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			int valorTotalReserva = 0;
			int diasEstadia = 0;

			public void propertyChange(PropertyChangeEvent evt) {
				// Activa el evento, despues que el usuario selecciona las fechas se debe
				// calcular el valor de la reserva
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {
					
					diasEstadia = DiasEstadia();
					valorTotalReserva = calcularTarifaEstadia(diasEstadia);

					if (diasEstadia > 0 && diasEstadia <= 365) {
						btnsiguiente.setVisible(true);
						txtValor.setText(String.valueOf(valorTotalReserva));
					} else {
						btnsiguiente.setVisible(false);
						
						if (diasEstadia > 365) {
							JOptionPane.showMessageDialog(null, "Lo sentimos, no se permiten reservaciones de mas de 365 días, favor verificar");
						}
						if (diasEstadia <= 0) {
							JOptionPane.showMessageDialog(null, "La fecha final debe ser mayor a la inicial, favor verificar");
						}
						txtValor.setText("");
					}
				} else {
					txtValor.setText("");
				}

			}
		});
		

		// txtValor = new JTextField(); Se traslada para antes de txtFechaSalida para
		// desactivar si hay error
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(78, 328, 43, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en Efectivo" }));
		panel.add(txtFormaPago);

		// JPanel btnsiguiente = new JPanel();Se traslada para antes de txtFechaSalida
		// para desactivar si hay error
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null) {

					Reserva reserva = new Reserva(txtFechaEntrada.getDate(), txtFechaSalida.getDate(),
							Integer.valueOf(txtValor.getText()), (String) txtFormaPago.getSelectedItem());

					reservaController.guardar(reserva);

					Integer numeroReservaActual = reserva.getId();

					RegistroHuesped registro = new RegistroHuesped(numeroReservaActual);
					
					JOptionPane.showMessageDialog(null, "Reserva guardada con éxito, id: "+numeroReservaActual);
					
					registro.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("CONTINUAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnsiguiente.add(labelGuardar);
		
		this.inicio=false;

	}

	public int DiasEstadia() {
		int valor;
		java.sql.Date fechaEntrada =  new java.sql.Date(txtFechaEntrada.getDate().getTime()) ;
		java.sql.Date fechaSalida = new java.sql.Date(txtFechaSalida.getDate().getTime());
		
		int dias = (int)((txtFechaSalida.getDate().getTime()  - 
				txtFechaEntrada.getDate().getTime())/(1000 * 60 * 60 * 24));
		
		return dias;
		
	}
	
	
	public int calcularTarifaEstadia(int dias) {
		int valor;
		valor = dias * 20;
		return valor;
	}

	// CÃ³digo que permite mover la ventana por la pantalla segÃºn la posiciÃ³n de
	// "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
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
	
}
