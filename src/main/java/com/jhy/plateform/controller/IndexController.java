package com.jhy.plateform.controller;

import com.github.pagehelper.util.StringUtil;
import com.jhy.plateform.domain.User;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.service.UserService;
import com.jhy.plateform.utils.ConstantUtil;
import com.jhy.plateform.utils.JsonModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "403";
    }

    @RequestMapping(value="index", method= RequestMethod.GET)
    public String index(Model model) throws KPException {
        //主体
		//Subject subject = SecurityUtils.getSubject();

		//身份
		//Object activeUser =  subject.getPrincipal();
		//model.addAttribute("user", activeUser);
		return "index";
	}

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String login() throws KPException {
		return "login";
	}

    @RequestMapping(value="login",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel login(String loginName, String password, HttpServletRequest request){
         JsonModel jsonModel = new JsonModel();
		// shiro在认证过程中出现错误后将异常类路径通过request返回
		/*String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
		    jsonModel.setSuccess(false);
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			    jsonModel.setMsg("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			    jsonModel.setMsg("用户名/密码错误");
			} else if("randomCodeError".equals(exceptionClassName)){
			    jsonModel.setMsg("验证码错误");
			} else{
				 jsonModel.setMsg("未知错误");
			}
		}else{
			Subject subject = SecurityUtils.getSubject();
			String sessionId = (String)subject.getSession().getId();
		    jsonModel.setSuccess(true);
		    jsonModel.setData(sessionId);
		    jsonModel.setMsg("登录成功");
        }*/

        boolean sign = StringUtil.isEmpty(loginName);
        if(sign){
            jsonModel.setSuccess(false);
            jsonModel.setMsg("账号不能为空");
            return jsonModel;
        }else{
            sign = StringUtil.isEmpty(password);
            if(sign){
                jsonModel.setSuccess(false);
                jsonModel.setMsg("密码不能为空");
                return jsonModel;
            }
        }

        User user = userService.login(loginName,password);
        HttpSession session = request.getSession();
        if(user==null){
            jsonModel.setSuccess(false);
            jsonModel.setMsg("账号或密码错误");
        }else{
            session.setAttribute(ConstantUtil.SESSION_KEY,user);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("登录成功");
        }
        return jsonModel;
    }


    //由于使用shiro的sessionManager，不用开发退出功能，使用shiro的logout拦截器即可
    //logout.action = logout

    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

    /*@PostMapping("/logout")
	public void logout(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();

		String sessionId = (String)session.getId();
		JedisShardInfo shardInfo = new JedisShardInfo("redis://192.168.1.234:6379");
		shardInfo.setPassword("123456");
		Jedis jedis = new Jedis(shardInfo);
		long jedis_key = jedis.del("shiro:session:"+sessionId);
	}*/
 }
