package com.community.resource.service.impl;

import com.community.resource.dao.*;
import com.community.resource.entity.*;
import com.community.resource.service.ResourceListService;
import com.community.resource.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:王喜
 * @Description :资源列表接口实现类
 * @Date: 2017/12/19 0019 18:19
 */

@Service
public class ResourceListServiceImpl implements ResourceListService{

    public static final Logger LOGGER = LoggerFactory.getLogger(ResourceListServiceImpl.class);

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    //设置几个全局变量
    User user = null;

    @Autowired
    private ResourceListDao resourceListDao;

    @Autowired
    private IndexDao indexDao;

    @Autowired
    private ResourceCommentDao resourceCommentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceInfoDao resourceInfoDao;

    @Autowired
    private ResourceInformationDao resourceInformationDao;

    @Value("${RESOURCE_UPLOAD_PATH}")
    private String RESOURCE_UPLOAD_PATH;

    @Value("${RESOURCE_BASE_URL}")
    private String RESOURCE_BASE_URL;

    /**
     * 根据id查询资源列表首部
     *
     * @param resourceTypeId
     * @return
     * @throws IOException
     */
    @Override
    public ResourceListTopVo listResourceListTopInfoById(Long resourceTypeId) throws IOException {
        ResourceType resourceType = indexDao.getById(resourceTypeId);
        //得到资源类别的名字
        String resourceTypeName = resourceType.getName();
        //处理每天的资源量以及排名

        //获取今日的该资源总数
        Integer todayTotal = countTodayResourceTotal(resourceTypeId);
        //获取昨天的该资源总数
        Integer yesterdayTotal = countYesterdayResourceTotal(resourceTypeId);
        //计算出资源总数的波动情况(0-->下降，1-->表示上升)
        Integer totalFlag = 1;
        if (todayTotal - yesterdayTotal < 0) {
            totalFlag = 0;
        }
        //计算出该类别资源的在总资源的排名
        Integer todayRank = rankTodayResourceType(resourceTypeName);
        ResourceListTopVo resourceListTopVo = new ResourceListTopVo(resourceTypeName,todayTotal,
                totalFlag,todayRank);
        return resourceListTopVo;
    }

    /**
     * 分页查询资源列表信息
     *
     * @param page
     * @return
     */
    @Override
    public PageResultVo listAllResourceByPage(Long resourceTypeId,Integer page) {
        //判断page是否是合法值
        if (page == null || page <= 0) {
            page = 1;
        }
        //定义每页允许显示10条资源信息
        int rowCount = 10;

        PageHelper.startPage(page, rowCount);
        List<Resource> list = resourceListDao.listAll(resourceTypeId);

        //遍历每个list元素，根据resourceId关联查出对应资源的评论数，然后放到Resource中
        for (Resource resource : list) {
            Integer countComment = resourceCommentDao.countResourceComment(resource.getId());
            resource.setResourceComment(countComment);
        }
        PageInfo<Resource> pageInfo = new PageInfo<>(list);
        return new PageResultVo(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPages());
    }

    /**
     * 上传资源信息
     *
     * @param file
     * @return
     */
    @Override
    public Boolean uploadZipFile(MultipartFile file,ResourceUploadVo resourceUploadVo) throws IOException{
        if (file.isEmpty()) {
            return false;
        }
        // 文件新路径(磁盘路径)
        String filePath = getFilePath(file.getOriginalFilename(),"resource");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("resource file upload .[{}] to [{}] .", file.getOriginalFilename(), filePath);
        }
        // 生成图片的绝对引用地址(网址)
        String resourceUrl = StringUtils.replace(StringUtils.substringAfter(filePath, RESOURCE_UPLOAD_PATH),
                "\\", "/");
        String resourceSrc = RESOURCE_BASE_URL + resourceUrl;

        //把网址路径写入数据库resource_info表
//        fileUploadResult.setUrl(resourceSrc);

        //把信息写入数据库
        uploadResourceInfoToDatabase(resourceSrc,resourceUploadVo);

        File newFile = new File(filePath);

        // 写文件到磁盘
        file.transferTo(newFile);

        return true;

    }


    /**
     * 根据session中的用户名查询用户的信息
     *
     * @param username
     * @return
     */
    @Override
    public User findUserInfoByUsername(String username) {
        user = userDao.getUserInfoByUsername(username);
        //获取该用户（楼主）的发布资源总数
        Integer resourceReleaseCount = resourceInfoDao.getResourceReleaseCount(user.getId());
        user.setResourceReleaseCount(resourceReleaseCount);
        return user;
    }

    /**
     * 自定义方法，把上传资源的信息写入数据库相应的表
     *
     * @param resourceSrc
     * @param resourceUploadVo
     */

//    @Autowired
//    HttpServletRequest request;

    public void uploadResourceInfoToDatabase(String resourceSrc,ResourceUploadVo resourceUploadVo) {

//        HttpSession session = request.getSession();
//        Integer browseNum = (Integer) session.getAttribute("browseNum");

        /**
         *首先插入到resource表中并返回主键id,在插入资源详情表
         *设置默认发布资源的用户为wangxi,与sso系统合并之后，根据findUserInfoByUsername获取用户id
         * 资源上传的默认flag=1
         */
        findUserInfoByUsername("wangxi");

        Resource resource = new Resource();
        resource.setUserId(user.getId());
        resource.setTypeId(resourceUploadVo.getResourceTypeId());
        resource.setAuthor(resourceUploadVo.getResourceAuthor());
        resource.setReleaseTime(new Date());
        resource.setBrowseNum(1000);
        resource.setDownNum(1000);
        resource.setTitle(resourceUploadVo.getResourceTitle());
        resource.setShowFlag(1);
        resource.setCreateTime(new Date());
        resource.setUpdateTime(new Date());

        Integer insertResourceResult = resourceListDao.insertResource(resource);

        //插入数据到resource_info
        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setResourceId(resource.getId());
        resourceInfo.setContent(resourceUploadVo.getResourceContent());
        resourceInfo.setUrl(resourceSrc);
        resourceInfo.setCreateTime(new Date());
        resourceInfo.setUpdateTime(new Date());

        Integer insertResourceInfoResult = resourceInformationDao.insertResourceInfo(resourceInfo);
    }


    /**
     * 富文本编辑器的图片上传
     *
     * @param pic
     * @return
     * @throws IOException
     */
    @Override
    public String uploadPicture(MultipartFile pic) throws IOException {

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(pic.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        // 封装Result对象，并且将文件的byte数组放置到result对象中
        PicUploadResultVo fileUploadResult = new PicUploadResultVo();

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        // 文件新路径
        String filePath = getFilePath(pic.getOriginalFilename(),"resourceImage");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pic file upload .[{}] to [{}] .", pic.getOriginalFilename(), filePath);
        }

        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, RESOURCE_UPLOAD_PATH),
                "\\", "/");
        String imgSrc = RESOURCE_BASE_URL + picUrl;
        fileUploadResult.setUrl(imgSrc);


        File newFile = new File(filePath);

        // 写文件到磁盘
        pic.transferTo(newFile);

        // 校验图片是否合法
        isLegal = false;
        try {
            BufferedImage img = ImageIO.read(newFile);
            if (img != null) {
                fileUploadResult.setWidth(img.getWidth() + "");
                fileUploadResult.setHeight(img.getHeight() + "");
                isLegal = true;
            }
        } catch (IOException e) {
        }

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        if (!isLegal) {
            // 不合法，将磁盘上的文件删除
            newFile.delete();
        }

        //返回图片的网址路径
        return imgSrc;
    }




    /**
     * 生成图片、压缩包名称和路径
     *
     * @param sourceFileName
     * @param root  自定义根路径文件夹
     * @return
     */
    //自定义方法
    private String getFilePath(String sourceFileName, String root) {
        String baseFolder = RESOURCE_UPLOAD_PATH + File.separator + root;
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator + new DateTime(nowDate).toString("MM") + File.separator
                + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "." + sourceFileName;
        return fileFolder + File.separator + fileName;
    }




    //自定义方法，统计今天的发布资源总量
    private Integer countTodayResourceTotal(Long resourceTypeId) {
        //获取本地时间 然后进行格式化
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        StringBuilder dateB = new StringBuilder(date);
        String dateResult = dateB.insert(0,"%").append("%").toString();
        Integer todayTotal = resourceListDao.countResourceTotal(dateResult,resourceTypeId);
        return todayTotal;
    }

    //自定义方法，统计昨天的发布资源总量
    private Integer countYesterdayResourceTotal(Long resourceTypeId) {
        //获取本地时间 然后进行格式化
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String dataStr = df.format(date);
        StringBuilder dateB = new StringBuilder(dataStr);
        String dateResult = dateB.insert(0,"%").append("%").toString();
        Integer todayTotal = resourceListDao.countResourceTotal(dateResult,resourceTypeId);
        return todayTotal;
    }
    //自定义方法，计算出今天该类别资源在整个资源的排名
    private Integer rankTodayResourceType(String resourceTypeName) {
        //如果不存该类别资源名，说明没有发布该资源，默认为0
        List<Resource> resources = resourceListDao.countResourceByResourceType();
        List<ResourceCountVo> resourceCountVos = new ArrayList<>();
        for (Resource resource : resources) {
            ResourceCountVo resourceCountVo = new ResourceCountVo(resource.getCount(),resource.getName());
            resourceCountVos.add(resourceCountVo);
        }
        //对每个种类的资源的数量进行排序
        Collections.sort(resourceCountVos, new Comparator<ResourceCountVo>(){
            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
			 * 上述是升序排列
             */
            public int compare(ResourceCountVo r1, ResourceCountVo r2) {

                //按照发布资源总数降序排列
                if(r1.getCount() > r2.getCount()){
                    return -1;
                }
                if(r1.getCount() == r2.getCount()){
                    return 0;
                }
                return 1;
            }
        });

        //根据资源类别名称得到该资源的排名
        Integer result = 0;
        for (int i = 0; i < resourceCountVos.size(); i++) {
            if (resourceCountVos.get(i).getName().equals(resourceTypeName)) {
                result = i;
                break;
            }
        }
        return result + 1;
    }

}
