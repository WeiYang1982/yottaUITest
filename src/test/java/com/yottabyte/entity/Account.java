package com.yottabyte.entity;

/**
 * @author sunxj
 */
public class Account {
    private static String accountName;
    private static String accountId;

    public static String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public static String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
