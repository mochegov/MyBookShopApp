package com.example.MyBookShopApp.data.user;

import com.example.MyBookShopApp.data.enums.ContactType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_contact")
public class UserContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ContactType type;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short approved;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String code;

    @Column(columnDefinition = "INT")
    private Integer codeTrails;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime codeTime;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "INT NOT NULL")
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public short getApproved() {
        return approved;
    }

    public void setApproved(short approved) {
        this.approved = approved;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeTrails() {
        return codeTrails;
    }

    public void setCodeTrails(Integer codeTrails) {
        this.codeTrails = codeTrails;
    }

    public LocalDateTime getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(LocalDateTime codeTime) {
        this.codeTime = codeTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
