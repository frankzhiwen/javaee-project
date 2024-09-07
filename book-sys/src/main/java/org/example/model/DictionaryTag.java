package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.base.BaseEntity;

import java.util.Date;

/**
 * 数据字典标签
 */
@Getter
@Setter
@ToString
public class DictionaryTag extends BaseEntity {
    
    private Integer id;

    /**
     * 键
     */
    private String dictionaryTagKey;

    /**
     * 值
     */
    private String dictionaryTagValue;

    /**
     * 备注
     */
    private String dictionaryTagDesc;

    /**
     * 数据字典id
     */
    private Integer dictionaryId;

    /**
     * 创建时间
     */
    private Date createTime;
}