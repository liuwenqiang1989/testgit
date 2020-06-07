package com.dao;

import com.model.Emp;
import com.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PurchaseDao {
    public int insertPurchase(Purchase purchase);
}
