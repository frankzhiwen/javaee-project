package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@Getter
@Setter
@ToString
public class User extends BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;
    
    private Integer id;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;
}