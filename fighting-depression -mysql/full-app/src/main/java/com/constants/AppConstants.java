package com.constants;

import java.util.*;

public class AppConstants {

    public static final String CELL_ID = "cellId";

    public static final String HEADER = "header";
    public static final String MISMATCH_CEll_ID = "missMatchCellId";
    public static final String XLS_VALUE = "xlsValue";
    public static final String DB_VALUE = "dbValue";

    public static final String EXTRA_IN_FILE= "extraInFile";
    public static final String EXTRA_IN_DB = "extraInDb";


    public static final List<String> RAT_LIST = new ArrayList<>(Arrays.asList("2G", "3G", "4G"));

    public static final Map<String,Integer> compareRatTypeMap = new HashMap<String,Integer>() {{
        put("2G",2);
        put("3G",3);
        put("4G",4);
    }};


    AppConstants(){

    }
}
