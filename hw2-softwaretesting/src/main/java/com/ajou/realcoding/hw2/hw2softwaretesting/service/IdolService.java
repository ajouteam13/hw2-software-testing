package com.ajou.realcoding.hw2.hw2softwaretesting.service;

import com.ajou.realcoding.hw2.hw2softwaretesting.domain.Idol;
import com.ajou.realcoding.hw2.hw2softwaretesting.repository.IdolRepository;

import java.util.List;

public class IdolService {
    private final IdolRepository idolRepository;

    public IdolService(IdolRepository idolRepository) {
        this.idolRepository = idolRepository;
    }

    public Idol findMemberByGroupName(String groupName) {
        Idol idol = idolRepository.findMemberByGroupName(groupName);
        return idol;
    }

    public List<Idol> findAllMembersByGroupName(String groupName){
        List<Idol> idols = idolRepository.findAll(groupName);
        return idols;
    }

    public Idol updateIdolProfile(String groupName, String name, int age, String position) {
        Idol idol = new Idol(groupName,name,age,position);
        return idol;
    }
}


