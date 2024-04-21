package com.mycompany.employee_management.controller;

import com.mycompany.employee_management.model.Employee;
import com.mycompany.employee_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //display list of employee
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1,"firstName","asc",model);
        /*
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
         */
    }

    //add new Employee
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        try {
            employeeService.saveEmployee(employee);
            return "redirect:/";
        } catch (Exception e) {
            // Add error message to the model
            model.addAttribute("error", "An error occurred while saving the employee.");
            // Show the new employee form again with the error message
            return "new_employee";
        }
    }

    //It is for update employee data
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    //delete employee
    @GetMapping("showFormForDelete/{id}")
    public String showFormForDelete(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    //add pagination add sorting String sortField, String sortDirection
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize,sortField,sortDir);
        List<Employee> listEmployees = page.getContent();
        //pagination
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        //shorting
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");

        //total list
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
