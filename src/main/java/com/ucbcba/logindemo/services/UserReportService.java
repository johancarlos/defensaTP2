package com.ucbcba.logindemo.services;


import com.ucbcba.logindemo.entities.UserReport;

public interface UserReportService {
    void saveReport(UserReport userReport);
    UserReport findUserReportbyId(Integer id);
    Iterable<UserReport> listAllUserReports();

    UserReport getUserReport(Integer id);
}
