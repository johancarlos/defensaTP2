package com.ucbcba.logindemo.services;

import com.ucbcba.logindemo.entities.UserReport;
import com.ucbcba.logindemo.repositories.PostRepository;
import com.ucbcba.logindemo.repositories.UserReportRepository;
import com.ucbcba.logindemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    private UserReportRepository userReportRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void saveReport(UserReport userReport) {
    }

    @Override
    public UserReport findUserReportbyId(Integer id) {
        return null;
    }

    @Override
    public Iterable<UserReport> listAllUserReports() {
        return null;
    }


    @Autowired
    public UserReportRepository getUserReportRepository() {
        return userReportRepository;
    }


    @Autowired
    @Qualifier(value = "userReportRepository")
    public void setUserReportRepository(UserReportRepository userReportRepository) {
        this.userReportRepository = userReportRepository;
    }


    @Override
    public UserReport getUserReport(Integer id){return userReportRepository.findById(id).get();}



}
