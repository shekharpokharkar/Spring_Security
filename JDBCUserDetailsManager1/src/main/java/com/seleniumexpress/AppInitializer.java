package com.seleniumexpress;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

		@Override
		protected Class<?>[] getRootConfigClasses() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected Class<?>[] getServletConfigClasses() {
			
			
			return new Class[] {AppConfig.class};
		}

		@Override
		protected String[] getServletMappings() {
			return new String [] {"/"};
		}

}
