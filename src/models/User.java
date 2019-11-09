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
@Table(name="usuarios")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	

	private String firstName;
	private String lastName;
	@Id
	private String email;
	private String password;
	private String adress;
	private String city;
	private String country;
	private int zipCode;
	private int telephone;
	private int seller;

	public User() {
		firstName = "";
		lastName = "";
		email = "";
		password = "";
		adress = "";
		city = "";
		country = "";
		zipCode = 0;
		telephone = 0;
		seller = 0;
	}
	public User(String firstName, String lastName, String email, String password, String adress, String city,
			String country, int zipCode, int telephone, int seller) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.adress = adress;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
		this.telephone = telephone;
		this.seller = seller;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getSeller() {
		return seller;
	}
	public void setSeller(int seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "Users [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", adress=" + adress + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode
				+ ", telephone=" + telephone + ", seller=" + seller + "]";
	}

}