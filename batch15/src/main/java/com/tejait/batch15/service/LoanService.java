package com.tejait.batch15.service;


import com.tejait.batch15.model.*;

import java.util.List;

public interface LoanService {
    Loans applyingLoan(Loans loan);

    List<Loans> getAllLoanAppliers();


    BusinessProduct getBusinessProductsByAppId(int appId);

    CompanyDetails getCompanyDetailsByAppId(int appId);

    CompanyAddress getCompanyAddressDetailsByAppId(int appId);


}
