package com.luv2code.springboot.cruddemo.service;


import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){

        this.employeeRepository=theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = this.employeeRepository.findById(theId);
        Employee theEmployee=null;

        if(result.isPresent()){
            theEmployee=result.get();
        }
        else{
            throw new RuntimeException(("Did not find employee id - "+ theId));
        }

        return theEmployee;
    }


    @Override
    public Employee save(Employee theEmployee) {
        return this.employeeRepository.save(theEmployee);
    }


    @Override
    public void deleteById(int theId) {
        this.employeeRepository.deleteById(theId);
    }
}
