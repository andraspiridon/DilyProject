package com.dily.controlers;

import com.dily.Mapper.UserMapper;
import com.dily.entities.User;
import com.dily.repositories.UserRepository;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by Andra on 4/11/2017.
 */
@Controller
@RequestMapping("/users")
public class UserControler {

    @Autowired
    private UserRepository repository;

    private DataSource dataSource;
    JdbcTemplate jdbcTemplate =new JdbcTemplate();
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findAllUsers(Model model) {
       // List<User> list ;
       // UserMapper userMapper=new UserMapper();
       // list = this.jdbcTemplate.query( "select * from user_table where name like '%Rusu%'", userMapper);
        model.addAttribute("users",repository.findAll());
        return "users";
    }

}