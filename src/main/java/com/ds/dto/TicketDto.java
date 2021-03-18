package com.ds.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TicketDto {
    private LocalDate date;
    private int nbCouvert;
    private int addition;
    private int tableNum;
    private int userId;
}
