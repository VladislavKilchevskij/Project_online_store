DROP TABLE IF EXISTS techStoreDB.Role;
DROP TABLE IF EXISTS techStoreDB.User;
DROP TABLE IF EXISTS techStoreDB.BlockHistory;
DROP TABLE IF EXISTS techStoreDB.Address;
DROP TABLE IF EXISTS techStoreDB.Category;
DROP TABLE IF EXISTS techStoreDB.Manufacturer;
DROP TABLE IF EXISTS techStoreDB.Product;
DROP TABLE IF EXISTS techStoreDB.Media;
DROP TABLE IF EXISTS techStoreDB.Order;
DROP TABLE IF EXISTS techStoreDB.Cart;

-- Роль пользователя
CREATE TABLE IF NOT EXISTS techStoreDB.Role
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(16) NOT NULL
);

-- Сведения о пользователях
CREATE TABLE IF NOT EXISTS techStoreDB.User
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    userName    VARCHAR(16)  NOT NULL,
    password    VARCHAR(32)  NOT NULL,
    name        VARCHAR(45)  NOT NULL,
    surname     VARCHAR(45)  NOT NULL,
    email       VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(45)  NOT NULL,
    role_id     INT          NOT NULL,
    isBlocked   BOOLEAN      NOT NULL DEFAULT FALSE,
    isDeleted   BOOLEAN      NOT NULL DEFAULT FALSE,
    createTime  TIMESTAMP    NOT NULL,
    CONSTRAINT fk_Users_Has_Role FOREIGN KEY (role_id) REFERENCES techStoreDB.Role (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- История блокировок пользователей
CREATE TABLE IF NOT EXISTS techStoreDB.BlockHistory
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    administrator_id INT                          NOT NULL,
    customer_id      INT                          NOT NULL,
    blockStatus      ENUM ('blocked', 'unlocked') NOT NULL DEFAULT 'blocked',
    blockDate        TIMESTAMP                    NOT NULL,
    CONSTRAINT fk_Administrator_From_User FOREIGN KEY (administrator_id) REFERENCES techStoreDB.User (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_Customer_From_User FOREIGN KEY (customer_id) REFERENCES techStoreDB.User (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Адрес(а) пользователя
CREATE TABLE IF NOT EXISTS techStoreDB.Address
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    country  VARCHAR(20) NOT NULL,
    region   VARCHAR(45),
    district VARCHAR(45),
    city     VARCHAR(45) NOT NULL,
    street   VARCHAR(45) NOT NULL,
    house    INT         NOT NULL,
    building VARCHAR(1),
    flat     INT,
    postCode INT         NOT NULL,
    user_id  INT         NOT NULL,
    CONSTRAINT fk_Adress_User FOREIGN KEY (user_id) REFERENCES techStoreDB.User (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Категория товара
CREATE TABLE IF NOT EXISTS techStoreDB.Category
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(15) NOT NULL
);

-- Производитель (марка) товара
CREATE TABLE IF NOT EXISTS techStoreDB.Manufacturer
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    manufacturerName VARCHAR(20) NOT NULL
);

-- Сведения о товарах (каталог)
CREATE TABLE IF NOT EXISTS techStoreDB.Product
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    productName       VARCHAR(100)   NOT NULL,
    category_id       INT            NOT NULL,
    manufacturer_id   INT            NOT NULL,
    modelName         VARCHAR(100)   NOT NULL,
    interfaceName     VARCHAR(64), -- GPU 			option
    frequency         INT,         -- GPU, CPU 	    option
    memorySize        INT,         -- GPU, RAM 	    option
    memoryType        VARCHAR(32), -- GPU, RAM 	    option
    memorySlotNumber  INT,         -- Motherboard	option
    coreNumber        INT,         -- CPU			option
    maxThreadNumber   INT,         -- CPU			option
    timingNymber      VARCHAR(32), -- RAM			option
    formFactor        VARCHAR(16), -- Motherboard	option
    versionPciExpress INT,         -- Motherboard	option
    price             DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_Product_Category FOREIGN KEY (category_id) REFERENCES techStoreDB.Category (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_Product_Brand FOREIGN KEY (manufacturer_id) REFERENCES techStoreDB.Manufacturer (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Изображения/ссылки на видеообзор товара
CREATE TABLE IF NOT EXISTS techStoreDB.Media
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    mdeiaType  ENUM ('img', 'video'),
    mediaName  VARCHAR(128) NOT NULL,
    mediaUrl   VARCHAR(256) NOT NULL,
    product_id INT          NOT NULL,
    CONSTRAINT fk_Media_Product FOREIGN KEY (product_id) REFERENCES techStoreDB.Product (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Сведения о заказе(ах) пользователя
CREATE TABLE IF NOT EXISTS techStoreDB.Order_details
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    orderStatus  ENUM ('IN_CART', 'DELIVERING', 'RECEIVED', 'PAID', 'PAYMENT_OVERDUE') NOT NULL DEFAULT 'IN_CART',
    totalPrice   DECIMAL(10, 2),
    orderDate    DATE,
    deliveryDate DATE,
    user_id      INT                                                                   NOT NULL,
    CONSTRAINT fk_Order_User FOREIGN KEY (user_id) REFERENCES techStoreDB.User (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Корзина, в т.ч. история покупок
CREATE TABLE IF NOT EXISTS techStoreDB.Cart
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    order_id   INT            NOT NULL,
    product_id INT            NOT NULL,
    amount     INT            NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_ProductInсCart_Order FOREIGN KEY (order_id) REFERENCES techStoreDB.Order (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_ProductInCart_Product FOREIGN KEY (product_id) REFERENCES techStoreDB.Product (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);