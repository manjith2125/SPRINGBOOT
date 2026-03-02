package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.exceptions.MailAlreadyExists;
import com.tejait.batch15.exceptions.MobileNumberAlreadyExists;
import com.tejait.batch15.model.*;
import com.tejait.batch15.repository.*;
import com.tejait.batch15.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoansRepository repository;
    private final BusinessProductRepository businessProductRepository;
    private final CompanyDetailsRepository companyDetailsRepository;
    private final CompanyAddressRepository companyAddressRepository;

    @Override
    public Loans applyingLoan(Loans loan) {

        // Check if mobile already exists
        if (repository.findByMobile(loan.getMobile()).isPresent()) {
            throw new MobileNumberAlreadyExists(
                    "Mobile number " + loan.getMobile() + " already exists!"
            );
        }

        // Check if mail already exists
        if (repository.findByMailId(loan.getMailId()).isPresent()) {
            throw new MailAlreadyExists(
                    "Mail " + loan.getMailId() + " already exists!"
            );
        }

        String fname = loan.getFname();
        String lname = loan.getLname();

        loan.setCustomerName(fname.concat(" " + lname));

        return repository.save(loan);
    }

    // 🔥 FIXED ERROR HERE
    @Override
    public java.util.List<Loans> getAllLoanAppliers() {
        return repository.findAll();
    }

    @Override
    public BusinessProduct getBusinessProductsByAppId(int appId) {
        return businessProductRepository.findByAppId(appId);
    }

    @Override
    public CompanyDetails getCompanyDetailsByAppId(int appId) {
        return companyDetailsRepository.findByAppId(appId);
    }

    @Override
    public CompanyAddress getCompanyAddressDetailsByAppId(int appId) {
        return companyAddressRepository.findByAppId(appId);
    }
}