package model;

import java.io.Serializable;

public class Estudiante implements Serializable
{
	private String name, ciudad;
	private long codigo;
	private int edad, semestre;
	
	public Estudiante (String name, String ciudad, long codigo, int edad, int semestre) 
	{
		this.name = name;
		this.ciudad = ciudad;
		this.codigo = codigo;
		this.edad = edad;
		this.semestre = semestre;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	

}