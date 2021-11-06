package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class UserService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Пользователя по ID
    public User getUserByID(Integer id) {
        String sqlGetUserByID = "select * from users u where u.id = ";

        List<User> users = jdbcTemplate.query(sqlGetUserByID + id,
                (ResultSet rs, int rowNumSign) -> {
                    User user = new User();
                    user.setId(id);
                    user.setUsername(rs.getString("username"));
                    return user;
                });
        if (users.isEmpty()) {
            return null;
        } else {
            return (users.get(0));
        }
    }
}
