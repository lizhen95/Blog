package com.myblog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.entity.Blogger;
import com.myblog.service.BloggerService;
import com.myblog.util.CryptographyUtil;

/**
 * 博主Controller层
 * @author lizhen
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
	/**
	 * 用户登录
	 * @param blogger
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "lizhen1995"));
		try{
			subject.login(token); // 登录验证
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误！");
			return "login";
		}
	}
	
	/**
	 * 查找博主信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("blogger",bloggerService.find());
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.addObject("pageTitle","关于博主_lizhen博客");
		mav.setViewName("mainTemp");
		return mav;
	}
}
