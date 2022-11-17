package jdbc.p2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.mysql.cj.protocol.Resultset;



public class Main {
	static Scanner entrada = new Scanner(System.in);
	Tabla tabla;
	Vuelo vuelos;
	static Statement st;
	static PreparedStatement pst;
	static Connection con;
	static int op=8;
	public static void main(String[] args) throws SQLException, IOException {
		try {
			con=conexion();
			
			if (con==null) 
			{
				System.out.println("ERORRR");
			}
			else {
				do 
				{
					System.out.println("BASE DE DATOS ");
					System.out.println("1.Crear reserva");
					System.out.println("2. Eliminar reserva");
					System.out.println("3. Listar reserva");
					System.out.println("4. Borrar usuario");
					System.out.println("5.Salir");
					
					op = entrada.nextInt();
					switch(op) 
					{
					case 1:
						hacerReserva();
						break;
					
					case 2:
						borrarReserva();
						break;
					
					case 3:
						listarReserva();
						break;
						
					case 4:
						borrarUsuario();
						break;
					
					
					}
				}while(op!=5);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	public static Connection conexion() 
	{
	   String bd = "practica1";
	   String login = "root";
	   String password = "Alumno2020";
	   String url = "jdbc:mysql://localhost/"+bd;  
	   Connection conn = null;

		      try {
		         conn = DriverManager.getConnection(url,login,password);
		         
		         if (conn != null) {
		            System.out.println("Conectando base de datos "+url+" ... Ok");
		            
		         }
		      }
		      catch(SQLException ex) {
		         System.out.println("No se ha podido conectar "+url);
		      }
			
		     return conn;
	}
	
	public static void crear() throws SQLException 
	{
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs =st.executeQuery("select * from usuario");
		System.out.println("Introduce el nombre");
		String nombre= entrada.next();
		entrada.nextLine();
		System.out.println("Introduce el apellido ");
		String apell= entrada.nextLine();
		System.out.println("Introduce el dni ");
		String dni= entrada.nextLine();
		System.out.println("Introduce la fecha de nacimiento");
		String fnac = entrada.nextLine();
		rs.moveToInsertRow();
		rs.updateString("nombre",nombre);
		rs.updateString("apellidos", apell);
		rs.updateString("dni", dni);
		rs.updateString("fechanac",fnac);
		rs.insertRow();
		
		
		}
	
	public static void read(String dni) 
	{
		try 
		{
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from usuario");
			while(rs.next()) 
			{
				if(rs.getString("dni").equals(dni)) 
				{
					String nombre = rs.getString("nombre");
					String apell = rs.getString("apellidos");
					String fnac = rs.getString("fechanac");	
					System.out.println(nombre +" | "+ apell + "|" + dni + "|"+ fnac);
					break;
				}
			}
			
						
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void update( String dni) 
	{
		String nombre;
		String apellido;
		String fnac;
		try 
		{
			
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from usuario");
			
			while(rs.next()) 
			{
				if(rs.getString("dni").equals(dni)) 
				{
					System.out.println("Introduce el nuevo nombre");
					nombre = entrada.nextLine();
					System.out.println("Introduce el nuevo apellido");
					apellido= entrada.nextLine();
					System.out.println("Introduce la nueva fecha de nacimiento");
					fnac = entrada.nextLine();
					rs.updateString(1, nombre);
					rs.updateString(2, apellido);
					rs.updateString(3, dni);
					rs.updateString(4,fnac);
					rs.updateRow();
				}
			}
			
			
		}catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void delete() throws SQLException 
	{
		System.out.println("Introduce el dni de la persona que quieres borrar");
		String dni =entrada.next();
		
		
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from usuario");
		while(rs.next()) {
			if(rs.getString("dni").equals(dni)) 
			{
				rs.deleteRow();
				
			}
		}
		
	}
	
	public static void importarCsv() throws IOException, SQLException 
	{
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from usuario");
		
		ArrayList<Tabla> usuarios = null;
		File csv = new File("C:\\Users\\Pablo\\eclipse-workspace2\\jdbc\\src\\main\\java\\jdbc\\p2\\usuarios.csv");
		BufferedReader br=null;
		String linea = null;
		String sep= ",";
		StringTokenizer st;
		String[] datos;
		br=new BufferedReader(new FileReader(csv));
		
			while((linea=br.readLine())!=null) 
			{
				datos = new String[4];
				st= new StringTokenizer(linea, sep);
				int dato=0;
				while(st.hasMoreTokens()) 
				{
					datos[dato]=st.nextToken();
					dato++;
				}
			
			rs.moveToInsertRow();
			rs.updateString("nombre",datos[0]);
			rs.updateString("apellidos", datos[1]);
			rs.updateString("dni", datos[2]);
			rs.updateString("fechanac",datos[3]);
			rs.insertRow();
			}
			
			
			
			
	}
	
	public static void importarOtroCsv() throws SQLException, IOException 
	{
		
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select * from vuelos");
		
		
		ArrayList<Vuelo> usuarios = null;
		File csv = new File("C:\\Users\\Pablo\\eclipse-workspace2\\jdbc\\src\\main\\java\\jdbc\\p2\\MOCK_DATA_V2.csv");
		BufferedReader br=null;
		String linea = null;
		String sep= ",";
		StringTokenizer st;
		String[] datos;
		br=new BufferedReader(new FileReader(csv));
		
			while((linea=br.readLine())!=null) 
			{
				datos = new String[8];
				st= new StringTokenizer(linea, sep);
				int dato=0;
				while(st.hasMoreTokens()) 
				{
					datos[dato]=st.nextToken();
					dato++;
				}
			
			rs.moveToInsertRow();
			rs.updateString("id",datos[0]);
			rs.updateString("origen", datos[1]);
			rs.updateString("abrevSalida", datos[2]);
			rs.updateString("fechaSalida",datos[3]);
			rs.updateString("destino",datos[4]);
			rs.updateString("abrevDestino",datos[5]);
			rs.updateString("fechaLlegada",datos[6]);
			rs.updateString("nPlazas",datos[7]);
			rs.insertRow();
			}
	}
	
	public static void hacerReserva() throws SQLException 
	{
		System.out.println("Introduce tu dni para hacer la reserva");
		String dni = entrada.next();
		
		System.out.println("Introduce el id del vuelo que quieres reservar");
		int idVuelo = entrada.nextInt();
		
		System.out.println("Cuantas plazas quieres reservar?");
		int resPlazas = entrada.nextInt();
		//st = con.prepareStatement("insert into reservas values(0,"+dni+","+idVuelo+","+resPlazas);
		
		
		
		try 
		{
			con.setAutoCommit(false);
			pst = con.prepareStatement("INSERT INTO reservas VALUES(0,'"+dni+"' , "+idVuelo+" , "+resPlazas+");");
			pst.execute();
			//ResultSet rs = st.executeQuery("insert into reservas values(0,"+dni+","+idVuelo+","+resPlazas);
			/*rs.moveToInsertRow();
			rs.updateString("idUsuario", dni);
			rs.updateInt("idVuelo", idVuelo);
			rs.updateInt("nPlazas", resPlazas);
			rs.updateInt("idReserva",+1);
			rs.insertRow();*/
			pst=con.prepareStatement("update vuelos set nPlazas = (nPlazas - "+resPlazas+") where id="+idVuelo+" ;");
			pst.execute();
		}catch(SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void borrarReserva() throws SQLException 
	{
		System.out.println("Dime el id de la reserva que quieres borrar");
		int borrarRes = entrada.nextInt();
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery("delete from reservas where id ="+ borrarRes);
		
		rs=st.executeQuery("select * from reservas where idReserva = "+ borrarRes);
		String idVuelo = rs.getString("idVuelo");
		String nPlazas = rs.getString("nPlazas");
		rs = st.executeQuery("update vuelos set nPlazas= nPlazas + "+nPlazas+"where id="+ idVuelo);
				//$sql = "UPDATE members SET creditos=creditos+$num WHERE uid='$uid'";
		
		
	}
	
	public static void listarReserva() throws SQLException 
	{
		System.out.println("Introduce el dni");
		String dni = entrada.nextLine();
		
		// select r.*, u.nombre from reservas r join usuario u on u.dni=r.idUsuario where u.dni=;
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("select r.*, u.nombre from reservas r join usuario u on u.dni=r.idUsuario where u.dni="+ dni);
		System.out.println(rs);
	}
	
	public static void borrarUsuario() throws SQLException 
	{
		System.out.println("Introduce el dni");
		String dni = entrada.nextLine();
		
		// select r.*, u.nombre from reservas r join usuario u on u.dni=r.idUsuario where u.dni=;
		st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
		
		
		ResultSet rs=st.executeQuery("select * from reservas where idUsuario = "+ dni);
		while(rs.next()) 
		{
		String idVuelo = rs.getString("idVuelo");
		String nPlazas = rs.getString("nPlazas");
		rs = st.executeQuery("update vuelos set nPlazas= nPlazas + "+nPlazas+"where id="+ idVuelo);
		}			
		rs = st.executeQuery("delete from reservas where idUsuario ="+ dni);
		rs =st.executeQuery("delete from usuario where dni="+ dni);      
	}

}
	
