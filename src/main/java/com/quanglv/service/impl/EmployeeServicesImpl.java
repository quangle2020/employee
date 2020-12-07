package com.quanglv.service.impl;

import com.quanglv.domain.Employee;
import com.quanglv.repository.EmployeeRepository;
import com.quanglv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServicesImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {

        return employeeRepository.findAll();
    }
}
