package com.travelstory.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingDemoService {
    @Autowired
    ModelMapper modelMapper;

    public void doSmth() {
    }
}
