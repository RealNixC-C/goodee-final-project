package com.goodee.finals.common.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.goodee.finals.staff.StaffDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String rememberId = request.getParameter("rememberId");
		HttpSession session = request.getSession();
		
		if (rememberId != null) {
			Cookie cookie = new Cookie("rememberId", request.getParameter("staffCode"));
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 365);
			cookie.setHttpOnly(true);
			
			response.addCookie(cookie);
		} 
		
		if (rememberId == null) {
			Cookie cookie = new Cookie("rememberId", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			cookie.setHttpOnly(true);
			
			response.addCookie(cookie);
		}
		
		session.removeAttribute("loginFailureCount");
		
		StaffDTO user = (StaffDTO) authentication.getPrincipal();
		
		if (passwordEncoder.matches("0000", user.getStaffPw())) {
			response.sendRedirect("/staff/password/change");
		} else {
			request.setAttribute("resultMsg", user.getStaffName() + "님 환영합니다!");
			request.setAttribute("resultIcon", "success");
			request.setAttribute("resultUrl", "/");
			
			request.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(request, response);
		}
	}

}
