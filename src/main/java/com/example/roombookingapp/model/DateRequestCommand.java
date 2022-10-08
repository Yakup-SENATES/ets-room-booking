package com.example.roombookingapp.model;

import java.util.Date;

public class DateRequestCommand {

    private Date date;

    public DateRequestCommand(Date date) {
        this.date = date;
    }

    public DateRequestCommand() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
