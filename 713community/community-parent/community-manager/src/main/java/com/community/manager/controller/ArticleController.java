package com.community.manager.controller;

import com.community.manager.entity.Article;
import com.community.manager.service.ArticleService;
import com.community.manager.vo.PageResultVo;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:chenfq
 * @Description: 文章相关的controller层
 * @Date:2017/11/14 19:36.
 */

@RequestMapping("article")
@Controller
public class ArticleController {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询文章信息
     *
     * @param page
     * @return
     */

    @GetMapping("page/{next}")
    public ResponseEntity<PageResultVo> viewAllArticleByPage(@PathVariable("next") Integer page) {

        boolean server_error_flag = false;

        PageResultVo pageResultVo = null;

        try {
            pageResultVo = articleService.listAllArticleByPage(page);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("分页查询文章失败！程序出错！--------------------------> methodName : ArticleController.viewAllArticleByPage");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == pageResultVo || null == pageResultVo.getRows()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(pageResultVo);
    }

    /**
     * 根据id查询文章
     *
     * @param articleId
     * @return
     */
    @GetMapping("{articleId}")
    public ResponseEntity<Article> viewResourceTypeById(@PathVariable("articleId") Long articleId){
        boolean server_error_flag = false;
        //定义对象
        Article article=null;
        try {
            article = articleService.getArticleById(articleId);
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("查询资源类别失败！程序出错！--------------------------> methodName : ResourceTypeController.viewResourceTypeById");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (null == article) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //200
        return ResponseEntity.ok(article);

    }

    /**
     * 更新资源类别
     *
     * @param articleId
     * @param article
     * @return
     */
    @PutMapping("{articleId}")
    public ResponseEntity<Void> updateArticle(@PathVariable("articleId") Long articleId,Article article){
        boolean server_error_flag = false;

        boolean result = false;
        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新文章类别，articleId = {}, article = {}",articleId, article);
            }

            result = articleService.updateArticleById(articleId, article);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("更新资源类别成功，resourceTypeId = {}, resourceType = {}",articleId, article);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("更新资源类别失败！程序出错！--------------------------> methodName : ResourceTypeController.updateResourceType");
        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //404
        if (!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        //204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 根据id删除文章
     *
     * @param articleId
     * @return
     */
    @DeleteMapping("{articleId}")
    public ResponseEntity<Void> removeArticleById(@PathVariable("articleId") Long articleId){
        boolean server_error_flag = false;
        boolean result = false;

        try {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除文章，articleId = {}",articleId);
            }

            result = articleService.removeArticleById(articleId);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("删除文章成功，articleId = {}",articleId);
            }
        } catch (Exception e) {
            server_error_flag = true;
            LOGGER.error("删除文章失败！程序出错！----------------------------> methodName : ArticleController.articleId");

        }

        //500
        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //500
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        //200
        return ResponseEntity.ok(null);
    }

    /**
     * 增加文章
     *
     * @param article
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> addArticle(Article article){

        boolean server_error_flag = false;
        boolean result = false;

        try {
            //对于新增，更新，删除类的操作(这些都属于改变表数据的操作)，需要在改变之前打日志信息，格式如下
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增文章，Article = {}", article);
            }

            result = articleService.insertArticle(article);

            //在改变之后也需要打印日志信息
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("新增文章成功，Article = {}", article);
            }
        } catch (Exception e) {
            server_error_flag = true;
            // 错误日志尽在catch里面打
            LOGGER.error("新增文章失败，程序出错！-------------------------> methodName : ArticleController.addArticle");
        }

        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if (server_error_flag) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据用户名模糊查询文章内容
     * @param searchVal
     * @param page
     * @param rows
     * @return
     */

    @GetMapping("param/page/{next}")
    public ResponseEntity<PageResultVo> viewArticleWithSearch(@RequestParam("searchVal") String searchVal,
                                                           @PathVariable("next") Integer page,
                                                           @RequestParam(value = "rows",defaultValue = "10") Integer rows){
        Boolean server_error_falg = false ;
        if(StringUtil.isEmpty(searchVal)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        PageResultVo pageResultVo = null;

        try {
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("根据用户名称模糊文章----------------------->method:ArticleController.viewArticleWithSearch");
            }
            pageResultVo = articleService.listArticleWithSearch(searchVal,page,rows);
            if(LOGGER.isInfoEnabled()){
                LOGGER.info("根据用户名模糊查询文章成功------------------->method:ArticleController.viewArticleWithSearch");
            }

        }catch (Exception e){
            server_error_falg = true;
            LOGGER.error("根据用户名模糊查询文章失败---------------------------》method：UserController.viewUserById");
        }

        //500
        if(server_error_falg){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        //404
        if(null == pageResultVo || pageResultVo.getRows().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(pageResultVo);

    }

}
