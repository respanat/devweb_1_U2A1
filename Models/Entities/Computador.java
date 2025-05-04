package Models.Entities;

public class Computador {
    private int id;
    private String marca;
    private String categoria;
    private String marcaCpu;
    private String velocidadCpu;
    private String tecnologiaRam;
    private String capacidadRam;
    private String tecnologiaDisco;
    private String capacidadDisco;
    private int numPuertosUSB;
    private int numPuertosHDMI;
    private String MarcaMonitor;
    private double pulgadas;
    private double precio;

    public Computador() {
    }

    public Computador(int id, String marca, String categoria, String marcaCpu, String velocidadCpu, String tecnologiaRam, String capacidadRam, String tecnologiaDisco, String capacidadDisco, int numPuertosUSB, int numPuertosHDMI, String marcaMonitor, double pulgadas, double precio) {
        this.id = id;
        this.marca = marca;
        this.categoria = categoria;
        this.marcaCpu = marcaCpu;
        this.velocidadCpu = velocidadCpu;
        this.tecnologiaRam = tecnologiaRam;
        this.capacidadRam = capacidadRam;
        this.tecnologiaDisco = tecnologiaDisco;
        this.capacidadDisco = capacidadDisco;
        this.numPuertosUSB = numPuertosUSB;
        this.numPuertosHDMI = numPuertosHDMI;
        MarcaMonitor = marcaMonitor;
        this.pulgadas = pulgadas;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarcaCpu() {
        return marcaCpu;
    }

    public void setMarcaCpu(String marcaCpu) {
        this.marcaCpu = marcaCpu;
    }

    public String getVelocidadCpu() {
        return velocidadCpu;
    }

    public void setVelocidadCpu(String velocidadCpu) {
        this.velocidadCpu = velocidadCpu;
    }

    public String getTecnologiaRam() {
        return tecnologiaRam;
    }

    public void setTecnologiaRam(String tecnologiaRam) {
        this.tecnologiaRam = tecnologiaRam;
    }

    public String getCapacidadRam() {
        return capacidadRam;
    }

    public void setCapacidadRam(String capacidadRam) {
        this.capacidadRam = capacidadRam;
    }

    public String getTecnologiaDisco() {
        return tecnologiaDisco;
    }

    public void setTecnologiaDisco(String tecnologiaDisco) {
        this.tecnologiaDisco = tecnologiaDisco;
    }

    public String getCapacidadDisco() {
        return capacidadDisco;
    }

    public void setCapacidadDisco(String capacidadDisco) {
        this.capacidadDisco = capacidadDisco;
    }

    public int getNumPuertosUSB() {
        return numPuertosUSB;
    }

    public void setNumPuertosUSB(int numPuertosUSB) {
        this.numPuertosUSB = numPuertosUSB;
    }

    public int getNumPuertosHDMI() {
        return numPuertosHDMI;
    }

    public void setNumPuertosHDMI(int numPuertosHDMI) {
        this.numPuertosHDMI = numPuertosHDMI;
    }

    public String getMarcaMonitor() {
        return MarcaMonitor;
    }

    public void setMarcaMonitor(String marcaMonitor) {
        MarcaMonitor = marcaMonitor;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Computador{" +
               "id=" + id +
               ", marca='" + marca + '\'' +
               ", categoria='" + categoria + '\'' +
               ", marcaCpu='" + marcaCpu + '\'' +
               ", velocidadCpu='" + velocidadCpu + '\'' +
               ", tecnologiaRam='" + tecnologiaRam + '\'' +
               ", capacidadRam='" + capacidadRam + '\'' +
               ", tecnologiaDisco='" + tecnologiaDisco + '\'' +
               ", capacidadDisco='" + capacidadDisco + '\'' +
               ", numPuertosUSB=" + numPuertosUSB +
               ", numPuertosHDMI=" + numPuertosHDMI +
               ", MarcaMonitor='" + MarcaMonitor + '\'' +
               ", pulgadas=" + pulgadas +
               ", precio=" + precio +
               '}';
    }
}
