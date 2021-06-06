package com.example.template.util;

import com.example.template.exceptions.BadRequestException;

public interface Constants {
    /**
     * Default Pagination Page Number
     */
    public String DEFAULT_PAGE_NUMBER = "0";


    /**
     * Default Pagination Page Size
     */
    public String DEFAULT_PAGE_SIZE = "100";


    /**
     * Maximum Page Size
     */
    public int MAX_PAGE_SIZE = 1000;


    /**
     * Validate Request Page Number and Page Size
     * @param pageNumber Page Number
     * @param pageSize Page Size
     */
    public static void validatePageNumberAndSize(int pageNumber, int pageSize) {
        if (pageNumber < 0) {
            throw new BadRequestException("Page number is less than zero.");
        }

        if (pageSize > Constants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size is greater than " + Constants.MAX_PAGE_SIZE);
        }
    }
}