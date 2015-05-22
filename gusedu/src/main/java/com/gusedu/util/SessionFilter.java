package com.gusedu.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gusedu.model.Usuario;

public class SessionFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String urlSolicitada = httpRequest.getRequestURI();
		System.out.println("urlSolicitada: " + urlSolicitada);
		Usuario user = (Usuario) httpRequest.getSession().getAttribute("userLogged");
		if (user == null) {
			if (urlSolicitada.contains("mobile") || urlSolicitada.contains("web") || urlSolicitada.contains("seguridad")) {
				httpResponse.sendRedirect("/gusedu/home.jsf");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		} else {
			if(urlSolicitada.contains("seguridad") )
			{
				if(user.getUsuTipoUsuario().getIdTipoUsuario()!=3)
				{
					httpResponse.sendRedirect("/gusedu/web/Principal.jsf");
					return;
				}
				
			}
				chain.doFilter(request, response);
				return;
			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
