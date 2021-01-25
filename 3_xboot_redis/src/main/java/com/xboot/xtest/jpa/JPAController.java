package com.xboot.xtest.jpa;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xboot.param.UserParam;
import com.xboot.repository.UserRepository;
import com.xboot.repository.model.User;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/jpa")
public class JPAController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = PageRequest.of(page, size, sort);
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users=userRepository.findList(pageable);
        model.addAttribute("users", users);
        return "test/jpa/user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "test/jpa/user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserParam userParam,BindingResult result, Model model) {
        String errorMsg="";
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg=errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            return "test/jpa/user/userAdd";
        }
        User u= userRepository.findByUserName(userParam.getUserName());
        if(u!=null){
            model.addAttribute("errorMsg","用户已存在!");
            return "test/jpa/user/userAdd";
        }
        User user=new User();
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userRepository.findById((long)id);
        model.addAttribute("user", user);
        return "test/jpa/user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserParam userParam, BindingResult result,Model model) {
        String errorMsg="";
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg=errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("user", userParam);
            return "test/jpa/user/userEdit";
        }

        User user=new User();
        BeanUtils.copyProperties(userParam,user);
        user.setRegTime(new Date());
        userRepository.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "redirect:/list";
    }
}
