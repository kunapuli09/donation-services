package com.starpath.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
* @version $Revision: 1.0 $ $Date: 2008/02/02 $
* @since Hibernate 3.2.1 / Spring 2.0.6 / Maven 2.0.4
* @author Krishna M. Kunapuli
* <p>
*   Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
* <br>
*/

@Entity
@Table(name="USER_PRIVILEGE")
@SuppressWarnings("serial")
public class UserPrivilege implements Serializable {
  private Integer id;
  private User user;
  private String privilege;
  
  public UserPrivilege() {}
  
  public UserPrivilege(String privilege) {
    this.privilege = privilege;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public String getPrivilege() {
    return privilege;
  }
  
  public void setPrivilege(String privilege) {
    this.privilege = privilege;
  }
}
