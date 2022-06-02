package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDEmoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;
    //ObjectProvider 를 사용함으로서 필요시 MyLogger를 찾을 수 있는 (lookup) 기능이 주입이 됨.

    @RequestMapping("log-demo")
    @ResponseBody  //뷰 화면 없이 문자로 바로 반환 받기 위해 사용
    public String logDemo(HttpServletRequest request)  {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDEmoService.logic("testId");
        return "ok";
    }
}
