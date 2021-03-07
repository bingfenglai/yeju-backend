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
package pers.lbf.yeju.provider.message.group.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import pers.lbf.yeju.common.core.constant.StatusConstants;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;
import pers.lbf.yeju.common.core.result.SimpleResult;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;
import pers.lbf.yeju.common.domain.entity.MessageGroup;
import pers.lbf.yeju.provider.message.group.dao.IMessageGroupDao;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;
import pers.lbf.yeju.service.interfaces.message.IMessageGroupService;
import pers.lbf.yeju.service.interfaces.message.pojo.SystemMessageGroupInfoBean;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/6 13:45
 */
@DubboService(interfaceClass = IMessageGroupService.class)
@Slf4j
public class MessageGroupServiceImpl implements IMessageGroupService {

    @Autowired
    private IMessageGroupDao messageGroupDao;

    @DubboReference
    private IAccountService accountService;

    @Override
    @Cacheable(cacheNames = "messageGroup", keyGenerator = "yejuKeyGenerator")
    public IResult<List<SystemMessageGroupInfoBean>> findAllSystemMessageGroupAndId() throws ServiceException {

        QueryWrapper<MessageGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("group_id", "group_name");
        queryWrapper.eq("group_status", StatusConstants.ABLE);
        queryWrapper.eq("group_type", "1");

        List<MessageGroup> messageGroups = messageGroupDao.selectList(queryWrapper);
        List<SystemMessageGroupInfoBean> result = new LinkedList<>();

        for (MessageGroup messageGroup : messageGroups) {
            SystemMessageGroupInfoBean bean = messageGroupToBean(messageGroup);

            result.add(bean);
        }

        return Result.ok(result);
    }

    /**
     * 检验当前账户是否在消息的sendTO消息组内
     *
     * @param groupId
     * @param receiver
     * @return pers.lbf.yeju.common.core.result.IResult<java.lang.Object>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/6 17:24
     */
    @Override
    @Cacheable(cacheNames = "messageGroup", keyGenerator = "yejuKeyGenerator")
    public IResult<Object> receiverExistIn(Long groupId, String receiver) throws ServiceException {
        Long accountId = accountService.findAccountIdByPrincipal(receiver).getData();
        Integer count = messageGroupDao.receiverExistIn(groupId, accountId);
        if (count == 0) {
            return SimpleResult.failed(ServiceStatusEnum.no_data_has_been_found);
        }
        return SimpleResult.ok();

    }

    @Cacheable(cacheNames = "messageGroup::list", key = "#principal")
    @Override
    public IResult<List<Long>> findSystemMessageGroupByPrincipal(String principal) throws ServiceException {
        Long accountId = accountService.findAccountIdByPrincipal(principal).getData();

        List<Long> groupIdList = messageGroupDao.findSystemMessageGroupByPrincipal(accountId);
        log.info("群组 {}", groupIdList);
        return Result.ok(groupIdList);
    }


    private SystemMessageGroupInfoBean messageGroupToBean(MessageGroup messageGroup) {
        SystemMessageGroupInfoBean systemMessageGroupInfoBean = new SystemMessageGroupInfoBean();
        systemMessageGroupInfoBean.setGroupId(messageGroup.getGroupId());
        systemMessageGroupInfoBean.setGroupName(messageGroup.getGroupName());
        systemMessageGroupInfoBean.setGroupStatus(messageGroup.getGroupStatus());
        systemMessageGroupInfoBean.setGroupType(messageGroup.getGroupType());
        systemMessageGroupInfoBean.setRemark(messageGroup.getRemark());
        return systemMessageGroupInfoBean;

    }
}