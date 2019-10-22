package com.souche.salt_common_01.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户from")
public class UserForm {
    @ApiModelProperty(value = "用户名",name = "userName",required = true)
    private String userName;

    @Override
    public String toString() { return "UserForm{" + "userName='" + userName + '\'' + '}';
    } /**
     * Getter method for property <tt>userName</tt>
     *
     * @return property value of userName
     */
    public String getUserName() { return userName;
    } /**
     * Setter method for property <tt>userName</tt>.
     *
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) { this.userName = userName;
    } }
