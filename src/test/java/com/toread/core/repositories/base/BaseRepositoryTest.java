package com.toread.core.repositories.base;

import com.toread.common.query.QueryOp;
import org.junit.Test;

import java.util.Calendar;
import java.util.regex.Matcher;

import static org.junit.Assert.*;

public class BaseRepositoryTest {
    @Test
    public  void testQueryPattern(){
        String value = QueryOp.and.getOpMethod()+"$"+ Calendar.getInstance().getTime();
        System.out.println(value);
        Matcher me =  BaseRepository.QUERY_PATTERN.matcher(value);
        me.matches();
        System.out.println(me.group(1));;
        System.out.println(me.group(2));;
    }
}