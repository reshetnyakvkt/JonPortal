package ua.com.jon.common.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 27.05.13
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "GROUP_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    @Temporal(value = TemporalType.DATE)
    private Date startDate;

    private boolean active;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "group"/*, fetch = FetchType.EAGER*/)
    //@JoinColumn(name = "GROUP_ID")
    private Set<User> users = new HashSet<User>();

    public Group() {
    }

    public Group(String name, Date startDate, boolean active, Iterable<User> users) {
        this(name, startDate, active, getUserSetByIter(users));
    }

    public Group(String name, Date startDate, boolean active, Set<User> users) {
        this.name = name;
        this.startDate = startDate;
        this.active = active;
        this.users = users;
    }


    private static Set<User> getUserSetByIter(Iterable<User> users) {
        Set<User> userSet = new HashSet<User>();
        for (User user : users) {
            userSet.add(user);
        }
        return userSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", active=" + active +
                '}';
    }
}
