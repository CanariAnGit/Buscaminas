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
public abstract class Usuario implements Serializable{
    
    private final String nombre, contraseña;
    private static Almacen_Usuarios todosUsuarios=new Almacen_Usuarios();
    
    public Usuario(String pseudonimo, String clave){  //Crea un usuario con el nombre y contraseña dados.
        this.nombre=pseudonimo;
        this.contraseña=clave;
    }
    
    public Almacen_Usuarios gettodosUsuarios(){  //Devuelve la variable de tipo Almacen_Usuarios.
        return todosUsuarios;
    }
    
    public String getNombre(){  //Devuelve el nombre del usuario.
        return this.nombre;
    }
    
    public String getClave(){  //Devuelve la clave del usuario.
        return this.contraseña;
    }
    
    @Override
    public boolean equals(Object o){  //Redefinición del método para comparar usuarios.
        if(o==this) return true;
        if(o==null) return false;
        if(this.getClass()!=o.getClass()) return false;
        
        if(o instanceof Jugador && this instanceof Jugador){  //Si ambos objetos son de tpo Jugador.
            Jugador cast=(Jugador) o;
            return (this.getNombre().equals(cast.getNombre()) && this.getClave().equals(cast.getClave()));
        }else if(o instanceof Administrador && this instanceof Administrador){  //Si ambos objetos son de tipo Administrador.
            Administrador cast=(Administrador) o;
            return (this.getNombre().equals(cast.getNombre()) && this.getClave().equals(cast.getClave()));
        }else return false;
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
    
    public String ordenarPorVictorias(){  //Devuelve una cadena de caracteres con los jugadores ordenados por victorias.
        String lista="";
        for(Jugador j : this.gettodosUsuarios().clasificacion_por_victorias()){  //Recorre la lista ordenada.
            lista+=(j.verPerfil() + "\n");
        }
        return lista;
    }
    
    public String ordenarPorNombre(){  //Devuelve una cadena de caracteres con los jugadores ordenados por nombre.
        String lista="";
        for(Jugador j : this.gettodosUsuarios().clasificacion_por_nombre()){  //Recorre la lista ordenada.
            lista+=(j.verPerfil() + "\n");
        }
        return lista;
    }
        
    
}
