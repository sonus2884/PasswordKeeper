package com.example.passwordkeeper.repository;

import com.example.passwordkeeper.model.PasswordKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordKeeperRepository extends JpaRepository<PasswordKeeper, Long> {

    @Query("select p from PasswordKeeper p inner join p.user u on u.id=?1")
    List<PasswordKeeper>getAllSavedPassword(Long user_id);

//    List<PasswordKeeper> findByUser_Id(Long id);
}
