package modelo;
import java.sql.*;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class database {
 /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
  private String db = "aa361";
  /** usuario */
  private String user = "root";
  /** contraseña de MySql*/
  private String password = "nikiekany";
  /** Cadena de conexion */
  private String url = "jdbc:mysql://localhost/"+db;
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;

   /** Constructor de clase */
   public database(){
        this.url = "jdbc:mysql://localhost/"+this.db;
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
           System.out.println("CONECTADO");
         conn = DriverManager.getConnection( this.url, this.user , this.password );         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }

}
