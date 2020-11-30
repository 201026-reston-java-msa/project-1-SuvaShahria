INSERT INTO ers_user_roles 
	VALUES (1, 'employee'),
	(2, 'finance');

INSERT INTO ers_reimbursement_status 
	VALUES (1, 'PENDING'),
	(2, 'APPROVED'),
	(3, 'DENIED');
	
INSERT INTO ers_reimbursement_type 
	VALUES (1, 'Food'),
	(2, 'Lodging'),
	(3, 'Travel'),
	(4, 'Other');
	

	
CREATE OR REPLACE FUNCTION trigger_set_time1() RETURNS TRIGGER 
AS $$
BEGIN 
	NEW.reimb_submitted = NOW();
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION trigger_set_time2() RETURNS TRIGGER 
AS $$
BEGIN 
	NEW.reimb_resolved = NOW();
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_time1 BEFORE INSERT ON ers_reimbursement FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time1();

CREATE TRIGGER set_time2 BEFORE UPDATE ON ers_reimbursement FOR EACH ROW 
EXECUTE PROCEDURE trigger_set_time2();