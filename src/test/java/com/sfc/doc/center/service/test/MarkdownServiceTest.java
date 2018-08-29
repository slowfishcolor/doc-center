package com.sfc.doc.center.service.test;

import com.sfc.doc.center.service.MarkdownService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarkdownServiceTest {

    @Autowired
    MarkdownService markdownService;

    @Test
    public void markdownToHtmlTest() {
        String md = "# Summary\n" +
                "\n" +
                "* [API网关](articles/apigateway/)\n" +
                "  * [产品介绍](articles/apigateway/1-/)\n" +
                "     * [产品概述](articles/apigateway/1-/Introduction.md)\n" +
                "     * [产品特性](articles/apigateway/1-/ProducFteatures.md)\n" +
                "  * [版本说明](articles/apigateway/2-/)\n" +
                "     * [版本说明](articles/apigateway/2-/version.md)\n" +
                "  * [安装与配置](articles/apigateway/3-/)\n" +
                "     * [部署手册](articles/apigateway/3-/deploy_guide.md)\n" +
                "  * [快速入门](articles/apigateway/4-/)\n" +
                "     * [快速入门--开放API](articles/apigateway/4-/openAPI.md)\n" +
                "     * [快速入门--调用API](articles/apigateway/4-/invokeAPI.md)\n" +
                "  * [使用手册](articles/apigateway/5-/)\n" +
                "    * [概述](articles/apigateway/5-/summary.md)\n" +
                "    * [使用 API 前准备](articles/apigateway/5-/preparation.md)\n" +
                "    * [使用者门户](articles/apigateway/5-/user_gateway.md)\n" +
                "    * [API 开发者](articles/apigateway/5-/developer_gateway.md)\n" +
                "    * [管理员门户](articles/apigateway/5-/admin_gateway.md)\n" +
                "    * [RPC 服务支持参数类型说明](articles/apigateway/5-/rpc_params_detail.md)";
        String html = markdownService.markdownToHtml(md);
        System.out.printf(html);
    }
}
