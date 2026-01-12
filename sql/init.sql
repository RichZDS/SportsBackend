-- MySQL 初始化脚本
-- 使用方法：mysql -u root -p consumption_mgmt < init.sql

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS consumption_mgmt DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE consumption_mgmt;

-- ===================== 建表语句 =====================

-- 地点表（需要先创建，因为 staff 表依赖它）
CREATE TABLE IF NOT EXISTS locations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  address VARCHAR(500),
  phone VARCHAR(20),
  manager VARCHAR(100),
  status VARCHAR(32) DEFAULT 'ACTIVE',
  description VARCHAR(1000),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 客户表
CREATE TABLE IF NOT EXISTS customers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(255),
  status VARCHAR(32) DEFAULT 'ACTIVE',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 员工表
CREATE TABLE IF NOT EXISTS employees (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(255),
  position VARCHAR(100),
  status VARCHAR(32) DEFAULT 'ACTIVE',
  salary DECIMAL(10, 2),
  hired_at DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 教练表
CREATE TABLE IF NOT EXISTS trainers (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(255),
  specialization VARCHAR(100),
  experience_years INT DEFAULT 0,
  status VARCHAR(32) DEFAULT 'ACTIVE',
  hourly_rate DECIMAL(10, 2),
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 课程表
CREATE TABLE IF NOT EXISTS courses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  trainer_id BIGINT,
  description VARCHAR(500),
  duration_minutes INT,
  max_participants INT DEFAULT 20,
  price DECIMAL(10, 2),
  schedule_time DATETIME,
  status VARCHAR(32) DEFAULT 'ACTIVE',
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 器材表
CREATE TABLE IF NOT EXISTS equipment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  category VARCHAR(100),
  brand VARCHAR(100),
  model VARCHAR(100),
  purchase_date DATETIME,
  purchase_price DECIMAL(10, 2),
  status VARCHAR(32) DEFAULT 'AVAILABLE',
  location VARCHAR(100),
  maintenance_date DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 消费记录表
CREATE TABLE IF NOT EXISTS consume_records (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  amount DECIMAL(10, 2),
  category VARCHAR(100),
  remark VARCHAR(255),
  paid_at DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 店员表（依赖地点表）
CREATE TABLE IF NOT EXISTS staff (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(20),
  email VARCHAR(255),
  location_id BIGINT,
  position VARCHAR(100) NOT NULL,
  status VARCHAR(32) DEFAULT 'ACTIVE',
  salary DECIMAL(10, 2),
  hired_at DATETIME,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (location_id) REFERENCES locations(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ===================== 初始数据 =====================

-- 清空旧数据（可选，谨慎使用）
-- TRUNCATE TABLE staff;
-- TRUNCATE TABLE consume_records;
-- TRUNCATE TABLE equipment;
-- TRUNCATE TABLE courses;
-- TRUNCATE TABLE trainers;
-- TRUNCATE TABLE employees;
-- TRUNCATE TABLE customers;
-- TRUNCATE TABLE locations;

-- 初始化地点数据
INSERT IGNORE INTO locations (id, name, address, phone, manager, status, description) VALUES
(1, '总店', '北京市朝阳区体育路100号', '010-12345678', '张经理', 'ACTIVE', '总店位于市中心，设施齐全'),
(2, '分店一', '北京市海淀区学院路200号', '010-87654321', '李经理', 'ACTIVE', '分店一靠近大学城，学生客户较多'),
(3, '分店二', '北京市西城区金融街300号', '010-11223344', '王经理', 'ACTIVE', '分店二位于金融区，高端客户较多');

-- 初始化客户数据
INSERT IGNORE INTO customers (id, name, phone, email, status) VALUES
(1, '张三', '13800000001', 'zhangsan@example.com', 'ACTIVE'),
(2, '李四', '13800000002', 'lisi@example.com', 'ACTIVE'),
(3, '王五', '13800000003', 'wangwu@example.com', 'INACTIVE');

-- 初始化员工数据
INSERT IGNORE INTO employees (id, name, phone, email, position, status, salary, hired_at) VALUES
(1, '赵经理', '13900000001', 'zhao@example.com', '经理', 'ACTIVE', 15000.00, NOW()),
(2, '钱主管', '13900000002', 'qian@example.com', '主管', 'ACTIVE', 12000.00, NOW()),
(3, '孙前台', '13900000003', 'sun@example.com', '前台', 'ACTIVE', 6000.00, NOW());

-- 初始化教练数据
INSERT IGNORE INTO trainers (id, name, phone, email, specialization, experience_years, status, hourly_rate) VALUES
(1, '周教练', '13700000001', 'zhou@example.com', '力量训练', 5, 'ACTIVE', 200.00),
(2, '吴教练', '13700000002', 'wu@example.com', '瑜伽', 3, 'ACTIVE', 180.00),
(3, '郑教练', '13700000003', 'zheng@example.com', '有氧运动', 7, 'ACTIVE', 220.00);

-- 初始化课程数据
INSERT IGNORE INTO courses (id, name, trainer_id, description, duration_minutes, max_participants, price, schedule_time, status) VALUES
(1, '力量训练入门', 1, '适合初学者的力量训练课程', 60, 15, 150.00, NOW(), 'ACTIVE'),
(2, '瑜伽放松', 2, '缓解压力的瑜伽课程', 90, 20, 120.00, NOW(), 'ACTIVE'),
(3, '有氧减脂', 3, '高效有氧运动课程', 45, 25, 100.00, NOW(), 'ACTIVE');

-- 初始化器材数据
INSERT IGNORE INTO equipment (id, name, category, brand, model, purchase_date, purchase_price, status, location, maintenance_date) VALUES
(1, '跑步机', '有氧器械', '舒华', 'SH-T5100', NOW(), 8000.00, 'AVAILABLE', 'A区', NOW()),
(2, '哑铃架', '力量器械', '力健', 'LJ-200', NOW(), 5000.00, 'AVAILABLE', 'B区', NOW()),
(3, '瑜伽垫', '辅助器械', 'LULULEMON', 'MAT-001', NOW(), 200.00, 'AVAILABLE', '瑜伽室', NULL);

-- 初始化消费记录数据
INSERT IGNORE INTO consume_records (id, customer_id, amount, category, remark, paid_at) VALUES
(1, 1, 199.00, '餐饮', '午餐', NOW()),
(2, 1, 58.50, '交通', '地铁', NOW()),
(3, 1, 899.00, '购物', '外套', NOW()),
(4, 2, 20.00, '餐饮', '咖啡', NOW()),
(5, 2, 320.00, '购物', '运动鞋', NOW()),
(6, 3, 150.00, '餐饮', '聚餐', NOW());

-- 初始化店员数据
INSERT IGNORE INTO staff (id, name, phone, email, location_id, position, status, salary, hired_at) VALUES
(1, '小张', '13800001001', 'xiaozhang@example.com', 1, '前台', 'ACTIVE', 5000.00, NOW()),
(2, '小李', '13800001002', 'xiaoli@example.com', 1, '销售', 'ACTIVE', 6000.00, NOW()),
(3, '小王', '13800001003', 'xiaowang@example.com', 2, '前台', 'ACTIVE', 5000.00, NOW()),
(4, '小赵', '13800001004', 'xiaozhao@example.com', 2, '销售', 'ACTIVE', 6000.00, NOW()),
(5, '小钱', '13800001005', 'xiaoqian@example.com', 3, '前台', 'ACTIVE', 5500.00, NOW());

-- 完成
SELECT '数据库初始化完成！' as message;


