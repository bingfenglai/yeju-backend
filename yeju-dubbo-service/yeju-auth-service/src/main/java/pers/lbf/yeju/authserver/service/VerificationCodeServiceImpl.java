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
package pers.lbf.yeju.authserver.service;

import org.apache.dubbo.config.annotation.DubboService;
import pers.lbf.yeju.authserver.enums.VerificationCodeTypeEnum;
import pers.lbf.yeju.authserver.pojo.dto.VerityDTO;
import pers.lbf.yeju.authserver.service.interfaces.IVerificationCodeService;
import pers.lbf.yeju.common.core.exception.RpcServiceException;
import pers.lbf.yeju.common.core.result.IResult;

/**验证码服务
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/14 22:42
 */
@DubboService(interfaceClass = IVerificationCodeService.class)
public class VerificationCodeServiceImpl implements IVerificationCodeService {
    @Override
    public <T> IResult<VerityDTO<T>> getVerificationCode(VerificationCodeTypeEnum type) throws RpcServiceException {
        return null;
    }

    @Override
    public IResult<Object> verify(String key, String code) throws RpcServiceException {
        return null;
    }
}
