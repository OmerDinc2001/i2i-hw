DECLARE
	str VARCHAR2(30) := 'defgddgfed';
	str_list VARCHAR2(30);
	used_str_list VARCHAR2(30);
	str_len NUMBER;
	no_of_indents NUMBER := 0;
	next_char VARCHAR2(1);
BEGIN
	DBMS_OUTPUT.PUT_LINE('String: ' || str);
	str_len := LENGTH(str);
	FOR i IN 1..str_len LOOP
		next_char := SUBSTR(str, i, 1);
		IF next_char NOT BETWEEN 'a' AND 'z' THEN
 		 DBMS_OUTPUT.PUT_LINE('error: expecting only lowercase alphabetic characters');
  		EXIT;
	END IF;
		IF (INSTR(str_list, next_char) > 0) THEN
			FOR i IN 1..no_of_indents LOOP
				DBMS_OUTPUT.PUT(' ');
			END LOOP;
			DBMS_OUTPUT.PUT_LINE(next_char);
			IF (INSTR(used_str_list, next_char) > 0) THEN
				DBMS_OUTPUT.PUT_LINE('error: duplicate character');
				EXIT;
			END IF;
			used_str_list := used_str_list || next_char;
			str_list := SUBSTR(str_list, 1, LENGTH(str_list) - 1);
			no_of_indents := no_of_indents - 1;
		ELSE
			str_list := str_list || next_char;
			no_of_indents := no_of_indents + 1;
			FOR i IN 1..no_of_indents LOOP
				DBMS_OUTPUT.PUT(' ');
			END LOOP;
			DBMS_OUTPUT.PUT_LINE(next_char);
		END IF;
	END LOOP;
END;
