insert into rol(id_rol, rol) values (1, 'ADMINISTRADOR');
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (1, '00000001R', 'nombre-1','apellido1-1', 'apellido2-1', null, 1, CURRENT_DATE);
insert into rol(id_rol, rol) values (2, 'USUARIO');
insert into usuario(id_usuario, nif, nombre, apellido1, apellido2, email, id_rol, fecha_alta) values (2, '00000009D', 'nombre-2','apellido1-2', 'apellido2-2', null, 2, CURRENT_DATE);

insert into tipo_entidad(id_tipo_entidad, nombre) values (1, 'Usuario');

commit;