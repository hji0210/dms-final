package com.dms.standarddataserver.bulk.common.utils;

import com.dms.standarddataserver.bulk.domain.enums.DataTypeCode;
import com.dms.standarddataserver.bulk.domain.enums.DomainTypeCode;

import java.util.Arrays;

public class DomainMethodUtil {

    public static boolean isNotMatchDomainTypeCode(String domainTypeCode) {
        return Arrays.stream(DomainTypeCode.values()).noneMatch(enumValue -> enumValue.name().replace("CODE_", "").equals(domainTypeCode));
    }

    public static String getDomainType(String domainTypeCode) {
        return Arrays.stream(DomainTypeCode.values())
                .filter(enumValue -> enumValue.name().replace("CODE_", "").equals(domainTypeCode))
                .findFirst()
                .map(DomainTypeCode::getDomainType)
                .orElse("");
    }

    public static boolean isNotMatchDataTypeCode(String dataTypeCode) {
        return Arrays.stream(DataTypeCode.values()).noneMatch(enumValue -> {
            int index = enumValue.name().indexOf('_');
            return enumValue.name().substring(index + 1).equals(dataTypeCode);
        });
    }

    public static String getDataType(String dataTypeCode) {
        String tempDataTypeCode = Arrays.stream(DataTypeCode.values())
                .filter(enumValue -> {
                    int index = enumValue.name().indexOf('_');
                    return enumValue.name().substring(index + 1).equals(dataTypeCode);
                })
                .findFirst()
                .map(DataTypeCode::name)
                .orElse("");

        int index = tempDataTypeCode.indexOf('_');
        return tempDataTypeCode.substring(0, index);
    }

    public static String createDomainName(String keyDomainName, String dataTypeCode, int dataLength, int dataScale) {
        String dataTypeAbbreviation = Arrays.stream(DataTypeCode.values())
                .filter(enumValue -> enumValue.name().startsWith(dataTypeCode))
                .findFirst()
                .map(DataTypeCode::getDataTypeAbbreviation)
                .orElse("");

        String domainName = keyDomainName + dataTypeAbbreviation;

        if (dataLength > 0) {
            domainName += dataLength;
        }

        if (dataScale > 0) {
            domainName += "," + dataScale;
        }

        return domainName;
    }
}
