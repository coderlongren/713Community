package com.community.manager.dao;


import java.util.List;

/**
 * @Author: xian
 * @Description: DAO基类
 * @Date:create in 2017/10/25 15:43
 */
public interface BaseDao<T> {

    /**
     * 新增数据
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> listAll();

    /**
     * 更新
     *
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    Integer deleteById(Long id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据id更新
     *
     * @param id
     * @return
     */
    Integer updateById(Long id);
}
