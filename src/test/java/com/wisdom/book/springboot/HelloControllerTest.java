package com.wisdom.book.springboot;

import com.wisdom.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;



@RunWith(SpringRunner.class)    // JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킨다.
@WebMvcTest(controllers = HelloController.class)    // Web에 집중할수 있는 어노테이션이다.
public class HelloControllerTest {

    @Autowired  // Spring이 관리하는 빈을 주입 받는다.
    private MockMvc mvc;    //웹 API 테스트 시 사용한다. Spring MVC의 시작점이며, 이 클래스를 통해 HTTP GET, POST에 대한 테스트를 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청한다.
        // HTTP Header의 Status를 검증한다.(200, 400, 500, 200이면 Ok)
        // mvc.perform의 결과를 검증한다.
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        // jsonPath는 JSON 응답값을 필드별로 검증할 수 있는 메소드이다.
        // $를 기준으로 필드명을 명시한다
        mvc.perform(
                get("/hello/dto").param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}