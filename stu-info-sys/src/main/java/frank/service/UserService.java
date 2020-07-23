package frank.service;

import com.github.pagehelper.PageHelper;
import frank.exception.BusinessException;
import frank.mapper.UserMapper;
import frank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user) {
        User query = new User();
        query.setUsername(user.getUsername());
        User exist = userMapper.selectOne(query);
        if(exist == null)
            throw new BusinessException("-USR001", "用户不存在");
        if(!exist.getPassword().equals(user.getPassword()))
            throw new BusinessException("-USR002", "用户或密码错误");
        return exist;
    }

    public List<User> query(User user) {
        PageHelper.startPage(user);
        return userMapper.selectByCondition(user);
    }

    public User queryById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public int insert(User user) {
        user.setCreateTime(new Date());
        int num = userMapper.insertSelective(user);
        if(num != 1)
            throw new BusinessException("-USR003", "新增用户数据数量异常");
        return num;
    }

    @Transactional
    public int updateById(User user) {
        int num = userMapper.updateByPrimaryKeySelective(user);
        if(num != 1)
            throw new BusinessException("-USR004", "修改用户数据数量异常");
        return num;
    }

    @Transactional
    public int deleteByIds(List<Integer> ids) {
        return userMapper.deleteByIds(ids);
    }
}
