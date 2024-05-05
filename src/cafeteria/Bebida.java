package cafeteria;

public class Bebida {
    // Atributos
    private boolean activo;
    private String nombre;
    private double precio;
    private String tipo;
    private String dimension;

    // Constructor
    public Bebida() {
        this.activo = true;
    }

    // Setters y Getters
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDimension() {
        return dimension;
    }
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    // Otros métodos
    @Override
    public String toString() {
        return "Bebida[\n" + 
        "activo = " + activo + 
        "\nnombre = " + nombre + 
        "\nprecio = " + precio + 
        "\ntipo = " + tipo + 
        "\ndimension = " + dimension + "]";
    }
}