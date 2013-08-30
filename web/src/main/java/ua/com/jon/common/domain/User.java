package ua.com.jon.common.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Date: 01.01.13
 * Time: 23:58
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LOGIN", unique = true)
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "REGISTER_DATE", nullable = false)
    private Date regDate;

//    @OneToMany(mappedBy = "user")
//    private Collection<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public User() {
    }

    public User(String login, String password, Date regDate, Group group) {
        this.login = login;
        this.password = password;
        this.regDate = regDate;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

/*    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }*/

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
