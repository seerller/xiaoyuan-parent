package com.xiaoyuan.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xiaoyuan.model.Message;
import org.springframework.stereotype.Service;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
@Service
public class MessageService implements IMessageService {
    @Override
    public int Send(Message Message) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIXhgPb7yRqNHt", "zj47VVwK1Tdxd5lKFri27XVjLCoBmh");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", Message.getPhoneNumbers());
        request.putQueryParameter("SignName", "娄底高安环保科技有限公司");
        request.putQueryParameter("TemplateCode", Message.getTemplateCode());//sms_123123
        request.putQueryParameter("TemplateParam", Message.getTemplateParam());//{\"code\":\"1231\"}"
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return 1;
        } catch (ServerException e) {
            e.printStackTrace();
            return 0;
        } catch (ClientException e) {
            e.printStackTrace();
            return 0;
        }
    }
}