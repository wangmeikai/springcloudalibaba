package com.wmk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 13:32
 **/
@Repository
public interface OrderMapper {
    int addOrderCount();
}
