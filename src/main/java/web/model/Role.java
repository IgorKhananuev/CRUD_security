package web.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

//    @Transient
    @ManyToMany(mappedBy = "roles")
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"),
//    inverseJoinColumns = @JoinColumn (name = "user_id"))
    private Set<User> users;

    public Role() {
    }

    public Role(Long id, String name, Set<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
