package com.configuration;

import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorLegacyImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppSequenceInformationExtractor extends SequenceInformationExtractorLegacyImpl {

    public static final AppSequenceInformationExtractor INSTANCE = new AppSequenceInformationExtractor();


    @Override
    protected Long resultSetStartValueSize(ResultSet resultSet) throws SQLException {
        String column = this.sequenceStartValueColumn();
        return column != null ? resultSet.getLong(column) : null;
    }

    @Override
    protected String sequenceStartValueColumn() {
        return "minimum_value";
    }
}
