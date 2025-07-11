USE library_db;

-- Roles
INSERT INTO roles (name) VALUES
('Administrator'),
('Librarian'),
('Staff');

-- System Users
INSERT INTO system_users (username, password_hash, full_name, role_id) VALUES
('admin', '$2a$10$hashedpassadmin', 'Admin User', 1),   -- Admin
('librarian1', '$2a$10$hashedpasslib', 'Librarian One', 2), -- Librarian
('staff1', '$2a$10$hashedpassstaff', 'Staff Member', 3); -- Staff

-- Publishers
INSERT INTO publishers (name, address, website) VALUES
('Oreilly Media', '1005 Gravenstein Highway North, Sebastopol, CA', 'https://oreilly.com'),
('Pearson', '221 River Street, Hoboken, NJ', 'https://www.pearson.com');

-- Categories
INSERT INTO categories (name, parent_id) VALUES
('Programming', NULL),
('Web Development', 1),
('Databases', 1),
('Fiction', NULL),
('Science Fiction', 4);

-- Authors
INSERT INTO authors (name, bio) VALUES
('Joshua Bloch', 'Author of Effective Java.'),
('Robert C. Martin', 'Uncle Bob, clean code expert.'),
('J.K. Rowling', 'Author of the Harry Potter series.');

-- Books
INSERT INTO books (title, language, publication_year, isbn, edition, summary, cover_image_url, category_id, publisher_id) VALUES
('Effective Java', 'English', 2018, '9780134685991', '3rd', 'Best practices for Java programming.', NULL, 1, 1),
('Clean Code', 'English', 2008, '9780132350884', '1st', 'A Handbook of Agile Software Craftsmanship.', NULL, 1, 2),
('Harry Potter and the Sorcerer\'s Stone', 'English', 1997, '9780747532699', '1st', 'Fantasy novel about a young wizard.', NULL, 5, 2);

-- Book Authors (Many-to-Many)
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1),  -- Effective Java by Joshua Bloch
(2, 2),  -- Clean Code by Robert C. Martin
(3, 3);  -- Harry Potter by J.K. Rowling

-- Members
INSERT INTO members (full_name, email, phone, address) VALUES
('Ali Hassan', 'ali@example.com', '01000000001', 'Cairo, Egypt'),
('Sara Magdy', 'sara@example.com', '01000000002', 'Alexandria, Egypt');

-- Borrow Transactions
INSERT INTO borrow_transactions (member_id, book_id, borrow_date, due_date, return_date, created_by) VALUES
(1, 1, '2025-07-01', '2025-07-10', NULL, 2),  -- Ali borrowed Effective Java
(2, 3, '2025-07-02', '2025-07-12', '2025-07-09', 3);  -- Sara borrowed & returned Harry Potter

-- User Logs
INSERT INTO user_logs (user_id, action) VALUES
(1, 'Created book: Effective Java'),
(2, 'Borrowed book: Effective Java by Ali'),
(3, 'Returned book: Harry Potter by Sara');
