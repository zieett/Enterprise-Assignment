package assignment.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(length = 1024)
    private String username;

    @Column(length = 1024)
    private String password;

    @Column(length = 1024)
    private String firstname;

    @Column(length = 1024)
    private String lastname;

    @Column(length = 1024)
    private String email;

    @Column(length = 1024)
    private String address;

    @Column(length = 1024)
    private String lastLogin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return username + " -- " + password + " -- " + firstname + " -- " + lastname + " -- " + address;
    }

    public void replace(Customer customer) {
        this.password = customer.getPassword();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.address = customer.getAddress();
    }
}
