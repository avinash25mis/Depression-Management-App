package com.configuration;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorLegacyImpl;
import org.hibernate.tool.schema.extract.spi.SequenceInformationExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppDialect extends PostgreSQL9Dialect {


    @Override
    public SequenceInformationExtractor getSequenceInformationExtractor() {
        return AppSequenceInformationExtractor.INSTANCE;
    }





}
