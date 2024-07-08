<img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/bingrLogo.png" width="400"/>

[![forthebadge](https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/madeWithJavaBadge.svg)](https://forthebadge.com)  [![forthebadge](https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/builtWithLoveBadge.svg)](https://forthebadge.com)

---

## About

**Bingr** is a subscription video on-demand service, written in Java using Spring Boot. The app offers a full movie management functionality for the site admin, an interface that allows users to purchase a monthly subscription and gain access to the hidden content, as well as the ability to save favourite movies to a private watchlist. The site is also fully responsive and features a modern design.

<br>

## Features

Some of the main Bingr features
* an admin panel which allows for creating, modifying and deleting movie entries
* secure login and registration
* permissions for different types of users
* a homepage where users can browse through available movies
* ability to save movies to a watchlist
* content hiding functionality for non-subscribed users
* payment management window, that can be easily integrated with an online payment service
* an interface which automatically handles the monthly subscription status
* modern and responsive web design

<br>

## Technologies

The project uses
* **Java 17** - the application's core logic, general functionality
* **Spring Boot** - framework for deploying the app with Apache Tomcat server
* **Spring Security** - security framework for handling authentication and authorization
* **MySQL** - database for storing user information, movies, subscriptions, and other relevant data
* **Lombok** - library to reduce boilerplate code
* **Hibernate** - framework used to initialize the database and map objects to tables
* **HTML, CSS, Bootstrap** - for creating a responsive and modern web design
* **JavaScript** - for adding interactive frontend elements
* **Maven** - a default build automation tool

<br>

## Launch

* you need **Java 17** and **MySQL 8.0.11**, with a MySQL server for the application to work properly
* please ensure that your MySQL server is running before starting the application
* you will need to initialize the admin role manually
* the app will automatically create the required tables in the database if they do not exist
* after a successful initialization, you can access Bingr at **localhost:8080**

<br>

## Preview

<p float="left">
  <kbd> <img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/preview1.png" width="500"/> </kbd>
</p>

<p float="left">
  <kbd> <img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/preview2.png" width="500"/> </kbd>
</p>

<p float="left">
  <kbd> <img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/preview3.png" width="500"/> </kbd>
</p>

<p float="left">
  <kbd> <img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/preview4.png" width="500"/> </kbd>
</p>

<p float="left">
  <kbd> <img src="https://raw.githubusercontent.com/kubixDev/Bingr/master/readmeImages/preview5.png" width="500"/> </kbd>
</p>

<br>

## Notice

All rights reserved. The Bingr app, its code and associated files in this repository are protected by copyright law. No part of this application may be reproduced, distributed, or transmitted in any form or by any means, without the prior written permission of the repository owner.

The movies shown in the preview are provided for illustrative purposes only. Bingr does not host any content, it only provides links to the movie trailers.

The "MADE TOMMY" font, provided by Madetype, is used in this project under a free license that permits both personal and non-commercial use.