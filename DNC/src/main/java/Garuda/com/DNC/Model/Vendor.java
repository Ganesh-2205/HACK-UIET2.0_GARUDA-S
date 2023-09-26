package Garuda.com.DNC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "Vendor")
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String user;



    @JsonIgnore
    @Column(name = "password", nullable = false,length = 1000)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor", orphanRemoval = true)
    private Set<Vendoradd> address = new LinkedHashSet<>();





    @Column(name = "mobile")
    private String mobile;

    @OneToMany(mappedBy = "vendor", orphanRemoval = true)
    private Set<Inventory> inventories = new LinkedHashSet<>();

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }




    public Set<Vendoradd> getAddress() {
        return address;
    }

    public void setAddress(Set<Vendoradd> address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     {
    }
}