insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Cerveza Steinburg', 0.3, 25,'Cerveza barata',1);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Buey de Kobe', 99.9, 6,'Ternera de vacuno Wagyu',2);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Hummus', 2.3, 37,'Crema de pure de garbanzos',3);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Mayonesa', 1.5, 28,'Salsa emulsionada fría ',4);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Tinto de verano', 1.6, 14,'Vino tinto y limonada',1);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Barbacoa', 1.6, 23,'Para acompañar carnes ',4);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Solomillo de cerdo', 19.95, 9,'Carne de cerdo',2);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Pure de patata', 3.5, 26,'Papas cocidas y molidas',3);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Merluza gallega de las Rías', 10.9, 4,'Pescado gallego',5);
insert into Producto(nombreProducto, precio, stock, descripcion, idCategoria) values ('Atún gallego', 8.9, 6,'Atun gallego de la mejor calidad',5);

insert into Categoria(nombre, descripcion) values ('Bebidas','Liquido que se ingiere');
insert into Categoria(nombre, descripcion) values ('Carnes','Tejido animal, principalmente muscular, que se consume como alimento');
insert into Categoria(nombre, descripcion) values ('Pures','El pure es una técnica culinaria que consiste en machacar o triturar un alimento cocido');
insert into Categoria(nombre, descripcion) values ('Salsas','Aderezo liquido o pastoso utilizado en algunos alimentos');
insert into Categoria(nombre, descripcion) values ('Pescados','Pez comestible una vez sacado del agua donde vive por cualquiera de los procedimientos de pesca y destinado al consumo alimenticio');

insert into Cliente (nombre, apellidos, fechaNacimniento, email, banco, titular, codigoSeguridad, numTarjeta, direccionEnvio, direccionFacturacion, nombreUsuario, password) values ('cliente','cliente','2000-11-24','cliente@gmail.com','cliente','cliente',222,2222222222222222,'cliente','cliente','cliente', '$2a$10$x81Gc/y2Abgous23qb/6DOAWHm9UlzZl35wRo4emOUAIglRce/Pzu');
insert into Cliente (nombre, apellidos, fechaNacimniento, email, banco, titular, codigoSeguridad, numTarjeta, direccionEnvio, direccionFacturacion, nombreUsuario, password) values ('administrador','administrador','2000-11-24','administrador@gmail.com','administrador','administrador',111,1111111111111111,'administrador','administrador','administrador', '$2a$10$x81Gc/y2Abgous23qb/6DOAWHm9UlzZl35wRo4emOUAIglRce/Pzu');

insert into Rol (idRol, nombreRol) values (1, 'ROL_USER');
insert into Rol (idRol, nombreRol) values (2, 'ROL_ADMIN');

insert into Cliente_rol (idRol, idCliente) values (1,1);
insert into Cliente_rol (idRol, idCliente) values (2,2);