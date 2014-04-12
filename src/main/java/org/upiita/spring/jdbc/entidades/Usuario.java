package org.upiita.spring.jdbc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//MAPEA LA CLASE Usuario con la tabla usuario
@Entity(name = "usuario")
public class Usuario {
	
	@Id
	@Column(name= "usuario_id")
	private Integer usuarioId;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString(){
		
		String representacion = "nombre: " + nombre + ","
								+ "usuarioID: " + usuarioId + ","
								+ "email:" + email + ","
								+ "password: " + password;
		
		return representacion;		
	}

	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nombre")
	private String nombre;

}
