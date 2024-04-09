package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {
    @Select("select * from users")
    List<User> getAllUser();
    @Select("SELECT * FROM users WHERE user_name = #{username} and password=#{password}")
    User isLoggedUser(String username,String password);

    @Insert("INSERT INTO users (user_name, password, email, country_code, phone_number) " +
            "VALUES (#{userName}, #{password}, #{email}, #{countryCode}, #{phoneNumber})")
    void insert(User user);

}
