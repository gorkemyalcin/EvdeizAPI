package com.urlayasam.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.urlayasam.project.models.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, String> {

	Company findCompanyById(Integer id);

	List<Company> findAll();

	void deleteById(Integer id);

	Company findCompanyByName(String companyName);
}
