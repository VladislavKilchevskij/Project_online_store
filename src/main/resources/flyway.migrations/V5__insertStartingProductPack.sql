USE techStoreDB;

INSERT INTO techStoreDB.Product (productName, category_id, manufacturer_id, modelName, interfaceName,
                                 frequency, memorySize, memoryType, memorySlotNumber, coreNumber, maxThreadNumber,
                                 timingNymber,
                                 formFactor, versionPciExpress, price)
VALUES ('Видеокарта Gigabyte GeForce RTX 4090 Gaming', 1, 1, 'GeForce RTX 4090', 'PCI Express x8 4.0', 2230, 24,
        'GDDR6X', null, null, null, null, null, null, 7703.65),
       ('Видеокарта ASUS TUF Gaming GeForce RTX 3060 Ti V2 OC Edition', 1, 2, 'GeForce RTX 3060 Ti',
        'PCI Express x16 4.0', 1410, 8, 'GDDR6', null, null, null, null, null, null, 1799.00),
       ('Видеокарта MSI GeForce RTX 3050 Ventus 2X 8G OC', 1, 3, 'GeForce RTX 3050', 'PCI Express x8 4.0', 1550, 8,
        'GDDR6', null, null, null, null, null, null, 1007.54),
       ('Видеокарта Palit GeForce GTX 1050 Ti StormX', 1, 4, 'GeForce GTX 1050', 'PCI Express x16 3.0', 1392, 4,
        'GDDR5', null, null, null, null, null, null, 565.10),
       ('Процессор Intel Core i5-11400F', 2, 5, 'Core i5-11400F', null, 2600, null, null, null, 6, 12, null, null, null,
        498.35),
       ('Процессор AMD Ryzen 5 5600X', 2, 6, 'Ryzen 5 5600X', null, 3700, null, null, null, 6, 12, null, null, null,
        582.69),
       ('Процессор Intel Core i3-10100F', 2, 5, 'Core i3-10100F', null, 3600, null, null, null, 4, 8, null, null, null,
        233.45),
       ('Процессор AMD Ryzen 7 5800X', 2, 6, 'Ryzen 7 5800X', null, 3800, null, null, null, 8, 16, null, null, null,
        867.94),
       ('Оперативная память Kingston FURY Beast 8GB', 3, 7, 'FURY Beast', null, null, 8, 'DDR4 DIMM', null, null, null,
        '16-18-18', null, null, 96.30),
       ('Оперативная память Crucial Ballistix 12GB', 3, 8, 'Ballistix', null, null, 12, 'DDR3', null, null, null,
        '14-16-16', null, null, 150.72),
       ('Оперативная память Team T-Force Vulcan Z 16GB', 3, 9, 'Vulcan Z', null, null, 16, 'DDR4', null, null, null,
        '14-16-16', null, null, 206.32),
       ('Оперативная память GeIL EVO Potenza 8GB', 3, 10, 'Potenza', null, null, 8, 'DDR5', null, null, null,
        '18-20-20', null, null, 345.65),
       ('Материнская плата Gigabyte B560M DS3H V2', 4, 1, 'B560M', null, null, null, null, 4, null, null, null, 'mATX',
        5, 310.78),
       ('Материнская плата MSI B450-A Pro Max', 4, 3, 'B450-A Pro Max', null, null, null, null, 8, null, null, null,
        'ATX', 6, 290.56),
       ('Материнская плата ASRock B660M Pro RS', 4, 11, 'B660M Pro', null, null, null, null, 6, null, null, null,
        'mATX', 6, 456.84),
       ('Материнская плата ASUS TUF Gaming B660M-Plus', 4, 2, 'B660M-Plus', null, null, null, null, 8, null, null, null,
        'ATX', 4, 617.75);