package frank.model;

import frank.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 宿舍楼
 */
@Getter
@Setter
@ToString
public class Building extends BaseEntity {
    
    private Integer id;

    /**
     * 名称
     */
    private String buildingName;

    /**
     * 描述
     */
    private String buildingDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    private Integer dormCount;
}