-- Ensure correct column types and sizes in MySQL

CREATE TABLE IF NOT EXISTS courses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  trainer_id BIGINT,
  description VARCHAR(500),
  duration_minutes INT,
  max_participants INT,
  price DECIMAL(10,2),
  schedule_time DATETIME,
  status VARCHAR(32),
  created_at DATETIME,
  updated_at DATETIME
);

ALTER TABLE courses MODIFY COLUMN price DECIMAL(10,2);

CREATE TABLE IF NOT EXISTS consume_records (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id BIGINT NOT NULL,
  amount DECIMAL(10,2),
  category VARCHAR(100),
  remark VARCHAR(255),
  paid_at DATETIME,
  created_at DATETIME,
  updated_at DATETIME
);

-- You may add more ALTER TABLE statements here if existing schema differs

