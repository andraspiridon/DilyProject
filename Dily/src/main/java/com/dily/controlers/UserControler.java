package com.dily.controlers;

import com.dily.Mapper.UserMapper;
import com.dily.entities.User;
import com.dily.repositories.UserRepository;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String findAllUsers(Model model) {
       // List<User> list ;
       // UserMapper userMapper=new UserMapper();
       // list = this.jdbcTemplate.query( "select * from user_table where name like '%Rusu%'", userMapper);
        model.addAttribute("users",repository.findAll());
        return "users";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete (@PathVariable int id){
        repository.delete(id);
        return new ModelAndView ("redirect:/users/getAllUsers");
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newUser() {
        return "new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("user_id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               @RequestParam("dateOfBirth") Date dateOfBirth) {
        repository.save(new User(id,name,username,password,email,dateOfBirth));
        return new ModelAndView("redirect:/users/getAllUsers");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("user_id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               @RequestParam("dateOfBirth")java.sql.Date dateOfBirth
                               ) {
        User user= repository.findOne(id);
        user.setUser_id(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        repository.save(user);
        return new ModelAndView("redirect:users/getAllUsers");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id,
                       Model model) {
        User user = repository.findOne(id);
        model.addAttribute("user", user);
        return "edit";
    }

}