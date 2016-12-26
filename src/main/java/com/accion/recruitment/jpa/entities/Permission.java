package com.accion.recruitment.jpa.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mudassir Hussain
 * @author $LastChangedBy: Mudassir Hussain $
 * $Date:: 12/26/16 00:11 AM#$
 */

@Entity
@Table(name="permission")
public class Permission extends BaseEntity {

    @Column(name = "permissionName")
    private String permissionName;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    @ManyToMany(mappedBy="permissionSet"
            , targetEntity = Groups.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
    private Set<Groups> groupsSet=new HashSet<>();

    public Set<Groups> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<Groups> groupsSet) {
        this.groupsSet = groupsSet;
    }


/*
    @ManyToMany(targetEntity = SubTabs.class, fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name = "permission_subtabs")
    @GenericGenerator(name="increment", strategy = "increment")
    @CollectionId(columns = @Column(name="permissionSubTabsId"),
            type=@Type(type="long"),generator = "increment"
    )
    private Collection<SubTabs> subTabsSet=new HashSet<>();

    public Collection<SubTabs> getSubTabsSet() {
        return subTabsSet;
    }

    public void setSubTabsSet(Collection<SubTabs> subTabsSet) {
        this.subTabsSet = subTabsSet;
    }


    public void setSubTabs(SubTabs subTabs) {
        this.subTabsSet.add(subTabs) ;
    }*/


    @Override
    public String toString() {
        return this.getPermissionName();
    }
}
