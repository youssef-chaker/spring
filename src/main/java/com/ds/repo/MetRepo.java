package com.ds.repo;

import com.ds.entity.met.Met;

import java.util.List;

public interface MetRepo {
    void addMet(String type , Met met);
    int deleteMet(String type , String nom);
    Met getMet(String type , String nom);
    List<Met> getMets();
    List<Met> getMets(String type);
    Met updateMet(Met met,String nom);
}
