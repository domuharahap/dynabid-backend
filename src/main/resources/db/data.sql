-- Sample Bidding Series (Active)
INSERT INTO bidding_series (series_name, start_date, end_date, status) VALUES
('FF', '2025-08-01 00:00:00', '2025-09-01 00:00:00', 'ACTIVE'),
('MM', '2025-08-01 00:00:00', '2025-09-05 00:00:00', 'ACTIVE'),
('NN', '2025-07-01 00:00:00', '2025-07-30 00:00:00', 'CLOSED');

-- Sample User for testing
INSERT INTO app_user (username, password) VALUES('testuser', 'testpwd');