package com.ucbcba.logindemo.repositories;

import com.ucbcba.logindemo.entities.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserReportRepository extends JpaRepository<UserReport, Integer> {

}
