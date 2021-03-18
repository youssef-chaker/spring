package com.ds.repo;

import com.ds.entity.Client;
import com.ds.entity.Table;

import java.util.List;

public interface TableRepo {
    void addTable(Table table);
    int deleteTable(int id);
    Table getTable(int id);
    List<Table> getTables();
    Table updateTable(Table table,int id);
}
