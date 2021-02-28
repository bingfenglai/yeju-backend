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
package pers.lbf.yeju.service.interfaces.job.pojo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/28 14:57
 */
public class JobDetailsBean extends JobInfoBean implements Serializable {

    private List<JobTriggerInfoBean> triggerInfoBeanList = new LinkedList<>();

    public void addTrigger(JobTriggerInfoBean trigger) {
        this.triggerInfoBeanList.add(trigger);
    }

    public List<JobTriggerInfoBean> getTriggerInfoBeanList() {
        return triggerInfoBeanList;
    }

    public void setTriggerInfoBeanList(List<JobTriggerInfoBean> triggerInfoBeanList) {
        this.triggerInfoBeanList = triggerInfoBeanList;
    }
}
