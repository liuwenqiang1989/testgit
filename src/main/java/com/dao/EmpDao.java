package com.dao;

import com.model.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmpDao {
     Emp getEmp(Map<String,Object> param);
     int insertEmp(Emp emp);

}
