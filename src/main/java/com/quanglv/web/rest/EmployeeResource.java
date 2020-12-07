package com.quanglv.web.rest;

import com.quanglv.domain.Employee;
import com.quanglv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/get-all")
//    public List<Employee> getAll(){
//        return employeeService.getAll();
//    }
    public ResponseEntity<?> getAll(@RequestParam String hihi){
        return ResponseEntity.ok(employeeService.getAll());
    }

}
