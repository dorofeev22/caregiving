package ru.dorofeev22.caregiving.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BunchPageable implements Pageable {

    private int limit = 0;
    private int offset = 0;
    private Sort sort;

    public BunchPageable(int skip, int offset) {
        if (skip < 0)
            throw new IllegalArgumentException("Skip must not be less than zero!");

        if (offset < 0)
            throw new IllegalArgumentException("Offset must not be less than zero!");

        this.limit = offset;
        this.offset = skip;
        this.sort = Sort.unsorted();
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return this;
    }

    @Override
    public Pageable first() {
        return this;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }}
