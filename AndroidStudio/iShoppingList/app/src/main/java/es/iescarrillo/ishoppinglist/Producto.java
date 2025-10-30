package es.iescarrillo.ishoppinglist;

import java.io.Serializable;

public class Producto implements Serializable {
String id;
String nombreProducto;
String infoProducto;
Boolean estadoCompra; //Si se necesita comprar o no
//Constructor vacio
    public Producto() {

    }
    //Constructor inicializado con todos los atributos
    public Producto(String id, String nombreProducto, String infoProducto, Boolean estadoCompra) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.infoProducto = infoProducto;
        this.estadoCompra = estadoCompra;
    }

    public String getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getInfoProducto() {
        return infoProducto;
    }

    public Boolean getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(Boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public void setInfoProducto(String infoProducto) {
        this.infoProducto = infoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return

                 nombreProducto ;

    }
}
