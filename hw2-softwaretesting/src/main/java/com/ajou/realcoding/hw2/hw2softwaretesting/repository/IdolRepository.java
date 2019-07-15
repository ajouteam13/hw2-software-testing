package com.ajou.realcoding.hw2.hw2softwaretesting.repository;

import com.ajou.realcoding.hw2.hw2softwaretesting.domain.Idol;

import java.util.List;


public interface IdolRepository {
    Idol findMemberByGroupName(String groupName);
    List<Idol> findAll(String groupName);
    Idol updateIdolProfile(String groupName, String name, int age, String position);
}
