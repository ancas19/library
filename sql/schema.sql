-- Table: images
CREATE TABLE images (
  id SERIAL PRIMARY KEY,
  file_name VARCHAR(255),
  file_path VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Table: people
CREATE TABLE people (
  id SERIAL PRIMARY KEY,
  dni VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(20),
  profile_image INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint fk_people_images foreign key (profile_image) references images(id)
);

-- Add indexes
CREATE INDEX idx_people_email ON people(email);
create index idx_people_dni on people(dni);


-- Table: roles
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  role_name VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create index idx_roles_role_name on roles(role_name);



-- Table: membership
CREATE TABLE memberships (
  id SERIAL PRIMARY KEY,
  membership_type VARCHAR(255),
  loan_limit INT,
  loan_period_days INT,
  grace_period_days INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add indexes
CREATE INDEX idx_membership_membership_type ON memberships(membership_type);


-- Table: users
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  person_id INT not null,
  username VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  role_id INT not null,
  membership_id INT not null,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint pk_user_people foreign key (person_id) references people(id),
  constraint pk_user_roles foreign key (role_id) references roles(id),
  constraint pk_user_memberships foreign key (membership_id) references memberships(id)
);

-- Add indexes
CREATE INDEX idx_users_person_id ON users(person_id);
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_role_id ON users(role_id);
CREATE INDEX idx_users_membership_id ON users(membership_id);
CREATE INDEX idx_users_created_at ON users(created_at);





-- Table: authors
CREATE TABLE authors (
  id SERIAL PRIMARY KEY,
  full_name VARCHAR(255),
  nationality VARCHAR(255),
  birthdate DATE,
  bio TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add indexes
CREATE INDEX idx_authors_nationality ON authors(nationality);
create  index idz_authors_full_name on authors(full_name);

-- Table: books
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  isbn VARCHAR(255) UNIQUE,
  author_id INT not null,
  publish_date TIMESTAMP,
  genre VARCHAR(255),
  available_copies INT,
  image_id INT not null,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint fk_books_authors foreign key (author_id) references authors(id),
  constraint fk_books_images foreign key (image_id) references images(id)
);

-- Add indexes
CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_books_author_id ON books(author_id);
CREATE INDEX idx_books_image_id ON books(image_id);
CREATE INDEX idx_books_created_at ON books(created_at);

-- Table: loans
CREATE TABLE loans (
  id SERIAL PRIMARY KEY,
  user_id INT not null,
  book_id INT not null,
  loan_date TIMESTAMP,
  due_date TIMESTAMP,
  return_date TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_created VARCHAR(255),
  updated_user VARCHAR(255),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  constraint pk_loans_users foreign key (user_id) references users(id),
  constraint pk_lonas_books foreign key (book_id) references books(id)
);

-- Add indexes
CREATE INDEX idx_loans_user_id ON loans(user_id);
CREATE INDEX idx_loans_book_id ON loans(book_id);
CREATE INDEX idx_loans_created_at ON loans(created_at);
