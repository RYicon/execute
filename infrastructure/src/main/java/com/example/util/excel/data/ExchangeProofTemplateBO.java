package com.example.util.excel.data;

import lombok.Data;

@Data
public class ExchangeProofTemplateBO {
    private Long Id;
    private String Name;
    private String ItemIdList;
    private String UserDefined;
    private String ProofType;
    private String IsActive;
    private String DataChange_CreateTime;
    private String DataChange_LastUser;
    private String DataChange_LastTime;
}
