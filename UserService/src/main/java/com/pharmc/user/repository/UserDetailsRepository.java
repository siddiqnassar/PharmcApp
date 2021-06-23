package com.pharmc.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pharmc.user.model.UserDetailsModel;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsModel, Long>{
    
    @Query(value= "SELECT * FROM user_details user WHERE user.id = :id", nativeQuery = true)
    public UserDetailsModel findUserById(long id);
    
    @Query(value= "SELECT * FROM user_details user WHERE user.email = :email", nativeQuery = true)
    public UserDetailsModel getUserDetailsEmail(String email);
    @Modifying 
    @Query(value="UPDATE user_details u set u.first_name = :firstName , u.last_name = :lastName , u.gender = :gender , u.mobile_no = :mobileNo , u.delivery_id = :deliveryId , u.account_id = :accountId where u.id = :id",nativeQuery = true)
    public int updateUserInfo(long id,String firstName,String lastName,String gender,long mobileNo,long deliveryId,long accountId);
    
    @Modifying 
    @Query(value="UPDATE user_details u set u.password = :password where u.id = :id",nativeQuery = true)
    public int changePassword(long id,String password);
    @Modifying
    @Query(value="DELETE FROM user_details WHERE id= :id",nativeQuery = true)
    public int deletUserInfo(long id);


}
