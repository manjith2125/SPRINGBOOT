package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.*;
import com.tejait.batch15.repository.CompanyAddressRepository;
import com.tejait.batch15.repository.CompanyDetailsRepository;
import com.tejait.batch15.repository.BusinessProductRepository;
import com.tejait.batch15.repository.LoansRepository;
import com.tejait.batch15.service.ApplicationOverviewService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ApplicationOverviewServiceImpl implements ApplicationOverviewService {

    private final CompanyDetailsRepository companyDetailsRepo;
    private final CompanyAddressRepository companyAddressRepo;
    private final BusinessProductRepository businessProductRepo;
    private final LoansRepository loansRepo;

    @Override
    public ApplicationOverview getOverviewByAppId(int appId) {

        // Fetch single objects (NOT LIST)
        CompanyDetails company =
                companyDetailsRepo.findByAppId(appId);

        CompanyAddress address =
                companyAddressRepo.findByAppId(appId);

        BusinessProduct product =
                businessProductRepo.findByAppId(appId);

        Loans loan =
                loansRepo.findByAppId(appId);

        // Create overview object
        ApplicationOverview overview = new ApplicationOverview();

        overview.setAppId(appId);

        // Company Details Mapping
        if (company != null) {
            overview.setCompanyName(company.getCompanyName());
            overview.setCompanyPan(company.getCompanyPan());
        }

        // Loan Mapping
        if (loan != null) {
            overview.setMail(loan.getMailId());
            overview.setMobile(loan.getMobile());
        }

        // Address Mapping
        if (address != null) {
            overview.setCity(address.getCity());
        }

        // Business Product Mapping
        if (product != null) {
            overview.setLoanAmt(product.getLoanAmount());
            overview.setTenure(product.getTenure());
        }

        return overview;
    }
}