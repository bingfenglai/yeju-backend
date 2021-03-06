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
package pers.lbf.yeju.consumer.message.notice;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.yeju.common.core.args.BaseFindPageArgs;
import pers.lbf.yeju.common.core.constant.DataDictionaryTypeConstant;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.util.DateConvertUtil;
import pers.lbf.yeju.consumer.base.util.SubjectHelper;
import pers.lbf.yeju.service.interfaces.dictionary.IDataDictionaryInfoService;
import pers.lbf.yeju.service.interfaces.dictionary.pojo.SimpleLabelAndValueBean;
import pers.lbf.yeju.service.interfaces.message.INoticeService;
import pers.lbf.yeju.service.interfaces.message.pojo.NoticeCreateArgs;
import pers.lbf.yeju.service.interfaces.message.pojo.SimpleNoticeInfoBean;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/17 21:30
 */
@RestController
@RequestMapping("/message/notice")
@Slf4j
public class NoticeController {
    @DubboReference
    private INoticeService noticeService;

    @DubboReference
    private IDataDictionaryInfoService dictionaryInfoService;

    @ApiOperation(value = "获取通知列表 分页", notes = "通知列表说明", httpMethod = "GET")
    @GetMapping("/list/{currentPage}")
    public Mono<PageResult<SimpleNoticeInfoBean>> findPage(
            @Validated @NotNull(message = "每页显示条数") @ApiParam("当前页") @PathVariable Long currentPage,
            @Validated @NotNull(message = "每页显示大小不能为空") @ApiParam("每页显示条数") @RequestParam Long size
    ) throws ServiceException {
        BaseFindPageArgs args = new BaseFindPageArgs();
        args.setCurrentPage(currentPage);
        args.setSize(size);

        PageResult<SimpleNoticeInfoBean> pageResult = noticeService.findPage(args.getCurrentPage(), args.getSize());
        if (pageResult.getList().size() > 0) {
            Map<String, String> noticeTypeMap = dictionaryInfoService.getDictMap("notice_type").getData();
            Map<String, String> statusMap = dictionaryInfoService.getDictMap(DataDictionaryTypeConstant.NOTICE_STATUS).getData();
            for (SimpleNoticeInfoBean bean : pageResult.getList()) {
                if (bean.getStatus() != null) {
                    bean.setStatusStr(statusMap.get(String.valueOf(bean.getStatus())));
                }

                if (bean.getNoticeType() != null) {
                    bean.setNoticeTypeStr(noticeTypeMap.get(bean.getNoticeType()));
                }
            }
        }


        return Mono.just(pageResult);
    }


    @ApiOperation(value = "获取NoticeType", notes = "NoticeType说明", httpMethod = "GET")
    @GetMapping("/type/list")
    public Mono<IResult<List<SimpleLabelAndValueBean>>> getNoticeTypeInfo() throws ServiceException {
        IResult<List<SimpleLabelAndValueBean>> result = dictionaryInfoService.findLabelAndValueByType(DataDictionaryTypeConstant.NOTICE_TYPE);
        return Mono.just(result);
    }

    /**
     * ！！！！注意：由于json序列化统一设置了驼峰命名法转下划线，
     * 因此前端参数 notice_title 转后端 noticeTitle
     * 注意前后端参数命名
     *
     * @param args
     * @return reactor.core.publisher.Mono<pers.lbf.yeju.common.core.result.IResult < java.lang.Object>>
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @version 1.0
     * @date 2021/3/4 15:54
     */
    @ApiOperation(value = "新增通知", notes = "新增通知说明", httpMethod = "POST")
    @PostMapping("/create")
    public Mono<IResult<Object>> create(@RequestBody @Validated NoticeCreateArgs args, ServerWebExchange webExchange) throws Exception {
        String[] dateRange = args.getDateRange();
        args.setStartTime(DateConvertUtil.stringToDate(dateRange[0]));
        args.setEndTime(DateConvertUtil.stringToDate(dateRange[1]));
        args.setCreateTime(new Date());
        String account = SubjectHelper.getSubjectAccount(webExchange);
        args.setCreateBy(account);
        return Mono.just(noticeService.create(args));
    }


}
