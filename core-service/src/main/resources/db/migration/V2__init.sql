create table categories
(
    id          bigserial primary key,
    title       varchar(255) unique,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into categories (title) values ('Фрукты'), ('Овощи'), ('Ягоды');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigint references categories (id),
    cost        numeric(8, 2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, cost, category_id) values
('Яблоко', 80.57, 1), ('Абрикос', 180.24, 1), ('Лимон', 250.78, 1),
('Картофель', 55.07, 2), ('Лук', 38.24, 2), ('Морковь', 31.58, 2),
('Клубника', 458.34, 3), ('Малина', 534.24, 3), ('Красная смородина', 279.45, 3),
('Черная смородина', 290.00, 3);

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_cost  numeric(8, 2) not null ,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id          bigserial primary key,
    product_id  bigint not null references products(id),
    order_id    bigint not null references orders(id),
    quantity    int not null ,
    cost_per_product numeric(8, 2) not null ,
    cost        numeric(8, 2) not null ,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);