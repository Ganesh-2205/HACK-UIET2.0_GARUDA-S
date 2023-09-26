package Garuda.com.DNC.Model;

import Garuda.com.DNC.Model.order.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "LocalUser")
@Table(name = "local_user")
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false,unique = true)
    private Long id;



    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "user", nullable = false)
    private String user;







    @Column(name = "mobile", nullable = false)
    private String mobile;




    @JsonIgnore
    @OneToMany(mappedBy = "localUser", orphanRemoval = true)
    private Set<Cart> carts = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "localUser", orphanRemoval = true)
    private Set<LocalAddress> localAddresses = new LinkedHashSet<>();
@JsonIgnore
    @OneToMany(mappedBy = "localUser", orphanRemoval = true)
    private Set<Orders> orderses = new LinkedHashSet<>();

    public Set<Orders> getOrderses() {
        return orderses;
    }

    public void setOrderses(Set<Orders> orderses) {
        this.orderses = orderses;
    }

    public Set<LocalAddress> getLocalAddresses() {
        return localAddresses;
    }

    public void setLocalAddresses(Set<LocalAddress> localAddresses) {
        this.localAddresses = localAddresses;
    }




    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }




    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {this.mobile = mobile;
    }





    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}