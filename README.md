# Shop2
This is my very first experience in Java web programming.
This is simple e-commerce application with ability to create and manage own account, choose and add goods to shopping cart and proceed with order.
Users can also manage their previous orders.
User with administration role can manage goods and catalogs. Also it is possible to see client orders statistic and operate orders status.


# Technologies stack!

- Spring as a base framework (core, security, mvc)
- Mysql DB
- JPA (Hibernate)
- JSP
- JQUERY for front-end
- Maven (Build and dependency manager)

# Notes

Here we have classic Maven multi-module project with 3 modules (DAO, Services,Web)

Shopping cart items stored in client http session.

There are english and russian language version, that done by Spring message bundles.

Application testet on Tomcat 7

There is also additional application for orders statistic review and pdf generation - [ https://github.com/itgabrut/StatisticForShop2 ] - connected to this application through rest web services.

