package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surnames;
	@Id
	private String email;
	private String password;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurnames() {
		return surnames;
	}
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}
	public int getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String city;
	private String country;
	private int postal_code;
	private int phone;
	private int seller;

	public User() {
		name = "";
		surnames = "";
		email = "";
		password = "";
		address = "";
		city = "";
		country = "";
		postal_code = 0;
		phone = 0;
		seller = 0;
	}
	public User(String name, String surnames, String email, String password, String address, String city,
			String country, int postal_code, int phone, int seller) {
		this.name = name;
		this.surnames = surnames;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.postal_code = postal_code;
		this.phone = phone;
		this.seller = seller;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public int getSeller() {
		return seller;
	}
	public void setSeller(int seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Users [firstName=" + name + ", lastName=" + surnames + ", email=" + email + ", password="
				+ password + ", adress=" + address + ", city=" + city + ", country=" + country + ", zipCode=" + postal_code
				+ ", telephone=" + phone + ", seller=" + seller + "]";
	}

}