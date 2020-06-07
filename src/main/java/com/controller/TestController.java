package com.controller;

import com.model.Emp;
import com.model.Purchase;
import com.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestController {

    @Resource
    private ITestService testService;


    @RequestMapping("/test")
    public String test(){
        return "test";
    }




    @RequestMapping(value = "/queryDb")
    @ResponseBody
    public List<Emp> queryDb() {
        try {
            List<Emp> list= testService.test();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/queryDbByMybatis")
    @ResponseBody
    public Emp queryDbByMybatis(String name) {
        try {
             Emp emp= testService.getEmp(name);
            return emp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/testInsert")
    @ResponseBody
    public String testInsert(String empName,String role,String purName,String money)  {
        try {
            Emp empParam=new Emp();
            empParam.setName(empName);
            empParam.setRole(role);

            Purchase purParam=new Purchase();
            purParam.setName(purName);
            purParam.setMoney(money);
            testService.insertWithNoTrans(empParam,purParam);
            return "success";
        }catch (Exception e){
            e.printStackTrace();


        }
        return null;
    }
}
