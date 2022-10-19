package jdbc.p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;



public class Main {
	static Scanner entrada = new Scanner(System.in);
	Tabla tabla;
	static Statement st;
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
					System.out.println("1.Insertar fila");
					System.out.println("2. Mostrar fila");
					System.out.println("3. Modificar fila");
					System.out.println("4. Borrar fila");
					System.out.println("5. Importar csv");
					System.out.println("6.Salir");
					
					op = entrada.nextInt();
					switch(op) 
					{
					case 1:
						crear();
						break;
					
					case 2:
						System.out.println("Introduce el dni de la persona que quieres buscar");
						entrada.nextLine();
						String dni = entrada.nextLine();
						read(dni);
						break;
					
					case 3:
						entrada.nextLine();
						System.out.println("Introduce el dni de la persona que quieres cambiar");
						String dniBusc= entrada.nextLine();
						update(dniBusc);
						break;
						
					case 4:
						delete();
						break;
					
					case 5:
						importarCsv();
						break;
					}
				}while(op!=6);
				
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
		File csv = new File("C:\\Users\\Pablo\\eclipse-workspace2\\jdbc\\src\\main\\java\\jdbc\\p1\\usuarios.csv");
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

}
	
