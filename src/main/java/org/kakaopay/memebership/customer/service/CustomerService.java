package org.kakaopay.memebership.customer.service;

import org.kakaopay.memebership.customer.vo.Customer;
import org.kakaopay.memebership.customer.vo.RelationShip;

public interface CustomerService {

    public void registerUserInfo (Customer customer);

    public void modifyUserInfo (Customer customer);

    public void registerRelationShips (Customer lead, Customer follower, RelationShip relationShip);

    public void modifyRelationShips (Customer lead, Customer follower, RelationShip relationShip);

}
