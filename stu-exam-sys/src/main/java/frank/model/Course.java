package frank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 课程信息
 */
@Getter
@Setter
@ToString
public class Course extends DictionaryTag {
    
    private Integer id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 创建时间
     */
    private Date createTime;
}