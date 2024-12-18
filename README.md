# Coupon Service

Este microservicio contiene dos operaciones que permiten maximizar el uso de un cupón de descuento y obtener el top 5 de productos que se han canjeado a partir de la operación mencionada anteriormente.

## Uso y documentación

• Documentación swagger: 
https://coupon-service.azurewebsites.net/promotions/v1/swagger-ui/index.html#/  

• Ver [Collection](./tests/coupon-service-postmancollection.json).  

Se puede probar el microservicio directamente desde el swagger. Existe una instancia levantada en AWS de la base de datos y en Azure está desplegado el microservicio.

Se puede probar mediante postman (ver collection)

Se puede probar levantando el código, usando el JDK 17 y Maven en su versión 3.9.1.

## Servicio

**Título del Producto:** Coupon Service  
**Version:** 1.0.0     
**BasePath:** /promotions/v1  

## Métodos expuestos

### POST /coupon  

Esta operación permite maximizar el uso de un cupón de descuento, permitiendo a un cliente utilizar el mayor monto posible aplicado a ítems que contenga en favoritos.

### GET /most-redeemed-products

Esta operación permite obtener los productos más canjeados a partir del /coupon mencionado anteriormente. Actualmente retorna el top 5.

## Tecnologias utilizadas:

Java 17

SpringBoot

ORM: JPA+Hibernate

Clouds: Azure (microservicio desplegado mediante github) y AWS (BBDDRR).

MySQL Workbench (levanté una instancia en AWS que me permite persistir lo pedido en nivel 2).

Swagger (OpenApi)

## Autor  
  Desarrollado por **Facundo Tomás Saracho**  
