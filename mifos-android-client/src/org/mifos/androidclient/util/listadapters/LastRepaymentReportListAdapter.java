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

package org.mifos.androidclient.util.listadapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mifos.androidclient.R;
import org.mifos.androidclient.entities.account.LoanAccountDetails;
import org.mifos.androidclient.entities.customer.LastRepayment;
import org.mifos.androidclient.entities.customer.LoanAccountBasicInformation;
import org.mifos.androidclient.util.ui.DateUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class LastRepaymentReportListAdapter extends SimpleExpandableListAdapter implements Filterable {

    public LastRepaymentReportListAdapter(Context context,
			Map<SimpleListItem, List<SimpleListItem>> items) {
		super(context, items);
	}

    @Override
    public View getGroupView(int groupPos, boolean isExpanded, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.last_repayment_group_list_item, parent, false);
        } else {
            row = convertView;
        }
        LastRepayment item = (LastRepayment) getGroup(groupPos);
        if (item != null) {
        	
            TextView name = (TextView) row.findViewById(R.id.client_name);
            TextView lastLoanDate = (TextView) row.findViewById(R.id.last_loan_date);
            
            if (item.isGroup()) {
            	((TextView) row.findViewById(R.id.client_name_label)).setText(R.string.lastRepaymentGroup_label);
            }
            
            name.setText(item.getCustomer().getDisplayName());
            lastLoanDate.setText(DateUtils.format(item.getLastInstallmentDate()));
        }
        synchronized (mExpandGroups) {
            if (mExpandGroups == true) {
                ExpandableListView list = (ExpandableListView)parent;
                list.expandGroup(groupPos);
            }
        }
        return row;
    }
    
    @Override
    public View getChildView(int groupPos, int childPos, boolean isLastChild, View convertView, ViewGroup parent) {
        View row;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.overdue_loan_list_item, parent, false);
        } else {
            row = convertView;
        }
        LoanAccountBasicInformation loan = (LoanAccountBasicInformation) getChild(groupPos, childPos);
        TextView text = null;
        if (loan != null) {
        	text = (TextView)row.findViewById(R.id.loanAccountListItem_accountName);
            text.setText(loan.getPrdOfferingName() + ", " + loan.getGlobalAccountNum());
            text = (TextView)row.findViewById(R.id.loanAccountListItem_status);
            text.setText(loan.getAccountStateName());
            if (loan.getAccountStateId() == LoanAccountDetails.ACC_STATE_PARTIAL_APPLICATION ||
            		loan.getAccountStateId() == LoanAccountDetails.ACC_STATE_APPLICATION_APPROVED ||
            				loan.getAccountStateId() == LoanAccountDetails.ACC_STATE_APPLICATION_PENDING_APPROVAL) {

                text = (TextView)row.findViewById(R.id.loanAccountListItem_amountDue_label);
                text.setVisibility(View.GONE);
                text = (TextView)row.findViewById(R.id.loanAccountListItem_amountDue);
                text.setVisibility(View.GONE);
            } else {
                text = (TextView)row.findViewById(R.id.loanAccountListItem_amountDue);
                text.setText(loan.getTotalAmountDue());
            }
            text = (TextView) row.findViewById(R.id.loanAccountListItem_amountInArrears);
            text.setText(loan.getTotalAmountInArrears());
        }
        return row;
    }
    
	@Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new LastRepaymentListFilter();
        }
        return mFilter;
    }

    private class LastRepaymentListFilter extends SimpleExpandableListFilter {

    	@Override
    	protected Map<SimpleListItem, List<SimpleListItem>> filterGroups(Map<SimpleListItem, 
    			List<SimpleListItem>> allItems, String constraint) {
    		 Map<SimpleListItem, List<SimpleListItem>> filteredItems = new HashMap<SimpleListItem, List<SimpleListItem>>();
    		 
    		 for (SimpleListItem group : allItems.keySet()) {
                 List<SimpleListItem> clients = new ArrayList<SimpleListItem>();
                 for (SimpleListItem client : allItems.get(group)) {
                 	clients.add(client);
                 }
                 if (group.getListLabel().toLowerCase().contains(constraint)) {
                     filteredItems.put(group, clients);
                 }
             }
    		 
    		 return filteredItems;
    	}
    	
    }

}
