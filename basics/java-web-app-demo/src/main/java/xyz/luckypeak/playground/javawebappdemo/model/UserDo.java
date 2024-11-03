package xyz.luckypeak.playground.javawebappdemo.model;

import java.util.Objects;

public class UserDo {

  private int id;

  private String username;

  private String password;

  private String email;

  private String phoneNumber;

  private UserRoleEnum role;

  public UserDo(
      int id,
      String username,
      String password,
      String email,
      String phoneNumber,
      UserRoleEnum role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public UserRoleEnum getRole() {
    return role;
  }

  public void setRole(UserRoleEnum role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDo userDo = (UserDo) o;
    return id == userDo.id
        && Objects.equals(username, userDo.username)
        && Objects.equals(password, userDo.password)
        && Objects.equals(email, userDo.email)
        && Objects.equals(phoneNumber, userDo.phoneNumber)
        && role == userDo.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, email, phoneNumber, role);
  }

  @Override
  public String toString() {
    return "UserDo{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ", role="
        + role
        + '}';
  }
}
