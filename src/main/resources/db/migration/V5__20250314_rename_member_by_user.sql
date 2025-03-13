-- Rename the table 'member' to 'user'
ALTER TABLE member RENAME TO "user";

-- Rename the sequence for the 'user' table if necessary
ALTER SEQUENCE member_seq RENAME TO user_seq;

-- Rename the foreign key constraints and indexes (if any)

-- If there are any foreign key constraints referencing 'member', update them (example for borrowing_history table)
-- Assuming foreign key constraint on 'member_id' in 'borrowing_history' table
-- ALTER TABLE borrowing_history DROP CONSTRAINT fk_borrowing_member;
-- ALTER TABLE borrowing_history ADD CONSTRAINT fk_borrowing_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE;

-- If necessary, rename indexes related to 'member'
-- DROP INDEX IF EXISTS idx_member_id;
-- CREATE INDEX idx_user_id ON borrowing_history(user_id);

-- You may also need to check for other references to 'member' and update them accordingly


-- Rename foreign key constraint for member_id in borrowing_history table
ALTER TABLE borrowing_history DROP CONSTRAINT fk_borrowing_member;

-- Rename the column `member_id` to `user_id` in borrowing_history
ALTER TABLE borrowing_history RENAME COLUMN member_id TO user_id;

-- Re-create the foreign key constraint for user_id
ALTER TABLE borrowing_history ADD CONSTRAINT fk_borrowing_user FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE;

-- Optional: If needed, you can also update the index name related to the foreign key
DROP INDEX IF EXISTS idx_borrowing_member;
CREATE INDEX idx_borrowing_user ON borrowing_history(user_id);
