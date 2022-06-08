package com.meituan.manage.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meituan.manage.base.enums.IsDeleted;
import com.meituan.manage.mapper.UserMapper;
import com.meituan.manage.model.User;
import com.meituan.manage.services.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Jyt
 * @date 2021/9/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Optional<User> login(String loginName, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
            return new Optional<>();
        }
        criteria.andEqualTo("loginName",loginName)
                .andEqualTo("password",password);
        User user = userMapper.selectOneByExample(example);
        return Optional.of(user);
    }

    @Override
    public Optional<Boolean> addUser(User user) {
        Boolean flag = userMapper.insertSelective(user) > 0;
        return Optional.of(flag);
    }

    @Override
    public Optional<Boolean> updateUser(User user) {
        Boolean flag = userMapper.updateByPrimaryKeySelective(user) < 0;
        return Optional.of(flag);
    }

    @Override
    public Optional<Boolean> delUserById(Long userId) {

        if (Objects.isNull(userId)){
            return Optional.of(false);
        }
        User user = User.builder()
                .id(userId)
                .isDeleted("YES")
                .build();
        Boolean flag = userMapper.updateByPrimaryKeySelective(user) > 0;
        return Optional.of(flag);
    }

    @Override
    public Optional<PageInfo<User>> pageUserList(Integer page, Integer pageSize, User user) {

        Example example = Example.builder(User.class).build();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",user.getId())
                .andEqualTo("status",user.getStatus())
                .andEqualTo("type",user.getType())
                .andEqualTo("isDeleted", IsDeleted.NO.getCode())
                .andLike("name","%"+user.getUserName()+"%");
        PageHelper.startPage(page,pageSize);
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        if (CollectionUtils.isEmpty(users)){
            return new Optional<>();
        }
        return Optional.of(userPageInfo);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        if (Objects.isNull(userId)){
            return Optional.empty();
        }
        User user = userMapper.selectByPrimaryKey(userId);
        return Optional.of(user);
    }
}
