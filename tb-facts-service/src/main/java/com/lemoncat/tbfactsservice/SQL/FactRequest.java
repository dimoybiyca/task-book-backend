package com.lemoncat.tbfactsservice.SQL;

public class FactRequest {

    public static final String getNumberOfRows = "SELECT count(*) FROM facts";

    public static final String getFactByIdQuery = "SELECT text FROM facts WHERE id = ?";
}
