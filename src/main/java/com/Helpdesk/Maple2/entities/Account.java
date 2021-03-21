package com.Helpdesk.Maple2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

    @Id
    private Integer id;
    private Role role;
    private String username;
    private String password;
    private String fullName;
    private boolean status;
    private String email;
    private String phone;
    private Set<Tickets> ticketsForEmployeeId = new HashSet<Tickets>(0);
    private Set<Tickets> ticketsForUserId = new HashSet<Tickets>(0);

    public Account() {
        super();
    }

    public Account(Role role, String username, String password, String fullName, boolean status, String email, String phone) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "username", nullable = false, length = 250)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 250)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Column(name = "email", nullable = false, length = 250)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", nullable = false, length = 250)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountByEmployeeId")
    public Set<Tickets> getTicketsForEmployeeId() {
        return this.ticketsForEmployeeId;
    }

    public void setTicketsForEmployeeId(Set<Tickets> ticketsForEmployeeId) {
        this.ticketsForEmployeeId = ticketsForEmployeeId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountByUserId")
    public Set<Tickets> getTicketsForUserId() {
        return this.ticketsForUserId;
    }

    public void setTicketsForUserId(Set<Tickets> ticketsForUserId) {
        this.ticketsForUserId = ticketsForUserId;
    }
}
