package com.example.base.domain;

import lombok.Data;

import java.util.Optional;

@Data
public class UserInfo {
    private Integer userId;
    private String userName;
    private Boolean isMan;
    private Boolean isSendProof;

    /**
     * 是否向用户发送凭证
     */
    public Boolean isIsSendProof() {
        return isSendProof;
    }

    public Optional<Boolean> optionalIsIsSendProof() {
        return Optional.ofNullable(isSendProof);
    }

    /**
     * 是否向用户发送凭证
     */
    public void setIsSendProof(final Boolean isSendProof) {
        this.isSendProof = isSendProof;
    }




}
