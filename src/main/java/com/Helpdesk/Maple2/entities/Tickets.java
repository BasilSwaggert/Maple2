package com.Helpdesk.Maple2.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Tickets implements java.io.Serializable {

    @Id
    private Integer id;
    private Account accountByEmployeeId;
    private Account accountByUserId;
    private Category category;
    private Status status;
    private String title;
    private String description;
    private Date createdDate;

    public Tickets() {
        super();
    }

    public Tickets(Account accountByEmployeeId, Account accountByUserId, Category category, Status status, String title, String description, Date createdDate) {
        this.accountByEmployeeId = accountByEmployeeId;
        this.accountByUserId = accountByUserId;
        this.category = category;
        this.status = status;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
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
    @JoinColumn(name = "user_id", nullable = false)
    public Account getAccountByUserId() {
        return this.accountByUserId;
    }

    public void setAccountByUserId(Account accountByUserId) {
        this.accountByUserId = accountByUserId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    public Account getAccountByEmployeeId() {
        return this.accountByEmployeeId;
    }

    public void setAccountByEmployeeId(Account accountByEmployeeId) {
        this.accountByEmployeeId = accountByEmployeeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "title", nullable = false, length = 250)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "createdDate", nullable = false)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
