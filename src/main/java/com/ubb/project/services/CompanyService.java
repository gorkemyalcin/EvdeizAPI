package com.urlayasam.project.services;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlayasam.project.exceptions.CompanyAlreadyExistsException;
import com.urlayasam.project.exceptions.CompanyNotFoundException;
import com.urlayasam.project.exceptions.NullParameterException;
import com.urlayasam.project.exceptions.UserNotFoundException;
import com.urlayasam.project.models.Company;
import com.urlayasam.project.repositories.CompanyRepository;
import com.urlayasam.project.requests.CompanyAddRequest;
import com.urlayasam.project.requests.CompanyUpdateRequest;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Company findCompanyById(Integer companyId) throws CompanyNotFoundException, NullParameterException {
		if (companyId != null) {
			Company company = companyRepository.findCompanyById(companyId);
			if (company != null) {
				return company;
			}
			throw new CompanyNotFoundException("No company was found with the id: " + companyId);
		}
		throw new NullParameterException("Given companyId parameter is null. ");
	}

	public Company findCompanyByName(String companyName) throws CompanyNotFoundException, NullParameterException {
		if (companyName != null) {
			Company company = companyRepository.findCompanyByName(companyName);
			if (company != null) {
				return company;
			}
			throw new CompanyNotFoundException("No company was found with the id: " + companyName);
		}
		throw new NullParameterException("Given companyName parameter is null. ");
	}

	public List<Company> findComapanies() throws UserNotFoundException {
		List<Company> companyList = companyRepository.findAll();
		if (companyList.isEmpty() || companyList == null) {
			throw new UserNotFoundException("There are no companies to be found.");
		}
		return companyList;
	}

	public boolean update(CompanyUpdateRequest companyUpdateRequest)
			throws CompanyNotFoundException, NullParameterException {
		if (validateCompanyUpdateRequestInput(companyUpdateRequest)) {
			Company company = companyRepository.findCompanyById(companyUpdateRequest.getId());
			company.setCompany(companyUpdateRequest);
			companyRepository.save(company);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean delete(Integer companyId) throws CompanyNotFoundException, NullParameterException {
		if (companyId == null) {
			throw new NullParameterException("The id field in the parameter is null.");
		}
		List<Company> companyList = companyRepository.findAll();
		for (Company company : companyList) {
			if (company.getId().equals(companyId)) {
				companyRepository.deleteById(companyId);
				return true;
			}
		}
		throw new CompanyNotFoundException("No company was found with id: " + companyId);
	}

	public boolean add(@Valid CompanyAddRequest paramCompany)
			throws CompanyAlreadyExistsException, NullParameterException {
		if (validateAddCompanyRequestInput(paramCompany)) {
			List<Company> companyList = companyRepository.findAll();
			for (Company company : companyList) {
				if (company.getName().equals(paramCompany.getName())) {
					throw new CompanyAlreadyExistsException(
							"There already exists a company with name: " + paramCompany.getName());
				}
			}
			Company companyToBeAdded = new Company();
			companyToBeAdded.setCompany(paramCompany);
			companyRepository.save(companyToBeAdded);
			return true;
		} else {
			throw new NullParameterException("The attributes inside the Company parameter have null values.");
		}
	}

	private boolean validateAddCompanyRequestInput(@Valid CompanyAddRequest paramCompany) {
		return paramCompany.getEmail() != null && paramCompany.getTelephone() != null && paramCompany.getName() != null;
	}

	private boolean validateCompanyUpdateRequestInput(CompanyUpdateRequest companyUpdateRequest) {
		return (companyUpdateRequest != null && companyUpdateRequest.getId() != null
				&& companyUpdateRequest.getEmail() != null && companyUpdateRequest.getDescription() != null
				&& companyUpdateRequest.getTelephone() != null && companyUpdateRequest.getCreationDate() != null
				&& companyUpdateRequest.getName() != null);
	}

	public List<Company> getCompaniesWithOptionalParams(Integer id, String name, String email, String telephone,
			String description, Date creationDate, String logo) throws CompanyNotFoundException {
		List<Company> companyList = companyRepository.findAll();
		eliminateNotEqualId(id, companyList);
		eliminateNotEqualName(name, companyList);
		eliminateNotEqualEmail(email, companyList);
		eliminateNotEqualTelephone(telephone, companyList);
		eliminateNotEqualDescription(description, companyList);
		eliminateNotEqualCreationDate(creationDate, companyList);
		eliminateNotEqualLogo(logo, companyList);

		if (companyList.isEmpty()) {
			throw new CompanyNotFoundException("There is no such company.");
		}
		return companyList;
	}

	private void eliminateNotEqualId(Integer id, List<Company> companyList) {
		if (id != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getId().equals(id)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualName(String name, List<Company> companyList) {
		if (name != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getName().equals(name)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualEmail(String email, List<Company> companyList) {
		if (email != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getEmail().equals(email)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualTelephone(String telephone, List<Company> companyList) {
		if (telephone != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getTelephone().equals(telephone)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualDescription(String description, List<Company> companyList) {
		if (description != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getDescription().equals(description)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualCreationDate(Date creationDate, List<Company> companyList) {
		if (creationDate != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getCreationDate().equals(creationDate)) {
					companyIterator.remove();
				}
			}
		}
	}

	private void eliminateNotEqualLogo(String logo, List<Company> companyList) {
		if (logo != null) {
			Iterator<Company> companyIterator = companyList.iterator();
			while (companyIterator.hasNext()) {
				Company nextCompany = companyIterator.next();
				if (!nextCompany.getLogo().equals(logo)) {
					companyIterator.remove();
				}
			}
		}
	}
}
