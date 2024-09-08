package com.dotoku.carhop.entity;

public enum RequestStatus {

        APPROVED(1),
        REJECTED(0),
        PENDING(2);

        private final int status;

        RequestStatus(int minutes) {
            this.status = minutes;
        }

        public int getStatus() {
            return status;
        }

}
