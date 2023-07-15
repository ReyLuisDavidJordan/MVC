package modelo;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;

public class Persona {
    private String idPersona;
    private String nombrePersona;
    private String apellidoPersona;
    private Date fechanacimineto;
    private String telefono;
    private String sexo;
    private double sueldo;
    private int cupo;
    private byte foto;
    private String correo;

    public Persona() {
    }

    public Persona(String idPersona, String nombrePersona, String apellidoPersona, Date fechanacimineto, String telefono, String sexo, double sueldo, int cupo, byte foto, String correo) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.apellidoPersona = apellidoPersona;
        this.fechanacimineto = fechanacimineto;
        this.telefono = telefono;
        this.sexo = sexo;
        this.sueldo = sueldo;
        this.cupo = cupo;
        this.foto = foto;
        this.correo = correo;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellidoPersona() {
        return apellidoPersona;
    }

    public void setApellidoPersona(String apellidoPersona) {
        this.apellidoPersona = apellidoPersona;
    }

    public Date getFechanacimineto() {
        return fechanacimineto;
    }

    public void setFechanacimineto(Date fechanacimineto) {
        this.fechanacimineto = fechanacimineto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        LocalDateTime date = Instant.ofEpochMilli(this.fechanacimineto.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return (int) ChronoUnit.YEARS.between(date, LocalDateTime.now());
    }
    
    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombrePersona=" + nombrePersona + ", apellidoPersona=" + apellidoPersona + ", fechanacimineto=" + fechanacimineto + ", telefono=" + telefono + ", sexo=" + sexo + ", sueldo=" + sueldo + ", cupo=" + cupo + ", foto=" + foto + ", correo=" + correo + '}';
    }
}
