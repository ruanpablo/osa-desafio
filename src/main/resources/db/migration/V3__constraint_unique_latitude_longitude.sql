ALTER TABLE tbl_agencies
ADD CONSTRAINT uk_lat_long UNIQUE (latitude, longitude);
