package com.urlayasam.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.urlayasam.project.controllers.CommentController;
import com.urlayasam.project.controllers.CompanyController;
import com.urlayasam.project.controllers.EventController;
import com.urlayasam.project.controllers.FavoriteController;
import com.urlayasam.project.controllers.FestivalController;
import com.urlayasam.project.controllers.UserController;
import com.urlayasam.project.models.UserType;
import com.urlayasam.project.requests.UserAddRequest;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "com.urlayasam.project")
public class ProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
		UserController us = context.getBean(UserController.class);
		EventController eventController = context.getBean(EventController.class);
		/*UserController userController = context.getBean(UserController.class);
		EventController eventController = context.getBean(EventController.class);
		CommentController commentController =context.getBean(CommentController.class);
		FavoriteController favoriteController = context.getBean(FavoriteController.class);
		FestivalController festivalController = context.getBean(FestivalController.class);
		CompanyController companyController = context.getBean(CompanyController.class);

		LoginController loginController = context.getBean(LoginController.class);*/
		
		/*UserAddRequest görkem = new UserAddRequest("gorkem", "123", "gorkemyalcin123@gmail.com", UserType.ADMIN);
		UserAddRequest irem = new UserAddRequest("irem", "123", "iremkaya@gmail.com", UserType.USER);

		userController.addUser(görkem);
		userController.addUser(irem);*/
	}

}
