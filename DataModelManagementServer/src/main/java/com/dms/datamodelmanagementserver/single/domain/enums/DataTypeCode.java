package com.dms.datamodelmanagementserver.single.domain.enums;

public enum DataTypeCode {
    CHAR("CHAR", "CH"),
    NCHAR("NCHAR", "NCH"),
    VARCHAR("VARCHAR", "VC"),
    NVARCHAR2_0004("NVARCHAR2", "NVC"),
    BINARY("BINARY", "BINARY"),
    BOOLEAN("BOOLEAN", "BOOLEAN"),
    VARBINARY("VARBINARY", "VARBINARY"),
    CLOB("CLOB", "CLOB"),
    NCLOB("NCLOB", "NCLOB"),
    INTEGER("INTEGER", "INT"),
    SMALLINT("SMALLINT", "SINT"),
    BIGINT("BIGINT", "BINT"),
    NUMERIC("NUMERIC", "NUMER"),
    FLOAT("FLOAT", "FL"),
    REAL("REAL", "RL"),
    DATE("DATE", "DT"),
    XML("XML", "XL");

    private final String dataTypeCode;
    private final String codeValue;

    DataTypeCode(String dataTypeCode, String codeValue) {
        this.dataTypeCode = dataTypeCode;
        this.codeValue = codeValue;
    }

    public String getDataTypeCode() { return this.dataTypeCode; }
    public String getCodeValue() { return this.codeValue; }
}
