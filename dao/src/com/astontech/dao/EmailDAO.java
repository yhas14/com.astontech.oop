package com.astontech.dao;

import com.astontech.bo.Email;

import java.util.List;

public interface EmailDAO {
    // Stored Procedures for Email

    public Email getEmailById(int emailId);
    public List<Email> getEmailList();

    public int  insertEmail(Email email);
    public boolean updateEmail(Email email);
    public boolean deleteEmail(int email);
}
