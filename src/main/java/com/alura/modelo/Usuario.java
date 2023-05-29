package com.alura.modelo;

public class Usuario {
	
	private Integer id;
	private String usuario;
	private String clave;
	
	public Usuario(Integer id, String usuario, String clave) {
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
	}

	public Usuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", clave=" + clave + "]";
	}
	
}
