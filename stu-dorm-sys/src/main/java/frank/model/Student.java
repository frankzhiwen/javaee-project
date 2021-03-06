package frank.model;

import frank.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 学生表
 */
@Getter
@Setter
@ToString
public class Student extends BaseEntity {
    
    private Integer id;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 毕业年份，数据字典000001
     */
    private String studentGraduateYear;

    /**
     * 专业，数据字典000002
     */
    private String studentMajor;

    /**
     * 邮箱
     */
    private String studentEmail;

    /**
     * 宿舍id
     */
    private Integer dormId;

    /**
     * 创建时间
     */
    private Date createTime;

    private String buildingId;

    private String buildingName;

    private String dormNo;

    private List<Integer> ids;
}