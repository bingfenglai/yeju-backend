package pers.lbf.yeju.provider.message.notice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.Notice;

/**
 * 系统通知（推送）公告表,记录系统的公告信息。仅保留公告有效期内的公告，超过有效期的公告搬历史表(TableSystemNotice)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-02-17 21:25:15
 */
public interface INoticeDao extends BaseMapper<Notice> {

}