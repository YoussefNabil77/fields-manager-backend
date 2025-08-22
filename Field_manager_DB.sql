Drop DATABASE Field_manager;
USE Field_manager;

-- User table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'player', -- admin, player
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Team table
CREATE TABLE Team (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

-- TeamPlayers table
CREATE TABLE TeamPlayers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    team_id INT NOT NULL,
    is_leader TINYINT(1) DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (team_id) REFERENCES Team(id)
);

-- Field table
CREATE TABLE Field (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    images TEXT,
    players_capacity INT NOT NULL
);

-- Week_day table
CREATE TABLE Week_day (
    id INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

-- FieldSlots table
CREATE TABLE FieldSlots (
    id INT PRIMARY KEY AUTO_INCREMENT,
    field_id INT NOT NULL,
    week_day_id INT NOT NULL,
    from_time TIME NOT NULL,
    to_time TIME NOT NULL,
    price_per_hour DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (field_id) REFERENCES Field(id),
    FOREIGN KEY (week_day_id) REFERENCES Week_day(id)
);

-- Booking table
CREATE TABLE Booking (
    id INT PRIMARY KEY AUTO_INCREMENT,
    player_id INT NOT NULL,
    team_id INT NOT NULL,
    field_slot_id INT NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (player_id) REFERENCES User(id),
    FOREIGN KEY (team_id) REFERENCES Team(id),
    FOREIGN KEY (field_slot_id) REFERENCES FieldSlots(id)
);

-- BookingStatusChange table
CREATE TABLE BookingStatusChange (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES Booking(id)
);

-- Review table
CREATE TABLE Review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    FOREIGN KEY (booking_id) REFERENCES Booking(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Enquiry table
CREATE TABLE Enquiry (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Setting table
CREATE TABLE Setting (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    logo_url VARCHAR(255),
    about_image_url VARCHAR(255),
    about_description TEXT,
    terms_and_conditions TEXT,
    facebook_url VARCHAR(255),
    whatsapp_number VARCHAR(20),
    phone_number VARCHAR(20),
    second_phone_number VARCHAR(20)
);

-- Pre-populate Week_day table
INSERT INTO Week_day (id, name) VALUES
(1, 'saturday'),
(2, 'sunday'),
(3, 'monday'),
(4, 'tuesday'),
(5, 'wednesday'),
(6, 'thursday'),
(7, 'friday');
