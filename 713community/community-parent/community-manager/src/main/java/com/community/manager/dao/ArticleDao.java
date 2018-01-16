package com.community.manager.dao;

import com.community.manager.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/14 19:55.
 */
public interface ArticleDao extends BaseDao<Article> {

    List<Article> listArticleWithSearch(@Param("searchVal") String searchVal);
}
