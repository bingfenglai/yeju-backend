/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.provider.platform.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.Post;
import pers.lbf.yeju.provider.base.util.PageUtil;
import pers.lbf.yeju.provider.platform.post.dao.IPostDao;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.platfrom.pojo.SimplePostInfoBean;
import pers.lbf.yeju.service.interfaces.platfrom.post.IPostService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 职位服务实现类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 0:59
 */
@DubboService(interfaceClass = IPostService.class)
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostDao postDao;

    @DubboReference
    private IDataDictionaryInfoService dictionaryInfoService;

    @Override
    @Cacheable(cacheNames = "postService:infoBean", key = "#id")
    public IResult<SimplePostInfoBean> findPostById(Long id) throws ServiceException {
        Post post = postDao.selectById(id);
        if (post != null) {
            SimplePostInfoBean bean = this.postToSimpleInfoBean(post);
            bean.setPostStatusStr(getPostStatusStr(bean.getPostStatus()));
            return Result.ok(bean);
        }
        throw ServiceException.getInstance(ServiceStatusEnum.no_data_has_been_found);
    }

    @Override
    @Cacheable(cacheNames = "postService:list", keyGenerator = "yejuKeyGenerator")
    public PageResult<SimplePostInfoBean> findPage(Long currentPage, Long size) throws ServiceException {
        Page<Post> page = PageUtil.getPage(Post.class, currentPage, size);
        Page<Post> postPage = postDao.selectPage(page, null);
        List<Post> postList = postPage.getRecords();
        List<SimplePostInfoBean> result = new LinkedList<>();
        IResult<Map<String, String>> mapResult = dictionaryInfoService.getDictMap("post_status");
        Map<String, String> map = mapResult.getData();
        for (Post post : postList) {
            SimplePostInfoBean bean = postToSimpleInfoBean(post);
            bean.setPostStatusStr(map.get(bean.getPostStatus()));
            result.add(bean);
        }
        return PageResult.ok(postPage.getTotal(), currentPage, size, result);
    }


    @Override
    @CacheEvict(cacheNames = "postService:list", allEntries = true)
    public IResult<Object> addPost(SimplePostInfoBean bean) throws ServiceException {
        Post post = this.simpleBeanToPost(bean);
        int insert = postDao.insert(post);
        if (insert == 1) {
            return SimpleResult.ok();
        }
        throw new ServiceException();
    }

    @Override
    @CacheEvict(cacheNames = {"postService:list", "postService:infoBean"}, allEntries = true)
    public IResult<Object> updatePost(SimplePostInfoBean bean) throws ServiceException {
        return null;
    }

    private SimplePostInfoBean postToSimpleInfoBean(Post post) {
        SimplePostInfoBean simplePostInfoBean = new SimplePostInfoBean();
        simplePostInfoBean.setPostName(post.getPostName());
        simplePostInfoBean.setPostCode(post.getPostCode());
        simplePostInfoBean.setRemark(post.getRemark());
        simplePostInfoBean.setPostStatus(post.getPostStatus());
        simplePostInfoBean.setCreateTime(post.getCreateTime());
        return simplePostInfoBean;

    }

    private String getPostStatusStr(String postStatus) {
        IResult<Map<String, String>> result = dictionaryInfoService.getDictMap("post_status");
        Map<String, String> data = result.getData();
        return data.get(postStatus);
    }


    private Post simpleBeanToPost(SimplePostInfoBean bean) {
        Post post = new Post();
        post.setPostName(bean.getPostName());
        post.setPostCode(bean.getPostCode());
        post.setRemark(bean.getRemark());
        post.setPostStatus(bean.getPostStatus());
        return post;
    }
}
