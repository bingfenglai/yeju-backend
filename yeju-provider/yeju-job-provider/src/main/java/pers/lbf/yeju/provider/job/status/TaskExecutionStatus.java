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
package pers.lbf.yeju.provider.job.status;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/23 14:42
 */
public enum TaskExecutionStatus implements IStatus {
    /**
     * 定时任务执行失败
     */
    UNKNOWN("定时任务执行失败", "TES01"),

    /**
     * 获取调度程序失败
     */
    FailedToGetScheduler("获取调度程序失败", "TES02"),

    /**
     * 创建定时任务失败
     */
    FailedToCreateAScheduledTask("创建定时任务失败", "TES03"),

    /**
     * 启动定时任务失败
     */
    FailedToStartTheScheduledTask("启动定时任务失败", "TES04"),

    /**
     * 获取触发器失败
     */
    FailedToGetTrigger("获取触发器失败", "TES05"),

    FailedToGetJobDetail("获取任务实例失败", "TES06"),

    FailedToDeleteJob("删除定时任务失败", "TES07"),

    FailedToPauseJob("停止任务失败", "TES08"),

    FailedUnschedulerJob("停止调度任务失败", "TES09"),

    FailedToShutdownTheScheduledTask("停止所有任务失败", "TES10"),

    FailedToRestoreJob("恢复任务失败", "TES11"),

    FailedToPauseAllJob("暂停所有任务出错", "TES12"),

    FailedToRestoreAllJob("恢复所有任务出错", "TES13"),

    jobInstanceDoesNotExist("定时任务不存在", "TES14");

    private String message;
    private String code;

    TaskExecutionStatus(String message, String code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }
}
