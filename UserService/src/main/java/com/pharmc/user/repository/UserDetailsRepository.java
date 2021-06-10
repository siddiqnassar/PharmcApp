package com.pharmc.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pharmc.user.model.UserDetailsModel;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsModel, Long>{
    
    @Query(value= "SELECT * FROM user_details user WHERE user.id = :id", nativeQuery = true)
    public UserDetailsModel findUserById(long id);
    
    @Query(value= "SELECT * FROM user_details user WHERE user.email = :email", nativeQuery = true)
    public UserDetailsModel getUserDetailsEmail(String email);

}
