/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.webservice;

import com.example.entities.User;
import com.example.model.UserBeanLocalimpl;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author Nicolas
 */
@WebService(serviceName = "rankingService")
@Stateless
public class rankingService {

    @EJB
    private UserBeanLocalimpl ejb;
    // Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getRankingforUser")
    public int getRankingforUser(@WebParam(name="user")String user) {
        List<User> users =  ejb.updateRanking();
        for(int i=0;i<users.size();i++){
        if(users.get(i).getUser().equals(user)){
        return users.get(i).getRanking();
        }
       
        }
         return 0;
        

    }

    @WebMethod(operationName = "getAllRankings")

    public List<User> getAllRankings() {
       return ejb.updateRanking();
    }

    
    
}
