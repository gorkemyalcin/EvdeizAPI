package com.urlayasam.project.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urlayasam.project.exceptions.CompanyAlreadyExistsException;
import com.urlayasam.project.exceptions.CompanyNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.models.Company;
import com.urlayasam.project.requests.CompanyAddRequest;
import com.urlayasam.project.requests.CompanyUpdateRequest;
import com.urlayasam.project.services.CompanyService;

@RestController
@RequestMapping("/companies")
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getCompanies(@PathVariable("id") Integer id) {
		Company company;
		try {
			company = companyService.findCompanyById(id);
		} catch (CompanyNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>(company, HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getCompaniesWithOptionalParameters(@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String name, @RequestParam(required = false) String email,
			@RequestParam(required = false) String telephone, @RequestParam(required = false) String description,
			@RequestParam(required = false) Date creationDate, @RequestParam(required = false) String logo) {
		List<Company> companyList;
		try {
			companyList = companyService.getCompaniesWithOptionalParams(id, name, email, telephone, description,
					creationDate, logo);
		} catch (CompanyNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyList);

	}

	@PostMapping
	public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyAddRequest companyAddRequest) {
		try {
			companyService.add(companyAddRequest);
		} catch (CompanyAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body("Company with name: " + companyAddRequest.getName() + " has been added.");
	}

	@PutMapping
	public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyUpdateRequest companyUpdateRequest) {
		try {
			companyService.update(companyUpdateRequest);
		} catch (CompanyNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully updated the company: " + companyUpdateRequest.getName(),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteCompany(@Valid @RequestBody Integer companyId) {
		try {
			companyService.delete(companyId);
		} catch (CompanyNotFoundException | NullParameterException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return new ResponseEntity<>("Succesfully deleted the company with id: " + companyId, HttpStatus.ACCEPTED);
	}
}
