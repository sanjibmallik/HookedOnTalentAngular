package com.accion.recruitment.jpa.entities;


import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */


@Entity
@Table(name = "groups")
public class Groups extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" ,insertable = true, nullable = false, unique = true, updatable = true)
    protected Integer id;

    @Column(length = 100, nullable = false)
    private String groupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name = "permission_group")
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="permissionGroupId"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<Permission> permissionSet=new HashSet<>();

    public Collection<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Collection<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }


    @ManyToMany(targetEntity = User.class , fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_group")
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="userGroupId"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<User> userSet=new HashSet<>();

    public Collection<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Collection<User> userSet) {
        this.userSet = userSet;
    }

    public void setUser(User user) {
        this.userSet.add(user) ;
    }

    @Override
    public String toString() {
        return this.getGroupName();
    }
}
