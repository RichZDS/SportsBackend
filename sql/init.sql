CREATE DATABASE IF NOT EXISTS consumption_mgmt DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE consumption_mgmt;

DROP TABLE IF EXISTS consume_records;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(128),
  status VARCHAR(16) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE consume_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  category VARCHAR(32) NOT NULL,
  remark VARCHAR(255),
  paid_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  INDEX idx_customer_paid_at (customer_id, paid_at),
  CONSTRAINT fk_consume_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO customers (name, phone, email, status, created_at, updated_at) VALUES
('张三', '13800000001', 'zhangsan@example.com', 'ACTIVE', NOW(), NOW()),
('李四', '13800000002', 'lisi@example.com', 'ACTIVE', NOW(), NOW()),
('王五', '13800000003', 'wangwu@example.com', 'INACTIVE', NOW(), NOW());

INSERT INTO consume_records (customer_id, amount, category, remark, paid_at, created_at, updated_at) VALUES
(1, 199.00, '餐饮', '午餐', '2025-01-08 12:30:00', NOW(), NOW()),
(1, 58.50, '交通', '地铁', '2025-01-15 08:10:00', NOW(), NOW()),
(1, 899.00, '购物', '外套', '2025-02-03 16:45:00', NOW(), NOW()),
(1, 35.00, '餐饮', '早餐', '2025-02-10 09:00:00', NOW(), NOW()),
(1, 120.00, '娱乐', '电影', '2025-03-02 20:00:00', NOW(), NOW()),
(2, 20.00, '餐饮', '咖啡', '2025-01-09 10:00:00', NOW(), NOW()),
(2, 320.00, '购物', '运动鞋', '2025-01-20 14:00:00', NOW(), NOW()),
(2, 66.00, '交通', '打车', '2025-02-11 22:00:00', NOW(), NOW()),
(2, 45.00, '餐饮', '晚餐', '2025-03-05 19:00:00', NOW(), NOW()),
(3, 150.00, '餐饮', '聚餐', '2025-01-05 18:00:00', NOW(), NOW()),
(3, 500.00, '购物', '家电配件', '2025-02-18 11:00:00', NOW(), NOW()),
(3, 25.00, '交通', '公交', '2025-03-10 08:30:00', NOW(), NOW());
