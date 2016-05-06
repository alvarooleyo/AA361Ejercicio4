package modelo;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class modelo extends database{

    /** Constructor de clase */
    public modelo (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaPersona()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"id","nombre","appaterno","apmaterno", "email"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
       try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM persona");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    Object[][] data = new String[registros][6];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM persona");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "appaterno" );
                data[i][3] = res.getString( "apmaterno" );
                data[i][3] = res.getString( "email" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }

    /** Registra un nuevo producto */
    public boolean agregaPersona(String nombre , String appaterno, String apmaterno, String email)
    {
        boolean res = false;
            String q=" INSERT INTO persona (nombre , appaterno, apmaterno, email  ) "
                    + "VALUES ('" + nombre + "', '" + appaterno + "','" + apmaterno + "','" + email + "') ";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                res = true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return res;
    }


    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean eliminaPersona( String id )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM persona WHERE  id='" + id + "' " ;
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }


   

}
