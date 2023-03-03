create table users
(
    id          bigserial primary key,
    username    varchar(255) not null,
    password    varchar(80) not null,
    first_name  varchar(36),
    is_active   boolean,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table roles
(
    id          bigserial primary key,
    name        varchar(50) not null
);

create table users_roles
(
    user_id     bigint not null references users(id),
    role_id     bigint not null references roles(id),
    primary key (user_id, role_id),
    created_at  timestamp default current_timestamp
);

insert into users (username, password, first_name, is_active) values
                             ('n.v.bekhter@mail.ru', '$2a$12$8jJ2aWY1jYu2fUTib.Ovuu7uiiodaPzHHExOSP9Ykm.lafgse9gim', 'Kolya', '1');

insert into roles (name) values
                             ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPERADMIN');

insert into users_roles (user_id, role_id) values
                             (1, 1),
                             (1, 2),
                             (1, 3);