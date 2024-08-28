package com.example.th.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.th.model.Documents;
import com.example.th.model.Employee;
import com.example.th.model.Role;
import com.example.th.service.DocumentService;
import com.example.th.service.EmployeeService;
import com.example.th.service.RoleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;
    
    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    @ResponseBody
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return employeeService.findById(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    // Endpoint for verifying documents
    @PutMapping("/documents/verify/{documentId}")
    public Documents verifyDocument(@PathVariable String documentId, @RequestParam String status) {
        return documentService.verifyDocument(documentId, status);
    }

    // Endpoint for creating a new role
    @PostMapping("/roles/create")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Endpoint for assigning a role to a user
    @PutMapping("/roles/assign")
    public void assignRoleToUser(@RequestParam String userId, @RequestParam String roleName) {
        roleService.assignRoleToUser(userId, roleName);
    }
}