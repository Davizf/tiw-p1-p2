package jhc.labmvc;

public class DataModelBean {

  // Holds value of property forename
  private String forename;

  // Holds value of property surname
  private String surname;

  // Holds value of property email
  private String email;

  // Creates new DataBean
  public DataModelBean() {
  }

  public DataModelBean(String forename,String surname,String email) {
    this.forename = forename;
    this.surname = surname;
    this.email = email;
  }

  /** Getter for property forename
   *  @return Value of property forename.
   */
  public String getForename() {
    return forename;
  }

  /** Setter for property forename
   *  @param forename New value of property forename.
   */
  public void setForename(String forename) {
    this.forename = forename;
  }

  /** Getter for property surname.
   * @return Value of property surname.
   */
  public String getSurname() {
    return surname;
  }

  /** Setter for property surname.
   * @param surname New value of property surname.
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /** Getter for property email.
   * @return Value of property email.
   */
  public String getEmail() {
    return email;
  }

  /** Setter for property email.
   * @param email New value of property email.
   */
  public void setEmail(String email) {
    this.email = email;
  }

    
}
