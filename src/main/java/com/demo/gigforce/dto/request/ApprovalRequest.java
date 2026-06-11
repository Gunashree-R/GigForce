package com.demo.gigforce.dto.request;

import com.demo.gigforce.enums.ApprovalStatus;

public class ApprovalRequest {

        private ApprovalStatus status;

        public ApprovalStatus getStatus() {
            return status;
        }

        public void setStatus(ApprovalStatus status) {
            this.status = status;
        }
}

