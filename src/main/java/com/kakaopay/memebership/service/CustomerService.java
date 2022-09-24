package com.kakaopay.memebership.service;

import com.kakaopay.memebership.dto.Customer;
import com.kakaopay.memebership.dto.Relationship;

public interface CustomerService {

    public void registerUserInfo (Customer customer);

    public void modifyUserInfo (Customer customer);

    public void registerRelationShips (Customer lead, Customer follower, Relationship relationShip);

    public void modifyRelationShips (Customer lead, Customer follower, Relationship relationShip);

}
