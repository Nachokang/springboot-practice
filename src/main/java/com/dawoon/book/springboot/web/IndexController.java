package com.dawoon.book.springboot.web;

import com.dawoon.book.springboot.config.auth.LoginUser;
import com.dawoon.book.springboot.config.auth.dto.SessionUser;
import com.dawoon.book.springboot.service.posts.PostsService;
import com.dawoon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // 원래 httpSession.getAttribute("user")로 가져오던 세션 정보 값을 개선
        // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 됨
        model.addAttribute("posts", postsService.findAllDesc());
        if(user!=null){ // 세션에 저장된 값이 있으면 model에 userName으로 등록, 없으면 로그인 버튼이 보임
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
