package frank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 考试信息
 */
@Getter
@Setter
@ToString
public class Exam extends DictionaryTag {
    
    private Integer id;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 考试备注
     */
    private String examDesc;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 班级id
     */
    private Integer classesId;

    /**
     * 创建时间
     */
    private Date createTime;

    private Classes classes;

    private Course course;
}