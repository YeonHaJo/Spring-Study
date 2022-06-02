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
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody  //뷰 화면 없이 문자로 바로 반환 받기 위해 사용
    public String logDemo(HttpServletRequest request)  {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger="  + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDEmoService.logic("testId");
        return "ok";
    }
}
