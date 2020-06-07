package com.service;

import com.model.Emp;
import com.model.Purchase;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ITestService {
    public List<Emp> test() throws Exception;
    public  Emp getEmp(String name) throws Exception;

    public void insertWithNoTrans(Emp emp, Purchase purchase) throws Exception;
}
