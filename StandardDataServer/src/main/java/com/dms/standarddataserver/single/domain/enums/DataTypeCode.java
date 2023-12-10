package com.dms.standarddataserver.single.domain.enums;


public enum DataTypeCode {
    CHAR_0001("CH"),
    NCHAR_0002("NCH"),
    VARCHAR2_0003("VC"),
    NVARCHAR2_0004("NVC"),
    INT_0005("INT"),
    TINYINT_0006("TINT"),
    SMALLINT_0007("SINT"),
    MEDIUMINT_0008("MINT"),
    BIGINT_0009("BINT"),
    INTEGER_0010("INTG"),
    DECIMAL_0011("DEC"),
    DOUBLE_0012("DB"),
    FLOAT_0013("FL"),
    REAL_0014("RL"),
    BIT_0015("BIT"),
    BINARY_0016("BN"),
    VARBINARY_0017("VBN"),
    RAW_0018("RAW"),
    NUMERIC_0019("NUMR"),
    BOOLEAN_0020("BOOL"),
    NUMBER_0021("NUM"),
    TIME_0022("TM"),
    TIMESTAMP_0023("TMS"),
    DATE_0024("DT"),
    DATETIME_0025("DTM"),
    YEAR_0026("YR"),
    TEXT_0027("TX"),
    TINYTEXT_0028("TTX"),
    MEDIUMTEXT_0029("MTX"),
    LONGTEXT_0030("LTX"),
    CLOB_0031("CL"),
    NCLOB_0032("NCL"),
    BLOB_0033("BL");

    private final String dataTypeAbbreviation;
    DataTypeCode(String dataTypeAbbreviation) {
        this.dataTypeAbbreviation = dataTypeAbbreviation;
    }

    public String getDataTypeAbbreviation() {
        return dataTypeAbbreviation;
    }
}

