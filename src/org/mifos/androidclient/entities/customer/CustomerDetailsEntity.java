/*
 * Copyright (c) 2005-2011 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.androidclient.entities.customer;

import org.mifos.androidclient.entities.BaseEntity;

import java.util.List;

public abstract class CustomerDetailsEntity extends BaseEntity {

    private CustomerAccountSummary customerAccountSummary;
    private Address address;
    private List<CustomerNote> recentCustomerNotes;
    private Boolean activeSurveys;
    private List<CustomerSurvey> customerSurveys;
    private CustomerMeeting customerMeeting;
    private List<SavingsAccountBasicInformation> savingsAccountsInUse;

    public CustomerAccountSummary getCustomerAccountSummary() {

        return customerAccountSummary;
    }

    public void setCustomerAccountSummary(CustomerAccountSummary customerAccountSummary) {
        this.customerAccountSummary = customerAccountSummary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CustomerNote> getRecentCustomerNotes() {
        return recentCustomerNotes;
    }

    public void setRecentCustomerNotes(List<CustomerNote> recentCustomerNotes) {
        this.recentCustomerNotes = recentCustomerNotes;
    }

    public Boolean getActiveSurveys() {
        return activeSurveys;
    }

    public void setActiveSurveys(Boolean activeSurveys) {
        this.activeSurveys = activeSurveys;
    }

    public List<CustomerSurvey> getCustomerSurveys() {
        return customerSurveys;
    }

    public void setCustomerSurveys(List<CustomerSurvey> customerSurveys) {
        this.customerSurveys = customerSurveys;
    }

    public CustomerMeeting getCustomerMeeting() {
        return customerMeeting;
    }

    public void setCustomerMeeting(CustomerMeeting customerMeeting) {
        this.customerMeeting = customerMeeting;
    }

    public List<SavingsAccountBasicInformation> getSavingsAccountsInUse() {
        return savingsAccountsInUse;
    }

    public void setSavingsAccountsInUse(List<SavingsAccountBasicInformation> savingsAccountsInUse) {
        this.savingsAccountsInUse = savingsAccountsInUse;
    }

}