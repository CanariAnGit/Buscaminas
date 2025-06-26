/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaminas;
import java.io.Serializable;

/**
 *
 * @author a.gonzalezro.2022
 */
public class Administrador extends Usuario implements Serializable{
    
    public Administrador() {
        super("admin", "1234");
    }
    
    public boolean autenticar(Usuario u){  //Verifica que la información introducida corresponde con la del admin.
        if(u instanceof Administrador){  //Si el usuario pasado es de tipo Administrador.
            return this.equals(u);
        }else return false;
    }
    
}
