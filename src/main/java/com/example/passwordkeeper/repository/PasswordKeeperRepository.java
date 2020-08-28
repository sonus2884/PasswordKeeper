package com.example.passwordkeeper.repository;

import com.example.passwordkeeper.model.PasswordKeeper;
import com.example.passwordkeeper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordKeeperRepository extends JpaRepository<PasswordKeeper, Long> {

    @Query(value = "select p.userName, p.password, p.website from PasswordKeeper p inner join p.user u on u.id = p.user.id where u.id = ?1")
    List<String>getAllSavedPassword(Long user_id);



    List<PasswordKeeper> findPasswordKeeperByUser_Id(Long id);
}
