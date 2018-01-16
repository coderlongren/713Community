package com.community.manager.service.impl;

import com.community.manager.dao.ArticleDao;
import com.community.manager.entity.Article;
import com.community.manager.service.ArticleService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author:chenfq
 * @Description:
 * @Date:2017/11/14 19:49.
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleDao articleDao;

    /**
     * 分页查询文章列表
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllArticleByPage(Integer page) {
        if (page <= 0 || null == page) {
            page = 1;
        }
        int rows = 10;

        /*
         * page:表示下一页
         * rows:表示每一页显示的记录数
         */
        PageHelper.startPage(page, rows);
        List<Article> list = articleDao.listAll();

        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */

    @Override
    public Article getArticleById(Long id) {
        return articleDao.getById(id);
    }

    /**
     * 根据id更新文章
     * @param id
     * @param article
     * @return
     */
    @Override
    public Boolean updateArticleById(Long id, Article article) {
        article.setUpdateTime(new Date());
        article.setId(id);
        return articleDao.update(article)==1;
    }

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @Override
    public Boolean removeArticleById(Long id) {
        return articleDao.deleteById(id)==1;
    }

    /**
     * 插入文章
     * @param article
     * @return
     */
    @Override
    public Boolean insertArticle(Article article) {
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        return articleDao.insert(article)==1;
    }

    /**
     * 搜索文章
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageResultVo listArticleWithSearch(String searchVal, Integer page, Integer rows) {
        if(page<=0||null==page){
            page=1;
        }
        PageHelper.startPage(page,rows);

        String val = new StringBuffer("%").append(searchVal).append("%").toString();
        List<Article> list = articleDao.listArticleWithSearch(val);
        PageInfo<Article> pageInfo = new PageInfo<>(list);

        return new PageResultVo(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPages());
    }
}
