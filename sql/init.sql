CREATE DATABASE IF NOT EXISTS consumption_mgmt DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE consumption_mgmt;

DROP TABLE IF EXISTS consumptions;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS coaches;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS employees;
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

CREATE TABLE employees (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  phone VARCHAR(20),
  role VARCHAR(32) NOT NULL,
  hire_date DATE NOT NULL,
  status VARCHAR(16) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE members (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(128),
  level VARCHAR(32) NOT NULL,
  status VARCHAR(16) NOT NULL,
  join_date DATE NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE coaches (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  phone VARCHAR(20),
  specialty VARCHAR(64),
  status VARCHAR(16) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  coach_id BIGINT NOT NULL,
  capacity INT NOT NULL,
  start_at DATETIME NOT NULL,
  end_at DATETIME NOT NULL,
  status VARCHAR(16) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  INDEX idx_course_coach (coach_id),
  CONSTRAINT fk_course_coach FOREIGN KEY (coach_id) REFERENCES coaches(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE equipment (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  category VARCHAR(64) NOT NULL,
  location VARCHAR(64),
  purchase_date DATE NOT NULL,
  status VARCHAR(16) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE consumptions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  member_id BIGINT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  category VARCHAR(32) NOT NULL,
  remark VARCHAR(255),
  paid_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  INDEX idx_member_paid_at (member_id, paid_at),
  CONSTRAINT fk_consumption_member FOREIGN KEY (member_id) REFERENCES members(id)
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

INSERT INTO employees (name, phone, role, hire_date, status, created_at, updated_at) VALUES
('赵经理', '13900000001', 'Manager', '2022-03-01', 'ACTIVE', NOW(), NOW()),
('孙助理', '13900000002', 'Reception', '2023-06-15', 'ACTIVE', NOW(), NOW());

INSERT INTO members (name, phone, email, level, status, join_date, created_at, updated_at) VALUES
('刘一', '13700000001', 'liu1@example.com', 'Gold', 'ACTIVE', '2024-05-01', NOW(), NOW()),
('陈二', '13700000002', 'chen2@example.com', 'Silver', 'ACTIVE', '2024-06-18', NOW(), NOW());

INSERT INTO coaches (name, phone, specialty, status, created_at, updated_at) VALUES
('王教练', '13600000001', '力量训练', 'ACTIVE', NOW(), NOW()),
('李教练', '13600000002', '瑜伽', 'ACTIVE', NOW(), NOW());

INSERT INTO courses (name, coach_id, capacity, start_at, end_at, status, created_at, updated_at) VALUES
('力量燃脂课', 1, 20, '2025-04-01 19:00:00', '2025-04-01 20:00:00', 'OPEN', NOW(), NOW()),
('晨间瑜伽', 2, 15, '2025-04-02 07:30:00', '2025-04-02 08:30:00', 'OPEN', NOW(), NOW());

INSERT INTO equipment (name, category, location, purchase_date, status, created_at, updated_at) VALUES
('跑步机A1', '有氧', '一楼', '2023-02-20', 'IN_SERVICE', NOW(), NOW()),
('哑铃套装', '力量', '二楼', '2022-11-05', 'IN_SERVICE', NOW(), NOW());

INSERT INTO consumptions (member_id, amount, category, remark, paid_at, created_at, updated_at) VALUES
(1, 399.00, '私教课', '三次课程', '2025-03-05 14:00:00', NOW(), NOW()),
(2, 59.00, '饮品', '运动饮料', '2025-03-06 16:30:00', NOW(), NOW());
