package com.example.MyBookShopApp.data.DTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_contacts")
public class UserContact {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @Column(nullable = false)
    private Boolean approved;
    private String code;
    private Integer codeTrials;
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date codeTime;
    @Column(nullable = false)
    private String contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeTrials() {
        return codeTrials;
    }

    public void setCodeTrials(Integer codeTrials) {
        this.codeTrials = codeTrials;
    }

    public Date getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(Date codeTime) {
        this.codeTime = codeTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
