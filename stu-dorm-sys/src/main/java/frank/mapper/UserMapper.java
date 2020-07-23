package frank.mapper;

import frank.base.BaseMapper;
import frank.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User login(String user);

    List<User> query(User user);
}