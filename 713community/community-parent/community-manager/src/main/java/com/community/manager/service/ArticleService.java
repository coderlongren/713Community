package com.community.manager.service;

import com.community.manager.entity.Article;
import com.community.manager.vo.PageResultVo;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/14 19:44.
 */
public interface ArticleService {

    /**
     * 分页查询文章
     * @param page
     * @return
     */
    PageResultVo listAllArticleByPage(Integer page);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Article getArticleById(Long id);

    /**
     * 更新文章
     * @param id
     * @param article
     * @return
     */
    Boolean updateArticleById(Long id,Article article);

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    Boolean removeArticleById(Long id);

    /**
     * 插入文章
     * @param article
     * @return
     */
    Boolean insertArticle(Article article);

    /**
     * 搜索文章
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    PageResultVo listArticleWithSearch(String searchVal,Integer page,Integer rows);
}
